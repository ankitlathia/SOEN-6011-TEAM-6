import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CloseReset extends JPanel {

	private JButton Exit, Reset, Help;
	public static JButton New;
	private XOButton Brd;

	CloseReset() {

		setLayout(new FlowLayout());

		Exit = new JButton("Exit");
		Reset = new JButton("Reset");
		Help = new JButton("Help");
		New = new JButton("New Game");

		Exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		Reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				Brd.ResetGame();

			}

		});

		Help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(null, "INSTRUCTIONS : \n It consist two players, X and O, \n"
						+ " who take turns marking the spaces in a 3×3 grid. \n"
						+ "The player who succeeds in placing three of their marks in a horizontal, \n vertical, or diagonal row wins the game.");
			}
		});

		add(New);
		add(Reset);
		add(Help);
		add(Exit);

	}

	public void SetObjectOfBoard(XOButton B) {

		Brd = B;

	}

}