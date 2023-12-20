package de.protubero.appinit;

import org.springframework.stereotype.Component;

import de.protubero.appconf.PimApplicationConfig;
import de.protubero.appconf.PimInitialization;
import de.protubero.appconf.PimPage;

@Component
public class FirstPimInitialization implements PimInitialization {

	@Override
	public void init(PimApplicationConfig appConf) {
		PimPage page = appConf.createPage("eins", DemoPageHandler.class);

		appConf.menuItem("One", DemoPageHandler.class);
		appConf.menuItem("One param", DemoPageHandler.class, "abc");
	}

}
