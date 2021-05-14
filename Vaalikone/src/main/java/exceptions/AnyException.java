package exceptions;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ParamException;

@Provider
public class AnyException implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception){

		ResponseBuilder builder = 
				Response.ok("This is just an exception of any type");
		return builder.build();
	}
}