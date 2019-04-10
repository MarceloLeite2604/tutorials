package com.github.marceloleite2604.dukesage;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("dukesAge")
public class DukesAgeResource {

	@Context
	private UriInfo context;

	/**
	 * Default constructor.
	 */
	public DukesAgeResource() {

	}

	/**
	 * Retrieves representation of an instance of DukesAgeResource
	 * 
	 * @return an instance of String
	 */
	@GET
	@Produces("text/plain")
	public String getText() {
		// Create a new Calendar for Duke's birthday
		Calendar dukesBirthday = new GregorianCalendar(1973, Calendar.APRIL, 25);
		// Create a new Calendar for today
		Calendar now = GregorianCalendar.getInstance();

		// Subtract today's year from Duke's birth year, 1995
		int dukesAge = now.get(Calendar.YEAR) - dukesBirthday.get(Calendar.YEAR);
		dukesBirthday.add(Calendar.YEAR, dukesAge);

		// If today's date is before May 23, subtract a year from Duke's age
		if (now.before(dukesBirthday)) {
			dukesAge--;
		}
		// Return a String representation of Duke's age
		return "" + dukesAge;
	}

	/**
	 * PUT method for updating or creating an instance of DukesAgeResource
	 * 
	 * @param content
	 *            representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
	@Consumes("text/plain")
	public void putText(String content) {
	}

}