package it.moda.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Calendario generated by hbm2java
 */
@Entity
@Table(name = "calendario")
@XmlRootElement
public class CalendarioDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1549147481386462192L;
	private Integer id;
	private Date data;
	private Boolean festivo;
	private String message;
	private Integer maxAgeC1;
	private Integer maxAgeC2;
	private Integer maxAgeRr;
	private Integer maxAgeR;
	private Boolean eliminato;
	private Integer totaleR;
	private Integer totaleRr;
	private Integer totaleC8;
	private Integer totaleC11;
	private Integer totaleI;
	private Integer totaleM;
	private Integer maxAgeB;
	private Integer totaleB;

	public CalendarioDTO() {
	}

	public CalendarioDTO(Date data, Boolean festivo, String message,
			Integer maxAgeC1, Integer maxAgeC2, Integer maxAgeRr,
			Integer maxAgeR, Boolean eliminato, Integer totaleR,
			Integer totaleRr, Integer totaleC8, Integer totaleC11,
			Integer totaleI, Integer totaleM, Integer maxAgeB, Integer totaleB) {
		this.data = data;
		this.festivo = festivo;
		this.message = message;
		this.maxAgeC1 = maxAgeC1;
		this.maxAgeC2 = maxAgeC2;
		this.maxAgeRr = maxAgeRr;
		this.maxAgeR = maxAgeR;
		this.eliminato = eliminato;
		this.totaleR = totaleR;
		this.totaleRr = totaleRr;
		this.totaleC8 = totaleC8;
		this.totaleC11 = totaleC11;
		this.totaleI = totaleI;
		this.totaleM = totaleM;
		this.maxAgeB = maxAgeB;
		this.totaleB = totaleB;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Data", length = 19)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "Festivo")
	public Boolean getFestivo() {
		return this.festivo;
	}

	public void setFestivo(Boolean festivo) {
		this.festivo = festivo;
	}

	@Column(name = "Message", length = 100)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "Max_age_C1")
	public Integer getMaxAgeC1() {
		return this.maxAgeC1;
	}

	public void setMaxAgeC1(Integer maxAgeC1) {
		this.maxAgeC1 = maxAgeC1;
	}

	@Column(name = "Max_age_C2")
	public Integer getMaxAgeC2() {
		return this.maxAgeC2;
	}

	public void setMaxAgeC2(Integer maxAgeC2) {
		this.maxAgeC2 = maxAgeC2;
	}

	@Column(name = "Max_age_RR")
	public Integer getMaxAgeRr() {
		return this.maxAgeRr;
	}

	public void setMaxAgeRr(Integer maxAgeRr) {
		this.maxAgeRr = maxAgeRr;
	}

	@Column(name = "Max_age_R")
	public Integer getMaxAgeR() {
		return this.maxAgeR;
	}

	public void setMaxAgeR(Integer maxAgeR) {
		this.maxAgeR = maxAgeR;
	}

	@Column(name = "Eliminato")
	public Boolean getEliminato() {
		return this.eliminato;
	}

	public void setEliminato(Boolean eliminato) {
		this.eliminato = eliminato;
	}

	@Column(name = "Totale_R")
	public Integer getTotaleR() {
		return this.totaleR;
	}

	public void setTotaleR(Integer totaleR) {
		this.totaleR = totaleR;
	}

	@Column(name = "Totale_RR")
	public Integer getTotaleRr() {
		return this.totaleRr;
	}

	public void setTotaleRr(Integer totaleRr) {
		this.totaleRr = totaleRr;
	}

	@Column(name = "Totale_c8")
	public Integer getTotaleC8() {
		return this.totaleC8;
	}

	public void setTotaleC8(Integer totaleC8) {
		this.totaleC8 = totaleC8;
	}

	@Column(name = "Totale_c11")
	public Integer getTotaleC11() {
		return this.totaleC11;
	}

	public void setTotaleC11(Integer totaleC11) {
		this.totaleC11 = totaleC11;
	}

	@Column(name = "Totale_I")
	public Integer getTotaleI() {
		return this.totaleI;
	}

	public void setTotaleI(Integer totaleI) {
		this.totaleI = totaleI;
	}

	@Column(name = "Totale_M")
	public Integer getTotaleM() {
		return this.totaleM;
	}

	public void setTotaleM(Integer totaleM) {
		this.totaleM = totaleM;
	}

	@Column(name = "Max_Age_B")
	public Integer getMaxAgeB() {
		return this.maxAgeB;
	}

	public void setMaxAgeB(Integer maxAgeB) {
		this.maxAgeB = maxAgeB;
	}

	@Column(name = "Totale_B", length = 50)
	public Integer getTotaleB() {
		return this.totaleB;
	}

	public void setTotaleB(Integer totaleB) {
		this.totaleB = totaleB;
	}
	//=IIf((([Max_age_R]-[Num_app]>0 Or IsNull([Max_age_R]-[Num_app])) And [festivo]=0);"DISP";"COMP")
}

