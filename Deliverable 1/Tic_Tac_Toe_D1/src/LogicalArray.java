import java.awt.Color;

import javax.swing.*;

public class LogicalArray {

	/*
	 * Class for checking Win condition checking
	 */
	private XOButton Brd;
	private int GArray[];

	LogicalArray(XOButton B) {

		GArray = new int[9];

		Brd = B;

		for (int i = 0; i < 9; i++) {
			GArray[i] = 0;
		}

	}

	public void ArrayInitialize(int i, int Marker) {

		GArray[i] = Marker;
		CheckValidationTOWin(Marker);
	}

	public void CheckValidationTOWin(int Marker) {

		// Check horizontal boxes in board

		if ((GArray[0] == Marker && GArray[1] == Marker && GArray[2] == Marker)
				|| (GArray[3] == Marker && GArray[4] == Marker && GArray[5] == Marker)
				|| (GArray[6] == Marker && GArray[7] == Marker && GArray[8] == Marker)) {

			if (Marker == 1) {

				JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : " + TicTacToe.player.get(0) + " Win the game");

			} else if (Marker == 2) {

				JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : " + TicTacToe.player.get(1) + " Win the game");

			}

			Brd.DisableAllPositions(false);

		} else

		if ((GArray[0] == Marker && GArray[3] == Marker && GArray[6] == Marker)
				|| (GArray[1] == Marker && GArray[4] == Marker && GArray[7] == Marker)
				|| (GArray[2] == Marker && GArray[5] == Marker && GArray[8] == Marker)) {
			// Check vertical boxes in board

			if (Marker == 1) {
				JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : " + TicTacToe.player.get(0) + " Win the game");

			} else if (Marker == 2) {
				JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : " + TicTacToe.player.get(1) + " Win the game");

			}

			Brd.DisableAllPositions(false);

		} else if ((GArray[0] == Marker && GArray[4] == Marker && GArray[8] == Marker)
				|| (GArray[6] == Marker && GArray[4] == Marker && GArray[2] == Marker))

		{
			// Check diagonal boxes in board

			if (Marker == 1) {
				JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : " + TicTacToe.player.get(0) + " Win the game");

			} else if (Marker == 2) {
				JOptionPane.showMessageDialog(Brd, "CONGRATULATIONS : " + TicTacToe.player.get(1) + " Win the game");

			}
			Brd.DisableAllPositions(false);

		}
		
	}

}