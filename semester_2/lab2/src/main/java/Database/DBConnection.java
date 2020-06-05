package Database;

import org.postgresql.copy.CopyOut;
import tableElements.*;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;


public class DBConnection {

    private static Connection currentConnection = null;
    private static String currentUserName = null;
    private static Random random = new Random();

    public static boolean createConnection(String login, String password, String role) {
        try {
            Class.forName("org.postgresql.Driver");
            currentConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/judge_system",
                    login, password);
            currentUserName = login;
            currentConnection.setAutoCommit(true);
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT r.rolname as username,r1.rolname as \"role\"\n" +
                        "FROM pg_catalog.pg_roles r JOIN pg_catalog.pg_auth_members m\n" +
                        "ON (m.member = r.oid)\n" +
                        "JOIN pg_roles r1 ON (m.roleid=r1.oid)\n" +
                        "WHERE r.rolcanlogin AND r1.rolname = '" + role + "' AND r.rolname = '" + login + "'\n" +
                        "ORDER BY 1;\n";
            ResultSet loginSet = statement.executeQuery(sql);
            if (loginSet.next())
                return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        currentUserName = null;
        currentConnection = null;
        return false;
    }

    public static boolean exists() {
        if (currentConnection != null)
            return true;
        else
            return false;
    }

    public static void addCase(Case newCase) {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT x.court_id " +
                    "FROM civil_servant x " +
                    "WHERE x.login = '" + currentUserName + "';";
            ResultSet curSet = statement.executeQuery(sql);
            curSet.next();
            Integer courtId = curSet.getInt("court_id");
            newCase.setCourtId(courtId);

            statement = currentConnection.createStatement();
            sql = "SELECT x.id " +
                    "FROM court y INNER JOIN judge x ON x.court_id = y.court_id " +
                    "WHERE y.court_id = " + courtId.toString() + ";";
            curSet = statement.executeQuery(sql);
            Vector<Integer> list = new Vector<Integer>();
            while (curSet.next()) {
                list.add(curSet.getInt("id"));
            }
            int n = random.nextInt(list.size());
            Integer judgeId = list.elementAt(n);
            newCase.setJudgeId(judgeId);

            statement = currentConnection.createStatement();
            sql = newCase.getRegSql();
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Case> getCases() {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT x.overall_id, x.year_id, x.plaintiff, x.defendant, x.text_of_case, x.court_id, x.judge_id, x.is_correct, x.date_of_trial, x.judgment, x.closed \n" +
                    "FROM cases x INNER JOIN judge y ON x.judge_id = y.id\n" +
                    "WHERE y.login = '" + currentUserName + "';";
            ResultSet curSet = statement.executeQuery(sql);
            List<Case> cases = new LinkedList<>();
            while (curSet.next()) {
                Case newCase = new Case(curSet.getInt("overall_id"), curSet.getString("year_id"),
                        curSet.getString("plaintiff"), curSet.getString("defendant"),
                        curSet.getString("text_of_case"), curSet.getInt("court_id"),
                        curSet.getInt("judge_id"), curSet.getInt("is_correct"),
                        curSet.getString("date_of_trial"), curSet.getString("judgment"),
                        curSet.getBoolean("closed"));
                cases.add(newCase);
            }
            return cases;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Case getCase(Integer id) {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT x.overall_id, x.year_id, x.plaintiff, x.defendant, x.text_of_case, x.court_id, x.judge_id, x.is_correct, x.date_of_trial, x.judgment, x.closed \n" +
                    "FROM cases x\n" +
                    "WHERE x.overall_id = '" + id.toString() + "';";
            ResultSet curSet = statement.executeQuery(sql);
            curSet.next();
            Case newCase = new Case(curSet.getInt("overall_id"), curSet.getString("year_id"),
                        curSet.getString("plaintiff"), curSet.getString("defendant"),
                        curSet.getString("text_of_case"), curSet.getInt("court_id"),
                        curSet.getInt("judge_id"), curSet.getInt("is_correct"),
                        curSet.getString("date_of_trial"), curSet.getString("judgment"),
                        curSet.getBoolean("closed"));
            return newCase;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void updateCase(Case curCase) {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "UPDATE cases\n" +
                    "SET is_correct = " + curCase.getIsCorrect() + ",\n" +
                    "   closed = " + curCase.getClosed();
            System.out.println(curCase.getJudgment());
            if (curCase.getJudgment() != null )
                sql += ",\n judgment = '" + curCase.getJudgment() + "'";
            if (curCase.getDateOfTrial() != null)
                sql += ",\n date_of_trial = '" + curCase.getDateOfTrial() +"'" ;
            sql += "\nWHERE overall_id = '" + curCase.getId() + "';";
            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Case> getActiveCases() {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT x.overall_id, x.year_id, x.plaintiff, x.defendant, x.text_of_case, x.court_id, x.judge_id, x.is_correct, x.date_of_trial, x.judgment, x.closed \n" +
                    "FROM cases x INNER JOIN assistant_judge y ON x.judge_id = y.judge_id\n" +
                    "WHERE y.login = '" + currentUserName + "';";
            ResultSet curSet = statement.executeQuery(sql);
            List<Case> cases = new LinkedList<>();
            while (curSet.next()) {
                if (!curSet.getBoolean("closed")) {
                    Case newCase = new Case(curSet.getInt("overall_id"), curSet.getString("year_id"),
                            curSet.getString("plaintiff"), curSet.getString("defendant"),
                            curSet.getString("text_of_case"), curSet.getInt("court_id"),
                            curSet.getInt("judge_id"), curSet.getInt("is_correct"),
                            curSet.getString("date_of_trial"), curSet.getString("judgment"),
                            curSet.getBoolean("closed"));
                    cases.add(newCase);
                }
            }
            return cases;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Court> getCourts() {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT * FROM court;";
            ResultSet curSet = statement.executeQuery(sql);
            List<Court> courts = new LinkedList<>();
            while (curSet.next()) {
                Court newCourt = new Court(curSet.getInt("court_id"), curSet.getString("city_name"),
                        curSet.getString("address"));
                courts.add(newCourt);
            }
            return courts;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static Vector<String> setTableRole(String profession) {
        String table = "", role = "";
        if (profession.equals("head_of_stuff") || profession.equals("clerk")) {
            table = "civil_servant";
            if (profession.equals("head_of_stuff"))
                role = "main_admin";
            else
                role = "clerk";
        }
        if (profession.equals("judge")) {
            table = "judge";
            role = "judge";
        }
        if (profession.equals("judge_assistant")) {
            table = "assistant_judge";
            role = "assistant";
        }
        Vector<String> ans = new Vector<>();
        ans.add(table);
        ans.add(role);
        return ans;
    }

    public static Vector<String> addNewPerson(String name, String surname,String profession, Integer courtId){
        try {
            String writeProf = profession;
            if (profession.equals("clerk"))
                writeProf = "Court clerk";
            if (profession.equals("head_of_stuff"))
                writeProf = "Head of stuff";
            if (profession.equals("judge") || profession.equals("judge_assistant"))
                writeProf = null;
            Employee employee = new Employee(null, name, surname, null,  courtId, writeProf);
            Statement statement;
            String sql;
            ResultSet curSet;
            if (profession.equals("secretary") || profession.equals("guardian")) {
                statement = currentConnection.createStatement();
                sql = "INSERT INTO civil_servant (name, surname, profession)" +
                        "VALUES ('"+ employee.getName() + "', '" + employee.getSurname() +
                        "', '" + employee.getProfession() + "');";
                System.out.println(sql);
                statement.executeUpdate(sql);
                return null;
            }
            else {
                Vector<String> roleTable = setTableRole(profession);
                String table = roleTable.elementAt(0);
                String role = roleTable.elementAt(1);
                Vector<String> login = new Vector<>();
                boolean flag = false;
                String newLogin = "";
                while (!flag) {
                    newLogin = profession + random.nextInt(10000);
                    statement = currentConnection.createStatement();
                    sql = "SELECT *\n" +
                            "FROM " + table + " x\n" +
                            "WHERE x.login = '" + newLogin + "';";
                    System.out.println(sql);
                    curSet = statement.executeQuery(sql);
                    if (!curSet.next())
                        flag = true;
                }
                login.add(newLogin);
                login.add(String.valueOf(random.nextInt(1000000000)));

                statement = currentConnection.createStatement();
                sql = "CREATE USER " + login.elementAt(0) + "\n" +
                        "WITH LOGIN PASSWORD '" + login.elementAt(1) + "' IN ROLE " + role + ";";
                System.out.println(sql);
                statement.executeUpdate(sql);
                statement = currentConnection.createStatement();
                if (table.equals("civil_servant"))
                    sql = "INSERT INTO civil_servant (name, surname, profession, login, court_id) " +
                            "VALUES ('"+ employee.getName() + "', '" + employee.getSurname() +
                            "', '" + employee.getProfession() + "', '" + login.elementAt(0) + "'" +
                            ", " + courtId + ");";
                else
                    sql = "INSERT INTO " + table + " (name, surname, login, court_id) " +
                            "VALUES ('"+ employee.getName() + "', '" + employee.getSurname() +
                            "', '" + login.elementAt(0) + "', " + courtId + ");";
                System.out.println(sql);
                statement.executeUpdate(sql);
                return login;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Judge> getJudges() {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT * FROM judge;";
            ResultSet curSet = statement.executeQuery(sql);
            List<Judge> judges = new LinkedList<>();
            while (curSet.next()) {
                Judge newJudges = new Judge(curSet.getInt("id"), curSet.getString("name"),
                        curSet.getString("surname"), curSet.getString("login"),
                        curSet.getInt("court_id"));
                judges.add(newJudges);
            }
            return judges;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<JudgeAssistant> getJudgeAssistants() {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT * FROM assistant_judge;";
            ResultSet curSet = statement.executeQuery(sql);
            List<JudgeAssistant> judgeAssistants = new LinkedList<>();
            while (curSet.next()) {
                JudgeAssistant newAssistants = new JudgeAssistant(curSet.getInt("id"), curSet.getString("name"),
                        curSet.getString("surname"), curSet.getString("login"),
                        curSet.getInt("judge_id"));
                judgeAssistants.add(newAssistants);
            }
            return judgeAssistants;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Employee> getEmployees() {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT * FROM civil_servant;";
            ResultSet curSet = statement.executeQuery(sql);
            List<Employee> employees = new LinkedList<>();
            while (curSet.next()) {
                Employee newEmployees = new Employee(curSet.getInt("id"), curSet.getString("name"),
                        curSet.getString("surname"), curSet.getString("login"),
                        curSet.getInt("court_id"), curSet.getString("profession"));
                if (!newEmployees.getLogin().equals(currentUserName))
                    employees.add(newEmployees);
            }
            return employees;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void deletePerson(String type, Integer id) {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT x.login " +
                    "FROM " + type + " x " +
                    "WHERE x.id = " + id + ";";
            ResultSet resSet = statement.executeQuery(sql);
            resSet.next();
            String login = resSet.getString("login");
            statement = currentConnection.createStatement();
            sql = "DELETE FROM " + type + " WHERE id = " + id + ";";
            statement.executeUpdate(sql);
            if (login != null) {
                statement = currentConnection.createStatement();
                sql = "DROP USER " + login + ";";
                statement.executeUpdate(sql);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
