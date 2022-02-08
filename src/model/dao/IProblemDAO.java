package model.dao;

import java.util.List;

import model.bean.Problem;

public interface IProblemDAO {
	boolean save(Problem problem);
	Problem getById(int problemId);
	List<Problem> listProblems();
}
