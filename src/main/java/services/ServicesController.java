package services;

import java.util.ArrayList;
import java.util.List;

public class ServicesController {
	private List<Service> services = new ArrayList<Service>();

	public void addService(String path, ServiceInterface handler) {
		while (path.endsWith("/") && path.length() > 1) {
			path = path.substring(0, path.length() - 1);
		}
		services.add(new Service(path, handler));
	}
	
	public List<Service> getServices() {
		return services;
	}
}

