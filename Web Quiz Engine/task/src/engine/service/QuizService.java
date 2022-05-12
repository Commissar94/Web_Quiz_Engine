package engine.service;

import engine.Answer;
import engine.Result;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class QuizService {

    public Result compareAnswers(Answer userAnswer, Answer rightAnswer) {


        Set<Integer> userSet = new HashSet<>(userAnswer.getAnswer());
        Set<Integer> rightSet = new HashSet<>(rightAnswer.getAnswer());

        if (userSet.equals(rightSet)) return new Result(true, Result.TRUTH);
        else return new Result(false, Result.LIE);
    }
}
