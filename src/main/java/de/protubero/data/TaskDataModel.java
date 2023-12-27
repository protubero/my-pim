package de.protubero.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.protubero.beanstore.api.BeanStore;
import de.protubero.beanstore.api.ExecutableBeanStoreTransaction;
import jakarta.annotation.PostConstruct;

@Component
public class TaskDataModel {


	@Autowired
	private BeanStore beanStore;
		
	@PostConstruct
    private void postConstruct() {
    }
	
	public List<Task> allTasks() {
		return beanStore.state().entity(Task.class).asList();
	}
	
	public List<Task> tasksByPriority(Priority priority) {
		return beanStore.state().entity(Task.class).stream().filter(task -> task.getPriority() == priority)
				.collect(Collectors.toList());
	}
	
	public void completeTask(Task task) {
		ExecutableBeanStoreTransaction tx = beanStore.transaction();
		Task updateableTask = tx.update(task);
		updateableTask.setCompleted(true);
		updateableTask.setCompletedAt(LocalDateTime.now());
		tx.executeBlocking();
	}
	
	public void uncompleteTask(Task task) {
		ExecutableBeanStoreTransaction tx = beanStore.transaction();
		Task updateableTask = tx.update(task);
		updateableTask.setCompleted(false);
		tx.executeBlocking();
	}
	
	public void updateTaskText(Task task, String text) {
		ExecutableBeanStoreTransaction tx = beanStore.transaction();
		Task updateableTask = tx.update(task);
		updateableTask.setText(text);
		tx.executeBlocking();
	}
	
	public Task createTask(String text, Priority priority) {
		ExecutableBeanStoreTransaction tx = beanStore.transaction();
		Task task = tx.create(Task.class);
		task.setText(text); 
		task.setCreatedAt(LocalDateTime.now());
		task.setPriority(priority);
		tx.executeBlocking();
		return task;
	}
	
}
