package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    List<Integer> answer;


    public Question() {

    }
}
