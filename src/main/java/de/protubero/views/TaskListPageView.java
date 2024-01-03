package de.protubero.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.protubero.data.Priority;
import de.protubero.data.Task;
import de.protubero.data.TaskDataModel;
import jakarta.annotation.PostConstruct;

@PageTitle("Tasks")
@Route(value = "tasks", layout = MainLayout.class)
public class TaskListPageView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8195598247572116805L;
	
	@Autowired
	private TaskDataModel dataModel;

	private Priority selectedPriority = Priority.Today;
	
	@Autowired
	private MainViewBus mainViewBus;
	
	private VerticalLayout taskList = new VerticalLayout();
	
	@PostConstruct
    private void postConstruct() {
		// Priority Selection
		HorizontalLayout priorityButtonLayout = new HorizontalLayout();		
		for (Priority priority : Priority.values()) {
			Button priorityButton = new Button(priority.name());
			priorityButton.setDisableOnClick(true);			
			priorityButton.addClickListener(evt -> {
				if (priority != selectedPriority) {
					selectedPriority = priority;
					renderTaskList();
					priorityButtonLayout.getChildren().forEach(component -> {
						if (component instanceof Button && component != priorityButton) {
							((Button) component).setEnabled(true);
						}
					});
				}	
			});
			priorityButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
			priorityButtonLayout.add(priorityButton);
		}
		add(priorityButtonLayout);

		// new task Input
		HorizontalLayout addNewTaskLayout = new HorizontalLayout();		
		TextField textField = new TextField();
		textField.addValueChangeListener(event -> {
			if (event.getValue().trim().length() > 3) {
				Task newTask = dataModel.createTask(event.getValue(), selectedPriority);
				Checkbox checkbox = createCheckbox(newTask);

				taskList.addComponentAsFirst(checkbox);
				
				textField.clear();
			}
		});

		//textField.setLabel("New Task");
		textField.setPlaceholder("Enter new task here ...");
		textField.setClearButtonVisible(true);
		//textField.setPrefixComponent(VaadinIcon.NOTEBOOK.create());
		addNewTaskLayout.add(textField);

		add(addNewTaskLayout);
		
		renderTaskList();

		add(taskList);
	}

	private void renderTaskList() {
		mainViewBus.getMainView().setPageTitle("Tasks (" + selectedPriority.name() + ")");
		
		taskList.removeAll();
		List<Task> tasks = dataModel.tasksByPriority(selectedPriority);		
		
		for (Task task : tasks) {
			HorizontalLayout checkBoxLayout = new HorizontalLayout();
			Checkbox checkbox = createCheckbox(task);
			Icon trashIcon = VaadinIcon.TRASH.create();
			trashIcon.addClickListener(evt -> {
				dataModel.delete(task);
				taskList.remove(checkBoxLayout);
			});
			checkBoxLayout.add(checkbox, trashIcon);

			taskList.add(checkBoxLayout);
		}
	}

	private Checkbox createCheckbox(Task task) {
		Checkbox checkbox = new Checkbox(task.getText());
		checkbox.setId(task.id().toString());
		Boolean isCompleted = task.getCompleted();
		if (isCompleted != null) {
			checkbox.setValue(isCompleted);
		}
		
		checkbox.addValueChangeListener(event -> {
			if (event.getValue().booleanValue()) {
				dataModel.completeTask(task);
			} else {
				dataModel.uncompleteTask(task);
			}
		});
		
		return checkbox;
	}


	


}
