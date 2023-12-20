package de.protubero.appinit;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.protubero.data.TaskDataModel;
import de.protubero.views.PimPageView;

@PageTitle("Demo")
@Route("eins")
public class DemoPageHandler extends PimPageView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8195598247572116805L;
	@Autowired
	private TaskDataModel dataModel;
	
	@Override
	public void render() {
		H2 h = new H2("Hi");
		add(h);
	}

}
