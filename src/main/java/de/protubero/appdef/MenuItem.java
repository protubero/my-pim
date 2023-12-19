package de.protubero.appdef;

import java.util.Objects;

import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.router.RouteParameters;

public class MenuItem {
	
	private String label;
	private SvgIcon icon;
	private PimPage page;
	private RouteParameters params;
	
	public MenuItem(String label, PimPage page, RouteParameters params) {
		this.label = Objects.requireNonNull(label);
		this.page = Objects.requireNonNull(page);
		this.params = Objects.requireNonNull(params);
	}
 

	
	public String getLabel() {
		return label;
	}

	public SvgIcon getIcon() {
		return icon;
	}

	public String getPath() {
		return page.getRoute();
	}
	
}
