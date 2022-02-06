package model.problem;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problem")
public class Problem implements Serializable {
	private static final long serialVersionUID = -3656431259068389491L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private Double time_limit;
	private String sampleInputProblem;
	private String sampleOutputProblem;
	private Long contestId;
}
