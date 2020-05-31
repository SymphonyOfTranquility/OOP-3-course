package Database;

import tableElements.Case;

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


}
