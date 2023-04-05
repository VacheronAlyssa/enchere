package encheres.bll;

import java.util.List;

import encheres.bo.ArticleVendu;
import encheres.bo.Categorie;
import encheres.bo.Utilisateur;
import encheres.dal.DALException;
import encheres.dal.dao.DAOArticleVendu;
import encheres.dal.dao.DAOFactory;

public class ArticleVenduManager {
    private static DAOArticleVendu dao;

    static {
        dao = DAOFactory.getDAOArticleVendu();
    }

    
    public ArticleVendu getArticleById(int id) throws DALException, BLLException {
        ArticleVendu articleVendu = dao.selectById(id);
        if (articleVendu == null) {
            BLLException bllException = new BLLException();
            bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
            throw bllException;
        }
        return articleVendu;
    }

    
  
    
    public List<ArticleVendu> getAllArticlesVendus() throws DALException, BLLException {
        List<ArticleVendu> articlesVendus = dao.selectAll();
        if (articlesVendus.isEmpty()) {
            BLLException bllException = new BLLException();
            bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
            throw bllException;
        } else {
            return articlesVendus;
        }
    }
   
    public List<Integer> getArticlesByEtat(String etat) throws DALException, BLLException {
        List<Integer> articlesVendus = dao.filterByEtat(etat);
        if (articlesVendus.isEmpty()) {
            BLLException bllException = new BLLException();
            bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
            throw bllException;
        } else {
            return articlesVendus;
        }
    }


    public List<ArticleVendu> getArticlesFromCategory(Categorie categorie) throws DALException, BLLException {
        List<ArticleVendu> articlesVendus = dao.filterByCategory(categorie);
        if (articlesVendus.isEmpty()) {
            BLLException bllException = new BLLException();
            bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
            throw bllException;
        } else {
            return articlesVendus;
        }
    }
  
    public List<ArticleVendu> getArticlesFilterByNomArticle(String filter) throws DALException, BLLException {
        List<ArticleVendu> articlesVendus = dao.filterByString(filter);
        if (articlesVendus.isEmpty()) {
            BLLException bllException = new BLLException();
            bllException.addError(ErrorCodesBLL.ERROR_NO_RESULTS);
            throw bllException;
        } else {
            return articlesVendus;
        }
  
    }




	public List<Integer> getArticlesFilteredBySellerAndState(Utilisateur utilisateurLogged,
			String fINISHED_AUCTION_STATE) {
		// TODO Auto-generated method stub
		return null;
	}
}
