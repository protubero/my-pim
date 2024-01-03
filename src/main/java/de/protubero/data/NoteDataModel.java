package de.protubero.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NoteDataModel extends AbstractDataModel<Note> {

	public NoteDataModel() {
		super(Note.class);
	}
	
	public List<Note> allNotes() {
		return filter(ModelPredicates.undeletedNote());
	}

	public List<Note> deletedNotes() {
		return filter(ModelPredicates.deletedNote());
	}
	
	public void updateNoteText(Note note, String text) {
		transaction(tx -> {
			Note updateableNote = tx.update(note);
			updateableNote.setText(text);
		});
	}
	
	public Note createNote(String text) {
		var result = transaction(tx -> {
			Note note = tx.create(Note.class);
			note.setText(text); 
			note.setCreatedAt(LocalDateTime.now());
		});
		return (Note) result.getInstanceEvents().get(0).newInstance();
	}

	public void delete(Note note) {
		transaction(tx -> {
			var updtask = tx.update(note);
			updtask.setDeleted(true);
			updtask.setDeletedAt(LocalDateTime.now());
		});
	}
	
	public void undelete(Note note) {
		transaction(tx -> {
			var updtask = tx.update(note);
			updtask.setDeleted(false);
			updtask.setDeletedAt(LocalDateTime.now());
		});
	}

}
