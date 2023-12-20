package de.protubero.appconf;

import java.util.Objects;

import com.vaadin.flow.component.Component;

public class PimPage {

	private String alias;
	private Class<? extends Component> handlerClass;
	
	public PimPage(String alias, Class<? extends Component> handlerClass) {
		this.alias = Objects.requireNonNull(alias);
		this.handlerClass = Objects.requireNonNull(handlerClass);
	}

	public String getAlias() {
		return alias;
	}

	public Class<? extends Component> getHandlerClass() {
		return handlerClass;
	}

	/*
	public PimPageHandler instantiateHandler() {
		PimPageHandler handler;
		try {
			beanFactory.
			handler = handlerClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("Error instaniating page handler", e);
		}
		return handler;
	}*/
	



}
