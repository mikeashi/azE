package de.uni_stuttgart.est.project.RepositorysTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.models.Calendar;
import de.uni_stuttgart.est.project.models.Repositorys.CalendarRepository;
import de.uni_stuttgart.est.project.models.Repositorys.UsersRepository;

class CalendarRepositoryTest {

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
		UsersRepository.session = db.getnewTestSession();	
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void test_add() {
		CalendarRepository.add(2018, 5, 1, false);
		Session session = CalendarRepository.session;
 		session.beginTransaction();
 		Calendar calendar = session.get(Calendar.class, 1);
 		session.getTransaction().commit();
 		assertEquals(2018,calendar.getYear());
 		assertEquals(5,calendar.getMonth());
 		assertEquals(1,calendar.getDay());
 		assertEquals(false,calendar.isWorkingDay());
	}
	@Test
	void test_addFromObj() {
		Calendar calendar = new Calendar();
		calendar.setDay(1);
		calendar.setMonth(5);
		calendar.setYear(2018);
		calendar.setWorkingDay(false);
		CalendarRepository.add(calendar);
		Session session = CalendarRepository.session;
 		session.beginTransaction();
 		calendar = session.get(Calendar.class, 1);
 		session.getTransaction().commit();
 		assertEquals(2018,calendar.getYear());
 		assertEquals(5,calendar.getMonth());
 		assertEquals(1,calendar.getDay());
 		assertEquals(false,calendar.isWorkingDay());
	}
	@Test
	void test_addFromLocalDate() {
		LocalDate day = DateTime.parse("2018-4-19").toLocalDate();
		CalendarRepository.add(day);
		Session session = CalendarRepository.session;
 		session.beginTransaction();
 		Calendar calendar = session.get(Calendar.class, 1);
		assertEquals(2018,calendar.getYear());
 		assertEquals(4,calendar.getMonth());
 		assertEquals(19,calendar.getDay());
 		assertEquals(true,calendar.isWorkingDay());
	}
	@Test
	void test_getAll() {
		CalendarRepository.add(2018, 5, 1, false);
		List<Calendar> calendar = CalendarRepository.getAll();
		assertEquals(2018,calendar.get(0).getYear());
 		assertEquals(5,calendar.get(0).getMonth());
 		assertEquals(1,calendar.get(0).getDay());
 		assertEquals(false,calendar.get(0).isWorkingDay());
	}
}
