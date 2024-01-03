package de.protubero.data;

import java.time.LocalDateTime;

import de.protubero.beanstore.entity.AbstractEntity;
import de.protubero.beanstore.entity.Entity;

@Entity(alias="note")
public class Note extends AbstractEntity implements DeletableEntity {

	private String text;
	private LocalDateTime createdAt;
	private Boolean deleted;
	private LocalDateTime deletedAt;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public Boolean getDeleted() {
		return deleted;
	}
	@Override
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	@Override
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	
}
