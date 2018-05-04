package de.uni_stuttgart.est.project.Services;

import java.util.ArrayList;

import org.joda.time.LocalDate;

public class WorkDays {
	public static ArrayList<Integer> Workdays= new ArrayList<>();
	public static boolean isWorkday(LocalDate day) {
		Workdays.add(1);
		Workdays.add(2);
		Workdays.add(3);
		Workdays.add(4);
		Workdays.add(5);
		if (Workdays.contains(day.getDayOfWeek()))
		  return true;
	  return false;
	}
	
}
