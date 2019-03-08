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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "PD_ITEM_MASTER")
@Where(clause = "IM_FLAG_STATUS<'2'")
public class Item {

	@Id
	@Column(name = "IM_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;			
	
	@Column(name = "IM_CATEGORY_ID")	
	private int categoryId;

	@Column(name = "IM_ITEM_CODE")
	private String itemCode;
	
	@Column(name = "IM_ITEM_NAME")
	private String itemName;
	
	@Column(name = "IM_ITEM_PRICE")
	private double itemPrice;
	
	@Column(name = "IM_FLAG_STATUS")
	private int flagStatus;
	
	@Column(name = "IM_SEND_FLAG")
	@ColumnDefault("0")
	private int sendFlag;
	
	@Column(name = "IM_CREATED_UC", updatable = false)
	private int createdUsercode;
	
	@Column(name = "IM_CREATED_DT", updatable = false, columnDefinition="DATETIME")
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	
	@Column(name = "IM_MODIFIED_UC", insertable=false)
	private int modifiedUsercode;
	
	@Column(name = "IM_MODIFIED_DT", insertable=false, columnDefinition="DATETIME")
	@UpdateTimestamp
	private LocalDateTime modifieddDateTime;
	
	@ManyToOne
	@JoinColumn(name="IM_CATEGORY_ID", insertable = false, updatable = false)
	private Category category;

	public int getItemId() {
		return itemId;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
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
	public Category getCategory() {
		return category;
	}	
}
