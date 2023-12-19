package de.protubero;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import de.protubero.appdef.PimApplicationConfig;
import de.protubero.appdef.PimPage;

@Component
public class FirstPimInitialization implements PimInitialization {

	@Override
	public void init(PimApplicationConfig appConf) {
		
		PimPage page = appConf.page("eins");
		page.setRoute("eins");

		appConf.menuItem("One", page, parameter);
	}

}
