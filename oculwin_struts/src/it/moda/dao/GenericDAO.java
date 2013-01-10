package it.moda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.moda.utils.ConnectionFactory;
import it.moda.utils.Paginator;

public class GenericDAO {
	private Paginator paginator;

	public Paginator getPaginator() {
		return paginator;
	}
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	private Connection con;
	private PreparedStatement ptmt;
	private ResultSet rs;
	
	public GenericDAO() {
		// TODO Auto-generated constructor stub
	}
	public GenericDAO(Paginator paginator,int rowNums) {
		this.paginator=paginator;
		this.paginator.setRowNums(rowNums);
	}

	public Paginator getPaginator(String table){
		try{
			StringBuffer sb;
			con=getConnection();
			//	if(pag.getTotRows()==0){
			sb = new StringBuffer("SELECT count(*) FROM "+table);
			ptmt=con.prepareStatement(sb.toString());
			rs=ptmt.executeQuery();
			if(rs.next()){paginator.setTotRows(rs.getInt(1));}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(ptmt!=null)
					ptmt.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
		return new Paginator(paginator.getPage(), paginator.getRowNums(), paginator.getTotRows());
	}

	public Connection getConnection() throws SQLException
	{
		Connection conn;
		conn=ConnectionFactory.getInstance().getConnection();
		return conn;
	}
}
