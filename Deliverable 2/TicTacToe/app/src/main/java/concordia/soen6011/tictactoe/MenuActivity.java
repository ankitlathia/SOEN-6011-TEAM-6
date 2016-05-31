package concordia.soen6011.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import java.util.*;

public class MenuActivity extends AppCompatActivity {


    private PlayerDBAdapter dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_menu);
        this.dbHelper = new PlayerDBAdapter(this);
    }

    public void menuClick(View v) {

        Intent intent = null;

        if (v.getId() == R.id.single_mode) {
            intent = new Intent(this, GameActivity.class);
            activityConfiguration(intent, GameConfiguration.SINGLE_MODE);
            startActivity(intent);
        }
        else if(v.getId() == R.id.series_three) {
            intent = new Intent(this, GameActivity.class);
            activityConfiguration(intent,GameConfiguration.TOURNAMENT_MODE_THREE);
            startActivity(intent);
        }
        else if(v.getId() == R.id.series_five) {
            intent = new Intent(this, GameActivity.class);
            activityConfiguration(intent,GameConfiguration.TOURNAMENT_MODE_FIVE);
            startActivity(intent);
        }
        else if(v.getId() == R.id.leaderboard) {
            intent = new Intent(this, LeaderBoardActivity.class);
            startActivity(intent);
        }
        else {
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }

    private void activityConfiguration(Intent intent, int mode){

        this.dbHelper.open();
        List<Player> players = this.dbHelper.getPlayers();

        intent.putExtra("mode", mode);
        intent.putExtra("game_count", 1);
        intent.putExtra("p1_count", 0);
        intent.putExtra("p2_count", 0);
        intent.putExtra("p1_name", players.get(0).getName());
        intent.putExtra("p2_name", players.get(1).getName());
        if(players.get(0).getSymbol() == 1) {
            intent.putExtra("p1_symbol", getResources().getString(R.string.symbol_cross));
            intent.putExtra("p2_symbol", getResources().getString(R.string.symbol_zero));
        }
        else{
            intent.putExtra("p1_symbol", getResources().getString(R.string.symbol_zero));
            intent.putExtra("p2_symbol", getResources().getString(R.string.symbol_cross));
        }

        this.dbHelper.close();

    }
}
