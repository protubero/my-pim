package de.protubero.appdef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.router.RouteParameters;

@Component
public class PimApplicationConfig {

	private Map<String, PimPage> pageMap = new HashMap<>();
	private List<MenuItem> menuItems = new ArrayList<>();
	
	public void menuItem(String label, PimPage page, RouteParameters params) {
		MenuItem item = new MenuItem(label);
		menuItems.add(item);
		return item;
	}
	
	public PimPage page(String alias) {
		if (pageMap.containsKey(alias)) {
			throw new RuntimeException("Duplicate page alias: " + alias);
		}
		
		PimPage page = new PimPage(alias);
		pageMap.put(alias, page);
		return page;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public Collection<PimPage> pageList() {
		return pageMap.values();
	}
	
}
