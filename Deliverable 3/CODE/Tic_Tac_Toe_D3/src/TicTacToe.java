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
import java.net.MalformedURLException;
import java.util.ArrayList;

public class TicTacToe extends JFrame {
	static JPanel p = new JPanel();
	XOButton buttons[] = new XOButton[9]; // Board and Button
	static int result;
	static ArrayList<String> player = new ArrayList<String>();
	
	static JLabel label2;
	static String p1 ;
	static String p2;
	static String xostr;
	
	private JSplitPane splitPane;
	
	static JFrame nameFrame = new JFrame("Player Form ");
	
    static JPanel namePanel = new JPanel(new GridLayout(0,1));
	static JTextField pName1 = new JTextField(5);
	static JTextField pName2 = new JTextField(5);
	static JTextField xo = new JTextField(5);

	private Menu TButtons; // Exit and Reset

	private XOButton GameBoard; // Board and Button
	
	private PlayerScore pScore;
	

	public TicTacToe() throws MalformedURLException {

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

		
		
		label2 = new JLabel("");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		add(label2, BorderLayout.SOUTH);
		
		setTitle("Tic Tac Toe");
		setVisible(true);
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		playBackgroundAudio.playAudio(this.getClass().getResource("ba.midi"));

		Menu.menuGameNew.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				dispose();
				//player.clear();
				try {
					TicTacToe C = new TicTacToe();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

}