package com.github.marceloleite2604.firstcup.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

@Entity
// Insert code here
@NamedQuery(name = "findAverageAgeDifferenceOfAllFirstcupUsers", query = "SELECT avg(u.ageDifference) FROM FirstcupUser u")
public class FirstcupUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(javax.persistence.TemporalType.DATE)
	protected Calendar birthday;
	protected int ageDifference;

	public FirstcupUser() {
	}

	public FirstcupUser(Date date, int difference) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		birthday = calendar;
		ageDifference = difference;
	}

	/**
	 * Get the value of ageDifference
	 *
	 * @return the value of ageDifference
	 */
	public int getAgeDifference() {
		return ageDifference;
	}

	/**
	 * Set the value of ageDifference
	 *
	 * @param ageDifference
	 *            new value of ageDifference
	 */
	public void setAgeDifference(int ageDifference) {
		this.ageDifference = ageDifference;
	}

	/**
	 * Get the value of birthday
	 *
	 * @return the value of birthday
	 */
	public Calendar getBirthday() {
		return birthday;
	}

	/**
	 * Set the value of birthday
	 *
	 * @param birthday
	 *            new value of birthday
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// Warning - this method won't work in the case the id fields are not
		// set.
		if (!(object instanceof FirstcupUser)) {
			return false;
		}
		FirstcupUser other = (FirstcupUser) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "${package}.entity.FirstcupUser[id=" + id + "]";
	}
}