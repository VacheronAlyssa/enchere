package encheres.dal.jdbc;

import encheres.bo.ArticleVendu;
import encheres.bo.Categorie;
import encheres.bo.Utilisateur;
import encheres.dal.DALException;
import encheres.dal.dao.DAOArticleVendu;
import encheres.dal.ErrorCodesDAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleVenduDAOJdbcImpl implements DAOArticleVendu {
    
    @Override
    public void insert(ArticleVendu articleVendu) throws DALException {
        Connection cnx = JdbcTools.connect();
        try {
            String INSERT = "INSERT INTO ARTICLES_VENDUS " +
                    "(nom_article, description, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente, no_utilisateur, no_categorie) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            fillPreparedStatement(articleVendu, stmt);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                articleVendu.setNoArticle(rs.getInt(1));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
            throw dalException;
        }
    }

 
    private void fillPreparedStatement(ArticleVendu articleVendu, PreparedStatement stmt) {
		// TODO Auto-generated method stub
		
	}


	@Override
    public ArticleVendu selectById(int id) throws DALException {
        Connection cnx = JdbcTools.connect();
        ArticleVendu articleVendu = null;
        try {
            String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                articleVendu = articleArticleVendu(rs);
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return articleVendu;
    }

   
    @Override
    public List<ArticleVendu> filterByCategory(Categorie categorie) throws DALException {
        Connection cnx = JdbcTools.connect();
        List<ArticleVendu> articlesVendus = new ArrayList<>();
        try {
            String SELECT_BY_CATEGORY = "SELECT * " +
                    "FROM ARTICLES_VENDUS " +
                    "INNER JOIN CATEGORIES C on ARTICLES_VENDUS.no_categorie = C.no_categorie " +
                    "WHERE C.no_categorie = ?";
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_CATEGORY);
            stmt.setInt(1, categorie.getNoCategorie());
            stmt.execute();
            ResultSet rs =  stmt.getResultSet();
            while (rs.next()) {
                articlesVendus.add(articleArticleVendu(rs));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return articlesVendus;
    }

    
    public List<Integer> filterByEtat(String etat) throws DALException {
        Connection cnx = JdbcTools.connect();
        List<Integer> articlesVendus = new ArrayList<>();
        try {
            String SELECT_BY_ETAT = "SELECT AV.no_article FROM ARTICLES_VENDUS AV WHERE AV.etat_vente = ?";
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ETAT);
            stmt.setString(1, etat);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                articlesVendus.add(rs.getInt("no_article"));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return articlesVendus;
    }



    
    @Override
    public List<ArticleVendu> filterByString(String filter) throws DALException {
        Connection cnx = JdbcTools.connect();
        List<ArticleVendu> articlesVendus = new ArrayList<>();
        try {
            String SELECT_BY_NAME_SEARCH = "SELECT * " +
                    "FROM ARTICLES_VENDUS " +
                    "WHERE nom_article LIKE ?";
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NAME_SEARCH);
            stmt.setString(1, "%" + filter + "%");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                articlesVendus.add(articleArticleVendu(rs));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return articlesVendus;
    }
    
    @Override
    public List<ArticleVendu> selectAll() throws DALException {
        Connection cnx = JdbcTools.connect();
        List<ArticleVendu> articlesVendus = new ArrayList<>();
        try {
            String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
            PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                articlesVendus.add(articleArticleVendu(rs));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;        }
        return articlesVendus;
    }

   
  
    private ArticleVendu articleArticleVendu(ResultSet rs) throws SQLException {
        return new ArticleVendu(
                rs.getInt("no_article"),
                rs.getString("nom_article"),
                rs.getString("description"),
                rs.getDate("date_debut_encheres"),
                rs.getDate("date_fin_encheres"),
                rs.getInt("prix_initial"),
                rs.getInt("prix_vente"),
                rs.getString("etat_vente"),
                rs.getInt("no_utilisateur"),
                rs.getInt("no_categorie")
        );
    }


	@Override
	public List<Integer> getArticlesFromASellerAndState(Utilisateur utilisateur, String state) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateCurrentPrice(int noArticle, int newPrice) throws DALException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Integer> getNoArticlesByUtilisateur1(Utilisateur utilisateur) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
}
