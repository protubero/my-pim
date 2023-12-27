package de.protubero.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value="", layout=MainLayout.class)
public final class HomePageView extends VerticalLayout implements BeforeEnterObserver {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2475945030001360485L;

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
	}


}
