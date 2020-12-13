package org.openmrs.module.crudexample.web.resource;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.crudexample.Item;
import org.openmrs.module.crudexample.api.CrudexampleService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.*;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.List;

/**
 * {@link Resource} for {@link Item}, supporting standard CRUD operations
 */
@Resource(name = RestConstants.VERSION_1 + "/item", supportedClass = Item.class, supportedOpenmrsVersions = { "1.8.*",
        "2.1.*", "2.2.*", "2.3.*" })
public class ItemResource1_8 extends DataDelegatingCrudResource<Item> {
	
	CrudexampleService exampleService = (CrudexampleService) Context.getService(CrudexampleService.class);
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (rep instanceof DefaultRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("strength");
			description.addProperty("unit");
			description.addProperty("code");
			description.addProperty("voided");
			description.addProperty("owner", Representation.REF);
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (rep instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("strength");
			description.addProperty("unit");
			description.addProperty("code");
			description.addProperty("voided");
			description.addProperty("owner", Representation.REF);
			description.addSelfLink();
			return description;
		}
		return null;
	}
	
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("name");
		description.addProperty("description");
		description.addProperty("strength");
		description.addProperty("unit");
		description.addProperty("code");
		
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getUpdatableProperties() {
		return getCreatableProperties();
	}
	
	@Override
	public Item newDelegate() {
		return new Item();
	}
	
	@Override
	public Item save(Item item) {
		item.setOwner(Context.getAuthenticatedUser());
		return exampleService.saveItem(item);
	}
	
	@Override
	public Item getByUniqueId(String s) {
		return exampleService.getItemByUuid(s);
	}
	
	@Override
	protected void delete(Item item, String s, RequestContext requestContext) throws ResponseException {
		exampleService.purgeItem(item);
		//		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	public void purge(Item item, RequestContext requestContext) throws ResponseException {
		exampleService.purgeItem(item);
	}
	
	protected NeedsPaging<Item> doGetAll(RequestContext context) {
		return new NeedsPaging<Item>(exampleService.getAllItems(), context);
		//		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	protected PageableResult doSearch(RequestContext context) {
		//		throw new ResourceDoesNotSupportOperationException();
		//		/*
		String query = context.getParameter("q");
		
		List<Item> itemsByQuery = null;
		
		if (query != null) {
			itemsByQuery = exampleService.getItems(query, false);
		} else {
			itemsByQuery = exampleService.getAllItems();
		}
		return new NeedsPaging<Item>(itemsByQuery, context);
		//		*/
	}
	
	@Override
	public Model getGETModel(Representation rep) {
		ModelImpl model = ((ModelImpl) super.getGETModel(rep));
		model.property("uuid", new StringProperty()).property("name", new StringProperty())
		        .property("strength", new StringProperty()).property("unit", new StringProperty())
		        .property("code", new StringProperty()).property("description", new StringProperty());
		
		if (rep instanceof DefaultRepresentation) {
			model.property("owner", new RefProperty("#/definitions/UserGet"));
		} else if (rep instanceof FullRepresentation) {
			model.property("owner", new RefProperty("#/definitions/UserGetRef"));
		}
		return model;
	}
	
	@Override
	public Model getUPDATEModel(Representation rep) {
		return getCREATEModel(rep);
	}
	
	@Override
	public Model getCREATEModel(Representation rep) {
		return new ModelImpl().property("name", new StringProperty()).property("description", new StringProperty())
		        .property("strength", new StringProperty()).property("unit", new StringProperty())
		        .property("code", new StringProperty()).required("name");
	}
	
}
