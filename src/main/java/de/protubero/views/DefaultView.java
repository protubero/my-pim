package de.protubero.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("My PIM")
@Route(value="", layout = MainLayout.class)
public final class DefaultView extends VerticalLayout  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5196469155027556572L;


}
