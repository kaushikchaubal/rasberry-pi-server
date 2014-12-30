package app;


import java.util.Map;

import services.Service;
import services.ServicesController;
import services.PageRendererService;
import services.TestService;
import nano.NanoHTTPD;
import nano.ServerRunner;

public class RasberryPiServer extends NanoHTTPD {
	
	private ServicesController servicesController;

    public RasberryPiServer() {
        super(3000);
        this.servicesController = new ServicesController();
        initHandlers();
    }
    
    private void initHandlers() {
		servicesController.addService("/", new PageRendererService("/"));
		servicesController.addService("/test", new TestService());
		servicesController.addService("/app", new PageRendererService("app"));
		servicesController.addService("/bower_components", new PageRendererService("bower_components"));
	}

    @Override 
    public Response serve(IHTTPSession session) {
    	super.serve(session);
        
    	String uri = session.getUri();
		Method method = session.getMethod();
		Map<String, String> header = session.getHeaders();
		Map<String, String> parms = session.getParms();
		
		for (Service service : servicesController.getServices()) {
			if (checkPath(uri, service.getPath())) {
				String substring = uri.substring(service.getPath().length());

				System.out.println("URI: " + uri);
				return service.getService().serve(substring, method, header, parms);
			}
		}

		System.out.println("No Service Found to handle: " + uri);
		return new NanoHTTPD.Response(NanoHTTPD.Response.Status.NOT_FOUND, MIME_HTML, "Not Found");
    }
    
    private boolean checkPath(String uri, String matchPath) {
		return uri.startsWith(matchPath + "/") || uri.equals(matchPath);
	}

    public static void main(String[] args) {
        ServerRunner.run(RasberryPiServer.class);
    }
}