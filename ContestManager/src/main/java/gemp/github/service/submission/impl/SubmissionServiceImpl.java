package gemp.github.service.submission.impl;

import gemp.github.model.submission.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gemp.github.repository.SubmissionRepository;
import gemp.github.service.submission.SubmissionService;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    private SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public Submission save(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Override
    public void deleteById(Long submissionId) {
        submissionRepository.deleteById(submissionId);
    }

    @Override
    public Submission findById(Long id) throws Exception {
        return submissionRepository.findById(id).orElseThrow(() -> new Exception());
    }

    @Override
    public List<Submission> findAll() {
        return submissionRepository.findAll();
    }
}