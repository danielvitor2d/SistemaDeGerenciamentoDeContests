package gemp.github.service.contest.impl;

import gemp.github.model.contest.Contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gemp.github.repository.ContestRepository;
import gemp.github.service.contest.ContestService;

import java.util.List;

@Service
public class ContestServiceImpl implements ContestService {
    private ContestRepository contestRepository;

    @Autowired
    public ContestServiceImpl(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    @Override
    public Contest save(Contest contest) {
        return contestRepository.save(contest);
    }

    @Override
    public void deleteById(Long contestId) {
        contestRepository.deleteById(contestId);
    }

    @Override
    public Contest findById(Long id) throws Exception {
        return contestRepository.findById(id).orElseThrow(() -> new Exception());
    }

    @Override
    public List<Contest> findAll() {
        return contestRepository.findAll();
    }
}
