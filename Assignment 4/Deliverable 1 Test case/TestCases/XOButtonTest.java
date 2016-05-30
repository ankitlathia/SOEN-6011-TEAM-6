package TestCases;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

import com.TicTacToe;
import com.XOButton;

public class XOButtonTest {
	
		@Test
		public void SetXOPosition1Test() {
			//TicTacToe obj = new TicTacToe();

			XOButton x = new XOButton();
			JButton Btn = new JButton();
			x.SetXOPosition(Btn, true);
			System.out.println(Btn.getIcon().toString());
			assertEquals("file:/C:/Users/s_khaksa/Desktop/SOEN-6011-TEAM-6-master/SOEN-6011-TEAM-6-master/Deliverable%201/Tic_Tac_Toe_D1/bin/com/O.png",Btn.getIcon().toString());
		} 
		
		@Test
		public void SetXOPosition2Test() {
			//TicTacToe obj = new TicTacToe();

			XOButton x = new XOButton();
			JButton Btn = new JButton();
			x.SetXOPosition(Btn, false);
			System.out.println(Btn.getIcon().toString());
			assertEquals("file:/C:/Users/s_khaksa/Desktop/SOEN-6011-TEAM-6-master/SOEN-6011-TEAM-6-master/Deliverable%201/Tic_Tac_Toe_D1/bin/com/X.png",Btn.getIcon().toString());
		} 
		@Test
		public void SwithPlayersTurn1Test() {

			XOButton x = new XOButton();
			TicTacToe obj = new TicTacToe();

			int returnValue= x.SwithPlayersTurn(true);
			assertEquals(1,returnValue);
		} 
		@Test
		public void SwithPlayersTurn2Test() {

			XOButton x = new XOButton();
			TicTacToe obj = new TicTacToe();

			int returnValue= x.SwithPlayersTurn(false);
			assertEquals(2,returnValue);
		} 
		@Test
		public void DefaultContentPositionTest() {
			XOButton x = new XOButton();
			x.DefaultContentPosition();
			boolean test = true;
			for (int i=0; i<9; i++){
				//String s = XOButton.buttons[i].getIcon().toString();
			   if(XOButton.buttons[i].getIcon() != null){
				
				   test = false; 
			   }
			}
			assertEquals(true,test);
		}



	
	}


