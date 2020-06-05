import Database.DBConnection;
import tableElements.Case;
import tableElements.Court;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

@WebServlet("/HFCreateNewAccount")
public class HFCreateNewAccount extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists()) {
            List<Court> courts = DBConnection.getCourts();
            System.out.println(courts.size());
            request.setAttribute("Courts", courts.toArray(new Court[0]));
            getServletContext().getRequestDispatcher("/headOfStuff/addNewPerson.jsp").forward(request, response);
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordHeadOfStuff");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get it");
        if (DBConnection.exists()) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String profession = request.getParameter("profession");
            Integer courtId = Integer.parseInt(request.getParameter("court"));
            if (name == null || name.isEmpty() || surname == null || surname.isEmpty() ||
                    profession == null || courtId == null)
            {
                request.setAttribute("error","Invalid name or surname");
                RequestDispatcher rq = request.getRequestDispatcher("/headOfStuff/addNewPerson.jsp");
                rq.forward(request, response);
                return;
            }
            Vector<String> login = DBConnection.addNewPerson(name, surname, profession, courtId);
            System.out.println(name + surname + profession);
            if (login != null) {
                System.out.println(login.elementAt(0) + " " + login.elementAt(1));
                response.sendRedirect("/lab2_war_exploded/HFSuccessfullyDone?login=" + login.elementAt(0) +
                        "&passwd=" + login.elementAt(1));
            }
            else
                response.sendRedirect("/lab2_war_exploded/HFSuccessfullyDone");
            System.out.println("Done!");
        }
        else
            response.sendRedirect("/lab2_war_exploded/checkPasswordHeadOfStuff");
    }
}
