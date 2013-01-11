package it.moda.dao;

import it.moda.dto.AgendaDettaglioDTO;
import it.moda.dto.CalendarioDTO;
import it.moda.utils.ConnectionFactory;
import it.moda.utils.Paginator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

public class CalendarioDAO extends GenericDAO{

	private static Logger log = Logger.getLogger(ConnectionFactory.class);
	Connection con=null;
	PreparedStatement ptmt=null;
	ResultSet rs=null;

	public CalendarioDAO()
	{

	}
	public CalendarioDAO(Paginator paginator,int rowNums) {
		super.setPaginator(paginator);
		super.getPaginator().setRowNums(rowNums);
	}



	public Paginator getPaginator(String table){
		try{
			StringBuffer sb;
			con=getConnection();
			//	if(pag.getTotRows()==0){
			sb = new StringBuffer("SELECT count(*) FROM "+table+ " WHERE DATA>=CURDATE()");
			ptmt=con.prepareStatement(sb.toString());
			rs=ptmt.executeQuery();
			if(rs.next()){this.getPaginator().setTotRows(rs.getInt(1));}
			
		}
		catch(SQLException e)
		{
			log.error(e.getMessage(),e);
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
				log.error(e.getMessage(),e);
			}
			catch(Exception e)
			{
				log.error(e.getMessage(),e);
			}

		}
		return new Paginator(this.getPaginator().getPage(), this.getPaginator().getRowNums(), this.getPaginator().getTotRows());
	}
	
	public List<CalendarioDTO> findAll(boolean firstTime)
	{
		List<CalendarioDTO> appuntamenti=new ArrayList<CalendarioDTO>();
		CalendarioDTO calendarioDTO=null;
		StringBuffer sb=null;
		try
		{
			con=getConnection();
//			if(firstTime){
//				sb = new StringBuffer("SELECT * FROM CALENDARIO ORDER BY DATA ");
//				ptmt=con.prepareStatement(sb.toString());
//				rs=ptmt.executeQuery();
//				int pageCount=0;
//				int recordCount=0;
//				GregorianCalendar today=(GregorianCalendar) GregorianCalendar.getInstance();
//				today.clear(GregorianCalendar.HOUR);
//				today.clear(GregorianCalendar.MINUTE);
//				today.clear(GregorianCalendar.SECOND);
//				today.clear(GregorianCalendar.MILLISECOND);
//				while(rs.next())
//				{
//					GregorianCalendar dataDB=(GregorianCalendar) GregorianCalendar.getInstance();
//					dataDB.setTime(rs.getDate("DATA"));
//					dataDB.clear(GregorianCalendar.HOUR);
//					dataDB.clear(GregorianCalendar.MINUTE);
//					dataDB.clear(GregorianCalendar.SECOND);
//					dataDB.clear(GregorianCalendar.MILLISECOND);
//					if(dataDB.compareTo(today)>=0 && recordCount<this.getPaginator().getRowNums())
//					{
//						calendarioDTO = new CalendarioDTO();
//
//						calendarioDTO.setId        (rs.getInt("ID"));
//						calendarioDTO.setData      (rs.getDate("Data"));
//						calendarioDTO.setFestivo   (rs.getBoolean("Festivo"));
//						calendarioDTO.setMessage   (rs.getString("Message"));
//						calendarioDTO.setMaxAgeC1  (rs.getInt("Max_age_C1"));
//						calendarioDTO.setMaxAgeC2  (rs.getInt("Max_age_C2"));
//						calendarioDTO.setMaxAgeRr  (rs.getInt("Max_age_RR"));
//						calendarioDTO.setMaxAgeR   (rs.getInt("Max_age_R"));
//						calendarioDTO.setEliminato (rs.getBoolean("Eliminato"));
//						calendarioDTO.setTotaleR   (rs.getInt("Totale_R"));
//						calendarioDTO.setTotaleRr  (rs.getInt("Totale_RR"));
//						calendarioDTO.setTotaleC8  (rs.getInt("Totale_c8"));
//						calendarioDTO.setTotaleC11 (rs.getInt("Totale_c11"));
//						calendarioDTO.setTotaleI   (rs.getInt("Totale_I"));
//						calendarioDTO.setTotaleM   (rs.getInt("Totale_M"));
//						calendarioDTO.setMaxAgeB   (rs.getInt("Max_Age_B"));
//						calendarioDTO.setTotaleB   (rs.getString("Totale_B"));
//
//						appuntamenti.add(calendarioDTO);
//						recordCount++;
//					}
//					
//					if(pageCount>=this.getPaginator().getRowNums()){
//						this.getPaginator().setPage(this.getPaginator().getPage()+1);
//						pageCount=0;
//					}else{
//						pageCount++;
//					}
//				}
//			}else{
				sb = new StringBuffer("SELECT * FROM CALENDARIO WHERE DATA >=CURDATE() ORDER BY DATA LIMIT ?, ?");
				ptmt=con.prepareStatement(sb.toString());
				ptmt.setInt(1, this.getPaginator().getPage()*this.getPaginator().getRowNums());
				ptmt.setInt(2, this.getPaginator().getRowNums());
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
//			}
		}
		catch(SQLException e)
		{
			log.error(e.getMessage(),e);
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
				log.error(e.getMessage(),e);
			}
			catch(Exception e)
			{
				log.error(e.getMessage(),e);
			}

		}
		return appuntamenti;
	}
	
	public List<AgendaDettaglioDTO> findByData(Date data){
		//SELECT [Agenda Dettaglio].mcod, [Agenda Dettaglio].dataora, [Agenda Dettaglio].Note, [Agenda Dettaglio].ORA, [Agenda Dettaglio].Ti_age, [Agenda Dettaglio].Ti_ope_age, [Agenda Dettaglio].PTEL, [Agenda Dettaglio].Visita, [Agenda Dettaglio].pden, [Agenda Dettaglio].Pg_age, [Agenda Dettaglio].pnascita
//		FROM [Agenda Dettaglio]
//				WHERE ((([Agenda Dettaglio].dataora)=[Forms]![Agenda Resina]![Sottomaschera Agenda resina].[Form]![Data]) AND (([Agenda Dettaglio].Ti_age)="R"))
//				ORDER BY [Agenda Dettaglio].ORA;
		List<AgendaDettaglioDTO> appuntamenti=new ArrayList<AgendaDettaglioDTO>();
		AgendaDettaglioDTO agendaDettaglioDTO=null;
		StringBuffer sb=null;
		try
		{
			con=getConnection();

				sb = new StringBuffer("SELECT * FROM `AGENDA DETTAGLIO` WHERE dataora = ? AND Ti_age = 'R' ORDER BY ORA ");
				ptmt=con.prepareStatement(sb.toString());
				ptmt.setDate(1, new java.sql.Date(data.getTime()));

				rs=ptmt.executeQuery();
				while(rs.next())
				{
					agendaDettaglioDTO = new AgendaDettaglioDTO();

					agendaDettaglioDTO.setPgAge(rs.getInt("Pg_age"));
					agendaDettaglioDTO.setMcod(rs.getInt("mcod"));
					agendaDettaglioDTO.setPden(rs.getString("pden"));
					agendaDettaglioDTO.setPtel(rs.getString("PTEL"));
					agendaDettaglioDTO.setDataora(rs.getDate("dataora"));
					agendaDettaglioDTO.setOra(rs.getString("ORA"));
					agendaDettaglioDTO.setNote(rs.getString("Note"));
					agendaDettaglioDTO.setTiAge(rs.getString("Ti_age"));
					agendaDettaglioDTO.setTiOpeAge(rs.getString("Ti_ope_age"));
					agendaDettaglioDTO.setVisita(rs.getBoolean("Visita"));
					agendaDettaglioDTO.setPnascita(rs.getDate("pnascita"));

					appuntamenti.add(agendaDettaglioDTO);
				}
//			}
		}
		catch(SQLException e)
		{
			log.error(e.getMessage(),e);
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
				log.error(e.getMessage(),e);
			}
			catch(Exception e)
			{
				log.error(e.getMessage(),e);
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
	//			log.error(e.getMessage(),e);
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
	//				log.error(e.getMessage(),e);
	//			}
	//			catch(Exception e)
	//			{
	//				log.error(e.getMessage(),e);
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
	//			log.error(e.getMessage(),e);
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
	//				log.error(e.getMessage(),e);
	//			}
	//			catch(Exception e)
	//			{
	//				log.error(e.getMessage(),e);
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
	//			log.error(e.getMessage(),e);
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
	//				log.error(e.getMessage(),e);
	//			}
	//			catch(Exception e)
	//			{
	//				log.error(e.getMessage(),e);
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
	//			log.error(e.getMessage(),e);
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
	//				log.error(e.getMessage(),e);
	//			}
	//			catch(Exception e)
	//			{
	//				log.error(e.getMessage(),e);
	//			}
	//
	//		}
	//
	//	}

}
