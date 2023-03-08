import jdk.jshell.spi.SPIResolutionException;

import java.util.Objects;

public class CheckWinner {
    private String cell = null;
    private int index;
    private int sum = 0;
    private String[] gameInfo;
    private int sideSize;

    public CheckWinner(String[] gameInfo ) {
        this.gameInfo = gameInfo;
//        this.sideSize = sideSize;
    }

    public String checkWinnerStatus(String[] gameInfo){
        this.gameInfo = gameInfo;
        String result = "free";
        if (!(result =checkRow()).equals("free")){
            return result;
        }else if(!(result =checkColumn()).equals("free")){
            return result;
        } else if (!(result =checckMainDiagonal()).equals("free")) {
            return result;
        }else if(!(result=checkSubDiagonal()).equals("free")){
            return result;
        }else {return "free";}
    }
    public String checkRow() {
        for (int i = 0; i < 4; i++) {
            cell = null;
            sum = 0;

            for (int j = 0; j < 4; j++) {

                index = 4 * i + j;

                if (gameInfo[index].equals(cell)) {
                    sum++;
                } else {
                    cell = gameInfo[index];
                    sum = 1;
                }

                if (!cell.equals("free") && sum == 3) {
                    return cell;
                }
            }
        }
        return "free";
    }

    public String checkColumn() {
        for (int i = 0; i < 4; i++) {
            cell = null;
            sum = 0;

            for (int j = 0; j < 4; j++) {
                index = 4 * j + i;

                if (gameInfo[index].equals(cell)) {
                    sum++;
                } else {
                    cell = gameInfo[index];
                    sum = 1;
                }

                if (!cell.equals("free") && sum == 3) {
                    return cell;
                }
            }
        }
        return "free";
    }

    public String checckMainDiagonal() {
        for (int i = -2; i <= 2; i++) {
            cell = null;
            sum =0;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (j - k != i) {
                        continue;
                    }
                    index = 4 * j + k;
                    if (gameInfo[index].equals(cell)) {
                        sum++;
                    } else {
                        cell = gameInfo[index];
                        sum = 1;
                    }
                    if (!cell.equals("free") && sum == 3) {
                        return cell;
                    }
                }
            }
        }
        return "free";
    }
    public String checkSubDiagonal() {


        for (int i = 1; i < 6; i++) {
            sum = 0;
            cell = null;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (j + k != i) {
                        continue;
                    }
                    index = 4 * j + k;
                    if (gameInfo[index].equals(cell)) {
                        sum++;
                    } else {
                        cell = gameInfo[index];
                        sum = 1;
                    }
                    if (!cell.equals("free") && sum == 3) {
                        return cell;
                    }

                }
            }
        }
        return "free";
    }
}
