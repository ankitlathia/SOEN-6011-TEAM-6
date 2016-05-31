import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToe extends JFrame {
	JPanel p = new JPanel();
	XOButton buttons[] = new XOButton[9]; // Board and Button
	int result;
	static ArrayList<String> player = new ArrayList<String>();
	
	static JLabel label2;
	String p1, p2, xostr ;
	
	private JSplitPane splitPane;
	
	JFrame nameFrame = new JFrame("Player Form ");
	
    JPanel namePanel = new JPanel(new GridLayout(0,1));
	JTextField pName1 = new JTextField(5);
	JTextField pName2 = new JTextField(5);
	JTextField xo = new JTextField(5);

	private Menu TButtons; // Exit and Reset

	private XOButton GameBoard; // Board and Button
	
	private PlayerScore pScore;
	

	public TicTacToe() {

		setLayout(new BorderLayout());
		nameFrame.setLayout(new GridLayout(0,1));

		GameBoard = new XOButton();
		TButtons = new Menu();
		pScore = new PlayerScore();
		

		TButtons.SetObjectOfBoard(GameBoard);
		
		GameBoard.setPreferredSize(new Dimension(200,60));
		pScore.setPreferredSize(new Dimension(10,10));
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,	GameBoard, pScore);
		splitPane.setResizeWeight(0.5);
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		
		setJMenuBar( Menu.menuBar );
		add(splitPane, BorderLayout.CENTER);
		//add(TButtons, BorderLayout.SOUTH);

		
			nameFrame.add(namePanel);
			namePanel.add(new JLabel("Player 1 Name : "));
			namePanel.add(pName1);
			namePanel.add(new JLabel("Player 1 Choose X or 0"));
			namePanel.add(xo);
			namePanel.add(new JLabel("Player 2 Name : "));
			namePanel.add(pName2);
			result = JOptionPane.showConfirmDialog(null,namePanel, "Name of player", JOptionPane.OK_OPTION);
			if(result == JOptionPane.OK_OPTION) {
				p1=pName1.getText();
				xostr = xo.getText();
				p2=pName2.getText();
				
				player.add(p1);
				player.add(xostr);
				player.add(p2);
//				
				PlayerScore.table.getColumnModel().getColumn(0).setHeaderValue(p1);
				PlayerScore.table.getColumnModel().getColumn(1).setHeaderValue(p2);
				
				
				if(player.get(1).equalsIgnoreCase("x")){
					XOButton.Player = false; // player x	
				}else{
					XOButton.Player = true; // player 0
				}
			}else {
				p1="Player 1";
				xostr = "x";
				p2="Player 2";
				
				player.add(p1);
				player.add("x");
				player.add(p2);
				PlayerScore.table.getColumnModel().getColumn(0).setHeaderValue(p1);
				PlayerScore.table.getColumnModel().getColumn(1).setHeaderValue(p2);
			}
			
		label2 = new JLabel(TicTacToe.player.get(0) + "'s turn");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		add(label2, BorderLayout.SOUTH);

		setVisible(true);
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//PlayAudio.playSound("/Users/ankit/Documents/workspace/Tic_Tac_Toe_D3/src/Puzzle.wav");

		Menu.menuGameNew.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				dispose();
				player.clear();
				TicTacToe C = new TicTacToe();

			}
		});

	}

}