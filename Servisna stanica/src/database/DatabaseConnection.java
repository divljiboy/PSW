package database;

import java.sql.Connection;

import javax.swing.JOptionPane;

/**
 * A singleton class representing the connection with the database.
 * All traffic between the application and the database is conducted through the {@link Connection} object in this class.
 * 
 * @author Borko Arsović
 *
 */
public class DatabaseConnection {

	private static DatabaseConnection instance = null;

	private Connection conn = null;

	/**
	 * Returns the only instance of the {@link DatabaseConnection} for this application.
	 * If there is no {@code DatabaseConnection} object, a new one will be created.
	 * 
	 * @return {@link DatabaseConnection}
	 */
	public static DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

	/**
	 * Constructor for the {@link DatabaseConnection} class.
	 * It uses the JDBC driver for the specified database to establish a connection.
	 * 
	 */
	private DatabaseConnection() {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * Returns the {@link Connection} object between the application and the database.
	 * @return {@link Connection}
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * Sets the {@link Connection} object between the application and the database.
	 * @param conn
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
