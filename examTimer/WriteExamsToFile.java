/**
 * 
 */
package examTimer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author 		Richard Henry (richardhenry602@gmail.com)
 * @since 		7 Jun 2016
 *
 */
public class WriteExamsToFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		ArrayList<Exam> exams = new ArrayList<Exam>();
		boolean Cont = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		while(Cont){
			System.out.print("Subject: ");
			String sub = sc.nextLine();
			System.out.println("Location: ");
			String loc = sc.nextLine();
			System.out.println("Date: ");
			Date date = sdf.parse(sc.nextLine());
			System.out.println("Level: ");
			int level = sc.nextInt();
			
			Exam exam = new Exam(sub, loc, level, date);
			exams.add(exam);
			
			sc.nextLine(); // Purge buffer
			System.out.println("Cont Y/N?");
			Cont = sc.nextLine().charAt(0) == 'Y';
		}
		
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter("exams.txt"));
		for (Exam e: exams){
			String line = e.getSubject() + ',' + e.getLocation() + ',' +  e.getLevel() + ',' + e.getDate().getTime() + "\n";
			System.out.println(line);
			fileWriter.write(line);
		}
		fileWriter.close();
		sc.close();
		

	}

}
