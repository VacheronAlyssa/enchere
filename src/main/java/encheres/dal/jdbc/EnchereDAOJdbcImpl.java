package encheres.dal.jdbc;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import encheres.bo.Enchere;
import encheres.bo.Utilisateur;
import encheres.dal.DALException;
import encheres.dal.ErrorCodesDAL;
import encheres.dal.dao.DAOEnchere;

public class EnchereDAOJdbcImpl implements DAOEnchere {
    private static final String SELECT_BY_UTILISATEUR = null;

	@Override
    public void insert(Enchere enchere) throws DALException {
        Connection cnx = JdbcTools.connect();
        try {
            String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, montant_enchere) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = cnx.prepareStatement(INSERT);
            stmt.setInt(1, enchere.getNoUtilisateur());
            stmt.setInt(2, enchere.getNoArticle());
            
            stmt.setInt(3, enchere.getMontantEnchere());
            stmt.executeUpdate();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
            throw dalException;
        }
    }

    @Override
    public List<Integer> getNoArticlesByUtilisateur1(Utilisateur utilisateur) throws DALException {
        Connection cnx = JdbcTools.connect();
        List <Integer> noArticlesMatched = new ArrayList<>();

        String SELECT_BY_UTILISATEUR= "SELECT E.no_article " +
                "FROM ENCHERES E " +
                "INNER JOIN ARTICLES_VENDUS AV on E.no_article = AV.no_article " +
                "WHERE AV.etat_vente = ? AND E.no_utilisateur = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR);
            stmt.setInt(1, utilisateur.getNoUtilisateur());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                noArticlesMatched.add(rs.getInt("no_article"));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }

        return noArticlesMatched;
    }

  

    @Override
    public Enchere selectById(int id) throws DALException {
        return null;
    }

    @Override
    public List<Enchere> selectAll() throws DALException {
        return null;
    }

	@Override
	public List<Integer> getNoArticlesByUtilisateur(Utilisateur utilisateur) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

  
}
