package br.com.wssonar.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Web_Service" database table.
 * 
 */
@Entity
@Table(name="\"Web_Service\"", schema="main")
@NamedQuery(name="WebService.findAll", query="SELECT w FROM WebService w")
public class WebService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ws_id", unique=true, nullable=false)
	private Integer wsId;

	@Column(name="ws_description", length=100)
	private String wsDescription;

	@Column(name="ws_name", nullable=false, length=40)
	private String wsName;

	@Column(name="ws_status", nullable=false)
	private Boolean wsStatus;
	
	@Column(name="ws_count_positive")
	private Integer wsCountPositive;
	
	@Column(name="ws_count_negative")
	private Integer wsCountNegative;
	
	@Column(name="ws_count_total")
	private Integer wsCountTotal;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="webService")
	private List<History> histories;
	
	@Transient
	private History lastHistory;

	public WebService() {
	}

	public Integer getWsId() {
		return this.wsId;
	}

	public void setWsId(Integer wsId) {
		this.wsId = wsId;
	}

	public String getWsDescription() {
		return this.wsDescription;
	}

	public void setWsDescription(String wsDescription) {
		this.wsDescription = wsDescription;
	}

	public String getWsName() {
		return this.wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public Boolean getWsStatus() {
		return this.wsStatus;
	}

	public void setWsStatus(Boolean wsStatus) {
		this.wsStatus = wsStatus;
	}
	
	public Integer getWsCountPositive() {
		return wsCountPositive;
	}

	public void setWsCountPositive(Integer wsCountPositive) {
		this.wsCountPositive = wsCountPositive;
	}

	public Integer getWsCountNegative() {
		return wsCountNegative;
	}

	public void setWsCountNegative(Integer wsCountNegative) {
		this.wsCountNegative = wsCountNegative;
	}

	public Integer getWsCountTotal() {
		return wsCountTotal;
	}

	public void setWsCountTotal(Integer wsCountTotal) {
		this.wsCountTotal = wsCountTotal;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setWebService(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setWebService(null);

		return history;
	}

	public History getLastHistory() {
		return lastHistory;
	}

	public void setLastHistory(History lastHistory) {
		this.lastHistory = lastHistory;
	}

}