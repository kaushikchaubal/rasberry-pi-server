package services;

import java.util.Map;

import nano.NanoHTTPD.Method;
import nano.NanoHTTPD.Response;

public interface ServiceInterface {
	public Response serve(String uri, Method method, Map<String, String> header, Map<String, String> parms);
}
