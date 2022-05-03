package engine;

import lombok.Getter;
import lombok.Setter;

public class Answer {

    @Getter @Setter
    Boolean success;

    @Getter @Setter
    String feedback;

    public Answer(Boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }
}
