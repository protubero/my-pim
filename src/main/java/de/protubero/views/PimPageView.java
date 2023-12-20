package de.protubero.views;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import de.protubero.appconf.PimApplicationModel;
import de.protubero.appconf.PimDataModel;
import de.protubero.appconf.PimPage;

public abstract class PimPageView extends VerticalLayout implements BeforeEnterObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636403310006333923L;

	@Autowired
	protected PimApplicationModel appModel;

	@Autowired
	protected PimDataModel dataModel;
	
	protected PimPage page;
	protected Optional<String> pageParam;


	public PimPageView() {
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		String pageAlias = event.getRouteParameters().get("pageAlias").get();
		Optional<PimPage> pageOpt = appModel.pageByAlias(pageAlias);
		if (pageOpt.isEmpty()) {
			throw new RuntimeException("invalid page alias " + pageAlias);
		}
		page = pageOpt.get();
		
	   pageParam = event.getRouteParameters().get("pageParam");
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		render();
	}

	public abstract void render();
	
	@Override
	protected void onDetach(DetachEvent detachEvent) {
	}
	
}
