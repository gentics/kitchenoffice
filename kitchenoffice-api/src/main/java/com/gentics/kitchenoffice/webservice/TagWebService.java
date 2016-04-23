/*
 * 
 */
package com.gentics.kitchenoffice.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.gentics.kitchenoffice.data.Tag;
import com.gentics.kitchenoffice.service.TagService;
import com.gentics.kitchenoffice.webservice.filter.CacheAnnotations.NoCache;

/**
 * The Class TagWebService.
 * 
 * Provides tag CRU functionality. Deletion of tags (cleanup) works
 * automatically when not used anymore.
 */
@Component
@PreAuthorize("isAuthenticated()")
@Scope("singleton")
@Path("/tags")
@NoCache
public class TagWebService {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(TagWebService.class);

	/** The tag service. */
	@Autowired
	private TagService tagService;

	/**
	 * Gets the tags.
	 * 
	 * @param page
	 *            the page
	 * @param size
	 *            the size
	 * @param search
	 *            the search
	 * @return the tags
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tag> getTags(@Context Pageable pageable, @QueryParam("search") String search) {
		log.debug("calling getTags");
		return tagService.findByNameLike(search, pageable);
	}

	/**
	 * Creates the or update tag.
	 * 
	 * @param tag
	 *            the tag
	 * @return the tag
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tag createOrUpdateTag(Tag tag) {
		log.debug("calling createOrUpdateTag");

		Assert.notNull(tag);
		Assert.hasText(tag.getName());

		return tagService.save(tag);
	}
}
