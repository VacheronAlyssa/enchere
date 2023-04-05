package encheres.dal.dao;

import encheres.bo.Utilisateur;
import encheres.dal.DALException;

import java.util.List;
 interface DAO<E> {
    void insert(E var) throws DALException;
    E selectById(int id) throws DALException;
    List<E> selectAll() throws DALException;
	List<Integer> getNoArticlesByUtilisateur1(Utilisateur utilisateur) throws DALException;
   
}
