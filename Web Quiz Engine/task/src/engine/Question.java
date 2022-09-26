package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import engine.security.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@ToString
@Entity
public class Question {

    @Getter
    @Setter
    @SequenceGenerator(name="question_generator", allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="question_generator")
    private long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    @NotBlank
    @Getter
    @Setter
    String title;

    @NotBlank
    @Getter
    @Setter
    String text;


    @Getter
    @Setter
    @NotNull
    @Size(min = 2)
    @ElementCollection
    List<String> options;

    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    List<Integer> answer;
}
