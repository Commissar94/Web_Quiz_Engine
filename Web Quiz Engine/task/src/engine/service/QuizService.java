package engine.service;

import engine.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class QuizService {

    public Result compareAnswers(List<Integer> userAnswer, List<Integer> rightAnswer){

        if (userAnswer == null) userAnswer = new ArrayList<>();
        if (rightAnswer == null) rightAnswer = new ArrayList<>();

        Set<Integer> userSet = new HashSet<>(userAnswer);
        Set<Integer> rightSet = new HashSet<>(rightAnswer);

        if (userSet.equals(rightSet)) return new Result(true, Result.TRUTH);
        else return new Result(false, Result.LIE);

    }
}
