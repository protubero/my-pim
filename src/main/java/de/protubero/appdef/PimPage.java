package de.protubero.appdef;

import java.util.HashSet;
import java.util.Set;

public class PimPage {

	private String alias;
	private Set<String> routeSet = new HashSet<>();
	
	
	public PimPage(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}


}
