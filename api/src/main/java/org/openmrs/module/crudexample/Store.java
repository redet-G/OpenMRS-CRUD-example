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

/**
 * Please note that a corresponding table schema must be created in liquibase.xml.
 */
//Uncomment 2 lines below if you want to make the Item class persistable, see also CrudexampleDaoTest and liquibase.xml
@Entity(name = "crudexample.Store")
@Table(name = "crudexample_store")
public class Store extends BaseOpenmrsData {
	
	public Store() {
	}
	
	@Id
	@GeneratedValue
	@Column(name = "crudexample_store_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "admin")
	private User admin;
	
	@Basic
	@Column(name = "location", length = 255)
	private String location;
	
	@Basic
	@Column(name = "name", length = 255)
	private String name;
	
	@Basic
	@Column(name = "is_dispensary", length = 255)
	private boolean is_dispensary;
	
	public User getAdmin() {
		return admin;
	}
	
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	public Boolean getIs_dispensary() {
		return is_dispensary;
	}
	
	public void setIs_dispensary(Boolean is_dispensary) {
		this.is_dispensary = is_dispensary;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
