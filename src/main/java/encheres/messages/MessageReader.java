package encheres.messages;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public abstract class MessageReader {
    private static Properties props;

    static {
        try {
            InputStream utf8in = MessageReader.class.getClassLoader().getResourceAsStream("encheres/messages/error_messages.properties");
            Reader reader = new InputStreamReader(utf8in, StandardCharsets.UTF_8);
            props = new Properties();
            props.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public static  String getMessageReader(int code)
    {
        String message;
        try {
            if(props != null) {
                message = props.getProperty(String.valueOf(code));
            } else {
                message="Probleme fichier";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "inconnue";
        }
        return message;
    }
}
