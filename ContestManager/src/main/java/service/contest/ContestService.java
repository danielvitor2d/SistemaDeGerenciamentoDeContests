package service.contest;

import model.contest.Contest;

import java.util.List;

public interface ContestService {
    Contest save(Contest contest);

    void deleteById(Long contestId);

    Contest findById(Long id) throws Exception;

    List<Contest> findAll();
}
