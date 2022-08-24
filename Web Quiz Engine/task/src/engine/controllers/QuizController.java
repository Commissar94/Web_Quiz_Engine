package engine.controllers;

import engine.Question;
import engine.Result;
import engine.repository.QuestionsRepository;
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

    @Autowired
    QuestionsRepository repository;

    @GetMapping("/api/quizzes/{id}")
    public Question getQuestion(@PathVariable long id) {

        if (id > repository.count() || id <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No quiz");
        }
        return repository.findById(id).get();

    }

    @GetMapping("/api/quizzes")
    public List<Question> getQuestions() {
        return (List<Question>) repository.findAll();
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Result setAnswer(@PathVariable long id, @RequestBody String answer) {

        String onlyDigitsAnswer = answer.replaceAll("\\D+", "");
        List<Integer> answerFromUser = new ArrayList<>();
        for (char digit : onlyDigitsAnswer.toCharArray()) {
            answerFromUser.add(Integer.parseInt(String.valueOf(digit)));
        }
        Question currentQuestion = repository.findById(id).get();
        List<Integer> rightAnswer = currentQuestion.getAnswer();
        return quizService.compareAnswers(answerFromUser, rightAnswer);

    }

    @PostMapping("/api/quizzes")
    public Question addQuiz(@Valid @RequestBody Question question) {
        question.setId(repository.count() + 1);
        System.out.println(question);
        repository.save(question);
        return repository.findById(question.getId()).get();
    }
}
