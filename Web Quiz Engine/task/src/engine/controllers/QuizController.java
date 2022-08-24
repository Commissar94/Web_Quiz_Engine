package engine.controllers;

import engine.Question;
import engine.Result;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    @Autowired
    QuizService quizService;

    List<Question> questions = new ArrayList<>();

    @GetMapping("/api/quizzes/{id}")
    public Question getQuestion(@PathVariable int id) {

        if (id > questions.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No quiz");
        }
        return questions.get(id - 1);

    }

    @GetMapping("/api/quizzes")
    public List<Question> getQuestions() {
        return questions;
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Result setAnswer(@PathVariable int id, @RequestBody String answer) {


        String onlyDigitsAnswer = answer.replaceAll("\\D+", "");
        List<Integer> answerFromUser = new ArrayList<>();
        for (char digit : onlyDigitsAnswer.toCharArray()) {
            answerFromUser.add(Integer.parseInt(String.valueOf(digit)));
        }
        //answerFromUser.add(Integer.parseInt(onlyDigitsAnswer));
if (onlyDigitsAnswer.isEmpty())
        System.out.println(onlyDigitsAnswer);
        Question currentQuestion = questions.get(id - 1);
        System.out.println(currentQuestion);

        List<Integer> rightAnswer = currentQuestion.getAnswer();
        System.out.println(rightAnswer);
        System.out.println(answerFromUser);
        //return new Result(false,Result.LIE);
        return quizService.compareAnswers(answerFromUser, rightAnswer);

    }

    @PostMapping("/api/quizzes")
    public Question addQuiz(@Valid @RequestBody Question question) {
        question.setId(questions.size() + 1);
        questions.add(question);
        System.out.println(question);
        return questions.get((int) (question.getId() - 1));
    }
}
