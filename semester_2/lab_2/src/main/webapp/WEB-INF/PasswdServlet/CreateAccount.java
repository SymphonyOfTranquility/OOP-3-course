import DataBase.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createAccount")
public class CreateAccount extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection.close();
        getServletContext().getRequestDispatcher("/createNewAccount/form.jsp").
                forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection.close();
        String userName = request.getParameter("uname");
        String email = request.getParameter("email");
        String password = request.getParameter("psw");
        String passwordRep = request.getParameter("psw_r");
        String userType = request.getParameter("user_type");
        System.out.println(userName + " " + password);
        String dbAns = DBConnection.addUser(userName, email, password, passwordRep, userType);
        if (dbAns.equals("error_passwd")){
            request.setAttribute("error", "Password is not same");
            RequestDispatcher rq = request.getRequestDispatcher("/createNewAccount/form.jsp");
            rq.forward(request, response);
        }
        else if (dbAns.equals("error_user"))
        {
            request.setAttribute("error", "User with this account name exists");
            RequestDispatcher rq = request.getRequestDispatcher("/createNewAccount/form.jsp");
            rq.forward(request, response);
        }
        else if (dbAns.equals("error_email"))
        {
            request.setAttribute("error", "User with this email exists");
            RequestDispatcher rq = request.getRequestDispatcher("/createNewAccount/form.jsp");
            rq.forward(request, response);
        }
        else
        {
            getServletContext().getRequestDispatcher("/createNewAccount/done.jsp").
                    forward(request, response);
        }
    }
}
