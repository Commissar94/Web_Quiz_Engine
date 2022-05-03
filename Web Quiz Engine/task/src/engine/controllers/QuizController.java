package engine.controllers;

import engine.Answer;
import engine.Question;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    List<Question> questions = new ArrayList<>();

    @GetMapping("/api/quizzes/{id}")
    public Question getQuestion(@PathVariable int id) {

        if (id > questions.size()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No quiz");
        }
            return questions.get(id-1);

    }

    @GetMapping("/api/quizzes")
    public List<Question> getQuestions() {
        return questions;
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Answer setAnswer(@PathVariable int id, @RequestParam int answer) {

        if (answer == questions.get(id-1).getAnswer()) {
            return new Answer(true, "Congratulations, you're right!");
        } else {
            return new Answer(false, "Wrong answer! Please, try again.");
        }

    }

    @PostMapping("/api/quizzes")
    public Question addQuiz(@RequestBody Question question) {
        questions.add(question);
        return questions.get(question.getId()-1);
    }
}
