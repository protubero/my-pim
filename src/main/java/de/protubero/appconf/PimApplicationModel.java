package de.protubero.appconf;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.sidenav.SideNav;

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
			nav.addItem(menuItem);
		});

		return nav;
	}


}
