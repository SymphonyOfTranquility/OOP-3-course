import Database.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkPasswordJudgeAssistant")
public class checkPasswordJudgeAssistant extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/assistant/checkPassword.jsp").
                forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("uname");
        String password = request.getParameter("psw");
        System.out.println(userName + " " + password);
        if (!DBConnection.createConnection(userName, password, "assistant")) {
            request.setAttribute("error","Invalid Username or Password");
            RequestDispatcher rq = request.getRequestDispatcher("/assistant/checkPassword.jsp");
            rq.forward(request, response);
        }
        else {
            response.sendRedirect("/lab2_war_exploded/judgeAssistantMainPage");
            //           getServletContext().getRequestDispatcher("/clerkCreateNewCase").forward(request, response);
        }
    }
}
