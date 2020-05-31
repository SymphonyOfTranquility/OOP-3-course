import Database.DBConnection;
import tableElements.Case;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/judgeAssistantMainPage")
public class MainPageJudgeAssistant  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists()) {

            List<Case> cases = DBConnection.getActiveCases();
            System.out.println(cases.size());
            request.setAttribute("Cases", cases.toArray(new Case[0]));
            getServletContext().getRequestDispatcher("/assistant/showAllActiveCases.jsp").forward(request, response);
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordJudgeAssistant");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists()) {
            System.out.println(request.getParameter("id"));
            if (request.getParameter("id") == null) {
                response.sendRedirect("/lab2_war_exploded/judgeAssistantMainPage");
                return;
            }
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("error", "Invalid Username or Password");
            response.sendRedirect("/lab2_war_exploded/judgeAssistantUpdateCase?id=" + id);
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordJudgeAssistant");
    }
}
