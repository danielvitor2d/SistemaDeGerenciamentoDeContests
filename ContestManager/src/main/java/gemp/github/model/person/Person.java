package gemp.github.model.person;

import gemp.github.dto.model.person.PersonDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person implements Serializable {
	private static final long serialVersionUID = -3656431259068389491L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Long age;
	private String email;
	private String phone;
	private String university;
	private Long personType;

	public PersonDTO convertEntityToDTO() {
		return new ModelMapper().map(this, PersonDTO.class);
	}
}
