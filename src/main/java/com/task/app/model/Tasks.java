package com.task.app.model;
import javax.validation.constraints.NotBlank;



import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "user")
public class Tasks {
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	@NotBlank
    @Size(max=100)
    @Indexed(unique=true)
	private String task;
	
	@NotBlank
    @Size(max=100)
    @Indexed(unique=true)
	private String status;
	
	
	public Tasks() {
		
	}
	
	
	public Tasks(String task, String status) {
		super();
		this.task = task;
		this.status = status;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", task=" + task + "status"+status+"]";
	}	
}
