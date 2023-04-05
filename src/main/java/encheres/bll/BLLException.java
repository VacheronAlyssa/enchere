package encheres.bll;

import java.util.List;
import java.util.ArrayList;

public class BLLException extends Exception {
    private static final long serialVersionUID = 1L;
    private List<Integer> listErrorCodes;

    public BLLException() {
        super();
        this.listErrorCodes=new ArrayList<>();
    }

    public void addError(int code)
    {
        if(!this.listErrorCodes.contains(code))
        {
            this.listErrorCodes.add(code);
        }
    }

    public boolean hasErrors()
    {
        return this.listErrorCodes.size()>0;
    }

    public List<Integer> getListErrorCodes()
    {
        return this.listErrorCodes;
    }

}