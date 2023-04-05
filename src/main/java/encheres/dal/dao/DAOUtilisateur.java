package encheres.dal.dao;

import encheres.bo.Utilisateur;
import encheres.dal.DALException;

import java.util.HashMap;

public interface DAOUtilisateur extends DAO<Utilisateur> {
    boolean checkForUniquePseudoAndMail(String pseudo, String mail) throws DALException;
    boolean checkForUniquePseudo(String pseudo) throws DALException;
    boolean checkForUniqueMail(String mail) throws DALException;
    Utilisateur selectUtilisateurByPseudo(String pseudo) throws DALException;
    HashMap<Integer, String> selectUtilisateursWithCurrentAuction() throws DALException;
    void updateCredit(int noUtilisateur, int newCredit) throws DALException;
	void update(Utilisateur utilisateur);
}