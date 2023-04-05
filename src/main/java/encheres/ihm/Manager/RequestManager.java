package encheres.ihm.Manager;

import encheres.bll.*;
import encheres.bo.ArticleVendu;
import encheres.dal.DALException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestManager {
    public static void processHomePageAttributes(HttpServletRequest request) throws DALException, BLLException {
        request.setAttribute("current_auctions", new ArticleVenduManager().getAllArticlesVendus());
        request.setAttribute("categories", new CategorieManager().getAllCategories());
        request.setAttribute("pseudos", new UtilisateurManager().getPseudosUtilisateursWithCurrentAuctions());
    }
    public static void processInsertOrUpdateAuctionPageAttributes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CategorieManager cm = new CategorieManager();
            request.setAttribute("categories", cm.getAllCategories());
        } catch (DALException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public static void processUpdateAuctionPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idAuctionToUpdate = Integer.valueOf(request.getParameter("id"));
        ArticleVenduManager avm = new ArticleVenduManager();
        List<String> errors = new ArrayList<>();
        try {
            ArticleVendu auctionToUpdate = avm.getArticleById(idAuctionToUpdate);
            request.setAttribute("auctionToUpdate", auctionToUpdate);
            RequestManager.processInsertOrUpdateAuctionPageAttributes(request, response);
        } catch (DALException e) {
            ErrorsManager.DALExceptionsCatcher(e, errors, request);
        } catch (BLLException e) {
            ErrorsManager.BLLExceptionsCatcher(e, errors, request);
        }
    }

    public static void processSeeAuctionPage(HttpServletRequest request, int idAuction) throws DALException, BLLException {
        ArticleVenduManager avm = new ArticleVenduManager();
        CategorieManager cm = new CategorieManager();
        EnchereManager em = new EnchereManager();

        UtilisateurManager um = new UtilisateurManager();
        ArticleVendu articleVendu = avm.getArticleById(idAuction);
        request.setAttribute("auction", articleVendu);
        request.setAttribute("category", cm.getCategorieById(articleVendu.getNoCategorie()));
        

        request.setAttribute("seller", um.getUtilisateurById(articleVendu.getNoUtilisateur()));
    }
}
