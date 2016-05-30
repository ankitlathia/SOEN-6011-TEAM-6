package com;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	int result;
	static ArrayList<String> player = new ArrayList<String>();
	
	static JLabel label2;
	String p1, p2, xostr ;
	
	JFrame nameFrame = new JFrame("Player Form ");
	
    JPanel namePanel = new JPanel(new GridLayout(0,1));
	JTextField pName1 = new JTextField(5);
	JTextField pName2 = new JTextField(5);
	JTextField xo = new JTextField(5);

	private CloseReset TButtons; // Exit and Reset

	private XOButton GameBoard; // Board and Button

	public TicTacToe() {

		setLayout(new BorderLayout());
		nameFrame.setLayout(new GridLayout(0,1));

		GameBoard = new XOButton();
		TButtons = new CloseReset();

		TButtons.SetObjectOfBoard(GameBoard);

		add(GameBoard, BorderLayout.CENTER);

		add(TButtons, BorderLayout.SOUTH);

		
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