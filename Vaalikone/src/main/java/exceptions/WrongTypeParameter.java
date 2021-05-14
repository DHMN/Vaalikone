package exceptions;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ParamException;

@Provider
public class WrongTypeParameter implements ExceptionMapper<ParamException> {

	@Override
	public Response toResponse(ParamException exception){

		ResponseBuilder builder = 
				Response.ok("Your PathParam is of wrong type - should be an integer");
		return builder.build();
	}
}