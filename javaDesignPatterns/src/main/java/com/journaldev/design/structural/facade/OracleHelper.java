package com.journaldev.design.structural.facade;

import java.sql.Connection;

/**
 * <p>
 * A class to generate a PDF or HTML report and retrieve a connection on Oracle
 * database.
 * </p>
 * <p>
 * This class, along with {@link MySqlHelper}, compose the set of interfaces
 * which must be abstracted by the Facade interface ({@link HelperFacade}).
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1557/facade-design-pattern-in-java">JournalDev
 *      Java Facade Design Pattern</a>
 */
public class OracleHelper {

	public static Connection getOracleDBConnection() {
		// get Oracle DB connection using connection parameters
		return null;
	}

	public void generateOraclePDFReport(String tableName, Connection connection) {
		System.out.println("Generating a PDF report for \"" + tableName + "\" table on Oracle database.");
	}

	public void generateOracleHTMLReport(String tableName, Connection connection) {
		System.out.println("Generating a HTML report for \"" + tableName + "\" table on Oracle database.");
	}

}