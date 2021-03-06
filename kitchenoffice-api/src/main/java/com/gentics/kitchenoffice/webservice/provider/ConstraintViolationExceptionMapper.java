package com.gentics.kitchenoffice.webservice.provider;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gentics.kitchenoffice.data.SystemMessage;
import com.gentics.kitchenoffice.data.SystemMessage.MessageType;
import com.sun.jersey.api.client.ClientResponse.Status;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	private static Logger log = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);

	@Override
	public Response toResponse(ConstraintViolationException ex) {

		log.info("Constraint violation", ex);

		SystemMessage message = new SystemMessage();

		String text = "";
		Iterator<?> iterator = ex.getConstraintViolations().iterator();

		while (iterator.hasNext()) {
			ConstraintViolation<?> current = (ConstraintViolation<?>) iterator.next();
			text += current.getPropertyPath().toString() + ": ";
			text += current.getMessage() + " ";
		}

		message.setDescription(text);
		message.setType(MessageType.error);

		return Response.status(Status.NOT_ACCEPTABLE).entity(message).type(MediaType.APPLICATION_JSON).build();
	}

}
