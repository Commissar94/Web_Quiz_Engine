package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
public class Question {
    private static int counter = 0;

    @Getter
    @Setter
    @Id
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
    //Answer answer;
    List<Integer> answer;

//    public void setAnswer(List<Integer> answer) {
//        if (answer == null || answer.isEmpty()){
//            this.answer = new ArrayList<>();
//            System.out.println("EMPTYYYYYY");
//        }
//        else {
//            System.out.println("NOT EMPTYYY");
//            this.answer = new ArrayList<>(answer);
//        }
//    }

    public Question() {
        //if (answer == null) this.answer = new Answer(new ArrayList<>());
    }
}
