package it.moda.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the 03oipazi database table.
 * 
 */
@Entity
@Table(name="03oipazi")
public class PazienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CAUSA_INT")
	private double causaInt;

	@Column(name="CAUSA2_INT")
	private double causa2Int;

	private String cellulare;

	private String cellulare2;

	@Column(name="CONT_M")
	private double contM;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_M")
	private Date dataM;

	private String entropio2;

	private String entropion;

	@Column(name="ETA_INT")
	private String etaInt;

	@Column(name="FLAG_PREV")
	private String flagPrev;

	private String fornice;

	private String fornice2;

	@Column(name="GRUP_M")
	private double grupM;

	private double mcod;

	@Lob
	private String note;

	private String papillom2;

	private String papillomi;

	private String pcap;

	private String pcitta;

	private String pden;

	private double pess;

	private double pess2;

	@Column(name="PG_CONTO")
	private double pgConto;

	private String pind;

	private String pnas;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pnascita;

	private String ppro;

	private String prot1;

	private String prot2;

	private String ptel;

	private String ptosi;

	private String ptosi2;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pulti;

	@Column(name="SOTT_M")
	private double sottM;

	private String tipo;

	@Column(name="TIPO_INT")
	private double tipoInt;

	@Column(name="TIPO2_INT")
	private double tipo2Int;

	public PazienteDTO() {
	}

	public double getCausaInt() {
		return this.causaInt;
	}

	public void setCausaInt(double causaInt) {
		this.causaInt = causaInt;
	}

	public double getCausa2Int() {
		return this.causa2Int;
	}

	public void setCausa2Int(double causa2Int) {
		this.causa2Int = causa2Int;
	}

	public String getCellulare() {
		return this.cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getCellulare2() {
		return this.cellulare2;
	}

	public void setCellulare2(String cellulare2) {
		this.cellulare2 = cellulare2;
	}

	public double getContM() {
		return this.contM;
	}

	public void setContM(double contM) {
		this.contM = contM;
	}

	public Date getDataM() {
		return this.dataM;
	}

	public void setDataM(Date dataM) {
		this.dataM = dataM;
	}

	public String getEntropio2() {
		return this.entropio2;
	}

	public void setEntropio2(String entropio2) {
		this.entropio2 = entropio2;
	}

	public String getEntropion() {
		return this.entropion;
	}

	public void setEntropion(String entropion) {
		this.entropion = entropion;
	}

	public String getEtaInt() {
		return this.etaInt;
	}

	public void setEtaInt(String etaInt) {
		this.etaInt = etaInt;
	}

	public String getFlagPrev() {
		return this.flagPrev;
	}

	public void setFlagPrev(String flagPrev) {
		this.flagPrev = flagPrev;
	}

	public String getFornice() {
		return this.fornice;
	}

	public void setFornice(String fornice) {
		this.fornice = fornice;
	}

	public String getFornice2() {
		return this.fornice2;
	}

	public void setFornice2(String fornice2) {
		this.fornice2 = fornice2;
	}

	public double getGrupM() {
		return this.grupM;
	}

	public void setGrupM(double grupM) {
		this.grupM = grupM;
	}

	public double getMcod() {
		return this.mcod;
	}

	public void setMcod(double mcod) {
		this.mcod = mcod;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPapillom2() {
		return this.papillom2;
	}

	public void setPapillom2(String papillom2) {
		this.papillom2 = papillom2;
	}

	public String getPapillomi() {
		return this.papillomi;
	}

	public void setPapillomi(String papillomi) {
		this.papillomi = papillomi;
	}

	public String getPcap() {
		return this.pcap;
	}

	public void setPcap(String pcap) {
		this.pcap = pcap;
	}

	public String getPcitta() {
		return this.pcitta;
	}

	public void setPcitta(String pcitta) {
		this.pcitta = pcitta;
	}

	public String getPden() {
		return this.pden;
	}

	public void setPden(String pden) {
		this.pden = pden;
	}

	public double getPess() {
		return this.pess;
	}

	public void setPess(double pess) {
		this.pess = pess;
	}

	public double getPess2() {
		return this.pess2;
	}

	public void setPess2(double pess2) {
		this.pess2 = pess2;
	}

	public double getPgConto() {
		return this.pgConto;
	}

	public void setPgConto(double pgConto) {
		this.pgConto = pgConto;
	}

	public String getPind() {
		return this.pind;
	}

	public void setPind(String pind) {
		this.pind = pind;
	}

	public String getPnas() {
		return this.pnas;
	}

	public void setPnas(String pnas) {
		this.pnas = pnas;
	}

	public Date getPnascita() {
		return this.pnascita;
	}

	public void setPnascita(Date pnascita) {
		this.pnascita = pnascita;
	}

	public String getPpro() {
		return this.ppro;
	}

	public void setPpro(String ppro) {
		this.ppro = ppro;
	}

	public String getProt1() {
		return this.prot1;
	}

	public void setProt1(String prot1) {
		this.prot1 = prot1;
	}

	public String getProt2() {
		return this.prot2;
	}

	public void setProt2(String prot2) {
		this.prot2 = prot2;
	}

	public String getPtel() {
		return this.ptel;
	}

	public void setPtel(String ptel) {
		this.ptel = ptel;
	}

	public String getPtosi() {
		return this.ptosi;
	}

	public void setPtosi(String ptosi) {
		this.ptosi = ptosi;
	}

	public String getPtosi2() {
		return this.ptosi2;
	}

	public void setPtosi2(String ptosi2) {
		this.ptosi2 = ptosi2;
	}

	public Date getPulti() {
		return this.pulti;
	}

	public void setPulti(Date pulti) {
		this.pulti = pulti;
	}

	public double getSottM() {
		return this.sottM;
	}

	public void setSottM(double sottM) {
		this.sottM = sottM;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getTipoInt() {
		return this.tipoInt;
	}

	public void setTipoInt(double tipoInt) {
		this.tipoInt = tipoInt;
	}

	public double getTipo2Int() {
		return this.tipo2Int;
	}

	public void setTipo2Int(double tipo2Int) {
		this.tipo2Int = tipo2Int;
	}

}