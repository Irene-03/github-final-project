import java.io.*;
import java.util.Formatter;


public class Setting {
    private final String address = "D:\\B - University\\AP - JAVA\\projects\\project-1\\tic-tac-toe project\\src\\new.txt";


    public Setting(int[] array) throws IOException {
        readSetting(array);
    }

    public void readSetting(int[] info) throws IOException {

        File file = new File(address);
        BufferedReader buf = new BufferedReader(new FileReader(file));
        int index = 0;


        for (int i = 0; i <3 ; i++) info[index++] = Integer.parseInt(buf.readLine());

    }

    public void changeSetting(int sideSize, int blockCell, int wonCondition) throws IOException {

        Formatter formatter = new Formatter(address);
        formatter.format("%s\n%s\n%s", sideSize, blockCell, wonCondition);
        formatter.close();


    }

    public void defaultSetting() throws IOException {

        Formatter formatter = new Formatter(address);
        formatter.format("%s\n%s\n%s",4,3,3 );
        formatter.close();
    }


}


