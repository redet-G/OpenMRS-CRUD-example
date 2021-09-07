/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.crudexample.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.crudexample.CrudexampleConfig;
import org.openmrs.module.crudexample.Item;
import org.openmrs.module.crudexample.Store;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface CrudexampleService extends OpenmrsService {
	
	/**
	 * Returns an item by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Item getItemByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an item. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param item
	 * @return
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	Item saveItem(Item item) throws APIException;
	
	/**
	 * gets all items.
	 * 
	 * @param
	 * @return List<Item>
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	List<Item> getAllItems() throws APIException;
	
	/**
	 * gets items with query.
	 * 
	 * @param
	 * @return List<Item>
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	List<Item> getItems(String query, boolean includeVoided) throws APIException;
	
	/**
	 * deletes an item.
	 * 
	 * @param item
	 * @return void
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	void purgeItem(Item item) throws APIException;
	
	/**
	 * return count of Items
	 * 
	 * @param query String
	 * @param includeVoided boolean
	 * @return long
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	long getCountOfItems(String query, boolean includeVoided) throws APIException;
	
	/***
	 * ############################################################################## /** store
	 * ##################################################################################
	 **/
	
	/**
	 * Returns an store by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Store getStoreByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an store. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param store
	 * @return
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	Store saveStore(Store store) throws APIException;
	
	/**
	 * gets all stores.
	 * 
	 * @param
	 * @return List<Store>
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	List<Store> getAllStores() throws APIException;
	
	/**
	 * gets stores with query.
	 * 
	 * @param
	 * @return List<Item>
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	List<Store> getStores(String query, boolean includeVoided) throws APIException;
	
	/**
	 * deletes an item.
	 * 
	 * @param store
	 * @return void
	 * @throws APIException
	 */
	@Authorized(CrudexampleConfig.MODULE_PRIVILEGE)
	@Transactional
	void purgeStore(Store store) throws APIException;
	
}
