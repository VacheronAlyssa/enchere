package encheres.ihm;

import encheres.bll.*;
import encheres.bo.ArticleVendu;
import encheres.bo.Utilisateur;
import encheres.dal.DALException;
import encheres.ihm.Manager.ErrorsManager;
import encheres.bll.ArticleVenduManager;
import encheres.bll.BLLException;
import encheres.bll.CategorieManager;
import encheres.bll.UtilisateurManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/acceuil")
public class ServletAcceuil extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        request.setAttribute("page", "home");
        List<String> errors = new ArrayList<>();
        ArticleVenduManager avm = new ArticleVenduManager();
        CategorieManager cm = new CategorieManager();
        UtilisateurManager um = new UtilisateurManager();
        String CURRENT_AUCTION_STATE = "EC";
        String FINISHED_AUCTION_STATE = "VE";
        

        request.setAttribute("current_auctions", null);
        try {
            Utilisateur utilisateurLogged = null;
            if (request.getUserPrincipal() != null) {
                utilisateurLogged = um.getUtilisateurByPseudo(request.getUserPrincipal().getName());
            }
            List<ArticleVendu> articlesVendus = avm.getAllArticlesVendus();
            String categoryFilter = request.getParameter("category_filter");
          
            
            List<ArticleVendu> articlesToFilter = new ArrayList<>(articlesVendus);
            if (categoryFilter != null && !request.getParameter("category_filter").equals("all")) {
               
            	
                for (ArticleVendu articleVendu : articlesToFilter) {
                    if (articleVendu.getNoCategorie() != Integer.valueOf(categoryFilter)) {
                        articlesVendus.remove(articleVendu);
                    }
                }
            }
           
            
            String stringFilter = request.getParameter("string_filter");
           
            
            articlesToFilter = new ArrayList<>(articlesVendus);
            if (stringFilter != null) {
               
            	
                for (ArticleVendu articleVendu : articlesToFilter) {
                    if (!articleVendu.getNomArticle().toLowerCase().contains(stringFilter.toLowerCase())) {
                        articlesVendus.remove(articleVendu);
                    }
                }
            }
         
            
            if (request.getParameter("filterChoice") != null) {
                String filterChoice = request.getParameter("filterChoice");
            
                
                articlesToFilter = new ArrayList<>(articlesVendus);
                switch (filterChoice) {
                    case "currtechprojetenchere/src/main/javaentAuctions":
                       
                    	List<Integer> currentAuctions = avm.getArticlesByEtat(CURRENT_AUCTION_STATE);
                        for(ArticleVendu articleVendu : articlesToFilter) {
                            
                            if (!currentAuctions.contains(articleVendu.getNoArticle())) {
                                articlesVendus.remove(articleVendu);
                            }
                        }
                        break;
           
                    case "myEndedSales":
                        List<Integer> endedSales = avm.getArticlesFilteredBySellerAndState(utilisateurLogged, FINISHED_AUCTION_STATE);
                        for (ArticleVendu articleVendu : articlesToFilter) {
                            if (!endedSales.contains(articleVendu.getNoArticle())) {
                                articlesVendus.remove(articleVendu);
                            }
                        }
                        request.setAttribute("filterByMyEndedSales", "true");
                        break;
                }
            }

            request.setAttribute("current_auctions", articlesVendus);
            request.setAttribute("categories", cm.getAllCategories());
            request.setAttribute("pseudos", um.getPseudosUtilisateursWithCurrentAuctions());
            // We also have to keep the different filters in the way they were before the http request
            request.setAttribute("categoryFilter", categoryFilter);
            request.setAttribute("stringFilter", stringFilter);

        } catch (DALException e) {
            ErrorsManager.DALExceptionsCatcher(e, errors, request);
        } catch (BLLException e) {
            ErrorsManager.BLLExceptionsCatcher(e, errors, request);
        }
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/index.jsp");
        List<String> errors = new ArrayList<>();
        request.setAttribute("page", "home");
        CategorieManager cm = new CategorieManager();
        ArticleVenduManager avm = new ArticleVenduManager();
        UtilisateurManager um = new UtilisateurManager();
        try {
            request.setAttribute("current_auctions", avm.getAllArticlesVendus());
            request.setAttribute("categories", cm.getAllCategories());
            request.setAttribute("pseudos", um.getPseudosUtilisateursWithCurrentAuctions());
        } catch (DALException e) {
            ErrorsManager.DALExceptionsCatcher(e, errors, request);
        } catch (BLLException e) {
            ErrorsManager.BLLExceptionsCatcher(e, errors, request);
        }
        rd.forward(request, response);
    }
}