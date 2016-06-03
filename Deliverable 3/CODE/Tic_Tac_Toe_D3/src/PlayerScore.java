import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PlayerScore extends JPanel {


	static int count = 0;
	static String[] colHeadings = {"","Player","Computer"};
	static int numRows = 0 ;
	static DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length);
	static JTable table = new JTable(model);
    
    JScrollPane scrollPane = new JScrollPane(table);
   

	PlayerScore() {

		setLayout(new BorderLayout());
		add(new JLabel("Score board"));
		add(scrollPane);

	}
	
	static public void totalScore() {
		int totalcol1 = 0;
		int totalcol2 = 0;
		count++;
		if(count == 3){
		for(int i = 0; i < model.getRowCount(); i++){
	        int val1 = (int) model.getValueAt(i, 1);
	        int val2 = (int) model.getValueAt(i, 2);
	        totalcol1 = val1+totalcol1;
	        totalcol2 = val2+totalcol2;
	    }
		PlayerScore.model.addRow(new Object[] { "Total : " , totalcol1, totalcol2});
		count = 0;
		}
	}
	

}