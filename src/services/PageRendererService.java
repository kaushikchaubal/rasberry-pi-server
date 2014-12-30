package services;

import java.io.InputStream;
import java.util.Map;

import nano.NanoHTTPD;
import nano.NanoHTTPD.Method;
import nano.NanoHTTPD.Response;
import nano.NanoHTTPD.Response.Status;

public class PageRendererService implements ServiceInterface {

	private String rootPath;
	private ClassLoader loader;

	public PageRendererService(String rootPath) {
		this.rootPath = rootPath;
		loader = PageRendererService.class.getClassLoader();
	}

	@Override
	public Response serve(String uri, Method method, Map<String, String> header, Map<String, String> parms) {

		uri = rootPath + uri;
		
		if (uri.indexOf('?') >= 0) {
			uri = uri.substring(0, uri.indexOf('?'));
		}
		
		// Illegal path - we shouldn't allow this
		if (uri.startsWith("..") || uri.endsWith("..") 
				|| uri.indexOf("../") >= 0
				|| uri.contains(":") || uri.contains("|")) {
			return new Response(Status.FORBIDDEN,
					NanoHTTPD.MIME_PLAINTEXT,
					"Illegal Path");
		}
		
		boolean isDir = false;
		if (uri.endsWith("/")) {
			isDir = true;
			uri = uri.replaceAll("^/", "") + "index.html";
		}
		
		// Load Resource
		InputStream f = loader.getResourceAsStream(uri);

		if (f == null) {
			if (isDir) {
				return new Response(Status.FORBIDDEN,
						NanoHTTPD.MIME_PLAINTEXT, "Forbidden location");
			} else {
				return new Response(Status.NOT_FOUND,
					NanoHTTPD.MIME_PLAINTEXT, "Not Found: " + uri );
			}
		}

		return new Response(Status.OK, NanoHTTPD.getMimeTypeForSuffix(uri), f);

	}


}
