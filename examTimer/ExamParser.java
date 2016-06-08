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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
		String line;
		String[] lineInParts = {"ERROR", "ERROR", "0", "0"};
		try {
			line = fileReader.readLine();
			lineInParts = line.split(",");
			long date = Long.parseLong(lineInParts[3]);
			Exam examFromLine = new Exam(lineInParts[0], lineInParts[1], Integer.parseInt(lineInParts[2]), new Date(date));
			return examFromLine;
		} catch ( NumberFormatException e){
			try {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm");
				return new Exam(lineInParts[0], lineInParts[1], Integer.parseInt(lineInParts[2]), df.parse(lineInParts[3]));
			} catch ( ParseException ee) {}
	        } catch ( IOException e){
			e.printStackTrace();
			return new Exam("ERROR", "ERROR", 0, "01/01/1970 00:00");
		} catch ( NullPointerException e){
			return new Exam("Error", "ERROR", 0, "01/01/1970 00:00");
		}
		return new Exam("ERROR", "ERROR", 0, "01/01/1970 00:00"); // Apparently this is needed, who knew?
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
		Collections.sort(allExams);	// Return them sorted
		return allExams;
	}
}
