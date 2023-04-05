package encheres.dal.dao;

import encheres.bo.Categorie;
import encheres.dal.DALException;

public interface DAOCategorie extends DAO<Categorie> {
    boolean checkForUniqueCategorieNomCategorie(String NomCategorieToCheck) throws DALException;
}
