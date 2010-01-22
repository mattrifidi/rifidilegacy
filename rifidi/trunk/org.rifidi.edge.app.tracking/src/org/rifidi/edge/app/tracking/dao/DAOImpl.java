package org.rifidi.edge.app.tracking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * An implementation of the ProductsDAO and the LogicalReadersDAO
 * 
 * @author Kyle Neumeier - kyle@pramari.comF
 * 
 */
public class DAOImpl implements ProductsDAO, LogicalReadersDAO {

	/** The Spring datasource object */
	private SimpleDriverDataSource datasource = null;

	@Override
	public String getProductName(String ID) {
		if (datasource == null)
			return ID;
		try {
			Connection conn = datasource.getConnection();
			if (conn == null) {
				return ID;
			}
			Statement statement = conn.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT name FROM products where tagid = "
							+ ID.toUpperCase());
			while (rs.next()) {
				String name = rs.getString("name");
				if (name != null) {
					return name;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ID;
	}

	@Override
	public String getLogicaReaderName(String readerID, int antenna) {
		if (datasource == null)
			return new String(readerID + ":" + antenna);

		try {
			Connection conn = datasource.getConnection();
			if (conn == null) {
				System.out.println("conn is null " + datasource.getUrl());
				return new String(readerID + ":" + antenna);
			}
			Statement statement = conn.createStatement();
			System.out.println(readerID + " " + antenna);
			ResultSet rs = statement
					.executeQuery("SELECT name FROM logicalreaders where readerid = "
							+ readerID);
			while (rs.next()) {
				// For some reason, I couldn't get AND operator to work in where
				// clause. must filter antenna here.
				int rowAntenna = rs.getInt("antenna");
				if (rowAntenna == antenna) {
					String name = rs.getString("name");
					if (name != null) {
						return name;
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new String(readerID + ":" + antenna);
	}

	/**
	 * Set by spring
	 * 
	 * @param datasource
	 *            the datasource to set
	 */
	public void setDatasource(SimpleDriverDataSource datasource) {
		this.datasource = datasource;
	}

}
