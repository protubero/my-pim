package de.protubero.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("My PIM")
@Route(value="")
public final class DefaultView extends VerticalLayout implements BeforeEnterObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5196469155027556572L;

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		event.forwardTo(HomePageView.class);
	}


}
