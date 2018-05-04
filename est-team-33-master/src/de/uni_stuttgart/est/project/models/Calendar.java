package de.uni_stuttgart.est.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calendar")
public class Calendar {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int year;
	private int month;
	private int day;
	private boolean isWorkingDay;
		
	public Calendar() {
	}
	
	public Calendar(int year, int month, int day, boolean isWorkingDay) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.isWorkingDay = isWorkingDay;
	}

	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public boolean isWorkingDay() {
		return isWorkingDay;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setWorkingDay(boolean isWorkingDay) {
		this.isWorkingDay = isWorkingDay;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
