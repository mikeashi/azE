package de.uni_stuttgart.est.project.ServicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.uni_stuttgart.est.project.Services.CalendarService;
import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.models.Calendar;
import de.uni_stuttgart.est.project.models.Repositorys.CalendarRepository;

public class CalendarServiceTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CalendarRepository.session = db.getnewTestSession();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		CalendarRepository.session = db.getSession();
	}

	@BeforeEach
	void setUp() throws Exception {
		CalendarRepository.session = db.getnewTestSession();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_isEmpty() {
		assertTrue(CalendarService.calendarIsEmpty());
		CalendarRepository.add(2018, 5, 1, false);
		assertFalse(CalendarService.calendarIsEmpty());
	}
	@Test
	void test_load() {
		LocalDate today = new DateTime().toLocalDate();
		CalendarService.loadCalendar();
		Session session = CalendarRepository.session;
 		session.beginTransaction();
		Calendar calendar = session.get(Calendar.class, 1);
		assertEquals(today.getYear(),calendar.getYear());
 		assertEquals(today.getMonthOfYear(),calendar.getMonth());
 		assertEquals(today.getDayOfMonth(),calendar.getDay());
	}
}
