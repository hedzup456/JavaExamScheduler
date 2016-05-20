/**
 * 
 */
package examTimer;


/**
 * @author 		Richard Henry (richardhenry602@gmail.com)
 * @since 		17 May 2016
 *
 */
public class examTimetabler {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exam testExam = new Exam("Computing", "B112", 2, "06/06/2016 09:15");
		
		System.out.println(testExam.getTimeTilExamMins() + " mins until this exam.");
		
	}

}
