package de.protubero.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.protubero.beanstore.api.BeanStore;
import de.protubero.beanstore.api.EntityReadAccess;
import jakarta.annotation.PostConstruct;

@Component
public class TaskDataModel {

	
	@Autowired
	private BeanStore beanStore;
		
	@PostConstruct
    private void postConstruct() {
    }
	
	public List<Task> allTasks() {
		EntityReadAccess<Task> taskStore = beanStore.read().entity(Task.class);
		return taskStore.asList();
	}
	
}
