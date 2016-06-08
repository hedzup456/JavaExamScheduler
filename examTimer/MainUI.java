/**
 * 	OPEN USING WINDOWBUILDER IN ECLIPSE
 *  This allows the use of the design tab, giving a preview of what stuff looks like.
 */
package examTimer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
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
	private JLabel subjectLabel, locationLabel, timeUntilLabel;

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
	private void updateOtherExamsTableContents(List<Exam> allExams, Exam topExam){
		allExams.remove(topExam); // Remove the exam currently displayed on top.
		ArrayList<String[]> newContents = new ArrayList<String[]>(allExams.size());
		
		for( Exam exam: allExams){
			long secs = exam.getTimeTilExam()/1000;	// Returns in millis
			
			long[] times = {secs, secs/60, (secs/60)/60, ((secs/60)/60)/24};	// Secs, mins, hours, days respectively.
			// Time corrections - current values in times is absolute, not cumulative
			times[0] %= 60;	// Correct sec
			times[1] %= 60;	// Minutes
			times[2] %= 24;	// hour
			times[3] %= times[2]*24;	// Days
			
			String[] timesS = new String[4];
			for (int i = 0; i < times.length; i++) timesS[i] = String.valueOf(times[i]);
			
			String[] newExam = {exam.getSubject() + ", " + exam.getLevelAsString(), exam.getLocation(), timesS[0], timesS[1], timesS[2], timesS[3]};
			newContents.add(newExam);
		}		
		tableContents = newContents.toArray(new String[0][0]);
		allExams.add(topExam);	// Re-add the top exam to the list.
		Collections.sort(allExams);
	}
	/**
	 * Updates the table's displayed state, pushing the most recent model.
	 */
	private void updateTable(){
		DefaultTableModel tableModel = (DefaultTableModel) otherExamsTable.getModel();
		tableModel.setRowCount(0);	// Clears old values.
		
		for (String[] e: tableContents) tableModel.addRow(e);
		tableModel.fireTableDataChanged();	// Update the table, ie refresh
	}
	/**
	 * Refreshes the table, and eventually the top block.
	 * 
	 * @param allExams	The list of all exams, needed to update the table contents.
	 */
	private void refresh(List<Exam> allExams){
		updateOtherExamsTableContents(allExams, allExams.get(0));
		updateTable();
		updateHeader(allExams.get(0));
	}
	/**
	 * Method to update the top section with the details from one exam.
	 * @param closestExam the exam whose details are used.
	 */
	private void updateHeader(Exam closestExam){
		subjectLabel.setText(closestExam.getSubject() + ", " + closestExam.getLevelAsString());
		locationLabel.setText(closestExam.getLocation());
		
		long secs = closestExam.getTimeTilExam()/1000;	// Returns in millis
		
		long[] times = {secs, secs/60, (secs/60)/60, ((secs/60)/60)/24};	// Secs, mins, hours, days respectively.
		// Time corrections - current values in times is absolute, not cumulative
		times[0] %= 60;	// Correct sec
		times[1] %= 60;	// Minutes
		times[2] %= 24;	// hour
		times[3] %= times[2]*24;	// Days
		
		// Allow neat things for date - no "0 days" etc
		StringBuilder timeRemainingSB = new StringBuilder();
		if(times[3] == 1L) {
			timeRemainingSB.append(times[3]);
			timeRemainingSB.append(" day, ");
		} else if (times[3] != 0L){
			timeRemainingSB.append(times[3]);
			timeRemainingSB.append(" days, ");
		}
		// hours
		if (times[2] == 1L){
			timeRemainingSB.append(times[2]);
			timeRemainingSB.append(" hour, ");
		} else if (times[2] != 0L){
			timeRemainingSB.append(times[2]);
			timeRemainingSB.append(" hours, ");
		}
		// mins
		if (times[1] == 1L){
			timeRemainingSB.append(times[1]);
			timeRemainingSB.append(" minute, ");
		} else if (times[1] != 0L){
			timeRemainingSB.append(times[1]);
			timeRemainingSB.append(" minutes, ");
		}
		// secs
		if (times[0] == 1L){
			timeRemainingSB.append(" and ");
			timeRemainingSB.append(times[0]);
			timeRemainingSB.append(" second");
		} else if (times[0] != 0L){
			timeRemainingSB.append(" and ");
			timeRemainingSB.append(times[0]);
			timeRemainingSB.append(" seconds");
		}
		timeRemainingSB.append('.');		
		timeUntilLabel.setText(timeRemainingSB.toString());
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List<Exam> allExams) {
		// Testing array for table
		tableContents = new String[0][0];
		
		frmExamsComingUp = new JFrame();
		frmExamsComingUp.setResizable(false);
		frmExamsComingUp.setTitle("Exams coming up");
		frmExamsComingUp.setBounds(100, 100, 701, 347);
		frmExamsComingUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExamsComingUp.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		frmExamsComingUp.getContentPane().add(verticalBox, BorderLayout.NORTH);
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		subjectLabel = new JLabel("SUBJECT");
		subjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(subjectLabel);
		
		locationLabel = new JLabel("LOCATION");
		locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(locationLabel);
		
		timeUntilLabel = new JLabel("TIME");
		timeUntilLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeUntilLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(timeUntilLabel);
		
		updateHeader(allExams.get(0));
		
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
				"Subject:", "Location:", "Seconds remaining:", "Minutes remaining:", "Hours remaining:", "Days remaining:" 
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
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh(allExams);
			}
		});
		frmExamsComingUp.getContentPane().add(btnRefresh, BorderLayout.SOUTH);
		updateOtherExamsTableContents(allExams, allExams.get(0));
		updateTable();
	}
}
