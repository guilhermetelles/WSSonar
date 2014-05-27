package br.com.wssonar.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "User" database table.
 * 
 */
@Entity
@Table(name="\"User\"", schema="main")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="us_id", unique=true, nullable=false)
	private Integer usId;

	@Column(name="us_email", nullable=false, length=100)
	private String usEmail;

	@Column(name="us_name", nullable=false, length=70)
	private String usName;

	@Column(name="us_password", nullable=false, length=255)
	private String usPassword;

	@Column(name="us_username", nullable=false, length=20)
	private String usUsername;

	public User() {
	}

	public Integer getUsId() {
		return this.usId;
	}

	public void setUsId(Integer usId) {
		this.usId = usId;
	}

	public String getUsEmail() {
		return this.usEmail;
	}

	public void setUsEmail(String usEmail) {
		this.usEmail = usEmail;
	}

	public String getUsName() {
		return this.usName;
	}

	public void setUsName(String usName) {
		this.usName = usName;
	}

	public String getUsPassword() {
		return this.usPassword;
	}

	public void setUsPassword(String usPassword) {
		this.usPassword = usPassword;
	}

	public String getUsUsername() {
		return this.usUsername;
	}

	public void setUsUsername(String usUsername) {
		this.usUsername = usUsername;
	}

}