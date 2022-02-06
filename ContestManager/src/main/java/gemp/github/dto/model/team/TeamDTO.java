package gemp.github.dto.model.team;

import com.fasterxml.jackson.annotation.JsonInclude;
import gemp.github.model.person.Person;
import gemp.github.model.team.Team;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO extends RepresentationModel<TeamDTO> {

	@NotNull(message="Id cannot be null")
	private Long id;

	@NotNull(message="Id cannot be null")
	private String teamName;

	@NotNull(message="Id cannot be null")
	private String teamPhotoUrl;

	@NotNull(message="Id cannot be null")
	private Long studentId01;

	@NotNull(message="Id cannot be null")
	private Long studentId02;

	@NotNull(message="Id cannot be null")
	private Long studentId03;

	@NotNull(message="Id cannot be null")
	private Long coachId;

	public Team convertDTOToEntity() {
		return new ModelMapper().map(this, Team.class);
	}
}
