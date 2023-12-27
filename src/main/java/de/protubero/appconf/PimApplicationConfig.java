package de.protubero.appconf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.sidenav.SideNavItem;

@Component
public class PimApplicationConfig {

	//private Map<String, PimPage> pageMap = new HashMap<>();
	private List<SideNavItem> menuItems = new ArrayList<>();
	
	
	public void menuItem(SideNavItem item) {
		menuItems.add(item);
	}

	public List<SideNavItem> getMenuItems() {
		return menuItems;
	}

	
}
