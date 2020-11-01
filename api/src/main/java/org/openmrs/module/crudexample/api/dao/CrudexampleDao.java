/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.crudexample.api.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.crudexample.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("crudexample.CrudexampleDao")
public class CrudexampleDao {

	@Autowired
	DbSessionFactory sessionFactory;

	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Item getItemByUuid(String uuid) {
		return (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}

	public Item saveItem(Item item) {
		getSession().saveOrUpdate(item);
		return item;
	}

	public List<Item> getAllItems() {
		return (List<Item>) getSession().createCriteria(Item.class).list();
	}

	public void purgeItem(Item item) {
		getSession().delete(item);
	}

	public Criteria createItemByQueryCriteria(String query, boolean includeVoided, boolean orderByNames) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Item.class, "i");
		if (!includeVoided) {
			criteria.add(Restrictions.eq("i.voided", false));
		}
		Disjunction or = Restrictions.disjunction();
		MatchMode mode = MatchMode.ANYWHERE;
		or.add(Restrictions.ilike("t.name", query, mode));
		or.add(Restrictions.ilike("t.description", query, mode));

		criteria.add(or);
		return criteria;
	}

	public Long getCountOfItems(String query, boolean includeVoided) {
		Criteria criteria = createItemByQueryCriteria(query, includeVoided, false);

		criteria.setProjection(Projections.countDistinct("i.crudexample_item_id"));
		return (Long) criteria.uniqueResult();
	}

	public List<Item> getItems(String query, boolean includeVoided) {

		if (query != null || !query.equals("=") || query.trim().length() != 0) {
			Criteria criteria = createItemByQueryCriteria(query,includeVoided,false);
			return (List<Item>) criteria.list();
		} else {
			return getAllItems();
		}
	}
}
