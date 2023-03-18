import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Function object = new Function();

    //const variable
    private final String cls = "\033[H\033[2J";
    private final String blueX = "\u001B[34m" + " X" + "\u001B[0m";
    private final String greenO = "\u001B[32m" + " O" + "\u001B[0m";
    private final String[] gameBoard = new String[16];
    private final String[] selfBoard = new String[16];
    private final int[] blockArray = new int[3];
    private final ArrayList<Integer> randomBlock = new ArrayList<>();
    private final int sequencing = 0;
    private final CheckWinner obj = new CheckWinner(selfBoard, 4);


    /**
     * a constructor that just call teh menu function
     */
    public Menu() {
        menu();
    }


    /**
     * print menu and get the input of player choice
     */
    public void menu() {

        int menuInput = 0;

        while (menuInput != 3) {
            System.out.print(cls);
            printMenu();
            menuInput = scanner.nextInt();
            menuInput = checkMenuInput(menuInput);

        }
    }

    /**
     * check users input and call suitable function
     *
     * @param menuInput user input after watching menu list
     * @return changed menu input in  other function
     */
    private int checkMenuInput(int menuInput) {
        switch (menuInput) {
            case 1 -> {
                //play whit AI
                System.out.println(cls);
                aiPlayer();
                menuInput = object.playAgain();
            }
            case 2 -> {
                //two player
                System.out.println(cls);
                twoPlayer();
                menuInput = object.playAgain();
            }
            case 3 -> System.out.println(cls);
            default -> {
                System.out.println(cls);
                System.out.println("wrong command , enter another number !!");
            }
        }
        return menuInput;
    }

    /**
     * just print menu list
     */
    private static void printMenu() {
        System.out.println("How do you wanna play the game ?\n");
        System.out.println("1- one player \n");
        System.out.println("2- two player \n");
        System.out.println("3- exit \n");
    }


    /**
     * making boards and arraylist for statr the game
     */
    public void twoPlayer() {

        //making the arraylist
        for (int i = 0; i < 16; i++) {
            randomBlock.add(i + 1);
        }
        //make the game boards
        object.randomBlock(blockArray, randomBlock);
        object.makeBoard(gameBoard, selfBoard, blockArray);


        // check the equal
        startGamingTwoPlayer(gameBoard, selfBoard, randomBlock, sequencing);
    }

    /**
     * call the functions in order for two player state
      * @param gameBoard board that we need to print after every changing
     * @param selfBoard board that we need to change in a certain way for program activity
     * @param randomBlock an array list which includes the allowed options for choose block cell
     * @param sequencing a variable program use for to determine the turn of the players
     */
    private void startGamingTwoPlayer(String[] gameBoard, String[] selfBoard, ArrayList<Integer> randomBlock, int sequencing) {
        int player;
        String result;
        for (int i = 0; i < 14; i++) {

            if (i == 13) {
                System.out.println(cls);
                System.out.println(" Equal result!!");
                object.printGameBoard(gameBoard);
                break;

            }
            System.out.println(cls);
            object.printGameBoard(gameBoard);

            boolean testInput;

            turnTwoPlayer(sequencing);
            player = scanner.nextInt();

            //check the block cell

            while (true) {

                testInput = object.checkInput(selfBoard, player, randomBlock);


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

            result = obj.checkWinnerStatus(selfBoard);

            if (result.equals(blueX)) {
                resultPlayer(gameBoard, "player one win !!\n\n");
                break;

            } else if (result.equals(greenO)) {
                resultPlayer(gameBoard, "player two win !!\n\n");
                break;
            }

        }
    }

    /**
     * just show the game result in the end  with print game board
     * @param gameBoard board that we changed in previous functions
     * @param resultPlayer a string that include the result mean which player is winner
     */
    private void resultPlayer(String[] gameBoard, String resultPlayer) {
        System.out.println(cls);
        System.out.print(resultPlayer);
        object.printGameBoard(gameBoard);
    }

    /**
     * determining the order of play of the players
      * @param sequencing a variable we changed in  startGamingTwoPlayer  function know we want to check it
     */
    private static void turnTwoPlayer(int sequencing) {
        if (sequencing % 2 == 0) {
            System.out.print("Player one :\t");
        } else {
            System.out.print(" player two :\t");
        }
    }

    /**
     * making boards and arraylist for start the game
     */
    public void aiPlayer() {

        //making the arraylist
        for (int i = 0; i < 16; i++) {
            randomBlock.add(i + 1);
        }
        //make the game boards
        object.randomBlock(blockArray, randomBlock);
        object.makeBoard(gameBoard, selfBoard, blockArray);

        // check the equal
        startGamingAiPlayer(gameBoard, selfBoard, randomBlock, sequencing);

    }

    /**
     * call the functions in order for AI player state
      * @param gameBoard board that we need to print after every changing
     * @param selfBoard board that we need to change in a certain way for program activity
     * @param randomBlock an array list which includes the allowed options for choose block cell and AI choice with rando function
     * @param sequencing a variable program use for to determine the turn of the players
     */
    private void startGamingAiPlayer(String[] gameBoard, String[] selfBoard, ArrayList<Integer> randomBlock, int sequencing) {
        String result;
        int player;
        int ai;
        for (int i = 0; i < 14; i++) {

            if (i == 13) {
                System.out.println(cls);
                System.out.println(" Equal result!!");
                object.printGameBoard(gameBoard);
                break;
            }

            System.out.println(cls);
            //print the game board
            object.printGameBoard(gameBoard);

            //input
            if (sequencing % 2 == 0) {
                boolean testInput;
                System.out.print(" you :\t");
                player = scanner.nextInt();


                //check the block cell
                while (true) {

                    testInput = object.checkInput(selfBoard, player, randomBlock);
                    if (testInput) {
                        break;
                    } else {
                        System.out.print(" you :\t");
                        player = scanner.nextInt();
                    }
                }
                //change the game boards
                randomBlock.remove((Integer) player);
                object.changeBoard(gameBoard, selfBoard, player, blueX);
            } else {
                ai = object.aiChoice(randomBlock);
                object.changeBoard(gameBoard, selfBoard, ai, greenO);
            }
            sequencing++;


            //check the winner status
            result = obj.checkWinnerStatus(selfBoard);

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



