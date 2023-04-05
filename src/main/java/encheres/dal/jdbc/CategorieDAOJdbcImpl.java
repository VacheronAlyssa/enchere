package encheres.dal.jdbc;

import encheres.bo.Categorie;
import encheres.bo.Utilisateur;
import encheres.dal.DALException;
import encheres.dal.dao.DAOCategorie;
import encheres.dal.ErrorCodesDAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategorieDAOJdbcImpl implements DAOCategorie {

    @Override
    public void insert(Categorie categorie) throws DALException {
        Connection cnx = JdbcTools.connect();
        try {
            String INSERT = "INSERT INTO CATEGORIES (nom_categorie) VALUES (?)";
            PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, categorie.getNomCategorie());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                categorie.setNoCategorie(rs.getInt(1));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
            throw dalException;
        }
    }
   
    @Override
    public Categorie selectById(int id) throws DALException {
        Connection cnx = JdbcTools.connect();
        Categorie categorie = null;
        try {
            String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                categorie = new Categorie(
                        rs.getInt("no_categorie"),
                        rs.getString("nom_categorie")
                );
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return categorie;
    }
    
    
    @Override
    public List<Categorie> selectAll() throws DALException {
        Connection cnx = JdbcTools.connect();
        List<Categorie> categories = new ArrayList<>();
        try{
            Statement stmt = cnx.createStatement();
            String SELECT_ALL = "SELECT * FROM CATEGORIES";
            stmt.execute(SELECT_ALL);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                categories.add(new Categorie(
                        rs.getInt("no_categorie"),
                        rs.getString("nom_categorie")
                ));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return categories;
    }

   

   
    
    public boolean checkForUniqueCategorieNomCategorie(String nomCategorieToCheck) throws DALException {
        Connection cnx = JdbcTools.connect();
        boolean isUnique = true;
        try {
            String CHECK_IF_NOMCATEGORIE_IS_UNIQUE = "SELECT * FROM CATEGORIES WHERE nom_categorie LIKE ?";
            PreparedStatement stmt = cnx.prepareStatement(CHECK_IF_NOMCATEGORIE_IS_UNIQUE);
            stmt.setString(1, nomCategorieToCheck);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                isUnique = false;
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return isUnique;
    }

	@Override
	public List<Integer> getNoArticlesByUtilisateur1(Utilisateur utilisateur) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
}


