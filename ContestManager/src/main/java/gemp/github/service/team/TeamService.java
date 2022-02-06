package gemp.github.service.team;

import gemp.github.model.team.Team;

import java.util.List;

public interface TeamService {
    Team save(Team team);

    void deleteById(Long teamId);

    Team findById(Long id) throws Exception;

    List<Team> findAll();
}
