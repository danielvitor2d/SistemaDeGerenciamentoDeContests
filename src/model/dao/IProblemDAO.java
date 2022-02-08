package src.model.dao;

import java.util.List;

import src.model.bean.Problem;

public interface IProblemDAO {
	boolean save(Problem problem);
	Problem getById(int problemId);
	List<Problem> listProblems();
}
