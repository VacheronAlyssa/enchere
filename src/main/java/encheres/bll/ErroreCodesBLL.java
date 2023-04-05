 package encheres.bll;


abstract class ErrorCodesBLL {
     static final int ERROR_LENGTH_PSEUDO_UTILISATEUR = 30000;
     static final int ERROR_LENGTH_NOM_UTILISATEUR = 30001;
     static final int ERROR_LENGTH_PRENOM_UTILISATEUR = 30002;
     static final int ERROR_LENGTH_EMAIL_UTILISATEUR = 30003;
     static final int ERROR_LENGTH_TELEPHONE_UTILISATEUR = 30004;
     static final int ERROR_LENGTH_RUE_UTILISATEUR = 30005;
     static final int ERROR_LENGTH_CODE_POSTAL_UTILISATEUR = 30006;
     static final int ERROR_LENGTH_VILLE_UTILISATEUR = 30007;
     static final int ERROR_FORMAT_EMAIL_UTILISATEUR = 30008;
     static final int ERROR_FORMAT_TELEPHONE_UTILISATEUR = 30009;
     static final int ERROR_PSEUDO_OR_MAIL_ALREADY_TAKEN = 30010;
     static final int ERROR_VALUE_STATUT_VENTE_ARTICLE = 30022;
     static final int ERROR_START_DATE_AFTER_END_DATE = 30023;
     static final int ERROR_DATE_BEFORE_TODAY = 30024;
     static final int ERROR_LENGTH_NOM_CATEGORIE = 30030;
     static final int ERROR_NOM_CATEGORIE_ALREADY_TAKEN = 30031;
     static final int ERROR_LENGTH_RUE_RETRAIT = 30040;
     static final int ERROR_LENGTH_CODE_POSTAL_RETRAIT = 30041;
     static final int ERROR_LENGTH_VILLE_RETRAIT = 30042;
     static final int ERROR_NO_RESULTS = 30050;
}
