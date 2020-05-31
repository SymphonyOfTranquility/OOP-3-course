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
import java.util.Date;
import java.util.List;

@WebServlet("/judgeUpdateCase")
public class UpdateCaseJudge extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists()) {
            Case curCase = DBConnection.getCase(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("Case", curCase);
            getServletContext().getRequestDispatcher("/judge/updateValueForCase.jsp").forward(request, response);
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordJudge");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get it");
        if (DBConnection.exists()) {
            String is_correct = request.getParameter("is_correct");
            String date_of_trial = request.getParameter("date_of_trial");
            String judgment = request.getParameter("judgment");
            String closed = request.getParameter("closed");
            String id = request.getParameter("id");
            Case curCase = DBConnection.getCase(Integer.parseInt(id));
            if (is_correct.equals("Yes"))
                curCase.setIsCorrect(1);
            else if (is_correct.equals("No"))
                curCase.setIsCorrect(2);

            if (!curCase.getClosed())
                curCase.setClosed(!closed.equals("No"));
            if (!judgment.isEmpty())
                curCase.setJudgment(judgment);
            if (!date_of_trial.isEmpty())
                curCase.setDateOfTrial(date_of_trial);
            System.out.println(judgment);
            System.out.println(date_of_trial);

            DBConnection.updateCase(curCase);
            System.out.println("Done!");
            response.sendRedirect("/lab2_war_exploded/judgeSuccessfullyAdded");
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordJudge");
    }
}
