package de.uni_stuttgart.est.project.modelsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.AccessControlException;

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
	private User admin;
	private User user;
	private Calendar day;
	
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
	void test_addRecord() {
		session.beginTransaction();
		admin = session.get(User.class, "admin");
		session.getTransaction().commit();
		session.beginTransaction();
		day = session.get(Calendar.class, 1);
		session.getTransaction().commit();
		//////////////////////////////////////////////
		// adding admin Workhours Record
		LoginSystemController.login("admin", "pass");
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours workhoursAdmin = new Workhours(admin,day,"8:30","18:00","00:45","09:30");
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
		//////////////////////////////////////////////

	}
	@Test
	void test_addNewRecord() {
		addNewRecord();
	}
	
	@Test
	void test_adminCanSeeOtherUsersWorkhours() {
		addNewRecord();
		LoginSystemController.login("admin", "pass");
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours dbWorkhoursUser = session.createNativeQuery("Select * from workhours where USER_USERNAME = '"
				+ user.getUserName() + "' and DAY_ID = '" + day.getId() + "'", Workhours.class).getSingleResult();
		session.getTransaction().commit();
		//////////////////////////////////////////////
		assertEquals("09:30", dbWorkhoursUser.getArraivedAt());
		assertEquals("16:00", dbWorkhoursUser.getLeftAt());
		assertEquals("08:10", dbWorkhoursUser.getWorkhours());
		assertEquals("00:45", dbWorkhoursUser.getPause());		
	}
	@Test
	void test_adminCanSetOtherUsersWorkhours() {
		addNewRecord();
		LoginSystemController.login("admin", "pass");
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours dbWorkhoursUser = session.createNativeQuery("Select * from workhours where USER_USERNAME = '"
				+ user.getUserName() + "' and DAY_ID = '" + day.getId() + "'", Workhours.class).getSingleResult();
		session.getTransaction().commit();
		//////////////////////////////////////////////
		dbWorkhoursUser.setArraivedAt("08:30");
		dbWorkhoursUser.setLeftAt("18:30");
		dbWorkhoursUser.setWorkhours("10:00");
		dbWorkhoursUser.setPause("01:00");
		assertEquals("08:30", dbWorkhoursUser.getArraivedAt());
		assertEquals("18:30", dbWorkhoursUser.getLeftAt());
		assertEquals("10:00", dbWorkhoursUser.getWorkhours());
		assertEquals("01:00", dbWorkhoursUser.getPause());		
	}
	@Test
	void test_userCanNotSeeOtherUsersWorkhours() {
		addNewRecord();
		LoginSystemController.login("user", "12345");
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours dbWorkhoursAdmin = session.createNativeQuery("Select * from workhours where USER_USERNAME = '"
				+ admin.getUserName() + "' and DAY_ID = '" + day.getId() + "'", Workhours.class).getSingleResult();
		session.getTransaction().commit();
		//////////////////////////////////////////////
		try {
			dbWorkhoursAdmin.getArraivedAt();
		}catch(AccessControlException e) { 
			assertEquals("You don't have the privileges to see ArraivedAt for another user", e.getMessage());
		}
		try {
			dbWorkhoursAdmin.getLeftAt();
		}catch(AccessControlException e) {
			assertEquals("You don't have the privileges to see LeftAt for another user", e.getMessage());
		}
		try {
			dbWorkhoursAdmin.getPause();
		}catch(AccessControlException e) {
			assertEquals("You don't have the privileges to see Pause for another user", e.getMessage());
		}
		try {
			dbWorkhoursAdmin.getWorkhours();
		}catch(AccessControlException e) {
			assertEquals("You don't have the privileges to see Workhours for another user", e.getMessage());
		}
	}
	@Test
	void test_userCanNotSetOtherUsersWorkhours() {
		addNewRecord();
		LoginSystemController.login("user", "12345");
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours dbWorkhoursAdmin = session.createNativeQuery("Select * from workhours where USER_USERNAME = '"
				+ admin.getUserName() + "' and DAY_ID = '" + day.getId() + "'", Workhours.class).getSingleResult();
		session.getTransaction().commit();
		//////////////////////////////////////////////
		try {
			dbWorkhoursAdmin.setArraivedAt("8:00");
		}catch(AccessControlException e) { 
			assertEquals("You don't have the privileges to add arraivedAt for another user", e.getMessage());
		}
		try {
			dbWorkhoursAdmin.setLeftAt("13:00");
		}catch(AccessControlException e) {
			assertEquals("You don't have the privileges to add leftAt for another user", e.getMessage());
		}
		try {
			dbWorkhoursAdmin.setPause("00:30");
		}catch(AccessControlException e) {
			assertEquals("You don't have the privileges to add Pause for another user", e.getMessage());
		}
		try {
			dbWorkhoursAdmin.setWorkhours("15:00");
		}catch(AccessControlException e) {
			assertEquals("You don't have the privileges to add Workhours for another user", e.getMessage());
		}
	}
	private void addNewRecord() {
		session.beginTransaction();
		admin = session.get(User.class, "admin");
		session.getTransaction().commit();
		session.beginTransaction();
		user = session.get(User.class, "user");
		session.getTransaction().commit();
		session.beginTransaction();
		day = session.get(Calendar.class, 1);
		session.getTransaction().commit();
		//////////////////////////////////////////////
		// adding admin Workhours Record
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
		//////////////////////////////////////////////
		// adding user Workhours Record
		LoginSystemController.login("user", "12345");
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours workhoursUser = new Workhours();
		workhoursUser.setUser(user);
		workhoursUser.setDay(day);
		workhoursUser.setArraivedAt("09:30");
		workhoursUser.setLeftAt("16:00");
		workhoursUser.setPause("00:45");
		workhoursUser.setWorkhours("08:10");
		session.save(workhoursUser);
		session.getTransaction().commit();
		//////////////////////////////////////////////
		session.beginTransaction();
		Workhours dbworkhoursUser = session.createNativeQuery("Select * from workhours where USER_USERNAME = '"
				+ user.getUserName() + "' and DAY_ID = '" + day.getId() + "'", Workhours.class).getSingleResult();
		session.getTransaction().commit();
		//////////////////////////////////////////////
		assertEquals("09:30", dbworkhoursUser.getArraivedAt());
		assertEquals("16:00", dbworkhoursUser.getLeftAt());
		assertEquals("08:10", dbworkhoursUser.getWorkhours());
		assertEquals("00:45", dbworkhoursUser.getPause());
		//////////////////////////////////////////////
	}

}
