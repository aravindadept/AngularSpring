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
@Table(name = "PD_CATEGORY")
@Where(clause = "C_FLAG_STATUS<'2'")
public class Category {

	@Id
	@Column(name = "C_CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;

	@Column(name = "C_CATEGORY_NAME")
	private String categoryName;
	
	@Column(name = "C_FLAG_STATUS")
	private int flagStatus;
	
	@Column(name = "C_SEND_FLAG", nullable=false, columnDefinition = "int default 0")	
	private int sendFlag;
	
	@Column(name = "C_CREATED_UC", updatable = false)
	private int createdUsercode;
	
	@Column(name = "C_CREATED_DT", updatable = false, columnDefinition="DATETIME")
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	
	@Column(name = "C_MODIFIED_UC", insertable = false)
	private int modifiedUsercode;
	
	@Column(name = "C_MODIFIED_DT", insertable=false, columnDefinition="DATETIME")
	@UpdateTimestamp
	private LocalDateTime modifieddDateTime;			

	@OneToMany(mappedBy="category")
	private Collection<Item> item;
	
	public int getCategoryId() {
		return categoryId;
	}	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public int getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(int sendFlag) {
		this.sendFlag = sendFlag;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public LocalDateTime getModifieddDateTime() {
		return modifieddDateTime;
	}	
}
