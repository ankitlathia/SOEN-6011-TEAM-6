package concordia.soen6011.tictactoe;

import android.content.res.Resources;
import android.widget.Button;
import android.widget.TextView;

public class GamePlay {

    private final int ROWS = 3;
    private final int COLS = 3;
    private final int playerOne = 1;
    private final int playerTwo = 2;
    private String playerOneName = "";
    private String playerTwoName = "";
    private String playerOneSymbol = "";
    private String playerTwoSymbol = "";
    private String[][] grid = new String[3][3];
    private int whichPlayer = 1; //1 = player_one & 2 = player_two

    public GamePlay(String playerOneName, String playerTwoName, String playerOneSymbol, String playerTwoSymbol){

        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneSymbol = playerOneSymbol;
        this.playerTwoSymbol = playerTwoSymbol;

        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++)
                this.grid[i][j] = "";
        }
    }

    public int getWhichPlayer(){
        return this.whichPlayer;
    }

    public void swapPlayerTurn(TextView playerTurn){

        if(this.whichPlayer==playerOne) {
            this.whichPlayer = playerTwo;
            playerTurn.setText(String.format("%s's turn",this.playerTwoName));
        }
        else {
            this.whichPlayer = playerOne;
            playerTurn.setText(String.format("%s's turn",this.playerOneName));
        }
    }

    public boolean checkWinner(){

        if (this.grid[0][0].equals(this.grid[1][1]) && this.grid[1][1].equals(this.grid[2][2]) && !this.grid[0][0].isEmpty() && !this.grid[1][1].isEmpty() && !this.grid[2][2].isEmpty())
            return true;

        if (this.grid[2][0].equals(this.grid[1][1]) && this.grid[1][1].equals(this.grid[0][2]) && !this.grid[2][0].isEmpty() && !this.grid[1][1].isEmpty() && !this.grid[2][0].isEmpty())
            return true;

        for (int i = 0; i < COLS; i++) {

            if (this.grid[i][0].equals(this.grid[i][1]) && this.grid[i][1].equals(this.grid[i][2]) && !this.grid[i][0].isEmpty())
                return true;

            if (this.grid[0][i].equals(this.grid[1][i]) && this.grid[1][i].equals(this.grid[2][i]) && !this.grid[0][i].isEmpty())
                return true;
        }

        return false;
    }

    public void updateGrid(int row, int col, Button box, Resources res){

        if(this.whichPlayer==playerOne) {
            this.grid[row][col] = this.playerOneSymbol;
            box.setText(this.playerOneSymbol);

            if(this.playerOneSymbol.equals(res.getString(R.string.symbol_cross)))
                box.setTextColor(res.getColor(R.color.green));
            else
                box.setTextColor(res.getColor(R.color.orange));
        }
        else{
            this.grid[row][col] = this.playerTwoSymbol;
            box.setText(this.playerTwoSymbol);
            if(this.playerTwoSymbol.equals(res.getString(R.string.symbol_cross)))
                box.setTextColor(res.getColor(R.color.green));
            else
                box.setTextColor(res.getColor(R.color.orange));
        }

    }

    public boolean isGameOver(){

        boolean _isOver = true;

        for(int i=0;i<ROWS && _isOver;i++){
            for(int j=0;j<COLS && _isOver;j++)
                if(this.grid[i][j].isEmpty()) {
                    _isOver = false;
                }
        }

        return _isOver;
    }

    public void resetGame(){

        for(int i=0; i < ROWS; i++){
            for(int j=0; j < COLS; j++)
                this.grid[i][j] = "";
        }

    }

}
