package gemp.github.dto.model.contest;

import com.fasterxml.jackson.annotation.JsonInclude;
import gemp.github.model.contest.Contest;
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
public class ContestDTO extends RepresentationModel<ContestDTO> {

	@NotNull(message="Id cannot be null")
	private Long id;

	@NotNull(message="Title cannot be null")
	private String title;

	@NotNull(message="Status cannot be null")
	private Long status;

	@NotNull(message="Date cannot be null")
	private Timestamp date;

	@NotNull(message="Duration cannot be null")
	private Double duration;

	@NotNull(message="Place cannot be null")
	private String place;

	@NotNull(message="JudgeId cannot be null")
	private Long judgeId;

	public Contest convertDTOToEntity() {
		return new ModelMapper().map(this, Contest.class);
	}
}
