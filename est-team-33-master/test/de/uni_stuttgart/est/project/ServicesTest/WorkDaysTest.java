package de.uni_stuttgart.est.project.ServicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import de.uni_stuttgart.est.project.Services.WorkDays;


class WorkDaysTest {

	@Test
	void test_isWorkday() {
		assertEquals(true, WorkDays.isWorkday(DateTime.parse("2018-4-19").toLocalDate()));
		assertEquals(true, WorkDays.isWorkday(DateTime.parse("2018-4-20").toLocalDate()));
		assertEquals(false, WorkDays.isWorkday(DateTime.parse("2018-4-21").toLocalDate()));
		assertEquals(false, WorkDays.isWorkday(DateTime.parse("2018-4-22").toLocalDate()));
	}

}
