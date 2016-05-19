import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToe extends JFrame {
	JPanel p = new JPanel();
	XOButton buttons[] = new XOButton[9]; // Board and Button

	static ArrayList<String> player = new ArrayList<String>();
	JFrame frame = new JFrame("InputDialog Example #1");
	static JLabel label2;

	private CloseReset TButtons; // Exit and Reset

	private XOButton GameBoard; // Board and Button

	public TicTacToe() {

		setLayout(new BorderLayout());

		GameBoard = new XOButton();
		TButtons = new CloseReset();

		TButtons.SetObjectOfBoard(GameBoard);

		add(GameBoard, BorderLayout.CENTER);

		add(TButtons, BorderLayout.SOUTH);

		for (int i = 0; i < 2; i++) {
			String name = JOptionPane.showInputDialog(frame, "Name of player" + (i + 1));

			if (name.equals("")) {
				name = "player" + (i + 1);
			}

			player.add(name);
		}
		for (int i = 0; i < player.size(); i++) {
			System.out.println(" " + player.get(i));
		}

		label2 = new JLabel(TicTacToe.player.get(0) + "'s turn");
		add(label2, BorderLayout.PAGE_START);

		setVisible(true);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CloseReset.New.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				dispose();
				player.clear();
				TicTacToe C = new TicTacToe();

			}
		});

	}

}