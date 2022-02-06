package gemp.github.dto.model.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import gemp.github.model.contest.Contest;
import gemp.github.model.person.Person;
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
public class PersonDTO extends RepresentationModel<PersonDTO> {

	@NotNull(message="Id cannot be null")
	private Long id;

	@NotNull(message="Id cannot be null")
	private String name;

	@NotNull(message="Id cannot be null")
	private Long age;

	@NotNull(message="Id cannot be null")
	private String email;

	@NotNull(message="Id cannot be null")
	private String phone;

	@NotNull(message="Id cannot be null")
	private String university;

	@NotNull(message="Id cannot be null")
	private Long personType;

	public Person convertDTOToEntity() {
		return new ModelMapper().map(this, Person.class);
	}
}
