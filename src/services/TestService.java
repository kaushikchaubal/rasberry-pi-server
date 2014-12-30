package services;

import java.util.Map;

import nano.NanoHTTPD;
import nano.NanoHTTPD.Method;
import nano.NanoHTTPD.Response;
import nano.NanoHTTPD.Response.Status;

public class TestService implements ServiceInterface {

	@Override
	public Response serve(String uri, Method method,
			Map<String, String> header, Map<String, String> parms) {

		return new NanoHTTPD.Response(Status.OK, NanoHTTPD.MIME_HTML, "Displaying result from TestService");
	}
}
