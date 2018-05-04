package de.uni_stuttgart.est.project.models.Repositorys;

import org.hibernate.Session;

import de.uni_stuttgart.est.project.Services.db;
/**
 * 
 * @author MikeAshi
 *
 */
public class WorkhoursRepository {
	public static  Session session = db.getSession();
	
	public static void addTodaysRecord() {
		//
	}
}
