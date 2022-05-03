package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Question {
    private static AtomicInteger counter = new AtomicInteger(0);

    @Getter @Setter
    int id;

    @Getter @Setter
    String title;

    @Getter @Setter
    String text;

    @Getter @Setter
    List<String> options;

    @Getter @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    int answer;


    public Question() {
        counter.incrementAndGet();
        this.id = counter.intValue();
    }
}
