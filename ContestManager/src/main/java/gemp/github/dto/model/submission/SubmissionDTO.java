package gemp.github.dto.model.submission;

import com.fasterxml.jackson.annotation.JsonInclude;
import gemp.github.model.person.Person;
import gemp.github.model.submission.Submission;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmissionDTO extends RepresentationModel<SubmissionDTO> {

	@NotNull(message="Id cannot be null")
	private Long id;

	@NotNull(message="Id cannot be null")
	private Long status;

	@NotNull(message="Id cannot be null")
	private Timestamp timestamp;

	@NotNull(message="Id cannot be null")
	private String sourceCode;

	@NotNull(message="Id cannot be null")
	private Long problemId;

	@NotNull(message="Id cannot be null")
	private Long teamId;

	public Submission convertDTOToEntity() {
		return new ModelMapper().map(this, Submission.class);
	}
}
