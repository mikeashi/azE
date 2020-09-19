package de.uni_stuttgart.est.project.Services;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import de.uni_stuttgart.est.project.models.Calendar;
import de.uni_stuttgart.est.project.models.Repositorys.CalendarRepository;

/**
 * @author MikeAshi
 *
 */
public class CalendarService {
	public static boolean calendarIsEmpty() {
		List<Calendar> calendar = CalendarRepository.getAll();
		return calendar.size() == 0;
	}

	public static void loadCalendar() {
		LocalDate today = new DateTime().toLocalDate();
		// check if the Calendar is Empty
		if (calendarIsEmpty()) {
			// create the first day record
			CalendarRepository.add(today);
		}
	}
}
