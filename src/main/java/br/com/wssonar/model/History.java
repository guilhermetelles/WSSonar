package br.com.wssonar.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "History" database table.
 * 
 */
@Entity
@Table(name="\"History\"", schema="main")
@NamedQuery(name="History.findAll", query="SELECT h FROM History h")
public class History implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ht_id", unique=true, nullable=false)
	private Integer htId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ht_back_online")
	private Date htBackOnline;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ht_down_date", nullable=false)
	private Date htDownDate;

	@Column(name="ht_error_result", length=250)
	private String htErrorResult;

	//bi-directional many-to-one association to Web_Service
	@ManyToOne
	@JoinColumn(name="ht_id_web_service", nullable=true)
	private WebService webService;
	
	@Transient
	private String htOfflineTotalTime;

	public History() {
	}

	public Integer getHtId() {
		return this.htId;
	}

	public void setHtId(Integer htId) {
		this.htId = htId;
	}

	public Date getHtBackOnline() {
		return this.htBackOnline;
	}

	public void setHtBackOnline(Date htBackOnline) {
		this.htBackOnline = htBackOnline;
	}

	public Date getHtDownDate() {
		return this.htDownDate;
	}

	public void setHtDownDate(Date htDownDate) {
		this.htDownDate = htDownDate;
	}

	public String getHtErrorResult() {
		return this.htErrorResult;
	}

	public void setHtErrorResult(String htErrorResult) {
		this.htErrorResult = htErrorResult;
	}

	public WebService getWebService() {
		return this.webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public String getHtOfflineTotalTime() {
		return htOfflineTotalTime;
	}

	public void setHtOfflineTotalTime(String htOfflineTotalTime) {
		this.htOfflineTotalTime = htOfflineTotalTime;
	}
	
}