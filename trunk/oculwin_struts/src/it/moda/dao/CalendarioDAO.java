package it.moda.dao;

import it.moda.dto.CalendarioDTO;
import it.moda.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalendarioDAO {

	Connection con=null;
	PreparedStatement ptmt=null;
	ResultSet rs=null;

	public CalendarioDAO()
	{

	}

	private Connection getConnection() throws SQLException
	{
		Connection conn;
		conn=ConnectionFactory.getInstance().getConnection();
		return conn;
	}
//	public void add(CalendarioDTO calendarioDTO)
//	{
//
//		try
//		{
//			String querystring="INSERT INTO EMPLOYEE VALUES(?,?,?,?,?)";
//			con=getConnection();
//			ptmt=con.prepareStatement(querystring);
//			ptmt.setString(1, String.valueOf(System.currentTimeMillis()));
//			ptmt.setString(2, calendarioDTO.getEmpName());
//			ptmt.setString(3, calendarioDTO.getEmpAddr());
//			ptmt.setString(4, calendarioDTO.getEmpEmail());
//			ptmt.setString(5, calendarioDTO.getEmpPhone());
//			ptmt.executeUpdate();
//
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				if(rs!=null)
//					rs.close();
//				if(ptmt!=null)
//					ptmt.close();
//				if(con!=null)
//					con.close();
//			}
//			catch(SQLException e)
//			{
//				e.printStackTrace();
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//
//	}
//	public void update(CalendarioDTO calendarioDTO)
//	{
//
//		try
//		{
//			String querystring="UPDATE EMPLOYEE SET EMP_NAME=?,EMP_ADDR=?," +
//					"EMP_EMAIL=?,EMP_PHONE=? WHERE EMP_ID=?";
//			con=getConnection();
//			ptmt=con.prepareStatement(querystring);
//
//			ptmt.setString(1, calendarioDTO.getEmpName());
//			ptmt.setString(2, calendarioDTO.getEmpAddr());
//			ptmt.setString(3, calendarioDTO.getEmpEmail());
//			ptmt.setString(4, calendarioDTO.getEmpPhone());
//			ptmt.setString(5, calendarioDTO.getEmpId());
//			ptmt.executeUpdate();
//
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				if(rs!=null)
//					rs.close();
//				if(ptmt!=null)
//					ptmt.close();
//				if(con!=null)
//					con.close();
//			}
//			catch(SQLException e)
//			{
//				e.printStackTrace();
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//
//	}
//
//	public void delete(String employeeId)
//	{
//
//		try
//		{
//			String querystring="DELETE FROM EMPLOYEE WHERE EMPID=?";
//			con=getConnection();
//			ptmt=con.prepareStatement(querystring);
//			ptmt.setString(1, employeeId);
//			ptmt.executeUpdate();
//
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				if(rs!=null)
//					rs.close();
//				if(ptmt!=null)
//					ptmt.close();
//				if(con!=null)
//					con.close();
//			}
//			catch(SQLException e)
//			{
//				e.printStackTrace();
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//
//	}

	public List<CalendarioDTO> findAll()
	{
		List<CalendarioDTO> appuntamenti=new ArrayList<CalendarioDTO>();
		CalendarioDTO calendarioDTO=null;
		try
		{
			StringBuffer sb = new StringBuffer("SELECT * FROM CALENDARIO ORDER BY DATA");
			con=getConnection();
			ptmt=con.prepareStatement(sb.toString());
			rs=ptmt.executeQuery();
			while(rs.next())
			{
				calendarioDTO = new CalendarioDTO();
				calendarioDTO.setId        (rs.getInt("ID"));
				calendarioDTO.setData      (rs.getDate("Data"));
				calendarioDTO.setFestivo   (rs.getBoolean("Festivo"));
				calendarioDTO.setMessage   (rs.getString("Message"));
				calendarioDTO.setMaxAgeC1  (rs.getInt("Max_age_C1"));
				calendarioDTO.setMaxAgeC2  (rs.getInt("Max_age_C2"));
				calendarioDTO.setMaxAgeRr  (rs.getInt("Max_age_RR"));
				calendarioDTO.setMaxAgeR   (rs.getInt("Max_age_R"));
				calendarioDTO.setEliminato (rs.getBoolean("Eliminato"));
				calendarioDTO.setTotaleR   (rs.getInt("Totale_R"));
				calendarioDTO.setTotaleRr  (rs.getInt("Totale_RR"));
				calendarioDTO.setTotaleC8  (rs.getInt("Totale_c8"));
				calendarioDTO.setTotaleC11 (rs.getInt("Totale_c11"));
				calendarioDTO.setTotaleI   (rs.getInt("Totale_I"));
				calendarioDTO.setTotaleM   (rs.getInt("Totale_M"));
				calendarioDTO.setMaxAgeB   (rs.getInt("Max_Age_B"));
				calendarioDTO.setTotaleB   (rs.getString("Totale_B"));

				appuntamenti.add(calendarioDTO);
			}
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
		return appuntamenti;
	}
//	public CalendarioDTO findByPrimaryKey(String empId)
//	{
//
//		CalendarioDTO calendarioDTO=null;
//		try
//		{
//			String querystring="SELECT * FROM EMPLOYEE WHERE EMP_ID=?";
//			con=getConnection();
//			ptmt=con.prepareStatement(querystring);
//			ptmt.setString(1, empId);
//			rs=ptmt.executeQuery();
//			if(rs.next())
//			{
//				calendarioDTO=new CalendarioDTO();
//				calendarioDTO.setEmpId(rs.getString(1));
//				calendarioDTO.setEmpName(rs.getString(2));
//				calendarioDTO.setEmpAddr(rs.getString(3));
//				calendarioDTO.setEmpEmail(rs.getString(4));
//				calendarioDTO.setEmpPhone(rs.getString(5));
//
//
//			}
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				if(rs!=null)
//					rs.close();
//				if(ptmt!=null)
//					ptmt.close();
//				if(con!=null)
//					con.close();
//			}
//			catch(SQLException e)
//			{
//				e.printStackTrace();
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//
//		}
//		return calendarioDTO;
//	}
//	public static void main(String[] args) {
//
//		EmployeeJdbcDAO employeeDAO=new EmployeeJdbcDAO();
//		System.out.println(employeeDAO.findAll().size());
//	}
}
