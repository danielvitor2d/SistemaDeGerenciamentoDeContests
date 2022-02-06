package gemp.github.model.submission;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "submission")
public class Submission implements Serializable {
	private static final long serialVersionUID = -3656431259068389491L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long status;
	private Timestamp timestamp;
	private String sourceCode;
	private Long problemId;
	private Long teamId;
}
