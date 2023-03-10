import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Function object = new Function();

    //const variable
    private final String cls = "\033[H\033[2J";
    private final String blueX = "\u001B[34m" + " X" + "\u001B[0m";
    private final String greenO = "\u001B[32m" + " O" + "\u001B[0m";
    private final ArrayList<String> gameBoard = new ArrayList<>();
    private final ArrayList<String> selfBoard = new ArrayList<>();
    private final ArrayList<Integer> blockArray = new ArrayList<>();
    private final ArrayList<Integer> randomBlock = new ArrayList<>();
    private final int sequencing = 0;
    private final CheckWinner obj = new CheckWinner();
    private final int[] infoArray = new int[3];
    public int sideSize;
    public int blockCell;
    public int wonCondition;
    public Setting settingObject = new Setting(infoArray);


    /**
     * a constructor that just call teh menu function
     */
    public Menu() throws IOException {
        menu();
    }


    /**
     * print menu and get the input of player choice
     */
    public void menu() throws IOException {

        int menuInput = 0;

        while (menuInput != 4) {
            //clear the screen
            System.out.print(cls);

            //print menu
            printMenu();

            //input
            menuInput = scanner.nextInt();


            // check the input
            menuInput = checkMenuInput(menuInput);

        }
    }

    private int checkMenuInput(int menuInput) throws IOException {

        settingObject.readSetting(infoArray);
        sideSize = infoArray[0];
        blockCell = infoArray[1];
        wonCondition = infoArray[2];

        switch (menuInput) {
            case 1 -> {
                //play whit AI
                System.out.println(cls);
                aiPlayer();
                menuInput = object.playAgain();
                gameBoard.clear();
                selfBoard.clear();
                blockArray.clear();
                randomBlock.clear();
            }
            case 2 -> {
                //two player
                System.out.println(cls);
                twoPlayer();
                menuInput = object.playAgain();
                gameBoard.clear();
                selfBoard.clear();
                blockArray.clear();
                randomBlock.clear();
            }
            case 3 -> {
                System.out.println(cls);
                settingMenu();
            }
            default -> {
                System.out.println(cls);
                System.out.println("wrong command , enter another number !!");
            }
        }
        return menuInput;
    }

    private void settingMenu() throws IOException {
        if (sideSize == 4 || blockCell == 3 || wonCondition == 3) {


            System.out.println("The settings are on default mode");
            System.out.printf("Side Size : " + sideSize +
                    "\nblock cell numbers : " + blockCell +
                    "\nwon condition cells : " + wonCondition + "\n\n\n");
            System.out.print("""
                    do you wanna change that ?
                    1 -Yes
                    2- No
                    3- Go back to default mode
                    """);
        } else {
            System.out.println("Last saved settings :");
            System.out.printf("Side Size : " + sideSize +
                    "\nblock cell numbers : " + blockCell +
                    "\nwon condition cells : " + wonCondition + "\n\n\n");
            System.out.print("""
                    do you wanna change that ?
                    1 -Yes 
                    2- No
                    3- Go back to default mode
                    """);
        }

        int selection = scanner.nextInt();
        while (true){
            if (selection == 1) {
                newSettingSelection();
                settingObject.changeSetting(sideSize, blockCell, wonCondition);
                settingObject.readSetting(infoArray);
                break;
            } else if(selection == 2) {
                settingObject.readSetting(infoArray);
                break;
            }else if (selection == 3){
                settingObject.defaultSetting();
                settingObject.readSetting(infoArray);
                break;
            }else {
                System.out.println("incorrect selection , enter another :\t");
                selection = scanner.nextInt();
            }}

    }

    private void newSettingSelection() {
        System.out.print("Enter side size of board :\t");
        int enter = scanner.nextInt();
        sideSize = checkNewSettingSelection(enter);

        System.out.print("Enter block cell number of board :\t");
        enter = scanner.nextInt();
        blockCell = checkNewSettingSelection(enter);

        System.out.print("Enter won condition number of board :\t");
        enter = scanner.nextInt();
        wonCondition = checkNewSettingSelection(enter);


    }

    private int checkNewSettingSelection(int enter) {
        while (true) {
            if (enter >= 0 && enter <= 100) {
                return enter;
            } else {
                System.out.println(" Incorrect Selection , Enter another : ");
                enter = scanner.nextInt();
            }
        }
    }


    private static void printMenu() {
        System.out.println("How do you wanna play the game ?\n");
        System.out.println("1- one player \n");
        System.out.println("2- two player \n");
        System.out.println("3- setting \n");
        System.out.println("4- exit \n");
    }


    /**
     * call the function when two player play the game
     */
    public void twoPlayer() {

        //making the arraylist
        for (int i = 0; i < sideSize * sideSize; i++) {
            randomBlock.add(i + 1);
        }
        //make the game boards
        object.randomBlock(blockArray, randomBlock, blockCell);
        object.makeBoard(gameBoard, selfBoard, blockArray, sideSize);


        // check the equal
        startGamingTwoPlayer(gameBoard, selfBoard, randomBlock, sequencing);
    }

    private void startGamingTwoPlayer(ArrayList<String> gameBoard, ArrayList<String> selfBoard, ArrayList<Integer> randomBlock, int sequencing) {
        int player;
        String result;
        for (int i = 0; i < sideSize * sideSize - blockCell + 1; i++) {
            //clear the screen
            if (i == sideSize * sideSize - blockCell) {
                System.out.println(cls);
                System.out.println(" Equal result!!");
                object.printGameBoard(gameBoard, sideSize);
                break;

            }
            System.out.println(cls);
            //print the game board
            object.printGameBoard(gameBoard, sideSize);

            //input
            boolean testInput;

            turnTwoPlayer(sequencing);
            player = scanner.nextInt();

            //check the block cell

            while (true) {

                testInput = object.checkInput(selfBoard, player, randomBlock, sideSize);


                if (testInput) {
                    break;
                } else {
                    turnTwoPlayer(sequencing);
                    player = scanner.nextInt();

                }
            }


            if (sequencing % 2 == 0) {
                object.changeBoard(gameBoard, selfBoard, player, blueX);
            } else {
                object.changeBoard(gameBoard, selfBoard, player, greenO);
            }

            sequencing++;

            //check the winner status

            result = obj.checkWinnerStatus(selfBoard, sideSize, wonCondition);

            if (result.equals(blueX)) {
                resultPlayer(gameBoard, "player one win !!\n\n");
                break;

            } else if (result.equals(greenO)) {
                resultPlayer(gameBoard, "player two win !!\n\n");
                break;
            }

        }
    }

    private void resultPlayer(ArrayList<String> gameBoard, String resultPlayer) {
        System.out.println(cls);
        System.out.print(resultPlayer);
        object.printGameBoard(gameBoard, sideSize);
    }

    private static void turnTwoPlayer(int sequencing) {
        if (sequencing % 2 == 0) {
            System.out.print("Player one :\t");
        } else {
            System.out.print(" player two :\t");
        }
    }

    /**
     * call the function when someone play whit AI
     */
    public void aiPlayer() {

        //making the arraylist
        for (int i = 0; i < sideSize * sideSize; i++) {
            randomBlock.add(i + 1);
        }
        //make the game boards
        object.randomBlock(blockArray, randomBlock, blockCell);
        object.makeBoard(gameBoard, selfBoard, blockArray, sideSize);

        // check the equal
        startGamingAiPlayer(gameBoard, selfBoard, randomBlock, sequencing);

    }

    private void startGamingAiPlayer(ArrayList<String> gameBoard, ArrayList<String> selfBoard, ArrayList<Integer> randomBlock, int sequencing) {
        String result;
        int player;
        int ai;
        for (int i = 0; i < sideSize * sideSize - blockCell + 1; i++) {

            if (i == sideSize * sideSize - blockCell) {
                System.out.println(cls);
                System.out.println(" Equal result!!");
                object.printGameBoard(gameBoard, sideSize);
                break;
            }

            System.out.println(cls);
            //print the game board
            object.printGameBoard(gameBoard, sideSize);

            //input
            if (sequencing % 2 == 0) {
                boolean testInput;
                System.out.print(" you :\t");
                player = scanner.nextInt();


                //check the block cell
                while (true) {

                    testInput = object.checkInput(selfBoard, player, randomBlock, sideSize);
                    if (testInput) {
                        break;
                    } else {
                        System.out.print(" you :\t");
                        player = scanner.nextInt();
                    }
                }
                //change the game boards
                object.changeBoard(gameBoard, selfBoard, player, blueX);
                randomBlock.remove((Integer) player);
            } else {
                ai = object.aiChoice(randomBlock);
                object.changeBoard(gameBoard, selfBoard, ai, greenO);
            }
            sequencing++;


            //check the winner status
            result = obj.checkWinnerStatus(selfBoard, sideSize, wonCondition);

            if (result.equals(blueX)) {
                resultPlayer(gameBoard, "   you win !!\n\n");
                break;
            } else if (result.equals(greenO)) {
                resultPlayer(gameBoard, "    AI win !!\n\n");
                break;
            }


        }
    }

}



