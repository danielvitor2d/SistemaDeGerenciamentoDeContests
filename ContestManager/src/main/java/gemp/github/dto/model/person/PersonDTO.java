package gemp.github.dto.model.person;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class PersonDTO implements Serializable {
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
}
