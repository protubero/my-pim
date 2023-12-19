package de.protubero.appconf;

import java.util.Objects;

import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.router.RouteParameters;

public class MenuItem {
	
	private String label;
	private SvgIcon icon;
	private PimPage page;
	private String param;
	
	public MenuItem(String label, PimPage page, String param) {
		this.label = Objects.requireNonNull(label);
		this.page = Objects.requireNonNull(page);
		this.param = Objects.requireNonNull(param);
	}

	public MenuItem(String label, PimPage page) {
		this.label = Objects.requireNonNull(label);
		this.page = Objects.requireNonNull(page);
	}
	
	public String getLabel() {
		return label;
	}

	public SvgIcon getIcon() {
		return icon;
	}

	public PimPage getPage() {
		return page;
	}

	public String getParam() {
		return param;
	}

	public String getPath() {
		String path = "page/" + page.getAlias();
		if (param != null) {
			path += "/" + param;
		}
		return path;
	}

	
}
