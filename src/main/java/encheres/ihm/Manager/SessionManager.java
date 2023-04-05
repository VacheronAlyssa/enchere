package encheres.ihm.Manager;

import encheres.bll.UtilisateurManager;
import encheres.bo.Utilisateur;
import encheres.dal.DALException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
   
    public static void setSessionConnected (HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300);
        session.setAttribute("isConnected", "true");
    }



    public static void setUtilisateurSessionBean(HttpServletRequest request, String pseudo_utilisateur) throws DALException {
        UtilisateurManager um = new UtilisateurManager();
        HttpSession session = request.getSession();
        Utilisateur utilisateurToBean = um.getUtilisateurByPseudo(pseudo_utilisateur);
        session.setAttribute("utilisateurSession", utilisateurToBean);
    }
}
