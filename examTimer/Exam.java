/**
 * 
 */
package examTimer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author 		Richard Henry (richardhenry602@gmail.com)
 * @since 		17 May 2016
 *
 */
public class Exam implements Comparable<Exam>{
	@Override
	public int compareTo(Exam other){
		/*
		 * Sorts by, in order:
		 * 	* Date (nearest first)
		 * 	* Level (Hardest first)
		 * 	* Name (Alphabetical order)
		 */
		int comparedResult = 0;
		if (other.getDate().getTime() > this.getDate().getTime()){	// If other is further in the future
			comparedResult = -1;									// This is nearer
		} else if (other.getDate().getTime() < this.getDate().getTime()){	// If this is further in the future
			comparedResult = 1;									// other is nearer
		}
		
		if (comparedResult == 0){	// If date isn't helpful
			// Nested for readability
			if (other.getLevel() > this.getLevel()){	// If other is higher level
				comparedResult = 1;						// Other is harder
			} else if (other.getLevel() < this.getLevel()){
				comparedResult = -1;					// This is harder
			}
		}
		
		if (comparedResult == 0){	// If date and level aren't helpful
			comparedResult = this.getSubject().compareToIgnoreCase(other.getSubject());
		}
	
		return comparedResult;
	}
	private Date date;
	private String location;
	private String subject;
	private int level;
	private long timeTilExam;

	public void updateTimeTilExam(){
		Date now = new Date();
		long timeDifference = date.getTime() - now.getTime();
		timeTilExam = timeDifference;		
	}
	/**
	 * @return the time until the exam
	 */
	public long getTimeTilExam(){
		updateTimeTilExam();
		return timeTilExam;
	}
	public long getTimeTilExamMins(){
		long millis = getTimeTilExam();
		return millis/60000;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @param date the date to set in dd/MM/yyyy HH:mm format
	 */
	public void setDate(String date){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try{
			this.date = formatter.parse(date);
		} catch (ParseException e) {
			System.out.println("Error. Can't parse that.");
		}
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * @param level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @param level to set
	 */
	public void setLevel(String level){
		switch(level){
		case "GCSE":
			this.level = 1;
			break;
		case "AS":
			this.level = 2;
			break;
		case "A2":
			this.level = 3;
			break;
		default:
			this.level = 0;
		}		
	}
	/**
	 * @return the current level
	 */
	public int getLevel(){
		return level;
	}
	public String getLevelAsString(){
		switch (level){
		case 1: return "GCSE";
		case 2: return "AS";
		case 3: return "A2";
		default: return "Not set";
		}
	}
	
	/**
	 * @return the time the exam is at
	 */
	public String getTime(){
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(date);
	}
	/**
	 * @return subject,location,datetime
	 */
	
	public String toString(){
		StringBuilder toReturn = new StringBuilder();
		toReturn.append(subject);
		toReturn.append(',');
		toReturn.append(location);
		toReturn.append(',');
		toReturn.append(level);
		toReturn.append(',');
		toReturn.append(date.getTime());
		
		return toReturn.toString();
	}
	
	// CONSTRUCTORS
	/**
	 * 
	 * @param subject	the subject of the exam
	 * @param location	the location of the exam
	 * @param level		the level of the exam (GCSE/AS/A2)
	 * @param datetime	the date/time of the exam
	 */
	public Exam(String subject, String location, String level, Date datetime){
		setDate(datetime);
		setLocation(location);
		setLevel(level);
		setSubject(subject);
	}
	/**
	 * 
	 * @param subject	the subject of the exam
	 * @param location	the location of the exam
	 * @param level		the level of the exam (GCSE/AS/A2)
	 * @param datetime	the date/time of the exam
	 */
	public Exam(String subject, String location, String level, String datetime){
		setDate(datetime);
		setLocation(location);
		setLevel(level);
		setSubject(subject);
	}
	/**
	 * 
	 * @param subject	the subject of the exam
	 * @param location	the location of the exam
	 * @param level		the level of the exam
	 * @param datetime	the date/time of the exam
	 */
	public Exam(String subject, String location, int level, Date datetime){
		setDate(datetime);
		setLocation(location);
		setLevel(level);
		setSubject(subject);
	}
	/**
	 * 
	 * @param subject	the subject of the exam
	 * @param location	the location of the exam
	 * @param level		the level of the exam
	 * @param datetime	the date/time of the exam
	 */
	public Exam(String subject, String location, int level, String datetime){
		setDate(datetime);
		setLocation(location);
		setLevel(level);
		setSubject(subject);
	}
	
	public static void main(String[] a){	// for testing
		Exam exam = new Exam("Computing 1", "The Internet", "AS", "25/12/2016 09:00");	// What a miserable time for an exam
		List<Exam> exams = new ArrayList<Exam>();
		exams.add(exam);
		exams.add(new Exam("Computing 2", "The Internet", "AS", "25/12/2016 09:00"));
		Collections.sort(exams);
		for(Exam examLooped: exams){
			System.out.println(examLooped.getSubject() + " " + examLooped.getLevelAsString());
		}
		
		try{
			BufferedWriter writer = new BufferedWriter( new FileWriter( "exams.txt"));
			for (Exam examL: exams) writer.write(examL.toString() + "\n");
			writer.close();
		} catch ( IOException e){
			e.printStackTrace();
		}		
	}
}
