package de.protubero;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

import de.protubero.appconf.PimApplicationConfig;
import de.protubero.appconf.PimApplicationModel;
import de.protubero.appconf.PimInitialization;

@Component
public class ApplicationServiceInitListener implements VaadinServiceInitListener {

	
	public static Logger log = LoggerFactory.getLogger(ApplicationServiceInitListener.class);
	
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
		log.info("Init Application Config. No of init modules=" + (initList ==null ? 0 : initList.size()));
				
		PimApplicationConfig appConf = new PimApplicationConfig();
		initList.forEach(init -> init.init(appConf));
		
		log.info("Init Application Model");
		model.init(appConf);

		log.info("Create Routes");
		//model.createRoutes();
	}
}