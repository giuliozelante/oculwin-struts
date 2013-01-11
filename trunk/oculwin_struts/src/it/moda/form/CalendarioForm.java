package it.moda.form;

import it.moda.dto.AgendaDettaglioDTO;
import it.moda.dto.CalendarioDTO;
import it.moda.dto.PazienteDTO;
import it.moda.utils.Paginator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionMapping;

@SuppressWarnings("serial")
public class CalendarioForm extends BaseForm {
	private List<CalendarioDTO> listCalendario;
	private List<AgendaDettaglioDTO> appuntamenti;
	private Paginator paginator;
	private boolean firstTime;
	private List<PazienteDTO> pazienti;
//	private Integer id;
@SuppressWarnings({ "unchecked", "rawtypes" })
	//	private Date data;
//	private Boolean festivo;
//	private String message;
//	private Integer maxAgeC1;
//	private Integer maxAgeC2;
//	private Integer maxAgeRr;
//	private Integer maxAgeR;
//	private Boolean eliminato;
//	private Integer totaleR;
//	private Integer totaleRr;
//	private Integer totaleC8;
//	private Integer totaleC11;
//	private Integer totaleI;
//	private Integer totaleM;
//	private Integer maxAgeB;
//	private String totaleB;
//	
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public Date getData() {
//		return data;
//	}
//	public void setData(Date data) {
//		this.data = data;
//	}
//	public Boolean getFestivo() {
//		return festivo;
//	}
//	public void setFestivo(Boolean festivo) {
//		this.festivo = festivo;
//	}
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
//	public Integer getMaxAgeC1() {
//		return maxAgeC1;
//	}
//	public void setMaxAgeC1(Integer maxAgeC1) {
//		this.maxAgeC1 = maxAgeC1;
//	}
//	public Integer getMaxAgeC2() {
//		return maxAgeC2;
//	}
//	public void setMaxAgeC2(Integer maxAgeC2) {
//		this.maxAgeC2 = maxAgeC2;
//	}
//	public Integer getMaxAgeRr() {
//		return maxAgeRr;
//	}
//	public void setMaxAgeRr(Integer maxAgeRr) {
//		this.maxAgeRr = maxAgeRr;
//	}
//	public Integer getMaxAgeR() {
//		return maxAgeR;
//	}
//	public void setMaxAgeR(Integer maxAgeR) {
//		this.maxAgeR = maxAgeR;
//	}
//	public Boolean getEliminato() {
//		return eliminato;
//	}
//	public void setEliminato(Boolean eliminato) {
//		this.eliminato = eliminato;
//	}
//	public Integer getTotaleR() {
//		return totaleR;
//	}
//	public void setTotaleR(Integer totaleR) {
//		this.totaleR = totaleR;
//	}
//	public Integer getTotaleRr() {
//		return totaleRr;
//	}
//	public void setTotaleRr(Integer totaleRr) {
//		this.totaleRr = totaleRr;
//	}
//	public Integer getTotaleC8() {
//		return totaleC8;
//	}
//	public void setTotaleC8(Integer totaleC8) {
//		this.totaleC8 = totaleC8;
//	}
//	public Integer getTotaleC11() {
//		return totaleC11;
//	}
//	public void setTotaleC11(Integer totaleC11) {
//		this.totaleC11 = totaleC11;
//	}
//	public Integer getTotaleI() {
//		return totaleI;
//	}
//	public void setTotaleI(Integer totaleI) {
//		this.totaleI = totaleI;
//	}
//	public Integer getTotaleM() {
//		return totaleM;
//	}
//	public void setTotaleM(Integer totaleM) {
//		this.totaleM = totaleM;
//	}
//	public Integer getMaxAgeB() {
//		return maxAgeB;
//	}
//	public void setMaxAgeB(Integer maxAgeB) {
//		this.maxAgeB = maxAgeB;
//	}
//	public String getTotaleB() {
//		return totaleB;
//	}
//	public void setTotaleB(String totaleB) {
//		this.totaleB = totaleB;
//	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.listCalendario = ListUtils.lazyList(new ArrayList(),
		        new Factory() {
		            public Object create() {
		                return new CalendarioDTO();
		            }
		        });
		this.appuntamenti = ListUtils.lazyList(new ArrayList(),
		        new Factory() {
		            public Object create() {
		                return new AgendaDettaglioDTO();
		            }
		        });
		this.pazienti = ListUtils.lazyList(new ArrayList(),
		        new Factory() {
		            public Object create() {
		                return new PazienteDTO();
		            }
		        });
		this.paginator = new Paginator();
		this.setFirstTime(true);
//		this.id = null;
//		this.data = new Date();
//		this.festivo = false;
//		this.message = null;
//		this.maxAgeC1 = null;
//		this.maxAgeC2  = null;
//		this.maxAgeRr  = null;
//		this.maxAgeR   = null;
//		this.eliminato = null;
//		this.totaleR   = null;
//		this.totaleRr  = null;
//		this.totaleC8  = null;
//		this.totaleC11 = null;
//		this.totaleI   = null;
//		this.totaleM   = null;
//		this.maxAgeB   = null;
//		this.totaleB   = null;
	}
	public List<CalendarioDTO> getListCalendario() {
		return listCalendario;
	}
	public void setListCalendario(List<CalendarioDTO> listCalendario) {
		this.listCalendario = listCalendario;
	}
	public Paginator getPaginator() {
		return paginator;
	}
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
	public boolean isFirstTime() {
		return firstTime;
	}
	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}
	public List<AgendaDettaglioDTO> getAppuntamenti() {
		return appuntamenti;
	}
	public void setAppuntamenti(List<AgendaDettaglioDTO> appuntamenti) {
		this.appuntamenti = appuntamenti;
	}
	public List<PazienteDTO> getPazienti() {
		return pazienti;
	}
	public void setPazienti(List<PazienteDTO> pazienti) {
		this.pazienti = pazienti;
	}
}
