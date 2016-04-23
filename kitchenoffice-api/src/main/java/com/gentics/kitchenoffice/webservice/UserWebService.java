/*
 * 
 */
package com.gentics.kitchenoffice.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.gentics.kitchenoffice.data.user.User;
import com.gentics.kitchenoffice.service.CrowdUserService;
import com.gentics.kitchenoffice.webservice.filter.CacheAnnotations.NoCache;

/**
 * The Class UserWebService.
 * 
 * Provides user CRUD functionality.
 */
@Component
@PreAuthorize("isAuthenticated()")
@Scope("singleton")
@Path("/users")
@NoCache
public class UserWebService {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(UserWebService.class);

	/** The user service. */
	@Autowired
	private CrowdUserService userService;

	/**
	 * Gets the user object of logged in user.
	 * 
	 * @return the me
	 */
	@GET
	@Path("/me")
	@Produces(MediaType.APPLICATION_JSON)
	public User getMe() {
		log.debug("Calling user getMe");
		return userService.getRefreshedUser();
	}

	/**
	 * Gets all users.
	 * 
	 * @param page
	 *            the page
	 * @param size
	 *            the size
	 * @return the list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findAll(@Context Pageable pageable) {
		log.debug("Calling user findAll");
		return userService.findAll(pageable);
	}

}
