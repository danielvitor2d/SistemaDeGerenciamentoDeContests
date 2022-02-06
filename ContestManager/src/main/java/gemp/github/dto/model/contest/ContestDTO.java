package gemp.github.dto.model.contest;

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
@Table(name = "contest")
public class ContestDTO implements Serializable {
	private static final long serialVersionUID = -3656431259068389491L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private Long status;
	private Timestamp date;
	private Double duration;
	private String place;
	private Long judgeId;
}
