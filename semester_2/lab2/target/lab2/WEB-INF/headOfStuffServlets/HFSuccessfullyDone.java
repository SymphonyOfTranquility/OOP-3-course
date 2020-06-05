import Database.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/HFSuccessfullyDone")
public class HFSuccessfullyDone extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists())
            getServletContext().getRequestDispatcher("/headOfStuff/successfullyDone.jsp").forward(request, response);
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordHeadOfStuff");
    }
}
