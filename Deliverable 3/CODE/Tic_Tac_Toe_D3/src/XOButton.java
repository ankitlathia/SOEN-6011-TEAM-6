import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener {
	static ImageIcon X;

	static ImageIcon O;

	JPanel p = new JPanel();
	static JButton buttons[] = new JButton[9];
	static JButton Pressed;

	private LogicalArray GArray; // Class with Array
	static boolean Player = false;
	private int PlayerMark = 1;
	int arrayX = 0;
	int arrayY = 0;
	static Random rnd=new Random();
	static int cnt;

	static int val;

	static int a;

	static int b;

	static int c=1;

	static int d=1;

	static int type=1;

	static int vs=1;
	static boolean turn=true;
    static char let;
    static String pl1="You";

	static String pl2="Computer";
	
	 // 0:nothing 1:X 2:O
	 

	public XOButton() {
		// creates the panel

		p.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton("");
			p.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		add(p);

		SetGameVariables();
		//ResetGame();

		X = new ImageIcon(this.getClass().getResource("X.png"));
		O = new ImageIcon(this.getClass().getResource("O.png"));
		
		this.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		Pressed = (JButton) e.getSource();
		if(Menu.menuPlayersPlayers.isSelected())
		playPlayers();
		else
		{
			for (int i = 0; i < 9; i++) {
				if (Pressed == buttons[i]) {
					 if(play(i)&&turn==true)
				            AI.compplay(val);
				}
			}
		}
		
			

	}
	
	public void playPlayers() {
		
		
		for (int i = 0; i < 9; i++) {
			if (Pressed == buttons[i]) {

				SetXOPosition(Pressed, Player); // change position text to "X" or "O"
				GArray.ArrayInitialize(i, PlayerMark); //Initialize game array
				JButtonPositionDisabler(buttons[i], Player); // Disable position of click
				PlayerMark = SwithPlayersTurn(Player); // Switch Turns of players
				
			}
			
		}
	}
	 static boolean play(int pos)
	    {
	        if(LogicalArray.GArray[pos]==0)
	        {
	        	XOButton.a=XOButton.c;
	        	XOButton.b=XOButton.c;
	        	XOButton.c=pos;
	        	flip();
	        	SetXOPositionComp(buttons[pos], let);
	            LogicalArray.GArray[pos]=val;
	            JButtonPositionDisablerComp(buttons[pos], let);
	            
	            LogicalArray.checkwinComVsPly(pos,LogicalArray.GArray[pos]);
	            return true;
	        }
	        else
	            return false;
	    }

	public void SetGameVariables() {

		GArray = new LogicalArray(this);

		DefaultContentPosition();
		DisableAllPositions(true);
		Player = false; // player x
		PlayerMark = 1; // default Value x

	}

	public void ResetGame() {

		SetGameVariables(); // Reset the Game
		cnt=0;
        val=1;
        let='\u0000';
		TicTacToe.label2.setText(TicTacToe.player.get(0) + "'s turn");

	}

	public int SwithPlayersTurn(boolean lastTrun) {

		System.out.println();

		if (lastTrun == true) {
			Player = false;
			TicTacToe.label2.setText(TicTacToe.player.get(0) + "'s turn");
			cnt++;
			return 1;

		} else if (lastTrun == false) {
			Player = true;
			TicTacToe.label2.setText(TicTacToe.player.get(2) + "'s turn");
			cnt++;
			return 2;
		} else {
			return 3;
		}

	}
	
	 static void flip()
	    {
	        if(let == 'X')
	        {
	            let = 'O';
	            val=4;
	            cnt++;
	        }
	        else
	        {
	            let = 'X';
	            val=1;
	            cnt++;
	        }
	    }

	public static void JButtonPositionDisabler(JButton Btn, boolean Play) {

		Btn.setEnabled(false);
		if (Play == true) { 
			
			Btn.setDisabledIcon(O);
			
		} else if (Play == false) {
			
			Btn.setDisabledIcon(X);
			
		}

	}
	
	public static void JButtonPositionDisablerComp(JButton Btn, char Play) {

		Btn.setEnabled(false);
		if (Play == 'X') { 
			
			Btn.setDisabledIcon(X);
			
		} else if (Play == 'O') {
			
			Btn.setDisabledIcon(O);
			
		}

	}

	public static void DisableAllPositions(boolean disable) {

		for (int i = 0; i < 9; i++) {

			buttons[i].setEnabled(disable);
		}

	}

	public static void SetXOPosition(JButton Btn, boolean Play) {

		if (Play == true) {
			Btn.setIcon(O);
		} else if (Play == false) {
			Btn.setIcon(X);
		}

	}
	
	public static void SetXOPositionComp(JButton Btn, char Play) {

		if (Play == 'X') {
			Btn.setIcon(X);
		} else if (Play == 'O') {
			Btn.setIcon(O);
		}

	}

	public static void DefaultContentPosition() {

		for (int i = 0; i < 9; i++) {

			buttons[i].setIcon(null);
		}

	}

}