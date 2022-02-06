package gemp.github.service.problem;

import gemp.github.model.problem.Problem;

import java.util.List;

public interface ProblemService {
    Problem save(Problem problem);

    void deleteById(Long problemId);

    Problem findById(Long id) throws Exception;

    List<Problem> findAll();
}
