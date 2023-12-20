package de.protubero.appconf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.protubero.beanstore.api.BeanStore;

@Component
public class PimDataModel {

	@Autowired
	private BeanStore beanStore;
	
}
