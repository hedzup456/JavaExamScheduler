/**
 * 	OPEN USING WINDOWBUILDER IN ECLIPSE
 *  This allows the use of the design tab, giving a preview of what stuff looks like.
 */
package examTimer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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
					ExamParser ep = new ExamParser("exams.txt");
					List<Exam> allExams = ep.readAllExams();
					ep.close();			
					
					MainUI window = new MainUI(allExams);
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
	public MainUI(List<Exam> allExams) throws InterruptedException {
		initialize(allExams);
	}
	/**
	 * Updates the contents of the table. Note, doesn't refresh the table, so no new data will be displayed/
	 * @param allExams, list of all exams as found from an ExamParser.
	 */
	private void updateOtherExamsTableContents(List<Exam> allExams){
		ArrayList<String[]> newContents = new ArrayList<String[]>(allExams.size());
				
		for( Exam exam: allExams){
			long secs = exam.getTimeTilExam();
			long[] times = {secs, secs/60, secs/60/60, secs/60/60/24};
			String[] timesS = new String[4];
			for (int i = 0; i < times.length; i++) timesS[i] = String.valueOf(times[i]);
			
			String[] newExam = {exam.getSubject() + ", " + exam.getLevelAsString(), exam.getLocation(), timesS[0], timesS[1], timesS[2], timesS[3]};
			newContents.add(newExam);
		}		
		tableContents = newContents.toArray(new String[0][0]);
	}
	private void updateTable(){
		DefaultTableModel tableModel = (DefaultTableModel) otherExamsTable.getModel();
		tableModel.setRowCount(0);	// Clears old values.
		
		for (String[] e: tableContents) tableModel.addRow(e);
		tableModel.fireTableDataChanged();	// Update the table, ie refresh
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Exam> allExams) {
		// Testing array for table
		tableContents = new String[][] {{"BLANK", "FOR", "TESTING"}};
		
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
			new Object[][] {
				{"BLANK", "FOR", "TESTING", "Time", "Time 2"},
			},
			new String[] {
				"Subject:", "Location:", "Days remaining:", "Hours remaining:", "Mins remaining:", "Seconds remaining" 
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		otherExamsTable.getColumnModel().getColumn(3).setResizable(false);
		scrollPaneForTable.setViewportView(otherExamsTable);
		updateOtherExamsTableContents(allExams);
		updateTable();
	}
}
