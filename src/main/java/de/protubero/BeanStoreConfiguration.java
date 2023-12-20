package de.protubero;

import java.io.File;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import de.protubero.beanstore.api.BeanStore;
import de.protubero.beanstore.api.BeanStoreFactory;
import de.protubero.beanstore.base.entity.AbstractEntity;
import de.protubero.beanstore.base.entity.Entity;
import de.protubero.data.Task;

@Configuration
public class BeanStoreConfiguration {

	public static Logger log = LoggerFactory.getLogger(BeanStoreConfiguration.class);

	@SuppressWarnings("unchecked")
	@Bean
	public BeanStore createBeanStore() {
		log.info("Create Bean Store");
		File dataFile = new File("c:/work/tasks.kryo");
		BeanStoreFactory factory = BeanStoreFactory.of(dataFile);
		factory.initNewStore(tx -> {
			Task newTask = tx.create(Task.class);
			newTask.setText("Hello World");
		});

		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

		Set<BeanDefinition> beanDefs = provider.findCandidateComponents("de.protubero.data");
		for (BeanDefinition bd : beanDefs) {
			if (bd instanceof AnnotatedBeanDefinition) {
				try {
					Class<? extends AbstractEntity> cls = (Class<? extends AbstractEntity>) Class.forName(bd.getBeanClassName());
					factory.registerEntity(cls);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Entity class not found", e);
				}
			}
		}

		return factory.create();
	}

}
