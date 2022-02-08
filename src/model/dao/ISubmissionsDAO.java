package src.model.dao;

import java.util.List;

import src.model.bean.Submission;

public interface ISubmissionsDAO {
	boolean save(Submission submission);
	boolean update(int submissionId, Submission submission);
	boolean delete(int submissionId);
	Submission getById(int submissionId);
	List<Submission> listSubmissions();
	List<Submission> listSubmissionsByTeam(int teamId);
}
