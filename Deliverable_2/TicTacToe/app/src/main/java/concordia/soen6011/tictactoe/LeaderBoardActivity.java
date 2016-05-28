package concordia.soen6011.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.widget.ListView;

public class LeaderBoardActivity extends AppCompatActivity {

    private PlayerDBAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_leaderboard);

        this.connectDB();
        this.showPlayerStats();

    }

    private void connectDB(){
        this.dbHelper = new PlayerDBAdapter(this);
        this.dbHelper.open();
    }

    private void showPlayerStats(){

        Cursor cursor = dbHelper.getPlayersStats();

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.players_stats,
                cursor,
                new String[] {
                        PlayerDBAdapter.Stat_PLAYERNAME,
                        PlayerDBAdapter.Stat_GAMETYPE,
                        PlayerDBAdapter.Stat_STATUS
                },
                new int[]{
                        R.id.playername,
                        R.id.gametype,
                        R.id.gamestatus
                },
                0);

        ListView leaderboardList = (ListView) findViewById(R.id.leaderboardlist);
        leaderboardList.setAdapter(dataAdapter);
    }
}
