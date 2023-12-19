package de.protubero.appconf;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouteConfiguration;

import de.protubero.views.MainLayout;
import de.protubero.views.PimPageView;

@Component
public class PimApplicationModel {

	public static Logger log = LoggerFactory.getLogger(PimApplicationModel.class);

	private PimApplicationConfig config;

	public void init(PimApplicationConfig config) {
		this.config = Objects.requireNonNull(config);
	}

	public SideNav createNavigation() {
		SideNav nav = new SideNav();

		config.getMenuItems().forEach(menuItem -> {
			SideNavItem item = new SideNavItem(menuItem.getLabel());
			item.setPath(menuItem.getPath());
			if (menuItem.getIcon() != null) {
				item.setPrefixComponent(menuItem.getIcon());
			}

			nav.addItem(item);
		});

		return nav;
	}

	@SuppressWarnings("unchecked")
	public void createRoutes() {
		// add view only during development time
		RouteConfiguration configuration = RouteConfiguration.forApplicationScope();
		configuration.setRoute("page/:pageAlias/:pageParam?", // path
				PimPageView.class, MainLayout.class);
	}

	public Optional<PimPage> pageByAlias(String pageAlias) {
		return config.page(pageAlias);
	}

}
