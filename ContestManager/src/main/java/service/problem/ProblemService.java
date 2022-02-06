package service.problem;

import model.problem.Problem;

import java.util.List;

public interface ProblemService {
    Problem save(Problem problem);

    void deleteById(Long problemId);

    Problem findById(Long id) throws Exception;

    List<Problem> findAll();
}
