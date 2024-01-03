package de.protubero.data;

import java.util.function.Predicate;

public class ModelPredicates {

	public static Predicate<Task> withPriority(Priority prio) {
		return (task) -> task.getPriority() == prio; 
	}

	public static Predicate<Task> undeletedTask() {
		return (task) -> task.getDeleted() != Boolean.TRUE; 
	}

	public static Predicate<Task> deletedTask() {
		return (task) -> task.getDeleted() == Boolean.TRUE;
	}
	
	public static Predicate<Note> undeletedNote() {
		return (note) -> note.getDeleted() != Boolean.TRUE; 
	}

	public static Predicate<Note> deletedNote() {
		return (note) -> note.getDeleted() == Boolean.TRUE;
	}
	
}
