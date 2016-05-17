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
public class Exam {
	private Date date;
	private String location;
	private String subject;
	
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
		DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy HH:mm");
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
	public Exam(String subject, String location, String datetime){
		setDate(datetime);
		setLocation(location);
		setSubject(subject);
	}

}
