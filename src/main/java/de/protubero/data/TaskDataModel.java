package de.protubero.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class TaskDataModel extends AbstractDataModel<Task> {

		
	public TaskDataModel() {
		super(Task.class);
	}

	@PostConstruct
    private void postConstruct() {
    }
	
	public List<Task> allTasks() {
		return filter(ModelPredicates.undeletedTask());
	}

	public List<Task> deletedTasks() {
		return filter(ModelPredicates.deletedTask());
	}
	
	public List<Task> tasksByPriority(Priority priority) {
		return filter(ModelPredicates.undeletedTask().and(ModelPredicates.withPriority(priority)));
	}

	public List<Task> deletedTasksByPriority(Priority priority) {
		return filter(ModelPredicates.deletedTask().and(ModelPredicates.withPriority(priority)));
	}

	public void completeTask(Task task) {
		transaction(tx -> {
			Task updateableTask = tx.update(task);
			updateableTask.setCompleted(true);
			updateableTask.setCompletedAt(LocalDateTime.now());
		});
	}

	public void uncompleteTask(Task task) {
		transaction(tx -> {
			Task updateableTask = tx.update(task);
			updateableTask.setCompleted(false);
		});
	}
	
	public void updateTaskText(Task task, String text) {
		transaction(tx -> {
			Task updateableTask = tx.update(task);
			updateableTask.setText(text);
		});
	}
	
	public Task createTask(String text, Priority priority) {
		var result = transaction(tx -> {
			Task task = tx.create(Task.class);
			task.setText(text); 
			task.setCreatedAt(LocalDateTime.now());
			task.setPriority(priority);
		});
		return (Task) result.getInstanceEvents().get(0).newInstance();
	}

	public void delete(Task task) {
		transaction(tx -> {
			var updtask = tx.update(task);
			updtask.setDeleted(true);
			updtask.setDeletedAt(LocalDateTime.now());
		});
	}
	
	public void undelete(Task task) {
		transaction(tx -> {
			var updtask = tx.update(task);
			updtask.setDeleted(false);
			updtask.setDeletedAt(LocalDateTime.now());
		});
	}
}
