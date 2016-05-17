/**
 * 
 */
package examTimer;

import java.util.Date;

/**
 * @author 		Richard Henry (richardhenry602@gmail.com)
 * @since 		17 May 2016
 *
 */
public class examTimetabler {
	private static int findMinsUntilExam(Exam exam){
		Date examDate = exam.getDate();
		Date timeNow = new Date();
		long differenceInTime = examDate.getTime() - timeNow.getTime();
		System.out.println(examDate.toGMTString() + " " + timeNow.toGMTString());
		long differenceMins = differenceInTime/60000;
		if (differenceMins > Integer.MAX_VALUE) return Integer.MAX_VALUE;
		else if (differenceMins < Integer.MIN_VALUE) return Integer.MIN_VALUE;
		else return (int)differenceMins;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exam testExam = new Exam("Computing", "B112", "06/06/2016 09:15");
		
		System.out.println(findMinsUntilExam(testExam));
	}

}
