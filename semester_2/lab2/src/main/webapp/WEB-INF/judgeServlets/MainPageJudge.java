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
import java.util.List;

@WebServlet("/judgeMainPage")
public class MainPageJudge extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists()) {

            List<Case> cases = DBConnection.getCases();
            request.setAttribute("Cases", cases.toArray(new Case[0]));
            getServletContext().getRequestDispatcher("/judge/showAllAvailableCases.jsp").forward(request, response);
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordJudge");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists()) {
            System.out.println(request.getParameter("id"));
            if (request.getParameter("id") == null) {
                response.sendRedirect("/lab2_war_exploded/judgeMainPage");
                return;
            }
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("error", "Invalid Username or Password");
            response.sendRedirect("/lab2_war_exploded/judgeUpdateCase?id=" + id);
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordJudge");
    }
}
