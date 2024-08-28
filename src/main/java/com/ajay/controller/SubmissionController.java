package com.ajay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.model.Submission;
import com.ajay.model.UserDto;
import com.ajay.service.SubmissionService;
import com.ajay.service.TaskService;
import com.ajay.service.UserService;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<Submission> submitTask(
			@RequestParam Long task_id,
			@RequestParam String github_link,
			@RequestHeader("Authorization")String jwt
			)throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		Submission submission = submissionService.submitTask(task_id, github_link, user.getId(), jwt);
		return new ResponseEntity<>(submission, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Submission> getSubmissionById(
			@PathVariable Long id,
			@RequestHeader("Authorization")String jwt
			)throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		Submission submission = submissionService.getTaskSubmissionById(id);
		return new ResponseEntity<>(submission, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Submission>> getAllSubmissions(
				@RequestHeader("Authorization")String jwt
			)throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		List<Submission> submissions = submissionService.getAllTaskSubmissions();
		return new ResponseEntity<>(submissions, HttpStatus.CREATED);
	}
	
	@GetMapping("/task/{taskId}")
	public ResponseEntity<List<Submission>> getAllSubmission(
				@PathVariable Long taskId,
				@RequestHeader("Authorization")String jwt
			)throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		List<Submission> submissions = submissionService.getTaskSubmmissionByTaskId(taskId);
		return new ResponseEntity<>(submissions, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Submission> acceptOrDeclineSubmission(
				@PathVariable Long id,
				@RequestParam("status") String status,
				@RequestHeader("Authorization")String jwt
			)throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		Submission submission = submissionService.acceptDeclineSubmission(id, status);
		return new ResponseEntity<>(submission, HttpStatus.CREATED);
	}
}
