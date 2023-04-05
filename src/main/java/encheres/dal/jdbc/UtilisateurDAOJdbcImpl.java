package encheres.dal.jdbc;

import encheres.bo.Utilisateur;
import encheres.dal.DALException;
import encheres.dal.dao.DAOUtilisateur;
import encheres.dal.ErrorCodesDAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UtilisateurDAOJdbcImpl implements DAOUtilisateur {

   
    @Override
    public void insert(Utilisateur utilisateur) throws DALException {
        Connection cnx = JdbcTools.connect();
        try {
            String INSERT = "INSERT INTO UTILISATEURS " +
                    "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mo_de_passe, credit, administrateur) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            fillPreparedStatement(utilisateur, stmt);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                utilisateur.setNoUtilisateur(rs.getInt(1));
            }
            setSecurityRoles(utilisateur);
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
            throw dalException;
        }
    }

   
    public Utilisateur selectById(int id) throws DALException {
        Connection cnx = JdbcTools.connect();
        Utilisateur utilisateur = null;
        try {
            String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                utilisateur = hydrateUtilisateur(rs);
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return utilisateur;
    }
    
    @Override
    public Utilisateur selectUtilisateurByPseudo(String pseudo) throws DALException {
        Connection cnx = JdbcTools.connect();
        Utilisateur utilisateur = null;
        try {
            String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
            stmt.setString(1, pseudo);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                utilisateur = hydrateUtilisateur(rs);
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return utilisateur;
    }

    @Override
    public HashMap<Integer, String> selectUtilisateursWithCurrentAuction() throws DALException {
        Connection cnx = JdbcTools.connect();
        HashMap<Integer, String> pseudos = new HashMap<> ();
        try {
            String SELECT_USERS_WITH_CURRENT_AUCTIONS =
                    "SELECT AV.no_article, pseudo " +
                    "FROM UTILISATEURS " +
                    "INNER JOIN ARTICLES_VENDUS AV on UTILISATEURS.no_utilisateur = AV.no_utilisateur ";
            Statement stmt = cnx.createStatement();
            stmt.execute(SELECT_USERS_WITH_CURRENT_AUCTIONS);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                pseudos.put(rs.getInt("no_article"), rs.getString("pseudo"));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return pseudos;
    }

    
    @Override
    public List<Utilisateur> selectAll() throws DALException {
        Connection cnx = JdbcTools.connect();
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            String SELECT_ALL = "SELECT * FROM UTILISATEURS";
            stmt.execute(SELECT_ALL);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                utilisateurs.add(hydrateUtilisateur(rs));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return utilisateurs;
    }
   
   

    /**
     * Update the credit of an user
     * @param noUtilisateur the id of the user
     * @param newCredit the amount of new credit
     * @throws DALException If there is any issue with the SQL query
     */
    @Override
    public void updateCredit(int noUtilisateur, int newCredit) throws DALException {
        Connection cnx = JdbcTools.connect();
        try {
            String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?";
            PreparedStatement stmt = cnx.prepareStatement(UPDATE_CREDIT);
            stmt.setInt(1, newCredit);
            stmt.setInt(2, noUtilisateur);
            stmt.executeUpdate();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_UPDATE);
            throw dalException;
        }
    }

    
   
    
   
    private void fillPreparedStatement(Utilisateur utilisateur, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, utilisateur.getPseudo());
        stmt.setString(2, utilisateur.getNom());
        stmt.setString(3, utilisateur.getPrenom());
        stmt.setString(4, utilisateur.getEmail());
        stmt.setString(5, utilisateur.getTelephone());
        stmt.setString(6, utilisateur.getRue());
        stmt.setString(7, utilisateur.getCodePostal());
        stmt.setString(8, utilisateur.getVille());
        stmt.setString(9, utilisateur.getMotDePasse());
        stmt.setInt(10, utilisateur.getCredit());
        stmt.setBoolean(11, utilisateur.isAdministrateur());
    }

    
    private Utilisateur hydrateUtilisateur(ResultSet rs) throws SQLException {
        return new Utilisateur(
                rs.getInt("no_utilisateur"),
                rs.getString("pseudo"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                rs.getString("telephone"),
                rs.getString("rue"),
                rs.getString("code_postal"),
                rs.getString("ville"),
                rs.getString("mot_de_passe"),
                rs.getInt("credit"),
                rs.getBoolean("administrateur")
        );
    }

    private void setSecurityRoles(Utilisateur utilisateur) throws SQLException {
        String ADD_ROLE = "INSERT INTO UTILISATEURS_ROLES (no_utilisateur, pseudo, nom_role) VALUES (?, ?, ?);";
        Connection cnx = JdbcTools.connect();
        PreparedStatement stmt = cnx.prepareStatement(ADD_ROLE);
        if (utilisateur.isAdministrateur()) {
            //TODO : Implements here the admin role
        } else {
            stmt.setInt(1, utilisateur.getNoUtilisateur());
            stmt.setString(2, utilisateur.getPseudo());
            stmt.setString(3, "basic_user");
        }
        stmt.executeUpdate();
    }

   
    @Override
    public boolean checkForUniquePseudoAndMail(String pseudo, String mail) throws DALException {
        Connection cnx = JdbcTools.connect();
        boolean isUnique = true;
        try {
            String CHECK_IF_PSEUDO_AND_MAIL_ALREADY_EXIST =
                    "SELECT * FROM UTILISATEURS " +
                    "WHERE pseudo LIKE ? OR email LIKE ?;";
            PreparedStatement stmt = cnx.prepareStatement(CHECK_IF_PSEUDO_AND_MAIL_ALREADY_EXIST);
            stmt.setString(1, pseudo);
            stmt.setString(2, pseudo);
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
    public boolean checkForUniquePseudo(String pseudo) throws DALException {
        Connection cnx = JdbcTools.connect();
        boolean isUnique = true;
        try {
            String CHECK_IF_PSEUDO_ALREADY_EXIST =
                    "SELECT * FROM UTILISATEURS " +
                            "WHERE pseudo LIKE ?;";
            PreparedStatement stmt = cnx.prepareStatement(CHECK_IF_PSEUDO_ALREADY_EXIST);
            stmt.setString(1, pseudo);
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
    public boolean checkForUniqueMail(String mail) throws DALException {
        Connection cnx = JdbcTools.connect();
        boolean isUnique = true;
        try {
            String CHECK_IF_MAIL_ALREADY_EXIST =
                    "SELECT * FROM UTILISATEURS " +
                            "WHERE email LIKE ?;";
            PreparedStatement stmt = cnx.prepareStatement(CHECK_IF_MAIL_ALREADY_EXIST);
            stmt.setString(1, mail);
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


	@Override
	public void update(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}
}
