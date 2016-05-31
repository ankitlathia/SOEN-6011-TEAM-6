package concordia.soen6011.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import java.util.*;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private PlayerDBAdapter dbHelper;
    private List<Player> players = null;
    private Switch playerOneSymbol = null;
    private Switch playerTwoSymbol = null;
    private EditText playerOneName = null;
    private EditText playerTwoName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_settings);
        playerOneSymbol = (Switch) findViewById(R.id.playeroneswitch);
        playerTwoSymbol = (Switch) findViewById(R.id.playertwoswitch);

        playerOneName = (EditText) findViewById(R.id.playeronename);
        playerTwoName = (EditText) findViewById(R.id.playertwoname);

        this.getPlayerSettings();

        this.bindEvents();
    }

    private void getPlayerSettings(){

        this.dbHelper = new PlayerDBAdapter(this);
        this.dbHelper.open();

        this.players = this.dbHelper.getPlayers();

        this.playerOneName.setText(this.players.get(0).getName());
        this.playerTwoName.setText(this.players.get(1).getName());

        if(this.players.get(0).getSymbol()==1) {
            this.playerOneSymbol.setChecked(true);
            this.playerTwoSymbol.setChecked(true);
        }
        else if(players.get(0).getSymbol()==0) {
            this.playerOneSymbol.setChecked(false);
            this.playerTwoSymbol.setChecked(false);
        }
    }

    private void bindEvents(){

        this.playerOneSymbol.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    playerTwoSymbol.setChecked(true);
                }else{
                    playerTwoSymbol.setChecked(false);
                }

            }
        });

        this.playerTwoSymbol.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    playerOneSymbol.setChecked(true);
                }else{
                    playerOneSymbol.setChecked(false);
                }

            }
        });
    }

    public void saveClick(View v){

        if(playerOneSymbol.isChecked()) {
            dbHelper.updatePlayer(players.get(0).getID(), playerOneName.getText().toString(), 1);
            dbHelper.updatePlayer(players.get(1).getID(), playerTwoName.getText().toString(), 1);
        }
        else{
            dbHelper.updatePlayer(players.get(0).getID(), playerOneName.getText().toString(), 0);
            dbHelper.updatePlayer(players.get(1).getID(), playerTwoName.getText().toString(), 0);
        }

        Toast.makeText(this, R.string.settings_save_msg, Toast.LENGTH_SHORT).show();

        finish();
    }
}
