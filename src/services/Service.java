package services;

public class Service implements Comparable<Service> {
	private String path;
	private ServiceInterface service;

	public Service(String path, ServiceInterface service) {
		this.path = path;
		this.service = service;
	}
	
	public ServiceInterface getService() {
		return service;
	}
	
	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "[" + path + " : " + service.getClass().getCanonicalName()
				+ "]";
	}

	@Override
	public int compareTo(Service arg0) {
		if (arg0.getPath().length() == this.getPath().length()) {
			return 0;
		}

		return arg0.getPath().length() > this.getPath().length() ? -1 : 1;
	}
}