package it.moda.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class AgendaDettaglioBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3319528243031366055L;
	private Integer pgAge;
	private Integer mcod;
	private String pden;
	private String ptel;
	private String dataora;
	private String ora;
	private String note;
	private String tiAge;
	private String tiOpeAge;
	private Boolean visita;
	private String pnascita;
	private Date datePnascita;
	private Boolean deleted;

	public AgendaDettaglioBean() {
	}

	public AgendaDettaglioBean(Integer pgAge, Integer mcod, String pden,
			String ptel, String dataora, String ora, String note, String tiAge,
			String tiOpeAge, Boolean visita, String pnascita, Boolean deleted) {
		this.pgAge = pgAge;
		this.mcod = mcod;
		this.pden = pden;
		this.ptel = ptel;
		this.dataora = dataora;
		this.ora = ora;
		this.note = note;
		this.tiAge = tiAge;
		this.tiOpeAge = tiOpeAge;
		this.visita = visita;
		this.pnascita = pnascita;
		this.deleted = deleted;
	}

	@Column(name = "Pg_age")
	public Integer getPgAge() {
		return this.pgAge;
	}

	public void setPgAge(Integer pgAge) {
		this.pgAge = pgAge;
	}

	@Column(name = "mcod")
	public Integer getMcod() {
		return this.mcod;
	}

	public void setMcod(Integer mcod) {
		this.mcod = mcod;
	}

	@Column(name = "pden", length = 150)
	public String getPden() {
		return this.pden;
	}

	public void setPden(String pden) {
		this.pden = pden;
	}

	@Column(name = "PTEL", length = 60)
	public String getPtel() {
		return this.ptel;
	}

	public void setPtel(String ptel) {
		this.ptel = ptel;
	}

	@Column(name = "dataora", length = 19)
	public String getDataora() {
		return this.dataora;
	}

	public void setDataora(String dataora) {
		this.dataora = dataora;
	}

	@Column(name = "ORA", length = 5)
	public String getOra() {
		return this.ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	@Column(name = "Note", length = 50)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "Ti_age", length = 1)
	public String getTiAge() {
		return this.tiAge;
	}

	public void setTiAge(String tiAge) {
		this.tiAge = tiAge;
	}

	@Column(name = "Ti_ope_age", length = 1)
	public String getTiOpeAge() {
		return this.tiOpeAge;
	}

	public void setTiOpeAge(String tiOpeAge) {
		this.tiOpeAge = tiOpeAge;
	}

	@Column(name = "Visita")
	public Boolean getVisita() {
		return this.visita;
	}

	public void setVisita(Boolean visita) {
		this.visita = visita;
	}

	@Column(name = "pnascita", length = 19)
	public String getPnascita() {
		return this.pnascita;
	}

	public void setPnascita(String pnascita) {
		this.pnascita = pnascita;
	}

	public Date getDatePnascita() {
		return datePnascita;
	}

	public void setDatePnascita(Date datePnascita) {
		this.datePnascita = datePnascita;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
