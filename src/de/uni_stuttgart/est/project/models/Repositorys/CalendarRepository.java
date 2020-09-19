package de.uni_stuttgart.est.project.models.Repositorys;

import java.util.List;

import org.hibernate.Session;
import org.joda.time.LocalDate;

import de.uni_stuttgart.est.project.Services.WorkDays;
import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.models.Calendar;

/**
 * 
 * @author MikeAshi
 *
 */
public class CalendarRepository {
	public static Session session = db.getSession();

	/**
	 * 
	 * @param calendar
	 */
	public static void add(Calendar calendar) {
		session.beginTransaction();
		session.save(calendar);
		session.getTransaction().commit();
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param isWorkingDay
	 */
	public static void add(int year, int month, int day, boolean isWorkingDay) {
		session.beginTransaction();
		Calendar calendar = new Calendar(year, month, day, isWorkingDay);
		session.save(calendar);
		session.getTransaction().commit();
	}

	/**
	 * 
	 * @param today
	 */
	public static void add(LocalDate today) {
		add(today.getYear(), today.getMonthOfYear(), today.getDayOfMonth(), WorkDays.isWorkday(today));
	}

	/**
	 * 
	 * @return List<Calendar>
	 */
	public static List<Calendar> getAll() {
		session.beginTransaction();
		List<Calendar> calendar = session
				.createNativeQuery("Select * from Calendar", de.uni_stuttgart.est.project.models.Calendar.class)
				.getResultList();
		session.getTransaction().commit();
		return calendar;
	}
}
