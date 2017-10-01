package com.journaldev.design.facade;

import java.sql.Connection;

/**
 * <p>
 * A class to generate a PDF or HTML report and retrieve a connection on MySQL
 * database.
 * </p>
 * <p>
 * This class, along with {@link OracleHelper}, compose the set of interfaces
 * which must be abstracted by the Facade interface ({@link HelperFacade}).
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1557/facade-design-pattern-in-java">JournalDev
 *      Java Facade Design Pattern</a>
 */
public class MySqlHelper {

	public static Connection getMySqlDBConnection() {
		// get MySql DB connection using connection parameters
		return null;
	}

	public void generateMySqlPDFReport(String tableName, Connection connection) {
		System.out.println("Generating a PDF report for \"" + tableName + "\" table on MySQL database.");
	}

	public void generateMySqlHTMLReport(String tableName, Connection connecton) {
		System.out.println("Generating a HTML report for \"" + tableName + "\" table on MySQL database.");
	}
}