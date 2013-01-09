package it.moda.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionFactory
{
	
	private static ConnectionFactory connectionFactory=null;
	private static Logger logger = Logger.getLogger(ConnectionFactory.class);
	private DataSource ds;
	private ConnectionFactory()
	{
		try
		{
			// Obtain our environment naming context
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			// Look up our data source
			ds = (DataSource) envCtx.lookup("jdbc/OculWinDS");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}

	public Connection getConnection() throws SQLException
	{
		return ds.getConnection();
	}

	public static ConnectionFactory getInstance()
	{
		if(connectionFactory==null)
		{
			connectionFactory=new ConnectionFactory();
		}
		return connectionFactory;
	}
}