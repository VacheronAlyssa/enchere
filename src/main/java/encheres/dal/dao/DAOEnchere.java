package encheres.dal.dao;

import encheres.bo.Enchere;
import encheres.bo.Utilisateur;
import encheres.dal.DALException;


import java.util.List;

public interface DAOEnchere extends DAO<Enchere> {
    List<Integer> getNoArticlesByUtilisateur(Utilisateur utilisateur) throws DALException;
    
}
