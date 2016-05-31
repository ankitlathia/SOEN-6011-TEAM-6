import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Menu extends 	JPanel implements	ActionListener
{
	private final int	ITEM_PLAIN	=	0;	// Item types
	private final int	ITEM_CHECK	=	1;
	private final int	ITEM_RADIO	=	2;

	private	JPanel		topPanel;
	static JMenuBar	menuBar;
	private XOButton Brd;
	
	private JMenu    menuGame;
	static JMenuItem menuGameNew;
	private	JMenuItem menuGameReset;
	private	JMenuItem menuGameExit;
	
	private JMenu    menuSettings;
	private	JMenuItem menuSettingsHelp;
	
	private	JMenu menuSettingsMusic;
	private JMenu    menuSettingsMusicToggle;
	private	JRadioButtonMenuItem menuSettingsMusicOn = new JRadioButtonMenuItem("ON", true);;
	private	JRadioButtonMenuItem menuSettingsMusicOff = new JRadioButtonMenuItem("OFF");;
	
	
	private	JMenuItem menuSettingsAbout;
	
	
	private JMenu    menuPlayers;
	static JCheckBoxMenuItem menuPlayersPlayers = new JCheckBoxMenuItem("Player VS Player", false);
	private	JCheckBoxMenuItem menuPlayersComputer = new JCheckBoxMenuItem("Player VS Computer", true);
	private JRadioButtonMenuItem menuPlayersEasy  = new JRadioButtonMenuItem("Easy", true);
	private JRadioButtonMenuItem menuPlayersMedium = new JRadioButtonMenuItem("Medium");
	private JRadioButtonMenuItem menuPlayersDifficult= new JRadioButtonMenuItem("Difficult");
	

	public Menu()
	{
		
		setLayout( new BorderLayout() );
		

		// Create the menu bar
		menuBar = new JMenuBar();

		//Game menu
		menuGame = new JMenu( "Game" );
		menuGame.setMnemonic( 'G' );
		menuBar.add( menuGame );
		
		menuGameNew = CreateMenuItem( menuGame, ITEM_PLAIN,"New Game", null, 'N', null );
		menuGameReset = CreateMenuItem( menuGame, ITEM_PLAIN, "Reset Game",null, 'R',null );
		menuGame.addSeparator();
		menuGameExit = CreateMenuItem( menuGame, ITEM_PLAIN, "Exit", null, 'E', null);
		
		menuGameReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				
				Brd.ResetGame();

			}

		});
		menuGameExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		
		//players menu
		menuPlayers = new JMenu( "Players" );
		menuPlayers.setMnemonic( 'P' );
		menuPlayers.add(menuPlayersPlayers);
		menuPlayers.add(menuPlayersComputer);
		menuPlayers.add(menuPlayersEasy);
		menuPlayers.add(menuPlayersMedium);
		menuPlayers.add(menuPlayersDifficult);
		menuBar.add( menuPlayers );
		
		menuPlayersPlayers.addItemListener(new ItemListener() {
			  @Override
			  public void itemStateChanged(ItemEvent e) {
			    if(e.getStateChange() == ItemEvent.SELECTED){
			    	XOButton.pl1="Player 1";
			    	XOButton.pl2="Player 2";
			    	Brd.ResetGame();
			    	XOButton.vs=2;
			    	XOButton.Player = false;
			    	
			    	menuPlayersComputer.setSelected(false);
			    	menuPlayersEasy.setEnabled(false);
			    	menuPlayersMedium.setEnabled(false);
			    	menuPlayersDifficult.setEnabled(false);
			    	
			    }else{
			    	menuPlayersComputer.setSelected(true);
			    	menuPlayersEasy.setEnabled(true);
			    	menuPlayersMedium.setEnabled(true);
			    	menuPlayersDifficult.setEnabled(true);
			    }
			  }
			 });
		
		menuPlayersComputer.addItemListener(new ItemListener() {
			  @Override
			  public void itemStateChanged(ItemEvent e) {
			    if(e.getStateChange() == ItemEvent.SELECTED){

			    	XOButton.pl1="You";
			    	XOButton.pl2="Computer";
			    	Brd.ResetGame();
			    	XOButton.vs=1;
			    	menuPlayersPlayers.setSelected(false);
			    }else{
			    	menuPlayersPlayers.setSelected(true);
			    }
			  }
			 });
		
		menuPlayersEasy.addItemListener(new ItemListener() {
			  @Override
			  public void itemStateChanged(ItemEvent e) {
			    if(e.getStateChange() == ItemEvent.SELECTED){
			    	XOButton.type = 1;
			    	menuPlayersMedium.setSelected(false);
			    	menuPlayersDifficult.setSelected(false);
			    }
			  }
			 });
		menuPlayersMedium.addItemListener(new ItemListener() {
			  @Override
			  public void itemStateChanged(ItemEvent e) {
			    if(e.getStateChange() == ItemEvent.SELECTED){
			    	XOButton.type = 2;
			    	menuPlayersDifficult.setSelected(false);
			    	menuPlayersEasy.setSelected(false);
			    }
			  }
			 });
		menuPlayersDifficult.addItemListener(new ItemListener() {
			  @Override
			  public void itemStateChanged(ItemEvent e) {
			    if(e.getStateChange() == ItemEvent.SELECTED){
			    	XOButton.type = 3;
			    	menuPlayersMedium.setSelected(false);
			    	menuPlayersEasy.setSelected(false);
			    }
			  }
			 });
		
		    
		
		//General settings
		menuSettingsMusic = new JMenu( "Music" );
		menuSettingsMusic.setMnemonic( 'M' );
		menuSettingsMusic.add(menuSettingsMusicOn);
		menuSettingsMusic.add(menuSettingsMusicOff);
		
		menuSettings = new JMenu( "Settings" );
		menuSettings.setMnemonic( 'H' );
		menuBar.add( menuSettings );
		
		menuSettingsHelp = CreateMenuItem( menuSettings, ITEM_PLAIN,"HELP", null, 'H', null );
		menuSettings.add(menuSettingsMusic);
		menuSettings.addSeparator();
		menuSettingsAbout = CreateMenuItem( menuSettings, ITEM_PLAIN,"About", null, 'A', null );
		
		menuSettingsMusicOn.addItemListener(new ItemListener() {
			  @Override
			  public void itemStateChanged(ItemEvent e) {
			    if(e.getStateChange() == ItemEvent.SELECTED){
			    	PlayAudio.playSound("/Users/ankit/Documents/workspace/Tic_Tac_Toe_D3/src/Puzzle.wav");
			    	PlayAudio.sourceLine.start();
			    	menuSettingsMusicOff.setSelected(false);
			    }
			  }
			 });
		
		menuSettingsMusicOff.addItemListener(new ItemListener() {
			  @Override
			  public void itemStateChanged(ItemEvent e) {
			    if(e.getStateChange() == ItemEvent.SELECTED){
			    	PlayAudio.sourceLine.stop();
			    	menuSettingsMusicOn.setSelected(false);
			    }
			  }
			 });
		
		menuSettingsHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(null, "INSTRUCTIONS : \n It consist two players, X and O, \n"
						+ " who take turns marking the spaces in a 3×3 grid. \n"
						+ "The player who succeeds in placing three of their marks in a horizontal, \n vertical, or diagonal row wins the game.");
			}
		});
		menuSettingsAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(null, "DEVELOPED BY : SOEN-6011 TEAM 6");
			}
		});
		
		
		
		
	}

	public JMenuItem CreateMenuItem( JMenu menu, int iType, String sText,
								ImageIcon image, int acceleratorKey,
								String sToolTip )
	{
		// Create the item
		JMenuItem menuItem;

		switch( iType )
		{
			case ITEM_RADIO:
				menuItem = new JRadioButtonMenuItem();
				break;

			case ITEM_CHECK:
				menuItem = new JCheckBoxMenuItem();
				break;

			default:
				menuItem = new JMenuItem();
				break;
		}

		// Add the item test
		menuItem.setText( sText );

		// Add the optional icon
		if( image != null )
			menuItem.setIcon( image );

		// Add the accelerator key
		if( acceleratorKey > 0 )
			menuItem.setMnemonic( acceleratorKey );

		// Add the optional tool tip text
		if( sToolTip != null )
			menuItem.setToolTipText( sToolTip );

		// Add an action handler to this menu item
		menuItem.addActionListener( this );

		menu.add( menuItem );

		return menuItem;
	}
	
	public void SetObjectOfBoard(XOButton B) {

		Brd = B;

	}
	
	

	public void actionPerformed( ActionEvent event )
	{
		System.out.println( event );
	}


}