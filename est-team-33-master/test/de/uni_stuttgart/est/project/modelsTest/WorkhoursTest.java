package de.uni_stuttgart.est.project.modelsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.uni_stuttgart.est.project.Controllers.LoginSystemController;
import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.models.Calendar;
import de.uni_stuttgart.est.project.models.User;
import de.uni_stuttgart.est.project.models.Workhours;
import de.uni_stuttgart.est.project.models.Repositorys.CalendarRepository;
import de.uni_stuttgart.est.project.models.Repositorys.UsersRepository;

class WorkhoursTest {
	private static Session session;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = db.getnewTestSession();
		UsersRepository.session = session;
		CalendarRepository.session = session;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		UsersRepository.session = db.getSession();
		CalendarRepository.session = db.getSession();
	}

	@BeforeEach
	void setUp() throws Exception {
		session = db.getnewTestSession();
		CalendarRepository.session = session;
		UsersRepository.session = session;
		// add admin
		UsersRepository.add(1, "admin", "admin", "admin", "pass");
		// add user
		UsersRepository.add(0, "user", "user", "user", "12345");
		// add new Calendar Record
		CalendarRepository.session = session;
		CalendarRepository.add(2018, 5, 1, true);
	}

	@Test
	void test_addNewRecord() {
		addNewRecord();
	}

	private void addNewRecord() {
		session.beginTransaction();
		User admin = session.get(User.class, "admin");
		session.getTransaction().commit();
		session.beginTransaction();
		User user = session.get(User.class, "user");
		session.getTransaction().commit();
		session.beginTransaction();
		Calendar day = session.get(Calendar.class, 1);
		session.getTransaction().commit();
		//////////////////////////////////////////////
		LoginSystemController.login("admin", "pass");
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours workhoursAdmin = new Workhours();
		workhoursAdmin.setUser(admin);
		workhoursAdmin.setDay(day);
		workhoursAdmin.setArraivedAt("8:30");
		workhoursAdmin.setLeftAt("18:00");
		workhoursAdmin.setPause("00:45");
		workhoursAdmin.setWorkhours("09:30");
		System.out.println(workhoursAdmin.toString());
		session.save(workhoursAdmin);
		session.getTransaction().commit();
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours dbWorkhoursAdmin = session.createNativeQuery("Select * from workhours where USER_USERNAME = '"
				+ admin.getUserName() + "' and DAY_ID = '" + day.getId() + "'", Workhours.class).getSingleResult();
		session.getTransaction().commit();
		//////////////////////////////////////////////
		assertEquals("8:30", dbWorkhoursAdmin.getArraivedAt());
		assertEquals("18:00", dbWorkhoursAdmin.getLeftAt());
		assertEquals("09:30", dbWorkhoursAdmin.getWorkhours());
		assertEquals("00:45", dbWorkhoursAdmin.getPause());
	}

}
