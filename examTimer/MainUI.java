/**
 * 	OPEN USING WINDOWBUILDER IN ECLIPSE
 *  This allows the use of the design tab, giving a preview of what stuff looks like.
 */
package examTimer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.JEditorPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

/**
 * @author 		Richard Henry (richardhenry602@gmail.com)
 * @since 		31 May 2016
 *
 */
public class MainUI {
	
	private String[][] tableContents;
	private JFrame frmExamsComingUp;
	private JTable otherExamsTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmExamsComingUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Testing array for table
		tableContents = new String[][] {
				{"1s",	"1l",	"1t"},
				{"2s",	"2l",	"2t"},
				{"3s",	"3l",	"3t"},
				{"4s",	"4l",	"4t"},
				{"5s",	"5l",	"5t"},
				{"6s",	"6l",	"6t"},
				{"7s",	"7l",	"7t"},
				{"8s",	"8l",	"8t"},
				{"9s",	"9l",	"9t"},
				{"10s",	"10l",	"10t"}
		};
		frmExamsComingUp = new JFrame();
		frmExamsComingUp.setResizable(false);
		frmExamsComingUp.setTitle("Exams coming up");
		frmExamsComingUp.setBounds(100, 100, 450, 300);
		frmExamsComingUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExamsComingUp.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		frmExamsComingUp.getContentPane().add(verticalBox, BorderLayout.NORTH);
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel subjectLabel = new JLabel("SUBJECT");
		subjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(subjectLabel);
		
		JLabel locationLabel = new JLabel("LOCATION");
		locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(locationLabel);
		
		JLabel timeUntilLabel = new JLabel("TIME");
		timeUntilLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeUntilLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(timeUntilLabel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 40));
		verticalBox.add(rigidArea);
		
		JScrollPane scrollPaneForTable = new JScrollPane();
		scrollPaneForTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneForTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frmExamsComingUp.getContentPane().add(scrollPaneForTable, BorderLayout.CENTER);
		
		otherExamsTable = new JTable();
		otherExamsTable.setModel(new DefaultTableModel(
			tableContents,
			new String[] {
				"Subject:", "Location:", "Time remaining:"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		otherExamsTable.getColumnModel().getColumn(0).setResizable(false);
		scrollPaneForTable.setViewportView(otherExamsTable);
	}
}
