package org.pace.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "PD_PURCHASE_ITEM")
@Where(clause = "PI_FLAG_STATUS<2")
public class PoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PI_ID")
	private int id;			
	
	@Column(name = "PI_PO_ID")
	private int poId;		
	
	@Column(name = "PI_TRAN_DATE", updatable = false, columnDefinition="DATETIME")
	@CreationTimestamp
	private LocalDate tranDate;

	@Column(name = "PI_ITEM_CODE")
	private String itemCode;
	
	@Column(name = "PI_ITEM_QTY")
	private int itemQty;
	
	@Column(name = "PI_ITEM_PRICE")
	private double itemPrice;
	
	@Column(name = "PI_AMOUNT")
	private double amount;
	
	@Column(name = "PI_FLAG_STATUS")
	private int flagStatus;
	
	@Column(name = "PI_CREATED_UC", updatable = false)
	private int createdUsercode;
	
	@Column(name = "PI_CREATED_DT", updatable = false, columnDefinition="DATETIME")
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	
	@Column(name = "PI_MODIFIED_UC", insertable=false)
	private int modifiedUsercode;
	
	@Column(name = "PI_MODIFIED_DT", insertable=false, columnDefinition="DATETIME")
	@UpdateTimestamp
	private LocalDateTime modifieddDateTime;
	
/*	  @ManyToOne
	    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	    @JoinColumn(name="PO_ID")
	    protected Po po;*/
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getPoId() {
		return poId;
	}
	
	public void setPoId(int poId) {
		this.poId=poId;
	}
	
	public LocalDate getTranDate() {
		return tranDate;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getItemQty() {
		return itemQty;
	}
	
	public void setItemQty(int itemQty) {
		this.itemQty=itemQty;
	}
	
	
	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
