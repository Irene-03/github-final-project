import java.io.IOException;

public class Main {
    /**
     * the function just make the object of menu class
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
//        Menu object = new Menu();
        LoginData loginData =new LoginData();
        loginData.checkExistFile();
        loginData.changeGameInfo("arefeh" , "123456",1);
    }

}
