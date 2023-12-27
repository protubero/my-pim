package de.protubero.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.RouteParameters;

import de.protubero.appconf.PimApplicationModel;
import de.protubero.appconf.PimDataModel;

public abstract class PimPageView extends VerticalLayout implements BeforeEnterObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636403310006333923L;

	@Autowired
	protected PimApplicationModel appModel;

	@Autowired
	protected PimDataModel dataModel;

	protected RouteParameters routeParameters;

	public PimPageView() {
	}

	public abstract String pageTitle();
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		routeParameters = event.getRouteParameters();
		beforeEnter();
	}

	public void beforeEnter() {
		
	}
	
	@Override
	protected void onAttach(AttachEvent attachEvent) {
	}

	public abstract void render();
	
	@Override
	protected void onDetach(DetachEvent detachEvent) {
	}
	
}
