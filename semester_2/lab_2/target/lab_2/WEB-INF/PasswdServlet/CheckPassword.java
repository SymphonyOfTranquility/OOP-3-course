import DataBase.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkPassword")
public class CheckPassword extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       getServletContext().getRequestDispatcher("/index.jsp").
                forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("uname");
        String password = request.getParameter("psw");
        System.out.println(userName + " " + password);
        String role = DBConnection.createConnection(userName, password);
        System.out.println(role);
        if (role == null) {
            request.setAttribute("error", "Invalid Username or Password");
            RequestDispatcher rq = request.getRequestDispatcher("/index.jsp");
            rq.forward(request, response);
        } else if (role.equals("tour_agent")) {
            response.sendRedirect("/lab_2_war_exploded/tourAgentPage");
        } else if (role.equals("client")) {
            response.sendRedirect("/lab_2_war_exploded/clientPage");
        }
        else
        {
            request.setAttribute("error", "Current user don't have access to this table");
            RequestDispatcher rq = request.getRequestDispatcher("/index.jsp");
            rq.forward(request, response);
        }
    }
}