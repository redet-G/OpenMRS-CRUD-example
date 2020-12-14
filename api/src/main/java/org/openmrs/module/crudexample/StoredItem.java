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
import org.openmrs.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Please note that a corresponding table schema must be created in liquibase.xml.
 */
//Uncomment 2 lines below if you want to make the Item class persistable, see also CrudexampleDaoTest and liquibase.xml
@Entity(name = "crudexample.StoredItem")
@Table(name = "crudexample_stored_item")
public class StoredItem extends BaseOpenmrsData {
	
	public StoredItem() {
	}
	
	@Id
	@GeneratedValue
	@Column(name = "crudexample_stored_item_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "store")
	private Store store;
	
	@Basic
	@Column(name = "expiry_date", length = 255)
	private Date expiry_date;
	
	@Basic
	@Column(name = "quantity", length = 255)
	private int quantity;
	
	@Basic
	@Column(name = "price", length = 255)
	private double price;
	
	@Basic
	@Column(name = "location", length = 255)
	private String location;
	
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
	
	public Store getStore() {
		return store;
	}
	
	public void setStore(Store store) {
		this.store = store;
	}
	
	public Date getExpiry_date() {
		return expiry_date;
	}
	
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
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
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
}
