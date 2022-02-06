package service.submission;

import model.submission.Submission;

import java.util.List;

public interface SubmissionService {
    Submission save(Submission submission);

    void deleteById(Long submissionId);

    Submission findById(Long id) throws Exception;

    List<Submission> findAll();
}
