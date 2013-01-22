package it.moda.dao;

import it.moda.bean.AgendaDettaglioBean;
import it.moda.bean.PazienteBean;
import it.moda.dto.CalendarioDTO;
import it.moda.utils.ConnectionFactory;
import it.moda.utils.Paginator;
import it.moda.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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



	public Paginator getPaginator(String table) throws SQLException{
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
			throw e;
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
				throw e;
			}
			catch(Exception e)
			{
				throw e;
			}

		}
		return new Paginator(this.getPaginator().getPage(), this.getPaginator().getRowNums(), this.getPaginator().getTotRows());
	}

	public List<CalendarioDTO> findAll(boolean firstTime) throws SQLException
	{
		List<CalendarioDTO> appuntamenti=new ArrayList<CalendarioDTO>();
		CalendarioDTO calendarioDTO=null;
		StringBuffer sb=null;
		try
		{
			con=getConnection();
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
				calendarioDTO.setTotaleB   (rs.getInt("Totale_B"));

				appuntamenti.add(calendarioDTO);
			}
		}
		catch(SQLException e)
		{
			throw e;
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
				throw e;
			}
			catch(Exception e)
			{
				throw e;
			}

		}
		return appuntamenti;
	}

	public List<AgendaDettaglioBean> findByData(Date data) throws SQLException{
		//SELECT [Agenda Dettaglio].mcod, [Agenda Dettaglio].dataora, [Agenda Dettaglio].Note, [Agenda Dettaglio].ORA, [Agenda Dettaglio].Ti_age, [Agenda Dettaglio].Ti_ope_age, [Agenda Dettaglio].PTEL, [Agenda Dettaglio].Visita, [Agenda Dettaglio].pden, [Agenda Dettaglio].Pg_age, [Agenda Dettaglio].pnascita
		//		FROM [Agenda Dettaglio]
		//				WHERE ((([Agenda Dettaglio].dataora)=[Forms]![Agenda Resina]![Sottomaschera Agenda resina].[Form]![Data]) AND (([Agenda Dettaglio].Ti_age)="R"))
		//				ORDER BY [Agenda Dettaglio].ORA;
		List<AgendaDettaglioBean> appuntamenti=new ArrayList<AgendaDettaglioBean>();
		AgendaDettaglioBean agendaDettaglioDTO=null;
		StringBuffer sb=null;
		try
		{
			con=getConnection();

			sb = new StringBuffer();
			sb.append("SELECT *, ");
			sb.append("       REPLACE(ora, '.', ':') AS newOra ");
			sb.append("FROM   `agenda dettaglio` ");
			sb.append("WHERE  dataora = ? ");
			sb.append("       AND ti_age = 'R' ");
			sb.append("       AND ( deleted IS NULL ");
			sb.append("              OR deleted = ( 0 ) ) ");
			sb.append("ORDER  BY ora ");
			ptmt=con.prepareStatement(sb.toString());
			ptmt.setDate(1, new java.sql.Date(data.getTime()));

			rs=ptmt.executeQuery();
			while(rs.next())
			{
				agendaDettaglioDTO = new AgendaDettaglioBean();

				agendaDettaglioDTO.setPgAge(rs.getInt("Pg_age"));
				agendaDettaglioDTO.setMcod(rs.getInt("mcod"));
				agendaDettaglioDTO.setPden(rs.getString("pden"));
				agendaDettaglioDTO.setPtel(rs.getString("PTEL"));
				agendaDettaglioDTO.setDataora(rs.getString("dataora"));
				agendaDettaglioDTO.setOra(rs.getString("newOra"));
				agendaDettaglioDTO.setNote(rs.getString("Note"));
				agendaDettaglioDTO.setTiAge(rs.getString("Ti_age"));
				agendaDettaglioDTO.setTiOpeAge(rs.getString("Ti_ope_age"));
				agendaDettaglioDTO.setVisita(rs.getBoolean("Visita"));
				agendaDettaglioDTO.setPnascita(Utils.formatDateForInput(rs.getDate("pnascita")));
				agendaDettaglioDTO.setDatePnascita(rs.getDate("pnascita"));

				appuntamenti.add(agendaDettaglioDTO);
			}
			//			}
		}
		catch(SQLException e)
		{
			throw e;
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
				throw e;
			}
			catch(Exception e)
			{
				throw e;
			}

		}
		return appuntamenti;

	}

	public List<PazienteBean> fillPazientiList() throws SQLException{
		//		SELECT [03OIPAZI].MCOD, [03OIPAZI].PDEN, [03OIPAZI].PNASCITA
		//		FROM 03OIPAZI
		//		ORDER BY [03OIPAZI].PDEN, [03OIPAZI].PNASCITA;
		List<PazienteBean> pazienti = new ArrayList<PazienteBean>();
		PazienteBean pazienteDTO = null;

		StringBuffer sb=null;
		try{
			con=getConnection();

			sb = new StringBuffer("SELECT MCOD, PDEN, PNASCITA FROM 03OIPAZI ORDER BY PDEN, PNASCITA ");
			ptmt=con.prepareStatement(sb.toString());

			rs=ptmt.executeQuery();

			while(rs.next())
			{
				pazienteDTO = new PazienteBean();

				pazienteDTO.setMcod(rs.getInt("mcod"));
				pazienteDTO.setPden(rs.getString("pden"));
				pazienteDTO.setPnascita(rs.getDate("pnascita")!=null?Utils.formatDate(rs.getDate("pnascita")):"");
				pazienteDTO.setDatePnascita(rs.getDate("pnascita"));
				//pazienteDTO.setDescPnascita(rs.getDate("pnascita")!=null?Utils.formatDate(rs.getDate("pnascita")):"");

				pazienti.add(pazienteDTO);
			}

		}catch(SQLException e)
		{
			throw e;
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
				throw e;
			}
			catch(Exception e)
			{
				throw e;
			}

		}
		return pazienti;

	}

	/**---------Delete Appuntamento-----	
	 Dim db As Database
	   Dim rsCalendario As DAO.Recordset
	   Dim rsAgenda As DAO.Recordset

	   Set db = CurrentDb
	   Me.AllowAdditions = False
	   'Aggiorna totali su Calendario
	   Set rsCalendario = db.OpenRecordset("CALENDARIO", dbOpenDynaset)
	   rsCalendario.FindFirst "DATA= " & "#" & Format(Forms![AGENDA RESINA]!Data, "mm/dd/yyyy") & "#"
	   If Not rsCalendario.NoMatch Then
	      Select Case Me!Ti_ope_age
	      Case "I"
	         rsCalendario.Edit
	         rsCalendario!Totale_I = Nz(rsCalendario!Totale_I, 0) - 1
	         '*** BABY
	         If mBaby = "B" Then
	            rsCalendario!Totale_B = Nz(rsCalendario!Totale_B, 0) - 1
	         End If
	         rsCalendario.Update
	      Case "R"
	         rsCalendario.Edit
	         rsCalendario!Totale_RR = Nz(rsCalendario!Totale_RR, 0) - 1
	         '*** BABY
	         If mBaby = "B" Then
	           rsCalendario!Totale_B = Nz(rsCalendario!Totale_B, 0) - 1
	         End If
	         rsCalendario.Update
	      Case "M"
	         rsCalendario.Edit
	         rsCalendario!Totale_M = Nz(rsCalendario!Totale_M, 0) - 1
	         rsCalendario.Update
	      'Case "B"
	      '   rsCalendario.Edit
	      '   rsCalendario!Totale_B = Nz(rsCalendario!Totale_B, 0) - 1
	      '   rsCalendario.Update
	      Case Else
	         rsCalendario.Edit
	         rsCalendario!Totale_R = Nz(rsCalendario!Totale_R, 0) - 1
	         rsCalendario.Update
	      End Select

	   End If
	   Set rsAgenda = db.OpenRecordset("Agenda Dettaglio", dbOpenDynaset)
	   rsAgenda.FindFirst "PG_AGE = " & Me!Pg_age
	   If Not rsAgenda.NoMatch Then
	      rsAgenda.Delete
	   End If
	   'DoCmd.DoMenuItem acFormBar, acEditMenu, 8, , acMenuVer70
	   'DoCmd.DoMenuItem acFormBar, acEditMenu, 6, , acMenuVer70

	   Me!PDEN.Enabled = False
	   Me!PTEL.Enabled = False
	   Me!ORA.Enabled = False
	   Me!NOTE.Enabled = False
	   'NoteAgg.Enabled = False
	   Me!Ti_ope_age.Enabled = False
	   If IsNull(Me!MCOD) Then
	      Nuovo.Enabled = True
	      DoCmd.GoToControl ("NUOVO")
	      Elimina.Enabled = False
	   Else
	      Elimina.Enabled = True
	   End If
	   Conferma.Enabled = False
	   Annulla.Enabled = False
	   Modifica.Enabled = True
	   'Riposizionamento sul giorno selezionato
	   DoCmd.RunMacro ("trova")
	   DoCmd.GoToRecord , , acGoTo, (lngrecordnum)
	   'Me.Requery
	   rsCalendario.Close
	   Set rsCalendario = Nothing
	 * 
	 * @param data
	 * @param appuntamento
	 * @return
	 * @throws Exception 
	 */
	public CalendarioDTO deleteAppuntamento(Date data,AgendaDettaglioBean appuntamento) throws Exception{
		CalendarioDTO calendarioDTO = null;
		StringBuffer sb = null;
		int result = 0; 
		try{
			con=getConnection();
			con.setAutoCommit(false);
			//XXX:Aggiorno il calendario
			calendarioDTO = aggiornaCalendario(calendarioDTO, sb, data, appuntamento, result, ptmt, false);

			//XXX:Cancello l'appuntamento (LOGICAMENTE non FISICAMENTE)
			con.setAutoCommit(false);
			sb = new StringBuffer();
			sb.append("UPDATE `agenda dettaglio` ");
			sb.append("SET    deleted = ? ");
			sb.append("WHERE  pg_age = ? ");
			ptmt=con.prepareStatement(sb.toString());
			ptmt.setBoolean(1, true);
			ptmt.setInt(2, appuntamento.getPgAge());
			result = ptmt.executeUpdate();
			if(result==0)
				throw new Exception("Logical Delete Failed");
			con.commit();
		}catch(Exception e){
			if(con!=null){
				try{
					if(con!=null){
						con.rollback();
					}
				}
				catch(SQLException ex)
				{
					throw ex;
				}
				catch(Exception ex)
				{
					throw ex;
				}
			}
			throw e;
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(ptmt!=null)
					ptmt.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw e;
			}

		}

		return calendarioDTO;
	}

	public void saveAllAppointments(Date data,List<AgendaDettaglioBean> appuntamenti) throws Exception{
		CalendarioDTO calendarioDTO = null;
		AgendaDettaglioBean appuntamento = null;
		StringBuffer sb = null;
		int result = 0; 
		try{
			con=getConnection();
			//XXX:Aggiorno il calendario
			for(Iterator i = appuntamenti.iterator();i.hasNext();){
				appuntamento = (AgendaDettaglioBean)i.next();
				con.setAutoCommit(false);
				if(appuntamento.getPgAge()==0 && appuntamento.getTiOpeAge().equalsIgnoreCase("I")){
					GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
					gc.setTime(data);
					if(gc.get(GregorianCalendar.DAY_OF_WEEK)==6)
						gc.add(GregorianCalendar.DATE, 3);
					else
						gc.add(GregorianCalendar.DATE, 1);
				
					sb = new StringBuffer();
					sb.append("SELECT data, ");
					sb.append("       festivo ");
					sb.append("FROM   calendario ");
					sb.append("WHERE  data = ? ");
					ptmt = con.prepareStatement(sb.toString());
					ptmt.setDate(1, new java.sql.Date(gc.getTimeInMillis()));
					rs = ptmt.executeQuery();
					con.setAutoCommit(false);
					
					if(rs.next()){
						if(rs.getBoolean("FESTIVO")&&gc.get(GregorianCalendar.DAY_OF_WEEK)==6)
							gc.add(GregorianCalendar.DATE, 3);
						else if(rs.getBoolean("FESTIVO")&&gc.get(GregorianCalendar.DAY_OF_WEEK)!=6)
							gc.add(GregorianCalendar.DATE, 1);
						
						sb = new StringBuffer();
						sb.append("UPDATE `03oinfat` ");
						sb.append("SET    pg_age = ( pg_age + 1 ) ");
						sb.append("WHERE  anno = ? ");
						ptmt=con.prepareStatement(sb.toString());
						ptmt.setInt(1, gc.get(GregorianCalendar.YEAR));
						if(ptmt.executeUpdate()==0)
							throw new Exception("Update Failed");

						appuntamento.setPgAge(appuntamento.getPgAge()+1);	
					}
					sb = new StringBuffer();
					sb.append("INSERT INTO `agenda dettaglio` ");
					sb.append("            (mcod, ");
					sb.append("             pden, ");
					sb.append("             ptel, ");
					sb.append("             pnascita, ");
					sb.append("             dataora, ");
					sb.append("             pg_age, ");
					sb.append("             ora, ");
					sb.append("             ti_age, ");
					sb.append("             ti_ope_age, ");
					sb.append("             note) ");
					sb.append("VALUES      (?, ");
					sb.append("             ?, ");
					sb.append("             ?, ");
					sb.append("             ?, ");
					sb.append("             ?, ");
					sb.append("             ?, ");
					sb.append("             ?, ");
					sb.append("             ?, ");
					sb.append("             ?, ");
					sb.append("             ?) ");
					ptmt=con.prepareStatement(sb.toString());
					ptmt.setInt(1,appuntamento.getMcod());
					ptmt.setString(2,appuntamento.getPden());
					ptmt.setString(3,appuntamento.getPtel());
					ptmt.setDate(4,new java.sql.Date(Utils.parseDate(appuntamento.getPnascita()).getTime()));
					ptmt.setDate(5, new java.sql.Date(gc.getTimeInMillis()));
					ptmt.setInt(6,appuntamento.getPgAge());
					ptmt.setString(7, "12.30");
					ptmt.setString(8, appuntamento.getTiAge());
					ptmt.setString(9, "A");
					ptmt.setString(10, "CONSEGNA");
					con.setAutoCommit(false);
					if(ptmt.executeUpdate()==0)
						throw new Exception("Update Failed");
					aggiornaCalendario(calendarioDTO, sb, gc.getTime(), appuntamento, result, ptmt, true);
				}
				String oldTiOpeAge = getOldTiOpeAge(appuntamento, sb, ptmt, rs);
				if(appuntamento.getPgAge()!=0 && !oldTiOpeAge.equalsIgnoreCase("I") && appuntamento.getTiOpeAge().equalsIgnoreCase("I")){
					throw new Exception("Appuntamento consegna non inserito,verificare il giorno successivo");
				}else{
					sb=new StringBuffer();
					sb.append("UPDATE `agenda dettaglio` ");
					sb.append("SET    mcod = ?, ");
					sb.append("       pden = ?, ");
					sb.append("       ptel = ?, ");
					sb.append("       pnascita = ?, ");
					sb.append("       dataora = ?, ");
					sb.append("       pg_age = ?, ");
					sb.append("       ora = ?, ");
					sb.append("       ti_age = ?, ");
					sb.append("       ti_ope_age = ?, ");
					sb.append("       note = ? ");
					sb.append("WHERE  dataora = ? ");
					ptmt = con.prepareStatement(sb.toString());
					ptmt.setInt(1, appuntamento.getMcod());
					ptmt.setString(2, appuntamento.getPden());
					ptmt.setString(3, appuntamento.getPtel());
					ptmt.setDate(4, new java.sql.Date(Utils.parseDate(appuntamento.getPnascita()).getTime()));
					ptmt.setDate(5, new java.sql.Date(Utils.parseDate(appuntamento.getDataora()).getTime()));
					ptmt.setInt(6, appuntamento.getPgAge());
					ptmt.setString(7, appuntamento.getOra());
					ptmt.setString(8, appuntamento.getTiAge());
					ptmt.setString(9, appuntamento.getTiOpeAge());
					ptmt.setString(10, appuntamento.getNote());
					ptmt.setDate(11, new java.sql.Date(data.getTime()));
					if(ptmt.executeUpdate()==0)
						throw new Exception("Update Failed");
					calendarioDTO = aggiornaCalendario(calendarioDTO, sb, data, appuntamento, result, ptmt, true);
				}
				
			}
			con.commit();
		}catch(Exception e){
			if(con!=null){
				try{
					if(con!=null){
						con.rollback();
					}
				}
				catch(SQLException ex)
				{
					throw ex;
				}
				catch(Exception ex)
				{
					throw ex;
				}
			}
			throw e;
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(ptmt!=null)
					ptmt.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw e;
			}

		}

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
	//			throw e;
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
	//				throw e;
	//			}
	//			catch(Exception e)
	//			{
	//				throw e;
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
	//			throw e;
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
	//				throw e;
	//			}
	//			catch(Exception e)
	//			{
	//				throw e;
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
	//			throw e;
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
	//				throw e;
	//			}
	//			catch(Exception e)
	//			{
	//				throw e;
	//			}
	//
	//		}
	//
	//	}
	//
	private CalendarioDTO aggiornaCalendario(CalendarioDTO calendarioDTO, StringBuffer sb,Date data,AgendaDettaglioBean appuntamento,int result,PreparedStatement ptmt,boolean save) throws Exception {
		sb = new StringBuffer("SELECT * FROM CALENDARIO WHERE DATA = ?");
		ptmt=con.prepareStatement(sb.toString());
		ptmt.setDate(1, new java.sql.Date(data.getTime()));
		rs=ptmt.executeQuery();
		if(rs.next()){
			calendarioDTO=new CalendarioDTO();
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
			calendarioDTO.setTotaleB   (rs.getInt("Totale_B"));
		}
		//XXX:Aggiorno il Calendario
		int eta=Utils.calculateAge((Utils.parseDate(appuntamento.getPnascita())));
		//XXX:controllo se è salvataggio o cancellazione
		if(save){
			if(appuntamento.getPgAge()==0){
				//XXX: Nuovo appuntamento
				sb = new StringBuffer("UPDATE 03OINFAT SET PG_AGE = (PG_AGE + 1) WHERE ANNO = ?");
				ptmt=con.prepareStatement(sb.toString());
				GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
				gc.setTime(data);
				ptmt.setInt(1, gc.get(GregorianCalendar.YEAR));
				if(ptmt.executeUpdate()==0)
					throw new Exception("Update Failed");

				appuntamento.setPgAge(appuntamento.getPgAge()+1);

				switch (appuntamento.getTiOpeAge()) {
				case "I":
					calendarioDTO.setTotaleI(Integer.valueOf(calendarioDTO.getTotaleI().intValue()!=0?calendarioDTO.getTotaleI().intValue()+1:0));
					if(eta<=Utils.ETA)
						calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()+1:0));

					break;
				case "R":
					calendarioDTO.setTotaleRr(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleRr().intValue()+1:0));
					if(eta<=Utils.ETA)
						calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()+1:0));
					break;
				case "M":
					calendarioDTO.setTotaleM(Integer.valueOf(calendarioDTO.getTotaleM().intValue()!=0?calendarioDTO.getTotaleM().intValue()+1:0));
					break;

				default:
					calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleR().intValue()!=0?calendarioDTO.getTotaleR().intValue()+1:0));
					break;
				}
			}else{
				String oldTiOpeAge = getOldTiOpeAge(appuntamento, sb, ptmt, rs);
				if(!appuntamento.getTiOpeAge().equalsIgnoreCase(oldTiOpeAge)){
					switch (appuntamento.getTiOpeAge()) {
					case "I":
						if(oldTiOpeAge.equalsIgnoreCase("A")||oldTiOpeAge.equalsIgnoreCase("L")||oldTiOpeAge.equalsIgnoreCase("C")){
							calendarioDTO.setTotaleI(Integer.valueOf(calendarioDTO.getTotaleI().intValue()!=0?calendarioDTO.getTotaleI().intValue()+1:0));
							calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleR().intValue()-1:0));
							if(eta<=Utils.ETA)
								calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()+1:0));
						}
						if(oldTiOpeAge.equalsIgnoreCase("M")){
							calendarioDTO.setTotaleI(Integer.valueOf(calendarioDTO.getTotaleI().intValue()!=0?calendarioDTO.getTotaleI().intValue()+1:0));
							calendarioDTO.setTotaleM(Integer.valueOf(calendarioDTO.getTotaleM().intValue()!=0?calendarioDTO.getTotaleM().intValue()-1:0));
							if(eta<=Utils.ETA)
								calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()+1:0));
						}
						if(oldTiOpeAge.equalsIgnoreCase("R")){
							calendarioDTO.setTotaleI(Integer.valueOf(calendarioDTO.getTotaleI().intValue()!=0?calendarioDTO.getTotaleI().intValue()+1:0));
							calendarioDTO.setTotaleRr(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleRr().intValue()-1:0));
						}
						break;
					case "M":
						if(oldTiOpeAge.equalsIgnoreCase("A")||oldTiOpeAge.equalsIgnoreCase("L")||oldTiOpeAge.equalsIgnoreCase("C")){
							calendarioDTO.setTotaleM(Integer.valueOf(calendarioDTO.getTotaleM().intValue()!=0?calendarioDTO.getTotaleM().intValue()+1:0));
							calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleR().intValue()-1:0));
						}
						if(oldTiOpeAge.equalsIgnoreCase("I")){
							calendarioDTO.setTotaleM(Integer.valueOf(calendarioDTO.getTotaleM().intValue()!=0?calendarioDTO.getTotaleM().intValue()+1:0));
							calendarioDTO.setTotaleI(Integer.valueOf(calendarioDTO.getTotaleI().intValue()!=0?calendarioDTO.getTotaleI().intValue()-1:0));
							if(eta<=Utils.ETA)
								calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()-1:0));
						}
						if(oldTiOpeAge.equalsIgnoreCase("R")){
							calendarioDTO.setTotaleM(Integer.valueOf(calendarioDTO.getTotaleM().intValue()!=0?calendarioDTO.getTotaleM().intValue()+1:0));
							calendarioDTO.setTotaleRr(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleRr().intValue()-1:0));
							if(eta<=Utils.ETA)
								calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()-1:0));
						}
						break;
					case "A": case "L": case "C":
						if(oldTiOpeAge.equalsIgnoreCase("I")){
							calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleR().intValue()!=0?calendarioDTO.getTotaleR().intValue()+1:0));
							calendarioDTO.setTotaleI(Integer.valueOf(calendarioDTO.getTotaleI().intValue()!=0?calendarioDTO.getTotaleI().intValue()-1:0));
						}
						if(oldTiOpeAge.equalsIgnoreCase("M")){
							calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleR().intValue()!=0?calendarioDTO.getTotaleR().intValue()+1:0));
							calendarioDTO.setTotaleM(Integer.valueOf(calendarioDTO.getTotaleM().intValue()!=0?calendarioDTO.getTotaleM().intValue()-1:0));
						}
						if(oldTiOpeAge.equalsIgnoreCase("R")){
							calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleR().intValue()!=0?calendarioDTO.getTotaleR().intValue()+1:0));
							calendarioDTO.setTotaleRr(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleRr().intValue()-1:0));
						}

					case "R":
						if(oldTiOpeAge.equalsIgnoreCase("I")){
							calendarioDTO.setTotaleRr(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleRr().intValue()+1:0));
							calendarioDTO.setTotaleI(Integer.valueOf(calendarioDTO.getTotaleI().intValue()!=0?calendarioDTO.getTotaleI().intValue()-1:0));
						}
						if(oldTiOpeAge.equalsIgnoreCase("M")){
							calendarioDTO.setTotaleRr(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleRr().intValue()+1:0));
							calendarioDTO.setTotaleM(Integer.valueOf(calendarioDTO.getTotaleM().intValue()!=0?calendarioDTO.getTotaleM().intValue()-1:0));
							if(eta<=Utils.ETA)
								calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()+1:0));
						}
						if(oldTiOpeAge.equalsIgnoreCase("A")||oldTiOpeAge.equalsIgnoreCase("L")||oldTiOpeAge.equalsIgnoreCase("C")){
							calendarioDTO.setTotaleRr(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleRr().intValue()+1:0));
							calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleR().intValue()-1:0));
						}
						break;
					default:
						calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleR().intValue()!=0?calendarioDTO.getTotaleR().intValue()+1:0));
						break;
					}
				}

			}
		}else{
			switch (appuntamento.getTiOpeAge()) {
			case "I":
				calendarioDTO.setTotaleI(Integer.valueOf(calendarioDTO.getTotaleI().intValue()!=0?calendarioDTO.getTotaleI().intValue()-1:0));
				if(eta<=Utils.ETA)
					calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()-1:0));

				break;
			case "R":
				calendarioDTO.setTotaleRr(Integer.valueOf(calendarioDTO.getTotaleRr().intValue()!=0?calendarioDTO.getTotaleRr().intValue()-1:0));
				if(eta<=Utils.ETA)
					calendarioDTO.setTotaleB(Integer.valueOf(calendarioDTO.getTotaleB().intValue()!=0?calendarioDTO.getTotaleB().intValue()-1:0));
				break;
			case "M":
				calendarioDTO.setTotaleM(Integer.valueOf(calendarioDTO.getTotaleM().intValue()!=0?calendarioDTO.getTotaleM().intValue()-1:0));
				break;

			default:
				calendarioDTO.setTotaleR(Integer.valueOf(calendarioDTO.getTotaleR().intValue()!=0?calendarioDTO.getTotaleR().intValue()-1:0));
				break;
			}
		}

		con.setAutoCommit(false);
		sb = new StringBuffer();
		sb.append("UPDATE calendario ");
		sb.append("SET    totale_i = ?, ");
		sb.append("       totale_rr = ?, ");
		sb.append("       totale_m = ?, ");
		sb.append("       totale_r = ?, ");
		sb.append("       totale_b = ? ");
		sb.append("WHERE  data = ? ");

		ptmt=con.prepareStatement(sb.toString());
		ptmt.setInt(1, calendarioDTO.getTotaleI().intValue());
		ptmt.setInt(2, calendarioDTO.getTotaleRr().intValue());
		ptmt.setInt(3, calendarioDTO.getTotaleM().intValue());
		ptmt.setInt(4, calendarioDTO.getTotaleR().intValue());
		ptmt.setInt(5, calendarioDTO.getTotaleB().intValue());
		ptmt.setDate(6, new java.sql.Date(data.getTime()));
		result = ptmt.executeUpdate();
		if(result==0)
			throw new Exception("Update Failed");
		return calendarioDTO;
	}
	private String getOldTiOpeAge(AgendaDettaglioBean appuntamento,StringBuffer sb,PreparedStatement ptmt,ResultSet rs) throws SQLException{
		sb = new StringBuffer();
		sb.append("SELECT *, ");
		sb.append("       from `agenda dettaglio` ");
		sb.append("WHERE  dataora = ? ");
		sb.append("       AND ti_age = 'R' ");
		sb.append("       AND ( deleted IS NULL ");
		sb.append("              OR deleted = ( 0 ) ) ");
		sb.append("       AND pg_age = ? ");
		ptmt=con.prepareStatement(sb.toString());
		ptmt.setDate(1, new java.sql.Date(Utils.parseDate(appuntamento.getDataora()).getTime()));
		ptmt.setInt(2, appuntamento.getPgAge());
		rs=ptmt.executeQuery();
		String oldTiOpeAge="";
		if(rs.next())
			oldTiOpeAge = rs.getString("Ti_ope_age");
		return oldTiOpeAge;
	}

}
