import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener {
	ImageIcon X, O;

	JPanel p = new JPanel();
	static JButton buttons[] = new JButton[9];

	private LogicalArray GArray; // Class with Array
	private boolean Player = false;
	private int PlayerMark = 1;
	int arrayX = 0;
	int arrayY = 0;
	/*
	 * 0:nothing 1:X 2:O
	 */

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

		X = new ImageIcon(this.getClass().getResource("X.png"));
		O = new ImageIcon(this.getClass().getResource("O.png"));
		this.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		JButton Pressed = (JButton) e.getSource();

		for (int i = 0; i < 9; i++) {
			if (Pressed == buttons[i]) {

				SetXOPosition(Pressed, Player); // change position text to "X"
												// or "O"
				GArray.ArrayInitialize(i, PlayerMark);
				JButtonPositionDisabler(buttons[i], Player); // Disable position
																// of click
				PlayerMark = SwithPlayersTurn(Player); // Switch Turns of
														// players

			}

		}

	}

	public void SetGameVariables() {

		GArray = new LogicalArray(this);

		DefaultContentPosition();
		DisableAllPositions(true);

		Player = false; // default player 1
		PlayerMark = 1; // default Value x

	}

	public void ResetGame() {

		SetGameVariables(); // Reset the Game
		TicTacToe.label2.setText(TicTacToe.player.get(0) + "'s turn");

	}

	public int SwithPlayersTurn(boolean last) {

		System.out.println();

		if (last == true) {
			Player = false;
			TicTacToe.label2.setText(TicTacToe.player.get(0) + "'s turn");
			return 1;

		} else if (last == false) {
			Player = true;
			TicTacToe.label2.setText(TicTacToe.player.get(1) + "'s turn");
			return 2;
		} else {
			return 3;
		}

	}

	public void JButtonPositionDisabler(JButton Btn, boolean Play) {

		Btn.setEnabled(false);
		if (Play == true) {
			Btn.setDisabledIcon(O);
		} else if (Play == false) {
			Btn.setDisabledIcon(X);
		}

	}

	public void DisableAllPositions(boolean Opp) {

		for (int i = 0; i < 9; i++) {

			buttons[i].setEnabled(Opp);
		}

	}

	public void SetXOPosition(JButton Btn, boolean Play) {

		if (Play == true) {
			Btn.setIcon(O);
		} else if (Play == false) {
			Btn.setIcon(X);
		}

	}

	public void DefaultContentPosition() {

		for (int i = 0; i < 9; i++) {

			buttons[i].setIcon(null);
		}

	}

}