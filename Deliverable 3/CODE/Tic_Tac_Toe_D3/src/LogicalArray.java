import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class LogicalArray {

	/*
	 * Class for checking Win condition checking
	 */
	private static XOButton xoBrd;
	static int[][] GArray;
	static ArrayList<Integer> player1Score = new ArrayList<Integer>(10);
	static ArrayList<Integer> player2Score = new ArrayList<Integer>(10);
	
	LogicalArray(XOButton B) {

		GArray = new int[3][3];

		xoBrd = B;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++){
			GArray[i][j] = 0;
			}
		}

	}

	public void ArrayInitialize(int i,int j, int Marker) {

		GArray[i][j] = Marker;
		CheckValidationTOWin(Marker);
	}

	public void CheckValidationTOWin(int Marker) {

		// Check horizontal boxes in board

		if((GArray[0][0] == Marker && GArray[0][1] == Marker && GArray[0][2] == Marker) ||
				(GArray[1][0] == Marker && GArray[1][1] == Marker && GArray[1][2] == Marker) ||
				(GArray[2][0] == Marker && GArray[2][1] == Marker && GArray[2][2] == Marker))
		{

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

			if((GArray[0][0] == Marker && GArray[1][0] == Marker && GArray[2][2] == Marker) ||
					(GArray[0][1] == Marker && GArray[1][1] == Marker && GArray[2][1] == Marker) ||
					(GArray[0][2] == Marker && GArray[1][2] == Marker && GArray[2][2] == Marker))	
		{
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

		} else 

			if ((GArray[0][0] == Marker && GArray[1][1] == Marker && GArray[2][2] == Marker)
					|| (GArray[2][0] == Marker && GArray[1][1] == Marker && GArray[0][2] == Marker))

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
	
	static void checkwinComVsPly(int l,int m,int n)
    {
        if(XOButton.cnt==1)
            if(XOButton.vs==1)
            	XOButton.turn=true;
        if(XOButton.cnt>4)
        {
            if((GArray[l][0]+GArray[l][1]+GArray[l][2]==n*3)||(GArray[0][m]+GArray[1][m]+GArray[2][m]==n*3))
            {
            	XOButton.cnt=n;
            }
            else
            {
                if((GArray[0][0]+GArray[1][1]+GArray[2][2]==n*3)||(GArray[2][0]+GArray[1][1]+GArray[0][2]==n*3))
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
                {
                	JOptionPane.showMessageDialog(xoBrd, "CONGRATULATIONS : " + XOButton.pl1 + " Win the game");
                }
                
                if(XOButton.cnt==0)
                {
                	JOptionPane.showMessageDialog(xoBrd,"The Game is a Draw!");
                }
                
                ResetGame();
                if(XOButton.vs==1)
                if(XOButton.pl1=="Computer")
                {
                	XOButton.turn=false;
                    AI.computerPlayer(XOButton.val);
                }
                else
                	XOButton.turn=false;
               
            }
            else
            if(XOButton.cnt==4)
            {
            	JOptionPane.showMessageDialog(xoBrd, XOButton.pl2 + " Win the game");

                String temp=XOButton.pl1;
                XOButton.pl1=XOButton.pl2;
                XOButton.pl2=temp;
                ResetGame();
                if(XOButton.vs==1)
                if(XOButton.pl1=="Computer")
                    AI.computerPlayer(XOButton.val);
                else
                	XOButton.turn=false;
            }
        }
    }
	
	public static void ResetGame() {
		
		GArray = new int[3][3];
		XOButton.DefaultContentPosition();
		XOButton.DisableAllPositions(true);
		XOButton.cnt=0;
		XOButton.val=1;
		XOButton.let='X';
		XOButton.Player = false; // player x
		XOButton.PlayerMark = 1; // default Value x
		
	}

}