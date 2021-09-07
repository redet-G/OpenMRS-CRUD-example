package org.openmrs.module.crudexample.web.resource;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.ObjectProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.crudexample.Store;
import org.openmrs.module.crudexample.api.CrudexampleService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.List;

/**
 * {@link Resource} for {@link Store}, supporting standard CRUD operations
 */
@Resource(name = RestConstants.VERSION_1 + "/store", supportedClass = Store.class, supportedOpenmrsVersions = { "1.8.*",
        "2.1.*", "2.2.*", "2.3.*" })
public class StoreResource1_8 extends DataDelegatingCrudResource<Store> {
	
	CrudexampleService exampleService = (CrudexampleService) Context.getService(CrudexampleService.class);
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (rep instanceof DefaultRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("location");
			description.addProperty("voided");
			description.addProperty("admin", Representation.REF);
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (rep instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("location");
			description.addProperty("voided");
			description.addProperty("admin", Representation.REF);
			description.addSelfLink();
			return description;
		}
		return null;
	}
	
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("name");
		description.addProperty("location");
		description.addProperty("admin");
		
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getUpdatableProperties() {
		return getCreatableProperties();
	}
	
	@Override
	public Store newDelegate() {
		return new Store();
	}
	
	@Override
	public Store save(Store store) {
		return exampleService.saveStore(store);
	}
	
	@Override
	public Store getByUniqueId(String s) {
		return exampleService.getStoreByUuid(s);
	}
	
	@Override
	protected void delete(Store store, String s, RequestContext requestContext) throws ResponseException {
		exampleService.purgeStore(store);
		//		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	public void purge(Store store, RequestContext requestContext) throws ResponseException {
		exampleService.purgeStore(store);
	}
	
	protected NeedsPaging<Store> doGetAll(RequestContext context) {
		return new NeedsPaging<Store>(exampleService.getAllStores(), context);
		//		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	protected PageableResult doSearch(RequestContext context) {
		//		throw new ResourceDoesNotSupportOperationException();
		//		/*
		String query = context.getParameter("q");
		
		List<Store> storesByQuery = null;
		
		if (query != null) {
			storesByQuery = exampleService.getStores(query, false);
		} else {
			storesByQuery = exampleService.getAllStores();
		}
		return new NeedsPaging<Store>(storesByQuery, context);
		//		*/
	}
	
	@Override
	public Model getGETModel(Representation rep) {
		ModelImpl model = ((ModelImpl) super.getGETModel(rep));
		model.property("uuid", new StringProperty()).property("name", new StringProperty())
		        .property("location", new StringProperty());
		
		if (rep instanceof DefaultRepresentation) {
			model.property("admin", new RefProperty("#/definitions/UserGet"));
		} else if (rep instanceof FullRepresentation) {
			model.property("admin", new RefProperty("#/definitions/UserGetRef"));
		}
		return model;
	}
	
	@Override
	public Model getUPDATEModel(Representation rep) {
		return getCREATEModel(rep);
	}
	
	@Override
	public Model getCREATEModel(Representation rep) {
		return new ModelImpl().property("name", new StringProperty()).property("location", new StringProperty())
		        .property("admin", new ObjectProperty()).required("name");
	}
	
}
