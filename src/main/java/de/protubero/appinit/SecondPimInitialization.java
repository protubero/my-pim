package de.protubero.appinit;

import org.springframework.stereotype.Component;

import de.protubero.appconf.PimApplicationConfig;
import de.protubero.appconf.PimInitialization;
import de.protubero.appconf.PimPage;

@Component
public class SecondPimInitialization implements PimInitialization {

	@Override
	public void init(PimApplicationConfig appConf) {
		PimPage page = appConf.createPage("zwei", DemoPageHandler.class);
		
		appConf.menuItem("Zwei", page);
	}

}
