package org.pace.model;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "PD_ROLE_MASTER")
@Where(clause = "RM_FLAG_STATUS<'2'")
public class Role {

	@Id
	@Column(name = "RM_ROLE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;

	@Column(name = "RM_ROLE_NAME")
	private String roleName;
	
	@Column(name = "RM_FLAG_STATUS")
	private int flagStatus;
	
	@Column(name = "RM_CREATED_UC", updatable = false)
	private int createdUsercode;
	
	@Column(name = "RM_CREATED_DT", updatable = false, columnDefinition="DATETIME")
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	
	@Column(name = "RM_MODIFIED_UC", insertable = false)
	private int modifiedUsercode;
	
	@Column(name = "RM_MODIFIED_DT", insertable=false, columnDefinition="DATETIME")
	@UpdateTimestamp
	private LocalDateTime modifieddDateTime;			

	@OneToMany(mappedBy="role")
	private Collection<UserInfo> user;
	
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	
}
