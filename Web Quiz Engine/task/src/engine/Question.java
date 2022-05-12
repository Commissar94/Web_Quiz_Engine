package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@ToString
public class Question {
    private static int counter = 0;

    @Getter
    @Setter
    private int id;

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
    List<String> options;

    @Getter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Answer answer;

    public void setAnswer(List<Integer> answer) {
        if (answer == null || answer.isEmpty()) this.answer = new Answer();
        this.answer = new Answer(answer);
    }

    public Question() {
        if (answer == null) this.answer = new Answer(new ArrayList<>());
    }
}
