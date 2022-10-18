package engine.controllers;

import engine.CompletedQuestions;
import engine.Question;
import engine.Result;
import engine.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import engine.security.User;
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
    QuestionsRepository questionsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }


    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable long id,
                                        @AuthenticationPrincipal User user) {


        Question question = questionsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        if (question.getUser().getId() == user.getId()) {
            questionsRepository.delete(question);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @GetMapping("/api/quizzes/{id}")
    public Question getQuestion(@PathVariable long id) {

        return questionsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/api/quizzes")
    public Page<Question> getQuestions(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        return quizService.getAllQuestions(page, pageSize, sortBy);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Result setAnswer(@PathVariable long id, @RequestBody String answer,
                            @AuthenticationPrincipal User user) {

        String onlyDigitsAnswer = answer.replaceAll("\\D+", "");
        List<Integer> answerFromUser = new ArrayList<>();
        for (char digit : onlyDigitsAnswer.toCharArray()) {
            answerFromUser.add(Integer.parseInt(String.valueOf(digit)));
        }
        Question currentQuestion = questionsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Integer> rightAnswer = currentQuestion.getAnswer();
        Result result = quizService.compareAnswers(answerFromUser, rightAnswer);
        quizService.saveTimeOfCompletedQuestion(result, id, user.getId());
        return result;

    }

    @PostMapping("/api/quizzes")
    public Question addQuiz(@Valid @RequestBody Question question,
                            @AuthenticationPrincipal User user) {
        question.setUser(user);
        return questionsRepository.save(question);
    }

    @GetMapping("/api/quizzes/completed")
    public Page<CompletedQuestions> getCompletedQuestions(
            @AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        return quizService.getAllCompletedQuestions(page, pageSize, user);
    }
}
