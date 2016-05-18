/**
 * 
 */
package examTimer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 		Richard Henry (richardhenry602@gmail.com)
 * @since 		17 May 2016
 *
 */
public class Exam implements Comparable<Exam>{
	@Override
	public int compareTo(Exam other){
		int comparedResult = 0;
		if (other.getDate().getTime() > this.getDate().getTime()){	// If other is further in the future
			comparedResult = -1;									// This is nearer
		} else if (other.getDate().getTime() < this.getDate().getTime()){	// If this is further in the future
			comparedResult = 1;									// other is nearer
		}
		return comparedResult;
	}
	private Date date;
	private String location;
	private String subject;
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
		toReturn.append(date.toString());
		
		return toReturn.toString();
	}
	/**
	 * 
	 * @param subject	the subject of the exam
	 * @param location	the location of the exam
	 * @param datetime	the date/time of the exam
	 */
	public Exam(String subject, String location, Date datetime){
		setDate(datetime);
		setLocation(location);
		setSubject(subject);
	}
	/**
	 * 
	 * @param subject	the subject of the exam
	 * @param location	the location of the exam
	 * @param datetime	the date/time of the exam
	 */
	public Exam(String subject, String location, String datetime){
		setDate(datetime);
		setLocation(location);
		setSubject(subject);
	}
	public static void main(String[] a){	// for testing
		Exam exam = new Exam("Computing 1", "The Internet", "25/12/2016 09:00");	// What a miserable time for an exam
	}
}
