package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class CompletedQuestions {

    @SequenceGenerator(name = "completed_question_generator", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "completed_question_generator")
    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long uuid;

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private Timestamp completedAt;

    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long userId;
}
