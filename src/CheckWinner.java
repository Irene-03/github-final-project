import java.util.ArrayList;

public class CheckWinner {
    private String cell = null;
    private int index;
    private int sum = 0;
    private ArrayList<String> gameInfo;


    /**
     * check the every possible set to win by 4 function
     * @param gameInfo the array have our information of gamer
     * @param sideSize the size of table
     * @return return winner symbol or free that mean anyone win or not
     */
    public String checkWinnerStatus(ArrayList<String> gameInfo ,int sideSize , int wonCondition){
        this.gameInfo = gameInfo;
        String result;
        if (!(result =checkRow(sideSize ,wonCondition)).equals("free")){
            return result;
        }else if(!(result =checkColumn(sideSize,wonCondition)).equals("free")){
            return result;
        } else if (!(result =checkMainDiagonal(sideSize,wonCondition)).equals("free")) {
            return result;
        }else if(!(result=checkSubDiagonal(sideSize,wonCondition)).equals("free")){
            return result;
        }else {return "free";}
    }

    /**
     * check the row of table to find won condition number equal amount
     * @param sideSize size of table
     * @return winner symbol or free
     */
    public String checkRow(int sideSize,int wonCondition) {
        for (int i = 0; i < sideSize; i++) {
            cell = null;
            sum = 0;

            for (int j = 0; j < sideSize; j++) {

                index = sideSize * i +j;

                if (gameInfo.get(index).equals(cell)) {
                    sum++;
                } else {
                    cell = gameInfo.get(index);
                    sum = 1;
                }

                if (!cell.equals("free") && sum == wonCondition) {
                    return cell;
                }
            }
        }
        return "free";
    }

    /**
     * check the column of table to find won condition number equal amount
     * @param sideSize size of table
     * @return winner symbol or free
     */
    public String checkColumn(int sideSize ,int wonCondition) {
        for (int i = 0; i < sideSize; i++) {
            cell = null;
            sum = 0;

            for (int j = 0; j < sideSize; j++) {
                index = sideSize * j + i;

                if (gameInfo.get(index).equals(cell)) {
                    sum++;
                } else {
                    cell = gameInfo.get(index);
                    sum = 1;
                }

                if (!cell.equals("free") && sum == wonCondition) {
                    return cell;
                }
            }
        }
        return "free";
    }

    /**
     * check the main diagonal of table to find won condition number equal amount
     * @param sideSize size of table
     * @return winner symbol or free
     */
    public String checkMainDiagonal(int sideSize, int wonCondition) {
        for (int i = 2-sideSize; i <= sideSize-2; i++) {
            cell = null;
            sum =0;
            for (int j = 0; j < sideSize; j++) {
                for (int k = 0; k < sideSize; k++) {
                    if (j - k != i) {
                        continue;
                    }
                    index = sideSize * j + k;
                    if (gameInfo.get(index).equals(cell)) {
                        sum++;
                    } else {
                        cell = gameInfo.get(index);
                        sum = 1;
                    }
                    if (!cell.equals("free") && sum == wonCondition) {
                        return cell;
                    }
                }
            }
        }
        return "free";
    }
    /**
     * check the sub diagonal of table to find won condition number equal amount
     * @param sideSize size of table
     * @return winner symbol or free
     */
    public String checkSubDiagonal(int sideSize ,int wonCondition) {


        for (int i = 1; i <= (sideSize *2 -3); i++) {
            sum = 0;
            cell = null;
            for (int j = 0; j < sideSize; j++) {
                for (int k = 0; k < sideSize; k++) {
                    if (j + k != i) {
                        continue;
                    }
                    index = sideSize * j + k;
                    if (gameInfo.get(index).equals(cell)) {
                        sum++;
                    } else {
                        cell = gameInfo.get(index);
                        sum = 1;
                    }
                    if (!cell.equals("free") && sum == wonCondition) {
                        return cell;
                    }

                }
            }
        }
        return "free";
    }
}
