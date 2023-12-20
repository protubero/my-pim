package de.protubero.appconf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.protubero.views.PimPageHandler;

@Component
public class PimApplicationConfig {

	//private Map<String, PimPage> pageMap = new HashMap<>();
	private List<MenuItem> menuItems = new ArrayList<>();
	
	@Autowired
	private BeanFactory beanFactory;
	
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
	
	public PimPage createPage(String alias, Class<? extends Component> handlerClass) {
		if (pageMap.containsKey(alias)) {
			throw new RuntimeException("Duplicate page alias: " + alias);
		}
		
		PimPage page = new PimPage(alias, handlerClass);
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
