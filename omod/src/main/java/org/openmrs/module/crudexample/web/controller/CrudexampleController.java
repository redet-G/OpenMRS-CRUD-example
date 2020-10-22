/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.crudexample.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.text.View;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.crudexample.Item;
import org.openmrs.module.crudexample.api.CrudexampleService;
import org.openmrs.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/${rootArtifactid}/${rootArtifactid}Link.form'.
 */
@Controller("crudexample.CrudexampleController")
@RequestMapping(value = "module/crudexample/")
public class CrudexampleController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	CrudexampleService exampleService;
	
	/** Success form view name */
	private final String VIEW = "/module/crudexample/crudexample";
	
	/**
	 * Initially called after the getUsers method to get the landing form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(value = "crudexample.form", method = RequestMethod.GET)
	public String onGet() {
		return VIEW;
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("/module/crudexample/create", "item", new Item());
	}
	
	@RequestMapping(value = "edit.form", method = RequestMethod.GET)
	public String showEditForm(HttpSession httpSession, @ModelAttribute("item") Item item, ModelMap model) {
		if (item.getId() == null) {
			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "curdexample.item.notFound");
			return "redirect:" + VIEW + ".form";
		}
		item = exampleService.getItemByUuid(item.getUuid());
		model.addAttribute("item", item);
		return "/module/crudexample/edit";
	}
	
	@RequestMapping(value = "delete.form", method = RequestMethod.GET)
	public String onDelete(HttpSession httpSession, @ModelAttribute("item") Item item) {
		try {
			exampleService.purgeItem(exampleService.getItemByUuid(item.getUuid()));
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "curdexample.item.deleted");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "curdexample.item.delete.failed");
		}
		return "redirect:" + VIEW + ".form";
	}
	
	/**
	 * All the parameters are optional based on the necessity
	 * 
	 * @param httpSession
	 * @param item
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "create.form", method = RequestMethod.POST)
	public String onPost(HttpSession httpSession, @ModelAttribute("item") Item item, BindingResult errors) {
		
		if (errors.hasErrors()) {
			// return error view
		}
		if (item.getId() != null) {
			Item itemTemp = exampleService.getItemByUuid(item.getUuid());
			itemTemp.setName(item.getName());
			itemTemp.setDescription(item.getDescription());
			exampleService.saveItem(itemTemp);
		} else {
			item.setOwner(Context.getUserContext().getAuthenticatedUser());
			exampleService.saveItem(item);
		}
		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "curdexample.item.saved");
		return "redirect:" + VIEW + ".form";
	}
	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The bean name defined in the ModelAttribute annotation and the type can be just defined
	 * by the return type of this method
	 */
	@ModelAttribute("items")
	protected List<Item> getUsers() throws Exception {
		List<Item> items = exampleService.getAllItems();
		
		// this object will be made available to the jsp page under the variable name
		// that is defined in the @ModuleAttribute tag
		return items;
	}
	
}
