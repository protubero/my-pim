package de.protubero.data;

import java.time.LocalDateTime;

public interface DeletableEntity  {

	Boolean getDeleted();
	
	void setDeleted(Boolean deleted);
	
	LocalDateTime getDeletedAt();
	
	void setDeletedAt(LocalDateTime deletedAt);
	
	
}
