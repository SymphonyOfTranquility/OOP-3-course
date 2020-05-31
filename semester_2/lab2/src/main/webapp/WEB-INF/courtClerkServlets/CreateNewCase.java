import Database.DBConnection;
import tableElements.Case;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/clerkCreateNewCase")
public class CreateNewCase extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists())
            getServletContext().getRequestDispatcher("/courtClerk/courtClerkMain.jsp").forward(request, response);
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordClerk");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists()) {
            String yearId = request.getParameter("year_id");
            String plaintiff = request.getParameter("plaintiff");
            String defendant = request.getParameter("defendant");
            String textOfCase = request.getParameter("text_of_case");
            Case newCase = new Case(null, yearId, plaintiff, defendant, textOfCase, null, null,
                    0, null, null, false);
            System.out.println(yearId + "\n" + plaintiff + "\n" + defendant + "\n" + textOfCase + "\n");
            DBConnection.addCase(newCase);
            System.out.println("Done!");
            response.sendRedirect("/lab2_war_exploded/clerkSuccessfullyAdded");
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordClerk");

    }
}
