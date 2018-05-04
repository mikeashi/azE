package de.uni_stuttgart.est.project.models.Repositorys;

import java.util.List;

import org.hibernate.Session;
import org.joda.time.LocalDate;

import de.uni_stuttgart.est.project.Services.WorkDays;
import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.models.Calendar;
public class CalendarRepository {
	public static  Session session = db.getSession();
	public static List<Calendar> getAll() {
		session.beginTransaction();
		List<Calendar> calendar = session.createNativeQuery("Select * from Calendar",
				de.uni_stuttgart.est.project.models.Calendar.class).getResultList();
 		session.getTransaction().commit();
 		return calendar;
	}
	public static void add(int year,int month, int day, boolean isWorkingDay) {
		session.beginTransaction();
		Calendar calendar = new Calendar(year,month,day,isWorkingDay);
		session.save(calendar);
 		session.getTransaction().commit();
	}
	public static void add(LocalDate today) {
		add(today.getYear(),today.getMonthOfYear(),today.getDayOfMonth(),WorkDays.isWorkday(today));
	}
	public static void add(Calendar calendar) {
		session.beginTransaction();
		session.save(calendar);
 		session.getTransaction().commit();		
	}
}
