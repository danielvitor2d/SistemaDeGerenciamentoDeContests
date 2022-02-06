package gemp.github.service.team.impl;

import gemp.github.model.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gemp.github.repository.TeamRepository;
import gemp.github.service.team.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteById(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    @Override
    public Team findById(Long id) throws Exception {
        return teamRepository.findById(id).orElseThrow(() -> new Exception());
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}