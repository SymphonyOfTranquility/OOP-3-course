import Database.DBConnection;
import tableElements.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

@WebServlet("/HFDeleteAccount")
public class HFDeletePerson extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists()) {
            List<Judge> judges = DBConnection.getJudges();
            List<JudgeAssistant> judgeAssistants = DBConnection.getJudgeAssistants();
            List<Employee> employees = DBConnection.getEmployees();
            request.setAttribute("Judges", judges.toArray(new Judge[0]));
            request.setAttribute("JudgeAssistants", judgeAssistants.toArray(new JudgeAssistant[0]));
            request.setAttribute("Employees", employees.toArray(new Employee[0]));
            getServletContext().getRequestDispatcher("/headOfStuff/deletePerson.jsp").forward(request, response);
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordHeadOfStuff");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get it");
        if (DBConnection.exists()) {
            String typeReq = request.getParameter("type");
            String id = request.getParameter("id");
            DBConnection.deletePerson(typeReq, Integer.parseInt(id));
            response.sendRedirect("/lab2_war_exploded/HFSuccessfullyDone?deleted=1");
            System.out.println("Done!");
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordHeadOfStuff");
    }
}
