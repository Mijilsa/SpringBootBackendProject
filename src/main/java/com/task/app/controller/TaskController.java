package com.task.app.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.app.exception.ResourceNotFoundException;
import com.task.app.model.Tasks;
import com.task.app.repo.TaskRepository;
import com.task.app.service.SequenceGeneratorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/tasks")
	public List<Tasks> getAllTasks() {
		return taskRepository.findAll();
	}
	
	@PostMapping("/tasks")
	public Tasks createTasks(@Valid @RequestBody Tasks tasks) {
		tasks.setId(sequenceGeneratorService.generateSequence(Tasks.SEQUENCE_NAME));
		return taskRepository.save(tasks);
	}
	
	@DeleteMapping("/tasks/{id}")
    public Map < String, Boolean > deleteTasks(@PathVariable(value = "id") Long id)
    throws ResourceNotFoundException {
		Tasks task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

		taskRepository.delete(task);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Tasks> completeTask(@PathVariable Long id, @RequestBody Tasks tasksdetails) throws ResourceNotFoundException{
		Tasks tasks = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("taskRepository not exist with id :" + id));
		
		tasks.setStatus(tasksdetails.getStatus());
		
		
		Tasks updatedTask = taskRepository.save(tasks);
		return ResponseEntity.ok(updatedTask);
	}
}
