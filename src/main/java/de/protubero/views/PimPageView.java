package de.protubero.views;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;

import de.protubero.appconf.PimApplicationModel;
import de.protubero.appconf.PimPage;

@PageTitle("Tasks")
public final class PimPageView extends VerticalLayout implements BeforeEnterObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3636403310006333923L;

	private PimApplicationModel model;
	
	private PimPage page;
	private Optional<String> pageParam;

	public PimPageView(@Autowired PimApplicationModel model) {
		this.model = Objects.requireNonNull(model);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		String pageAlias = event.getRouteParameters().get("pageAlias").get();
		Optional<PimPage> pageOpt = model.pageByAlias(pageAlias);
		if (pageOpt.isEmpty()) {
			throw new RuntimeException("invalid page alias " + pageAlias);
		}
		page = pageOpt.get();
		
	   Optional<String> pageParam = event.getRouteParameters().get("pageParam");
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		page.onAttach(this, pageParam);
	}

}
