/**
 * 
 */
package examTimer;
import java.util.List;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author 		Richard Henry (richardhenry602@gmail.com)
 * @since 		17 May 2016
 *
 */
public class examTimetabler {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException{	// Lazy handling. I'm not really worried if it updates early.
		while(true){
			System.out.println("             LOOPING");
			main();
			TimeUnit.SECONDS.sleep(10);
		}
	}
	public static void main(){
		ExamParser parser = new ExamParser("/home/rjh/temp");
		
		List<Exam> examsList = parser.readAllExams();
		Collections.sort(examsList);
		
		for(Exam currentExam:examsList){
			long timeLeft = currentExam.getTimeTilExamMins();
			String type = " minutes";
			if (timeLeft > 1440){
				timeLeft /= 1440;
				type = " days";
			} else if (timeLeft > 60){
				timeLeft /= 60;
				type = " hours";
			}
			if (timeLeft == 1){
				type = type.substring(0, type.length()-1);
			}

			System.out.println(currentExam.getSubject() + ", in " + timeLeft + type);
		}
	}
}
