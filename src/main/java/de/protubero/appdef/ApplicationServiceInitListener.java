package de.protubero.appdef;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

import de.protubero.PimInitialization;
import de.protubero.views.MainLayout;
import de.protubero.views.TaskListPageView;

@Component
public class ApplicationServiceInitListener implements VaadinServiceInitListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7730766293430102445L;

    @Autowired
    private List<PimInitialization> initList;	
	
    @Autowired
    private PimApplicationModel model;
    
    
	@Override
	public void serviceInit(ServiceInitEvent event) {
		if (initList != null) {
			System.out.println("app configs = " + initList.size());
		}
		
		PimApplicationConfig appConf = new PimApplicationConfig();
		initList.forEach(init -> init.init(appConf));
		
		model.init(appConf);

		model.createRoutes();
	}
}