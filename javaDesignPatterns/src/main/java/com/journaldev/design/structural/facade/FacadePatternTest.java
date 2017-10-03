package com.journaldev.design.structural.facade;

import java.sql.Connection;

/**
 * The Facade interface provides a unified interface to a set of interfaces in a
 * subsystem. It defines a higher level interface that makes the subsystem
 * easier to use.
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1557/facade-design-pattern-in-java">JournalDev
 *      Java Facade Design Pattern</a>
 */
public class FacadePatternTest {

	public static void main(String[] args) {
		String tableName = "Employee";

		// Without using the facade interface, we need to retrieve a connection and
		// instantiate an object for each type of database to generate a report.
		Connection connection = MySqlHelper.getMySqlDBConnection();
		MySqlHelper mySqlHelper = new MySqlHelper();
		mySqlHelper.generateMySqlHTMLReport(tableName, connection);

		connection = OracleHelper.getOracleDBConnection();
		OracleHelper oracleHelper = new OracleHelper();
		oracleHelper.generateOraclePDFReport(tableName, connection);

		// Using the Facade interface, there is no need to instantiate the object. It is
		// only necessary to request the report generation informing the database type.
		HelperFacade.generateReport(HelperFacade.DBTypes.MYSQL, HelperFacade.ReportTypes.HTML, tableName);
		HelperFacade.generateReport(HelperFacade.DBTypes.ORACLE, HelperFacade.ReportTypes.PDF, tableName);
	}

}