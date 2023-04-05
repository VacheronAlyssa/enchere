package encheres.bll;



import encheres.bo.Enchere;

import encheres.dal.DALException;
import encheres.dal.dao.DAOEnchere;
import encheres.dal.dao.DAOFactory;

public class EnchereManager {
    public static DAOEnchere dao;

    static {
        dao = DAOFactory.getDAOEnchere();
    }
    
    public void createEnchere(Enchere enchere) throws DALException {
        dao.insert(enchere);
    }

    

  
}
