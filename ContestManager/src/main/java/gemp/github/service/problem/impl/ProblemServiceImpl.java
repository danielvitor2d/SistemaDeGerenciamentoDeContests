package gemp.github.service.problem.impl;

import gemp.github.model.problem.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gemp.github.repository.ProblemRepository;
import gemp.github.service.problem.ProblemService;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    private ProblemRepository problemRepository;

    @Autowired
    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public Problem save(Problem problem) {
        return problemRepository.save(problem);
    }

    @Override
    public void deleteById(Long problemId) {
        problemRepository.deleteById(problemId);
    }

    @Override
    public Problem findById(Long id) throws Exception {
        return problemRepository.findById(id).orElseThrow(() -> new Exception());
    }

    @Override
    public List<Problem> findAll() {
        return problemRepository.findAll();
    }
}
