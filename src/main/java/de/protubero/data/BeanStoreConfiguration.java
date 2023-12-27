package de.protubero.data;

import java.io.File;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.esotericsoftware.kryo.kryo5.Serializer;
import com.esotericsoftware.kryo.kryo5.serializers.DefaultSerializers.EnumSerializer;

import de.protubero.beanstore.api.BeanStore;
import de.protubero.beanstore.api.BeanStoreFactory;

@Configuration
public class BeanStoreConfiguration {

	public static Logger log = LoggerFactory.getLogger(BeanStoreConfiguration.class);

	@SuppressWarnings("unchecked")
	@Bean
	public BeanStore createBeanStore() {
		log.info("Create Bean Store");
		File dataFile = new File("c:/work/tasks.kryo");
		BeanStoreFactory factory = BeanStoreFactory.of(dataFile);
		factory.registerEntity(Task.class);
		factory.kryoConfig().register(Priority.class, (Serializer) new EnumSerializer(Priority.class), 303);
		factory.initNewStore(tx -> {
			Task newTask = tx.create(Task.class);
			newTask.setText("Eat lunch");
			newTask.setCreatedAt(LocalDateTime.now());
			newTask.setPriority(Priority.Today);
		});

		/*
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
		*/

		return factory.create();
	}

}
