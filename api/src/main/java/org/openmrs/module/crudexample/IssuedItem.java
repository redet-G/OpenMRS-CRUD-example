/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.crudexample;

import org.openmrs.BaseOpenmrsData;

import javax.persistence.*;
import java.util.Date;

/**
 * Please note that a corresponding table schema must be created in liquibase.xml.
 */
//Uncomment 2 lines below if you want to make the Item class persistable, see also CrudexampleDaoTest and liquibase.xml
@Entity(name = "crudexample.IssuedItem")
@Table(name = "crudexample_issued_item")
public class IssuedItem extends BaseOpenmrsData {
	
	public IssuedItem() {
	}
	
	@Id
	@GeneratedValue
	@Column(name = "crudexample_issued_item_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "store")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name = "stored_item")
	private StoredItem stored_item;
	
	@ManyToOne
	@JoinColumn(name = "waive")
	private Waive waive;
	
	@Basic
	@Column(name = "quantity")
	private int quantity;
	
	@Basic
	@Column(name = "price")
	private double price;
	
	@Basic
	@Column(name = "is_waived")
	private boolean is_waived;
	
	@Basic
	@Column(name = "customer_name", length = 255)
	private String customer_name;
	
	@Basic
	@Column(name = "waive_approval", length = 255)
	private String waive_approval;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String getUuid() {
		return super.getUuid();
	}
	
	@Override
	public void setUuid(String uuid) {
		super.setUuid(uuid);
	}
	
	public Waive getWaive() {
		return waive;
	}
	
	public void setWaive(Waive waive) {
		this.waive = waive;
	}
	
	public boolean isIs_waived() {
		return is_waived;
	}
	
	public void setIs_waived(boolean is_waived) {
		this.is_waived = is_waived;
	}
	
	public String getCustomer_name() {
		return customer_name;
	}
	
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	public String getWaive_approval() {
		return waive_approval;
	}
	
	public void setWaive_approval(String waive_approval) {
		this.waive_approval = waive_approval;
	}
	
	public Store getStore() {
		return store;
	}
	
	public void setStore(Store store) {
		this.store = store;
	}
	
	public StoredItem getStored_item() {
		return stored_item;
	}
	
	public void setStored_item(StoredItem stored_item) {
		this.stored_item = stored_item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
