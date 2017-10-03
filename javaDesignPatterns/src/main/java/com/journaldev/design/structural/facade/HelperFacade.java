package com.journaldev.design.structural.facade;

import java.sql.Connection;

/**
 * <p>
 * A class to simplify the use of the {@link MySqlHelper} and
 * {@link OracleHelper} classes. Using only this class, the user can generate a
 * HTML or PDF report through a Oracle or a MySQL database, with no need to
 * instantiate an object for each one of them.
 * </p>
 * <p>
 * As the name implies, the Facade interface works as a facade to simplify the
 * using of the set of interfaces by classes consuming them.
 * </p>
 * 
 * @see <a href=
 *      "https://www.journaldev.com/1557/facade-design-pattern-in-java">JournalDev
 *      Java Facade Design Pattern</a>
 */
public class HelperFacade {

	public static void generateReport(DBTypes dbType, ReportTypes reportType, String tableName) {
		Connection connection = null;

		switch (dbType) {
		case MYSQL:
			connection = MySqlHelper.getMySqlDBConnection();
			MySqlHelper mySqlHelper = new MySqlHelper();
			switch (reportType) {
			case HTML:
				mySqlHelper.generateMySqlHTMLReport(tableName, connection);
				break;
			case PDF:
				mySqlHelper.generateMySqlPDFReport(tableName, connection);
				break;
			}
			break;
		case ORACLE:
			connection = OracleHelper.getOracleDBConnection();
			OracleHelper oracleHelper = new OracleHelper();
			switch (reportType) {
			case HTML:
				oracleHelper.generateOracleHTMLReport(tableName, connection);
				break;
			case PDF:
				oracleHelper.generateOraclePDFReport(tableName, connection);
				break;
			}
			break;
		}
	}

	public static enum DBTypes {
		MYSQL, ORACLE;
	}

	public static enum ReportTypes {
		HTML, PDF;
	}
}