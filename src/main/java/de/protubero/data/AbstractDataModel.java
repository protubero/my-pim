package de.protubero.data;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import de.protubero.beanstore.api.BeanStore;
import de.protubero.beanstore.api.BeanStoreTransactionResult;
import de.protubero.beanstore.api.ExecutableBeanStoreTransaction;
import de.protubero.beanstore.entity.AbstractEntity;

public abstract class AbstractDataModel<T extends AbstractEntity> {

	@Autowired
	protected BeanStore beanStore;
	
	protected Class<T> entityClass;
	
	public AbstractDataModel(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public List<T> filter(Predicate<T> predicate) {
		return beanStore.state().entity(entityClass).stream().filter(predicate).collect(Collectors.toList());
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}
	
	protected BeanStoreTransactionResult transaction(Consumer<ExecutableBeanStoreTransaction> consumer) {
		ExecutableBeanStoreTransaction tx = beanStore.transaction();
		consumer.accept(tx);
		return tx.executeBlocking();
	}
	

	
}
