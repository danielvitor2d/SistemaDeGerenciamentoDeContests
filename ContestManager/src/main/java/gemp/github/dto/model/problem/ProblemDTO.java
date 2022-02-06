package gemp.github.dto.model.problem;

import com.fasterxml.jackson.annotation.JsonInclude;
import gemp.github.model.person.Person;
import gemp.github.model.problem.Problem;
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
public class ProblemDTO extends RepresentationModel<ProblemDTO> {

	@NotNull(message="Id cannot be null")
	private Long id;

	@NotNull(message="Id cannot be null")
	private String title;

	@NotNull(message="Id cannot be null")
	private String description;

	@NotNull(message="Id cannot be null")
	private Double time_limit;

	@NotNull(message="Id cannot be null")
	private String sampleInputProblem;

	@NotNull(message="Id cannot be null")
	private String sampleOutputProblem;

	@NotNull(message="Id cannot be null")
	private Long contestId;

	public Problem convertDTOToEntity() {
		return new ModelMapper().map(this, Problem.class);
	}
}
