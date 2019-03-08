package org.pace.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "PD_USER_DETAILS")
@Where(clause = "UD_FLAG_STATUS<'2'")
public class UserInfo {

	@Id
	@Column(name = "UD_USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;			
	
	@Column(name = "UD_ROLE_ID")	
	private int roleId;

	@Column(name = "UD_USER_NAME")
	private String userName;
	
	@Column(name = "UD_PASSWORD")
	private String password;
	
	@Column(name = "UD_FLAG_STATUS")
	private int flagStatus;
	
	@Column(name = "UD_CREATED_UC", updatable = false)
	private int createdUsercode;
	
	@Column(name = "UD_CREATED_DT", updatable = false, columnDefinition="DATETIME")
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	
	@Column(name = "UD_MODIFIED_UC", insertable=false)
	private int modifiedUsercode;
	
	@Column(name = "UD_MODIFIED_DT", insertable=false, columnDefinition="DATETIME")
	@UpdateTimestamp
	private LocalDateTime modifieddDateTime;
	
	@ManyToOne
	@JoinColumn(name="UD_ROLE_ID", insertable = false, updatable = false)
	private Role role;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(int flagStatus) {
		this.flagStatus = flagStatus;
	}

	public int getCreatedUsercode() {
		return createdUsercode;
	}

	public void setCreatedUsercode(int createdUsercode) {
		this.createdUsercode = createdUsercode;
	}

	public int getModifiedUsercode() {
		return modifiedUsercode;
	}

	public void setModifiedUsercode(int modifiedUsercode) {
		this.modifiedUsercode = modifiedUsercode;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public LocalDateTime getModifieddDateTime() {
		return modifieddDateTime;
	}
	
	public Role getRole() {
		return role;
	}

}
