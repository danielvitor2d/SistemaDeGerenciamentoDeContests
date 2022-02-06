package service.team;

import model.team.Team;

import java.util.List;

public interface TeamService {
    Team save(Team team);

    void deleteById(Long teamId);

    Team findById(Long id) throws Exception;

    List<Team> findAll();
}
