package encheres.dal.dao;


import encheres.dal.jdbc.*;

public class DAOFactory {
    public static DAOUtilisateur getDAOUtilisateur() {
        return new UtilisateurDAOJdbcImpl();
    }
    public static DAOArticleVendu getDAOArticleVendu() {
        return new ArticleVenduDAOJdbcImpl();
    }
    public static DAOCategorie getDAOCategorie() {
        return new CategorieDAOJdbcImpl();
    }
    public static DAOEnchere getDAOEnchere() {
        return new EnchereDAOJdbcImpl();
    }
}
