import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import Database.DBConnection;

@WebServlet("/checkPasswordClerk")
public class CourtClerkCheckPassword extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/courtClerk/checkPassword.jsp").
                forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("uname");
        String password = request.getParameter("psw");
        System.out.println(userName + " " + password);
        if (!DBConnection.createConnection(userName, password, "clerk")) {
            request.setAttribute("error","Invalid Username or Password");
            RequestDispatcher rq = request.getRequestDispatcher("/courtClerk/checkPassword.jsp");
            rq.forward(request, response);
        }
        else {
            response.sendRedirect("/lab2_war_exploded/clerkCreateNewCase");
         //           getServletContext().getRequestDispatcher("/clerkCreateNewCase").forward(request, response);
        }
    }
}
