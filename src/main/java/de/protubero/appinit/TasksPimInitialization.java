package de.protubero.appinit;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;

import de.protubero.appconf.PimApplicationConfig;
import de.protubero.appconf.PimInitialization;
import de.protubero.views.HomePageView;
import de.protubero.views.TaskListPageView;

@Component
public class TasksPimInitialization implements PimInitialization {

	@Override
	public void init(PimApplicationConfig appConf) {
		appConf.menuItem(new SideNavItem("Home", HomePageView.class));
		
		SideNavItem tasksMenuItem = new SideNavItem("Tasks", TaskListPageView.class);
		
		appConf.menuItem(tasksMenuItem);
	}

}
