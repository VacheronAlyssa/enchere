package encheres.ihm.Manager;

import encheres.bll.BLLException;
import encheres.dal.DALException;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

import encheres.bll.BLLException;
import encheres.dal.DALException;
import encheres.messages.MessageReader;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ErrorsManager {

    public static void BLLExceptionsCatcher(BLLException e, List<String> errors, HttpServletRequest request) {
        for (Integer code_error : e.getListErrorCodes()) {
            errors.add(MessageReader.getMessageReader(code_error));
        }
        request.setAttribute("errors", errors);
    }
   
    
    public static void DALExceptionsCatcher(DALException e, List<String> errors, HttpServletRequest request) {
        request.setAttribute("error_name", "Erreur avec la base de données : ");
        for (Integer code_error : e.getListErrorCodes()) {
            errors.add(MessageReader.getMessageReader(code_error));
        }
        request.setAttribute("errors", errors);
    }
}
