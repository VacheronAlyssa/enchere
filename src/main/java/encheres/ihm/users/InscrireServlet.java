package encheres.ihm.users;

import encheres.bll.BLLException;
import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;
import encheres.dal.DALException;
import encheres.ihm.Manager.ErrorsManager;
import encheres.ihm.Manager.PasswordManager;
import encheres.ihm.Manager.RequestManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/createLogin")
public class InscrireServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UtilisateurManager um = new UtilisateurManager();
        List<String> errors = new ArrayList<>();
        // Hash password
        String password = request.getParameter("password");
        String generatedPassword = PasswordManager.hashPassword(password);
        // New user
        Utilisateur utilisateur = new Utilisateur(
                request.getParameter("pseudo"),
                request.getParameter("name"),
                request.getParameter("first_name"),
                request.getParameter("mail"),
                request.getParameter("phone"),
                request.getParameter("street"),
                request.getParameter("post_code"),
                request.getParameter("city"),
                generatedPassword,
                0,
                false
        );
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        try {
            um.createUtilisateur(utilisateur);
        } catch (BLLException e) {
            ErrorsManager.BLLExceptionsCatcher(e, errors, request);
        } catch (DALException e) {
            ErrorsManager.DALExceptionsCatcher(e, errors, request);
        }
        if (errors.isEmpty()) {
            try {
                RequestManager.processHomePageAttributes(request);
            } catch (DALException e) {
                ErrorsManager.DALExceptionsCatcher(e, errors, request);
            } catch (BLLException e) {
                ErrorsManager.BLLExceptionsCatcher(e, errors, request);
            }
            request.setAttribute("loginCreated", "true");
            request.setAttribute("page", "home");
        } else {
            request.setAttribute("page", "createLogin");
            request.setAttribute("utilisateurError", utilisateur);
        }
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        request.setAttribute("page", "createLogin");
        rd.forward(request, response);
    }
}
