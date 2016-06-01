import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class LogicalArray {

	/*
	 * Class for checking Win condition checking
	 */
	private static XOButton xoBrd;
	static int[] GArray;
	static ArrayList<Integer> player1Score = new ArrayList<Integer>(10);
	static ArrayList<Integer> player2Score = new ArrayList<Integer>(10);
	
	LogicalArray(XOButton B) {

		GArray = new int[9];

		xoBrd = B;

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

				JOptionPane.showMessageDialog(xoBrd, "CONGRATULATIONS : " + TicTacToe.player.get(0) + " Win the game");
				player1Score.add(1);
				player2Score.add(0);
				PlayerScore.model.addRow(new Object[] { 1, 0});
				PlayerScore.totalScore();
				ResetGame();
				
			} else if (Marker == 2) {

				JOptionPane.showMessageDialog(xoBrd, "CONGRATULATIONS : " + TicTacToe.player.get(2) + " Win the game");
				player1Score.add(0);
				player2Score.add(1);
				PlayerScore.model.addRow(new Object[] { 0, 1});
				PlayerScore.totalScore();
				ResetGame();
			}

			xoBrd.DisableAllPositions(false);

		} else

		if ((GArray[0] == Marker && GArray[3] == Marker && GArray[6] == Marker)
				|| (GArray[1] == Marker && GArray[4] == Marker && GArray[7] == Marker)
				|| (GArray[2] == Marker && GArray[5] == Marker && GArray[8] == Marker)) {
			// Check vertical boxes in board

			if (Marker == 1) {
				JOptionPane.showMessageDialog(xoBrd, "CONGRATULATIONS : " + TicTacToe.player.get(0) + " Win the game");
				player1Score.add(1);
				player2Score.add(0);
				PlayerScore.model.addRow(new Object[] { 1, 0});
				PlayerScore.totalScore();
				ResetGame();
				
			} else if (Marker == 2) {
				JOptionPane.showMessageDialog(xoBrd, "CONGRATULATIONS : " + TicTacToe.player.get(2) + " Win the game");
				player1Score.add(0);
				player2Score.add(1);
				PlayerScore.model.addRow(new Object[] { 0, 1});
				PlayerScore.totalScore();
				ResetGame();
			}

			xoBrd.DisableAllPositions(false);

		} else if ((GArray[0] == Marker && GArray[4] == Marker && GArray[8] == Marker)
				|| (GArray[6] == Marker && GArray[4] == Marker && GArray[2] == Marker))

		{
			// Check diagonal boxes in board

			if (Marker == 1) {
				JOptionPane.showMessageDialog(xoBrd, "CONGRATULATIONS : " + TicTacToe.player.get(0) + " Win the game");
				player1Score.add(1);
				player2Score.add(0);
				PlayerScore.model.addRow(new Object[] { 1, 0});
				PlayerScore.totalScore();
				ResetGame();
				
			} else if (Marker == 2) {
				JOptionPane.showMessageDialog(xoBrd, "CONGRATULATIONS : " + TicTacToe.player.get(2) + " Win the game");
				player1Score.add(0);
				player2Score.add(1);
				PlayerScore.model.addRow(new Object[] { 0, 1});
				PlayerScore.totalScore();
				ResetGame();
			}
			xoBrd.DisableAllPositions(false);

		}
		
	}
	
	static void checkwinComVsPly(int pos,int n)
    {
        if(XOButton.cnt==1)
            if(XOButton.vs==1)
            	XOButton.turn=true;
        if(XOButton.cnt>4)
        {
            if((GArray[0]+GArray[1]+GArray[2]==n*3) || (GArray[3]+GArray[4]+GArray[5]==n*3) || (GArray[6]+GArray[7]+GArray[8]==n*3) ||
            (GArray[0]+GArray[3]+GArray[6]==n*3) || (GArray[1]+GArray[4]+GArray[7]==n*3) || (GArray[2]+GArray[5]+GArray[8]==n*3))
            {
            	XOButton.cnt=n;
            }
            else
            {
                if((GArray[0]+GArray[4]+GArray[8] == n*3) || (GArray[6]+GArray[4]+GArray[2] == n*3))
                {
                	XOButton.cnt=n;
                }
                else
                {
                    if(XOButton.cnt==9)
                    {
                    	XOButton.cnt=0;
                    }
                }
            }
            if(XOButton.cnt==1||XOButton.cnt==0)
            {
                if(XOButton.cnt==1)
                	JOptionPane.showMessageDialog(xoBrd, "CONGRATULATIONS : " + XOButton.pl1 + " Win the game");
                xoBrd.DisableAllPositions(false);
                XOButton.DefaultContentPosition();
                if(XOButton.cnt==0)
                	JOptionPane.showMessageDialog(xoBrd,"The Game is a Draw!");
                xoBrd.DisableAllPositions(false);
                XOButton.DefaultContentPosition();
                ResetGame();
                if(XOButton.vs==1)
                if(XOButton.pl1=="Computer")
                {
                	XOButton.turn=false;
                    AI.compplay(XOButton.val);
                }
                else
                	XOButton.turn=false;
               
            }
            else
            if(XOButton.cnt==4)
            {
            	JOptionPane.showMessageDialog(xoBrd, XOButton.pl2 + " Win the game");
            	xoBrd.DisableAllPositions(false);
            	XOButton.DefaultContentPosition();
//                String temp=XOButton.pl1;
//                XOButton.pl1=XOButton.pl2;
//                XOButton.pl2=temp;
                ResetGame();
                if(XOButton.vs==1)
                if(XOButton.pl1=="Computer")
                    AI.compplay(XOButton.val);
                else
                	XOButton.turn=false;
            }
        }
    }
	
	public static void ResetGame() {
		
		GArray = new int[9];
		XOButton.DefaultContentPosition();
		XOButton.DisableAllPositions(true);
		XOButton.cnt=0;
		XOButton.val=1;
		XOButton.let='\u0000';
		XOButton.Player = false; // player x
		XOButton.PlayerMark = 1; // default Value x
		
	}

}