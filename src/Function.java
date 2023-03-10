import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Function {
    static Scanner scanner = new Scanner(System.in);

    public final String blueX = "\u001B[34m" + " X" + "\u001B[0m";
    public final String greenO = "\u001B[32m" + " O" + "\u001B[0m";
    public final String redBlock = "\u001B[31m" + " #" + "\u001B[0m";


    /**
     * get three cell to block it before the game
     *
     * @param blockArray  get number of cell that we want to block it
     * @param randomBlock an array list that we need to get random and after that remove the choice to not choose again
     */
    public void randomBlock(ArrayList<Integer> blockArray, ArrayList<Integer> randomBlock, int blockCellNumber) {
        Random rand = new Random();
        int index;
        for (int i = 0; i < blockCellNumber; i++) {
            index = rand.nextInt(randomBlock.size() - 1);

            blockArray.add(randomBlock.get(index));

            randomBlock.remove(index);
        }


    }


    /**
     * make our boards array to show and check the gamer status
     *
     * @param gameBoard  the array have something to show to gamer
     * @param selfBoard  the array have some information for check ing the winner status
     * @param blockArray the array have some amount that we want block it in game board and self board
     */
    public void makeBoard(ArrayList<String> gameBoard, ArrayList<String> selfBoard, ArrayList<Integer> blockArray , int sideSize) {
        for (int i = 0; i < sideSize*sideSize; i++) {
            gameBoard.add(Integer.toString(i + 1));
            selfBoard.add("free");
        }
        for (int x : blockArray) {
            gameBoard.set(x - 1, redBlock);
            selfBoard.set(x - 1, redBlock);
        }
    }


    /**
     * change our array to show and to compare
     *
     * @param gameBoard board for show to players
     * @param selfBoard board for comparing winner status
     * @param player    cell's player choice
     * @param symbol    determine whose choice is
     */
    public void changeBoard(ArrayList<String> gameBoard, ArrayList<String> selfBoard, int player, String symbol) {
        gameBoard.set(player - 1 ,symbol) ;
        selfBoard.set(player - 1 ,symbol) ;
    }


    /**
     * this function check player input and if its blocked or choose already say input again
     *
     * @param selfBoard the board build for comparing
     * @param player    cell of player choices
     * @return return if player choice is correct or not as boolean type
     */
    public boolean checkInput(ArrayList<String> selfBoard, int player, ArrayList<Integer> randomBlock, int sideSize) {
        if (player > sideSize*sideSize) {
            System.out.println("\u001B[35m" + "Your selection is incorrect!! choose another" + "\u001B[0m");
            return false;
        } else if (selfBoard.get(player - 1).equals(redBlock)) {
            System.out.println("\u001B[35m" + "This cell is blocked !! choose another." + "\u001B[0m");
            return false;
        } else if (selfBoard.get(player - 1).equals(blueX) || Objects.equals(selfBoard.get(player - 1), greenO)) {
            System.out.println("\u001B[35m" + "This cell is already selected !! choose another." + "\u001B[0m");
            return false;
        } else {
            randomBlock.remove((Integer) player);
            return true;
        }
    }


    /**
     * this function get a random as AI choice among the remaining values
     *
     * @param randomBlock this array list includes residual values
     * @return return the AI amount choice
     */
    public int aiChoice(ArrayList<Integer> randomBlock) {

        int index;
        int result;
        Random rand = new Random();
        index = rand.nextInt(randomBlock.size() );
        result = randomBlock.get(index);
        randomBlock.remove(index);

        return result;
    }

    /**
     * just print the game board for player after every changing
     *
     * @param board an array include changed and unchanged equal
     */
    public void printGameBoard(ArrayList<String> board , int sideSize) {

        for (int i = 0; i < sideSize; i++) {
            System.out.printf(" %2s",board.get(i*sideSize));
            for (int j = 1; j < sideSize; j++) {
                System.out.printf("| %2s", board.get(i*sideSize+j));
            }
            System.out.print("\n");
            System.out.print("---");
            for (int j = 1; j < sideSize; j++) {
                System.out.print("|---");
            }
            System.out.println();
        }

//        System.out.printf(" %2s| %2s| %2s| %2s \n", board[0], board[1], board[2], board[3]);
//        System.out.println("---|---|---|---");
//        System.out.printf(" %2s| %2s| %2s| %2s \n", board[4], board[5], board[6], board[7]);
//        System.out.println("---|---|---|---");
//        System.out.printf(" %2s| %2s| %2s| %2s \n", board[8], board[9], board[10], board[11]);
//        System.out.println("---|---|---|---");
//        System.out.printf(" %2s| %2s| %2s| %2s \n\n", board[12], board[13], board[14], board[15]);

    }

    /**
     * after finished the game , player need to choose that want to play again or not
     *
     * @return player choice
     */
    public int playAgain() {
        System.out.println("Do you wanna play again? ");
        System.out.println("1-Yes");
        System.out.println("2- No");
        int menuInput = scanner.nextInt();
        switch (menuInput) {
            case 1 -> {
                return 3;
            }
            case 2 -> {
                return 4;
            }
        }
        return 1;
    }



}
