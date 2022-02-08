package model.dao;

import java.util.List;

import model.bean.Contest;

public interface IContestDAO {
	boolean save(Contest contest);
	boolean update(int contestId, Contest contest);
	boolean delete(int contestId);
	Contest getById(int contestId);
	List<Contest> listContests();
}
