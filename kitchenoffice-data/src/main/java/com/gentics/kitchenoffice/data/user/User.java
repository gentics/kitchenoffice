package com.gentics.kitchenoffice.data.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.security.core.userdetails.UserDetails;

import com.gentics.kitchenoffice.data.AbstractPersistable;
import com.gentics.kitchenoffice.data.event.Location;

@NodeEntity
public class User extends AbstractPersistable implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1479792543611842572L;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "userfirstnamesearch")
	private String firstName;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "userlastnamesearch")
	private String lastName;

	@Indexed(indexType = IndexType.FULLTEXT, indexName = "userusernamesearch")
	private String username;

	@JsonIgnore
	private String password;

	private String email;

	@JsonIgnore
	private boolean enabled;

	@Fetch
	@RelatedTo(type = "HAS_ROLES", direction = Direction.BOTH, elementClass = Role.class)
	private Set<Role> roles = new HashSet<Role>();
	
	@RelatedTo(type = "SUBSCRIBES", direction = Direction.BOTH, elementClass = Location.class)
	private Set<Location> locationSubscriptions= new HashSet<Location>();

	public User() {

	}

	public User(String firstName, String lastName, String userName, boolean enabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = userName;
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	@JsonIgnore
	public Collection<Role> getAuthorities() {
		return roles;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}

	@JsonIgnore
	public Set<Role> getRoles() {
		return roles;
	}

	@JsonIgnore
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Location> getLocationSubscriptions() {
		return locationSubscriptions;
	}

	public void setLocationSubscriptions(Set<Location> locationSubscriptions) {
		this.locationSubscriptions = locationSubscriptions;
	}

}
