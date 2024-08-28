package com.ajay.service;

import java.util.List;

import com.ajay.model.Submission;

public interface SubmissionService {
	
	Submission submitTask(Long taskId, String gitHubLink, Long userId, String jwt) throws Exception;
	
	Submission getTaskSubmissionById(Long submissionId) throws Exception;
	
	List<Submission> getAllTaskSubmissions();
	
	List<Submission> getTaskSubmmissionByTaskId(Long taskId);
	
	Submission acceptDeclineSubmission(Long id, String status) throws Exception ;
	
}
