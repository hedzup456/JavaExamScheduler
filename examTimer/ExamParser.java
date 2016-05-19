/**
 * 
 */
package examTimer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 		Richard Henry (richardhenry602@gmail.com)
 * @since 		19 May 2016
 *
 */
public class ExamParser {
	private BufferedReader fileReader;
	
	/**
	 * Constructor
	 * 
	 * @param fileLocation	String containing the location of the file
	 */
 	public ExamParser( String fileLocation){
		try{
			fileReader = new BufferedReader( new FileReader(fileLocation));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	/**
	 * Constructor
	 * 
	 * @param file	File object that is the file to read.
	 */
	public ExamParser( File file){
		try{
			fileReader = new BufferedReader( new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Simple method to read one line from the given text file and parse it to an Exam object.
	 * 
	 * @return One exam, that is on the one line in the file
	 */
	public Exam readExam(){
		try{
			String line = fileReader.readLine();
			String[] lineInParts = line.split(",");
			long date = Long.parseLong(lineInParts[3]);
			Exam examFromLine = new Exam(lineInParts[0], lineInParts[1], Integer.parseInt(lineInParts[2]), new Date(date));
			return examFromLine;
		} catch ( IOException e){
			e.printStackTrace();
			return new Exam("ERROR", "", 0, "");
		} catch ( NullPointerException e){
			return new Exam("Error", "", 0, "");
		}
	}
	public void close(){
		try{
			fileReader.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to real all the exams from the file.
	 * 
	 * @return
	 */
	public List<Exam> readAllExams(){
		List<Exam> allExams= new ArrayList<Exam>();
		Exam readExam;
		while ( !(readExam = readExam()).getSubject().equalsIgnoreCase("ERROR")){
			allExams.add(readExam);
		}
		return allExams;
	}
	
	//	TESTING
	public static void main( String[] a){
		ExamParser examParser = new ExamParser("exams.txt");
		List<Exam> examList= examParser.readAllExams();
		
		for( Exam exam: examList){
			System.out.println(exam.getSubject());
		}
	}
}
