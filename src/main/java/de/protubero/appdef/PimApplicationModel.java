package de.protubero.appdef;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouteConfiguration;

import de.protubero.views.MainLayout;
import de.protubero.views.TaskListPageView;

@Component
public class PimApplicationModel {

	private PimApplicationConfig config;
	
	public void init(PimApplicationConfig config) {
		this.config = Objects.requireNonNull(config);
	}
	
	public void configPage(String path, String param) {
		PimPage page = config.pageByPath();
		
	}

    public SideNav createNavigation() {
        SideNav nav = new SideNav();
        
        config.getMenuItems().forEach(menuItem -> {
        	SideNavItem item = new SideNavItem(menuItem.getLabel());
        	item.setPath(menuItem.getPath());
        	if (menuItem != null) {
        		item.setPrefixComponent(menuItem.getIcon());
        	}	
        	
        	nav.addItem(item);
        });
//        
//        nav.addItem(new SideNavItem("Tasks", "tasks/xy", LineAwesomeIcon.GLOBE_SOLID.create()));
//        
//        SideNavItem sideNavItem = new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create());
//        sideNavItem.addItem(new SideNavItem("About sub", AboutView.class, LineAwesomeIcon.FILE.create()));
//		nav.addItem(sideNavItem);

        return nav;
    }

	@SuppressWarnings("unchecked")
	public void createRoutes() {
		// add view only during development time
		RouteConfiguration configuration = RouteConfiguration.forApplicationScope();

		config.pageList().forEach(page -> {
			configuration.setRoute(page.getRoute(), // path
					TaskListPageView.class,
					MainLayout.class
			);			
		});
		
	}
	
}
