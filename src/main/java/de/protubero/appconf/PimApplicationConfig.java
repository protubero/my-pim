package de.protubero.appconf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.router.RouteParameters;

@Component
public class PimApplicationConfig {

	private Map<String, PimPage> pageMap = new HashMap<>();
	private List<MenuItem> menuItems = new ArrayList<>();
	
	public MenuItem menuItem(String label, PimPage page, String param) {
		MenuItem item = new MenuItem(label, page, param);
		menuItems.add(item);
		return item;
	}

	public MenuItem menuItem(String label, PimPage page) {
		MenuItem item = new MenuItem(label, page);
		menuItems.add(item);
		return item;
	}
	
	public PimPage createPage(String alias) {
		if (pageMap.containsKey(alias)) {
			throw new RuntimeException("Duplicate page alias: " + alias);
		}
		
		PimPage page = new PimPage(alias);
		pageMap.put(alias, page);
		return page;
	}
	
	public Optional<PimPage> page(String alias) {
		return Optional.ofNullable(pageMap.get(alias));
	}
	

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public Collection<PimPage> pageList() {
		return pageMap.values();
	}
	
}
