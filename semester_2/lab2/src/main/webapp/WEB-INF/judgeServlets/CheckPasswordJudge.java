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

@WebServlet("/checkPasswordJudge")
public class CheckPasswordJudge extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/judge/checkPassword.jsp").
                forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("uname");
        String password = request.getParameter("psw");
        System.out.println(userName + " " + password);
        if (!DBConnection.createConnection(userName, password, "judge")) {
            request.setAttribute("error","Invalid Username or Password");
            RequestDispatcher rq = request.getRequestDispatcher("/judge/checkPassword.jsp");
            rq.forward(request, response);
        }
        else {
            response.sendRedirect("/lab2_war_exploded/judgeMainPage");
            //           getServletContext().getRequestDispatcher("/clerkCreateNewCase").forward(request, response);
        }
    }
}
