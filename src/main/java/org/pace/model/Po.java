package org.pace.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "PD_PURCHASE_ORDER")
@Where(clause = "PO_FLAG_STATUS<2")
public class Po {

	@Id
	@Column(name = "PO_ID")
	private int id;			
	
	@Column(name = "PO_TRAN_DATE", updatable = false, columnDefinition="DATETIME")
	@CreationTimestamp
	private LocalDate tranDate;

	@Column(name = "PO_AMOUNT")
	private double amount;
	
	@Column(name = "PO_FLAG_STATUS")
	private int flagStatus;
	
	@Column(name = "PO_CREATED_UC", updatable = false)
	private int createdUsercode;
	
	@Column(name = "PO_CREATED_DT", updatable = false, columnDefinition="DATETIME")
	@CreationTimestamp
	private LocalDateTime createdDateTime;
	
	@Column(name = "PO_MODIFIED_UC", insertable=false)
	private int modifiedUsercode;
	
	@Column(name = "PO_MODIFIED_DT", insertable=false, columnDefinition="DATETIME")
	@UpdateTimestamp
	private LocalDateTime modifieddDateTime;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="PI_PO_ID",referencedColumnName="PO_ID")
	private List<PoItem> poItem;

/*	 @OneToMany(mappedBy = "po")
	    protected List<PoItem> poItem;*/
	 
	 
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		
		this.id=id;
	}
	
	public LocalDate getTranDate() {
		return tranDate;
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
	
	public List<PoItem> getPoItem() {
		
		return poItem;
	}
	
	public void setPoItem(List<PoItem> poItem) {
		
		this.poItem=poItem;
	}

}
