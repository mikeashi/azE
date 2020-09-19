package de.uni_stuttgart.est.project.models;

import java.security.AccessControlException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.uni_stuttgart.est.project.Controllers.LoginSystemController;

/**
 * 
 * @author MikeAshi
 *
 */
@Entity
@Table(name = "workhours")
public class Workhours {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private User user;
	@OneToOne
	private Calendar day;
	private String ArraivedAt;
	private String leftAt;
	private String pause;
	private String Workhours;

	public Workhours() {
	}

	/**
	 * 
	 * @param user
	 * @param day
	 * @param arraivedAt
	 * @param leftAt
	 * @param pause
	 * @param workhours
	 */
	public Workhours(User user, Calendar day, String arraivedAt, String leftAt, String pause, String workhours) {
		this.user = user;
		this.day = day;
		ArraivedAt = arraivedAt;
		this.leftAt = leftAt;
		this.pause = pause;
		Workhours = workhours;
	}

	public String getArraivedAt() {
		if (!LoginSystemController.currentUser.isAdmin()) {
			if (this.getUser().getUserName() != LoginSystemController.currentUser.getUserName()) {
				throw new AccessControlException("You don't have the privileges to see ArraivedAt for another user");
			} else {
				return ArraivedAt;
			}
		} else {
			return ArraivedAt;
		}
	}

	public Calendar getDay() {
		return day;
	}

	public String getLeftAt() {
		if (!LoginSystemController.currentUser.isAdmin()) {
			if (this.getUser().getUserName() != LoginSystemController.currentUser.getUserName()) {
				throw new AccessControlException("You don't have the privileges to see LeftAt for another user");
			} else {
				return leftAt;
			}
		} else {
			return leftAt;
		}
	}

	public String getPause() {
		if (!LoginSystemController.currentUser.isAdmin()) {
			if (this.getUser().getUserName() != LoginSystemController.currentUser.getUserName()) {
				throw new AccessControlException("You don't have the privileges to see Pause for another user");
			} else {
				return pause;
			}
		} else {
			return pause;
		}
	}

	public User getUser() {
		return user;
	}

	public String getWorkhours() {
		if (!LoginSystemController.currentUser.isAdmin()) {
			if (this.getUser().getUserName() != LoginSystemController.currentUser.getUserName()) {
				throw new AccessControlException("You don't have the privileges to see Workhours for another user");
			} else {
				return Workhours;
			}
		} else {
			return Workhours;
		}
	}

	/**
	 * 
	 * @param arraivedAt
	 */
	public void setArraivedAt(String arraivedAt) {
		if (!LoginSystemController.currentUser.isAdmin()) {
			if (this.getUser().getUserName() != LoginSystemController.currentUser.getUserName()) {
				throw new AccessControlException("You don't have the privileges to add arraivedAt for another user");
			} else {
				this.ArraivedAt = arraivedAt;
			}
		} else {
			this.ArraivedAt = arraivedAt;
		}
	}

	/**
	 * 
	 * @param day
	 */
	public void setDay(Calendar day) {
		this.day = day;
	}

	/**
	 * 
	 * @param leftAt
	 */
	public void setLeftAt(String leftAt) {
		if (!LoginSystemController.currentUser.isAdmin()) {
			if (this.getUser().getUserName() != LoginSystemController.currentUser.getUserName()) {
				throw new AccessControlException("You don't have the privileges to add leftAt for another user");
			} else {
				this.leftAt = leftAt;
			}
		} else {
			this.leftAt = leftAt;
		}
	}

	/**
	 * 
	 * @param pause
	 */
	public void setPause(String pause) {
		if (!LoginSystemController.currentUser.isAdmin()) {
			if (this.getUser().getUserName() != LoginSystemController.currentUser.getUserName()) {
				throw new AccessControlException("You don't have the privileges to add Pause for another user");
			} else {
				this.pause = pause;
			}
		} else {
			this.pause = pause;
		}
	}

	/**
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 * @param workhours
	 */
	public void setWorkhours(String workhours) {
		if (!LoginSystemController.currentUser.isAdmin()) {
			if (this.getUser().getUserName() != LoginSystemController.currentUser.getUserName()) {
				throw new AccessControlException("You don't have the privileges to add Workhours for another user");
			} else {
				this.Workhours = workhours;
			}
		} else {
			this.Workhours = workhours;
		}
	}
}
