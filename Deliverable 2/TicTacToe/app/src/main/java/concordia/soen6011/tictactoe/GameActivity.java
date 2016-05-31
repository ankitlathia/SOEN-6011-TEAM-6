package concordia.soen6011.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

public class GameActivity extends AppCompatActivity {

    private Button cellBox;
    private TextView playerTurn;
    private TextView playerOneName;
    private TextView playerTwoName;
    private TextView playerOneStatus;
    private TextView playerTwoStatus;

    private Bundle data = null;

    private int GAME_MODE = 0;
    private int GAME_COUNT = 0;
    private int P1_GAME_COUNT = 0;
    private int P2_GAME_COUNT = 0;
    private int winnerPlayer = 0;

    private boolean isWon = false;
    private boolean isOver = false;

    private GamePlay gamePlay = null;
    private PlayerDBAdapter dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_game);

        this.playerTurn = (TextView)findViewById(R.id.playerturn);
        this.playerOneName = (TextView)findViewById(R.id.playeronename);
        this.playerTwoName = (TextView)findViewById(R.id.playertwoname);
        this.playerOneStatus = (TextView)findViewById(R.id.playeronestatus);
        this.playerTwoStatus = (TextView)findViewById(R.id.playertwostatus);

        this.initGameConfig();

    }

    private void initGameConfig(){

        this.data = getIntent().getExtras();

        this.gamePlay = new GamePlay(data.getString("p1_name"),data.getString("p2_name"),data.getString("p1_symbol"),data.getString("p2_symbol"));
        this.playerOneName.setText(String.format("%s: %s",data.getString("p1_name"),data.getString("p1_symbol")));
        this.playerTwoName.setText(String.format("%s: %s",data.getString("p2_name"),data.getString("p2_symbol")));

        this.GAME_MODE = data.getInt("mode");
        this.GAME_COUNT = data.getInt("game_count");
        this.P1_GAME_COUNT = data.getInt("p1_count");
        this.P2_GAME_COUNT = data.getInt("p2_count");

        //by default the player 1 will be the first
        if(data.getInt("p1_count")==data.getInt("p2_count"))
            this.playerTurn.setText(String.format("%s's turn",data.getString("p1_name")));
        else if(data.getInt("p1_count") > data.getInt("p2_count"))
            this.playerTurn.setText(String.format("%s's turn",data.getString("p1_name")));
        else if(data.getInt("p1_count") < data.getInt("p2_count"))
            this.playerTurn.setText(String.format("%s's turn",data.getString("p2_name")));

        this.playerOneStatus.setText(String.format("%s wins: %d", data.getString("p1_name"), data.getInt("p1_count")));
        this.playerTwoStatus.setText(String.format("%s wins: %d", data.getString("p2_name"), data.getInt("p2_count")));

        if(GAME_MODE==GameConfiguration.TOURNAMENT_MODE_THREE || GAME_MODE==GameConfiguration.TOURNAMENT_MODE_FIVE) {
            switch(GAME_COUNT){
                case 1:Toast.makeText(getBaseContext(), R.string.first_game, Toast.LENGTH_SHORT).show();break;
                case 2:Toast.makeText(getBaseContext(), R.string.second_game, Toast.LENGTH_SHORT).show();break;
                case 3:Toast.makeText(getBaseContext(), R.string.last_game, Toast.LENGTH_SHORT).show();break;
                case 4:Toast.makeText(getBaseContext(), R.string.fourth_game, Toast.LENGTH_SHORT).show();break;
                case 5:Toast.makeText(getBaseContext(), R.string.last_game, Toast.LENGTH_SHORT).show();break;
            }
        }
    }

    private void configureGameEndState(){

        String winMsg = "";

        if(GAME_MODE == GameConfiguration.SINGLE_MODE){

            if(this.isOver){
                this.winnerPlayer = GameConfiguration.GAME_OVER;
                winMsg = getResources().getString(R.string.game_over);
            }
            else if(this.isWon){
                if(this.gamePlay.getWhichPlayer()==GameConfiguration.PLAYER_ONE) {
                    this.winnerPlayer = GameConfiguration.PLAYER_ONE;
                    winMsg = String.format("%s Wins", data.getString("p1_name"));
                }
                else{
                    this.winnerPlayer = GameConfiguration.PLAYER_TWO;
                    winMsg = String.format("%s Wins", data.getString("p2_name"));
                }
            }
        }
        else if(GAME_MODE==GameConfiguration.TOURNAMENT_MODE_THREE || GAME_MODE==GameConfiguration.TOURNAMENT_MODE_FIVE){

            if(P1_GAME_COUNT < P2_GAME_COUNT) {
                this.winnerPlayer = GameConfiguration.PLAYER_TWO;
                winMsg = String.format("%s Wins", data.getString("p2_name"));
            }
            else if(P1_GAME_COUNT > P2_GAME_COUNT) {
                this.winnerPlayer = GameConfiguration.PLAYER_ONE;
                winMsg = String.format("%s Wins", data.getString("p1_name"));
            }
            else if(P1_GAME_COUNT == P2_GAME_COUNT & P1_GAME_COUNT > 0 & P2_GAME_COUNT > 0) {
                this.winnerPlayer = GameConfiguration.GAME_DRAW;
                winMsg = getResources().getString(R.string.game_draw);
            }else {
                this.winnerPlayer = GameConfiguration.GAME_OVER;
                winMsg = getResources().getString(R.string.game_over);
            }
        }

        this.playerOneStatus.setText(String.format("%s wins: %d", data.getString("p1_name"), this.P1_GAME_COUNT));
        this.playerTwoStatus.setText(String.format("%s wins: %d", data.getString("p2_name"), this.P2_GAME_COUNT));
        this.playerTurn.setText(winMsg);
        this.gameEnd(winMsg);
    }

    public void boxClick(View v){

        this.cellBox = (Button)findViewById(v.getId());
        if(this.cellBox.getText().toString().isEmpty() && !this.isWon && !this.isOver) {
            switch (this.cellBox.getId()) {
                case R.id.cell00:
                    this.gamePlay.updateGrid(0, 0, this.cellBox, getResources());break;
                case R.id.cell01:
                    this.gamePlay.updateGrid(0, 1, this.cellBox, getResources());break;
                case R.id.cell02:
                    this.gamePlay.updateGrid(0, 2, this.cellBox, getResources());break;
                case R.id.cell10:
                    this.gamePlay.updateGrid(1, 0, this.cellBox, getResources());break;
                case R.id.cell11:
                    this.gamePlay.updateGrid(1, 1, this.cellBox, getResources());break;
                case R.id.cell12:
                    this.gamePlay.updateGrid(1, 2, this.cellBox, getResources());break;
                case R.id.cell20:
                    this.gamePlay.updateGrid(2, 0, this.cellBox, getResources());break;
                case R.id.cell21:
                    this.gamePlay.updateGrid(2, 1, this.cellBox, getResources());break;
                case R.id.cell22:
                    this.gamePlay.updateGrid(2, 2, this.cellBox, getResources());break;
            }

            this.checkGameState();
        }
    }

    public void checkGameState(){

        this.isWon = this.gamePlay.checkWinner();
        if (!this.isWon) {
            this.isOver = this.gamePlay.isGameOver();
            if (this.isOver) {
                if((GAME_MODE==GameConfiguration.TOURNAMENT_MODE_THREE && GAME_COUNT < GameConfiguration.GAME_COUNT_THREE) || (GAME_MODE == GameConfiguration.TOURNAMENT_MODE_FIVE && GAME_COUNT < GameConfiguration.GAME_COUNT_FIVE)) {
                    this.GAME_COUNT++;
                    this.repeatGame();
                }
                else
                    this.configureGameEndState();
            } else {
                this.gamePlay.swapPlayerTurn(this.playerTurn);
            }
        } else {

            if (this.gamePlay.getWhichPlayer() == GameConfiguration.PLAYER_ONE) this.P1_GAME_COUNT++;
            else if (this.gamePlay.getWhichPlayer() == GameConfiguration.PLAYER_TWO) this.P2_GAME_COUNT++;

            if((GAME_MODE==GameConfiguration.TOURNAMENT_MODE_THREE && GAME_COUNT < GameConfiguration.GAME_COUNT_THREE) || (GAME_MODE == GameConfiguration.TOURNAMENT_MODE_FIVE && GAME_COUNT < GameConfiguration.GAME_COUNT_FIVE)) {
                this.GAME_COUNT++;
                this.repeatGame();
            }
            else
                this.configureGameEndState();
        }

    }

    private void repeatGame(){
        this.setGameConfig(this.GAME_COUNT, this.P1_GAME_COUNT, this.P2_GAME_COUNT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menunew:
                this.setGameConfig(1,0,0);
                return true;
            case R.id.menureset:
                this.resetGame();
                Toast.makeText(getApplicationContext(), R.string.game_reset_msg, Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuexit:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void resetGame(){

        Button cell = (Button)findViewById(R.id.cell00);
        cell.setText("");
        cell = (Button)findViewById(R.id.cell01);
        cell.setText("");
        cell = (Button)findViewById(R.id.cell02);
        cell.setText("");
        cell = (Button)findViewById(R.id.cell10);
        cell.setText("");
        cell = (Button)findViewById(R.id.cell11);
        cell.setText("");
        cell = (Button)findViewById(R.id.cell12);
        cell.setText("");
        cell = (Button)findViewById(R.id.cell20);
        cell.setText("");
        cell = (Button)findViewById(R.id.cell21);
        cell.setText("");
        cell = (Button)findViewById(R.id.cell22);
        cell.setText("");

        this.gamePlay.resetGame();
    }

    private void setGameConfig(int count, int p1Count, int p2Count){

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("mode", GAME_MODE);
        intent.putExtra("game_count", count);
        intent.putExtra("p1_count", p1Count);
        intent.putExtra("p2_count", p2Count);
        intent.putExtra("p1_name", this.data.getString("p1_name"));
        intent.putExtra("p2_name", this.data.getString("p2_name"));
        intent.putExtra("p1_symbol", this.data.getString("p1_symbol"));
        intent.putExtra("p2_symbol", this.data.getString("p2_symbol"));
        startActivity(intent);
        finish();
    }

    private void gameEnd(String winMsg){

        String _gameType = "";

        this.dbHelper = new PlayerDBAdapter(getBaseContext());
        this.dbHelper.open();

        switch(this.GAME_MODE){
            case GameConfiguration.SINGLE_MODE: _gameType = getResources().getString(R.string.game_mode_single);break;
            case GameConfiguration.TOURNAMENT_MODE_THREE: _gameType = getResources().getString(R.string.game_mode_three);break;
            case GameConfiguration.TOURNAMENT_MODE_FIVE: _gameType = getResources().getString(R.string.game_mode_five);break;
        }

        if(this.winnerPlayer==GameConfiguration.PLAYER_ONE)
            this.dbHelper.insertPlayerStat(this.data.getString("p1_name"), _gameType, getResources().getString(R.string.game_win));
        else
            this.dbHelper.insertPlayerStat(this.data.getString("p1_name"), _gameType, getResources().getString(R.string.game_loss));

        if(this.winnerPlayer == GameConfiguration.PLAYER_TWO)
            this.dbHelper.insertPlayerStat(this.data.getString("p2_name"), _gameType, getResources().getString(R.string.game_win));
        else
            this.dbHelper.insertPlayerStat(this.data.getString("p2_name"), _gameType, getResources().getString(R.string.game_loss));

        this.dbHelper.close();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        if(this.winnerPlayer==GameConfiguration.GAME_DRAW || this.winnerPlayer==GameConfiguration.GAME_OVER)
            alertDialog.setTitle(R.string.oops);
        else
            alertDialog.setTitle(R.string.congratulations);

        alertDialog.setMessage(winMsg);

        alertDialog.setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });

        alertDialog.setNegativeButton(R.string.game_new, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                setGameConfig(1,0,0);
            }
        });

        alertDialog.show();
    }
}
