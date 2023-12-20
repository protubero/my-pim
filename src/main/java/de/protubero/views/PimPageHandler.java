package de.protubero.views;

import com.vaadin.flow.component.HasOrderedComponents;

public interface PimPageHandler {

	default void onDetach() {
		
	}

	void render(HasOrderedComponents pageLayout);

}
