import java.io.*;

public class StateOne {
    public static void setting()   {
        String address = "D:\\B - University\\AP - JAVA\\projects\\project-1\\tic-tac-toe project\\new.text";
        File file = new File(address);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String st;
        while (true) {
            try {
                if (!((st = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(st);
        }
    }
}
