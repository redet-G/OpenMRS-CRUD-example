/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.crudexample.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.crudexample.Item;
import org.openmrs.module.crudexample.api.CrudexampleService;
import org.openmrs.module.crudexample.api.dao.CrudexampleDao;

import java.util.List;

public class CrudexampleServiceImpl extends BaseOpenmrsService implements CrudexampleService {
	
	CrudexampleDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(CrudexampleDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Item getItemByUuid(String uuid) throws APIException {
		return dao.getItemByUuid(uuid);
	}
	
	@Override
	public Item saveItem(Item item) throws APIException {
		if (item.getOwner() == null) {
			item.setOwner(userService.getUser(1));
		}
		
		return dao.saveItem(item);
	}
	
	@Override
	public List<Item> getAllItems() throws APIException {
		return dao.getAllItems();
	}
	
	@Override
	public void purgeItem(Item item) throws APIException {
		dao.purgeItem(item);
	}
}
