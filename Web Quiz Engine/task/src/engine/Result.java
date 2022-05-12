package engine;

import lombok.Getter;
import lombok.Setter;

public class Result {

    public static final String TRUTH = "Congratulations, you're right!";
    public static final String LIE = "Wrong answer! Please, try again.";

    @Getter @Setter
    Boolean success;

    @Getter @Setter
    String feedback;

    public Result(Boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }
}
