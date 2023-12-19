package de.protubero.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;

@PageTitle("Tasks")
public class TaskListPageView extends PimPageView implements BeforeEnterObserver {

    private String type;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
    	type = event.getRouteParameters().get("type").get();
    	System.out.println(type);
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1104214703503557483L;

	
	public TaskListPageView() {
		
	}
	
	@Override
	protected void onAttach(AttachEvent attachEvent) {
		H2 h2 = new H2();
		h2.setText(type);
		add(h2);
		System.out.println("Create Task View " + type);
	}
	
}
