package de.protubero.data;

import java.time.LocalDateTime;

import de.protubero.beanstore.entity.AbstractEntity;
import de.protubero.beanstore.entity.Entity;

@Entity(alias="task")
public class Task extends AbstractEntity implements DeletableEntity {

	private Priority priority;
	private Boolean completed;
	private String text;
	private LocalDateTime createdAt;
	private LocalDateTime completedAt;
	private Boolean deleted;
	private LocalDateTime deletedAt;
	
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
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
	public LocalDateTime getCompletedAt() {
		return completedAt;
	}
	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
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
