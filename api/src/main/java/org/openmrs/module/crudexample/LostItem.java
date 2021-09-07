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

/**
 * Please note that a corresponding table schema must be created in liquibase.xml.
 */
//Uncomment 2 lines below if you want to make the Item class persistable, see also CrudexampleDaoTest and liquibase.xml
@Entity(name = "crudexample.LostItem")
@Table(name = "crudexample_lost_item")
public class LostItem extends BaseOpenmrsData {

	public LostItem() {
	}
	
	@Id
	@GeneratedValue
	@Column(name = "crudexample_lost_item_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "store")
	private Store store;
	
	@ManyToOne
	@JoinColumn(name = "stored_item")
	private StoredItem stored_item;

	
	@Basic
	@Column(name = "quantity")
	private int quantity;

	@Basic
	@Column(name = "reason", length = 5000)
	private String reason;
	
	@Basic
	@Column(name = "comment", length = 5000)
	private String comment;
	
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
