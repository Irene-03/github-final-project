import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


public class LoginData {
    Scanner scanner = new Scanner(System.in);
    private final String cls = "\033[H\033[2J";
    private int indexUser = 0;
    private ArrayList<String> gamersInfo = new ArrayList<>();
    private Menu menuObject = new Menu();


    public static void checkExistFile() {

        File file;

        try {
            file = new File("src/loginData.txt");
            //check exist
            if (file.exists()) ;
            else {
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("\n");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void startMenu() throws IOException {
        while (true) {
            System.out.println(cls);
            System.out.println("1-log in");
            System.out.println("2-sign up");
            System.out.println("3-log out");
            System.out.println("4-show the my information table");
            System.out.println("5-exist");
            int startInput;
            startInput = scanner.nextInt();
            if (startInput == 1) {
                login();
            } else if (startInput == 2) {
                signUp();
            } else if (startInput == 3) {
                //----------------------
            } else if (startInput == 4) {
                if (gamersInfo.size() != 0) {
                    showInfoTable();
                } else {
                    System.out.println(cls);
                    System.out.println("pleas first log in or sign up!");
                }

            } else if (startInput == 5) {
                break;
            } else {
                System.out.println("Invalid input , enter again!! ");
            }
        }
    }

    public void signUp() throws IOException {

        ArrayList<String> gamerInfo = new ArrayList<>();
        String pass;
        String user;
        System.out.println(cls);
        File file = new File("src/loginData.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        System.out.print("please enter your username:\t ");
        user = scanner.nextLine();
        System.out.print("\n pleas enter your password: \t");
        pass = scanner.nextLine();
        bufferedWriter.write(user + "\n" + pass + "\n" + "0\n0\n0\n0\n");

        String check;
        while ((check = bufferedReader.readLine()) != null) {
            gamersInfo.add(check);

        }


    }

    public void login() throws IOException {

        String pass;
        String user;
        System.out.println(cls);
        File file = new File("src/loginData.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        System.out.print("please enter your username:\t ");
        user = scanner.nextLine();
        System.out.print("\n pleas enter your password: \t");
        pass = scanner.nextLine();
        if (checkExist(user, pass) == true) {
            String check;
            while ((check = bufferedReader.readLine()) != null) {
                gamersInfo.add(check);

            }
        } else {
            System.out.print("\n dont have any account with this user and password!!");
        }


    }

    public boolean checkExist(String user, String pass) throws IOException {
        ArrayList<String> gamerInfo = new ArrayList<>();
        File file = new File("src/loginData.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String check;
        int counter = 0;


        while ((check = bufferedReader.readLine()) != null) {
            gamerInfo.add(check);

        }
        for (int i = 0; i < gamerInfo.size(); i++) {
            if (user.equals(gamerInfo.get(i))) {
                counter++;
            } else if (pass.equals(gamerInfo.get(i)) && counter == 1) {
                indexUser = i - 1;
                return true;
            } else {
                counter = 0;
            }
        }
        return false;
    }


    public static void changeGameInfo(String user, String pass, int num) throws IOException {
        ArrayList<String> gamerInfo = new ArrayList<>();
        File file = new File("src/loginData.txt");
        String check;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        StringBuffer inputBuffer = new StringBuffer();


        // copy text in file in arraylist
        while ((check = bufferedReader.readLine()) != null) {
            gamerInfo.add(check);

        }

        //check and change the arralist and overwrite in file
        int counter = 0;
        int size = 0;
        if (gamerInfo.size() != 0) {

            while (true) {

                if ((gamerInfo.get(size)).equals(user)) {
                    counter++;
                    size++;
                }
                if ((gamerInfo.get(size)).equals(pass) && counter == 1) {
                    counter++;
                    size++;

                } else {
                    counter = 0;
                    size++;
                }
                if (counter == 2) {
                    size += num;
                    String tempp = gamerInfo.get(size);
                    int temp = Integer.parseInt(tempp) + 1;
                    gamerInfo.remove(size);
                    gamerInfo.add(size, String.valueOf(temp));


                    size += (3 - num);
                    tempp = gamerInfo.get(size);
                    temp = Integer.parseInt(tempp) + 1;
                    gamerInfo.remove(size);
                    gamerInfo.add(size, String.valueOf(temp));


                    counter = 0;

                    break;
                }


            }
            Formatter formatter = new Formatter(file);
            for (int i = 0; i < gamerInfo.size(); i++) {
                String creat = gamerInfo.get(i);
                formatter.format("%s\n", creat);
            }
            formatter.close();


        }
    }


//        String check;
//        int line;
//        File file = new File("src/loginData.text");
//
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
//            StringBuffer inputBuffer = new StringBuffer();
//            boolean test = false;
//            int counter=0;
//
//            while ((check = bufferedReader.readLine()) != null) {
//                if (check.equals(user)){
//                    counter++;
//                }
//                if (check.equals(pass)){
//                    counter++;
//                    test = true;
//                    inputBuffer.append("123");
//                }
//                if (counter ==2 ){
//                    if (counter == num){
//                        line = Integer.parseInt(check);
//                        line++;
//                        inputBuffer.append(line);
//                        break;
//                    }
//                    counter++;
//
//                }
//                if (counter == 3){
//                    if(counter == num){
//                        line = Integer.parseInt(check );
//                        line++;
//                        inputBuffer.append(line);
//                        break;
//                    }
//                    counter++;
//                }
//                if (counter == 4){
//                    if(counter == num){
//                        line = Integer.parseInt(check) ;
//                        line++;
//                        inputBuffer.append(line);
//                        break;
//                    }
//                    counter = 0;
//
//                }
//
//
//            }
//            if (test == false){
//              bufferedWriter.write(user + "\n" + pass + "\n");
//              int temp = 1;
//              for (int i = 0; i < 3; i++) {
//                 if (temp == num) {
//                    bufferedWriter.write("1"+"\n");
//                 } else {
//                    bufferedWriter.write("0"+"\n");
//                 }
//                 temp++;
//              }
//              bufferedWriter.write("1"+"\n");
//
//
//           }
//            bufferedWriter.close();
////            bufferedReader.close();
//
//       System.out.println(bufferedReader.readLine());
//        System.out.println(bufferedReader.readLine());
//        System.out.println(bufferedReader.readLine());
//        System.out.println(bufferedReader.readLine());
//        System.out.println(bufferedReader.readLine());
//        System.out.println(bufferedReader.readLine());
//
//        bufferedReader.close();
//
//
//    }
}

