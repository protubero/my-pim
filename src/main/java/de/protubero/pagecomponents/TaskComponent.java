package de.protubero.pagecomponents;

import com.vaadin.flow.component.checkbox.Checkbox;

public class TaskComponent extends PageComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2156647342364069504L;

	private Checkbox checkbox;
	
	public TaskComponent() {
		checkbox = new Checkbox();

		add(checkbox);		
	}
	
	public void setLabel(String label) {
		checkbox.setLabel(label);
	}
	
}
