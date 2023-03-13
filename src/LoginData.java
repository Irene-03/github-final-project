import java.io.File;
import java.io.IOException;

public class LoginData {

   public void checkExistFile () throws IOException {
      System.out.println(new File(".").getCanonicalPath());
      
   }
}
