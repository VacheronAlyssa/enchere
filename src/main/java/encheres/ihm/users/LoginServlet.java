package encheres.ihm.users;

import encheres.dal.DALException;

import encheres.ihm.Manager.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/login_error"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        if (request.getRequestURI().contains("error")) {
            
        	
            request.setAttribute("page", "login");
            request.setAttribute("login_error", "true");
            rd.forward(request, response);
        } else if (request.isUserInRole("basic_user")) {
           
        	
            SessionManager.setSessionConnected(request);
            try {
               
            	
                SessionManager.setUtilisateurSessionBean(request, request.getUserPrincipal().getName());
            } catch (DALException e) {
                
            	
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            response.sendRedirect(request.getContextPath());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.isUserInRole("basic_user")) {
            SessionManager.setSessionConnected(request);
            try {
               
                SessionManager.setUtilisateurSessionBean(request, request.getUserPrincipal().getName());
            } catch (DALException e) {
               
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            HttpSession session = request.getSession();
            
            if (session.getAttribute("uriAndParamsRequested") != null) {
                response.sendRedirect((String) session.getAttribute("uriAndParamsRequested"));
            } else {
                response.sendRedirect(request.getContextPath());
            }

        } else {
           
            request.setAttribute("page", "login");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
            rd.forward(request, response);
        }
    }
}
