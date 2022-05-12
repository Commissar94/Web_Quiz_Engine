package engine.controllers;

import engine.Answer;
import engine.Result;
import engine.Question;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

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
    public Result setAnswer(@PathVariable int id, @RequestBody Answer answer) {

        Question currentQuestion = questions.get(id - 1);
        Answer userAnswer = answer;
        Answer rightAnswer = currentQuestion.getAnswer();
        return quizService.compareAnswers(userAnswer, rightAnswer);

    }

    @PostMapping("/api/quizzes")
    public Question addQuiz(@Valid @RequestBody Question question) {
        question.setId(questions.size() + 1);
        questions.add(question);
        System.out.println(question);
        return questions.get(question.getId() - 1);
    }
}
