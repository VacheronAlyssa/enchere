package encheres.bll;

import java.util.List;

import encheres.bo.Categorie;
import encheres.dal.DALException;
import encheres.dal.dao.DAOCategorie;
import encheres.dal.dao.DAOFactory;

public class CategorieManager {
    private static DAOCategorie dao;

    static {
        dao = DAOFactory.getDAOCategorie();
    }

    public void createCategorie(Categorie categorie) throws DALException, BLLException {
        BLLException bllException = new BLLException();
        if (categorie.getNomCategorie().length() > 30) {
            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_NOM_CATEGORIE);
        }
        if (!dao.checkForUniqueCategorieNomCategorie(categorie.getNomCategorie())) {
            bllException.addError(ErrorCodesBLL.ERROR_NOM_CATEGORIE_ALREADY_TAKEN);
        }
        if (bllException.hasErrors()) {
            throw bllException;
        } else {
            dao.insert(categorie);
        }
    }
    
    public Categorie getCategorieById(int id) throws DALException {
        return dao.selectById(id);
    }
   
    public List<Categorie> getAllCategories() throws DALException {
        return dao.selectAll();
    }
}
