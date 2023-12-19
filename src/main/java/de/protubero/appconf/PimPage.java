package de.protubero.appconf;

import java.util.Objects;
import java.util.Optional;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PimPage {

	private String alias;
	
	
	public PimPage(String alias) {
		this.alias = Objects.requireNonNull(alias);
	}

	public String getAlias() {
		return alias;
	}

	public void onAttach(VerticalLayout pimPageLayout, Optional<String> pageParam) {
		
	}


//	public void addRoute(String route) {
//		if (routes.stream().filter(r -> r.equals(route)).findAny().isPresent()) {
//			throw new RuntimeException("Duplicate route: " + route);
//		}
//		routes.add(route);
//	}
//
//	public List<String> getRoutes() {
//		return routes;
//	}
//	
//	public Optional<String> mainRoute() {
//		if (routes.size() == 0) {
//			return Optional.empty();
//		} else {
//			return Optional.of(routes.get(0)); 
//		}
//	}
	
	


}
