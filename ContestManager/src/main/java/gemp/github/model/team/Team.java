package gemp.github.model.team;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
public class Team implements Serializable {
	private static final long serialVersionUID = -3656431259068389491L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String teamName;
	private String teamPhotoUrl;
	private Long studentId01;
	private Long studentId02;
	private Long studentId03;
	private Long coachId;
}
