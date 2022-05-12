package engine;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
public class Answer {

    @Getter
    @Setter
    List<Integer> answer;

    public Answer() {
        this.answer = new ArrayList<>();
    }
}
