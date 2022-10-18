package engine.service;

import engine.CompletedQuestions;
import engine.Question;
import engine.Result;
import engine.repository.CompletedQuestionsRepository;
import engine.repository.QuestionsRepository;
import engine.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    CompletedQuestionsRepository completedQuestionsRepository;

    public Page<Question> getAllQuestions(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return questionsRepository.findAll(paging);
    }

    public Page<CompletedQuestions> getAllCompletedQuestions(Integer pageNo, Integer pageSize, String sortBy, User user) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "completedAt");

        return completedQuestionsRepository.findAllByUserIdWithPaginationOrderByCompletedAtDesc(user.getId(), paging);
    }


    public Result compareAnswers(List<Integer> userAnswer, List<Integer> rightAnswer) {

        if (userAnswer == null) userAnswer = new ArrayList<>();
        if (rightAnswer == null) rightAnswer = new ArrayList<>();

        Set<Integer> userSet = new HashSet<>(userAnswer);
        Set<Integer> rightSet = new HashSet<>(rightAnswer);

        if (userSet.equals(rightSet)) return new Result(true, Result.TRUTH);
        else return new Result(false, Result.LIE);

    }

    public void saveTimeOfCompletedQuestion(Result result, long id, long userId) {
        if (result.getSuccess().equals(true)) {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            CompletedQuestions completedQuestion = new CompletedQuestions();
            completedQuestion.setCompletedAt(timestamp);
            completedQuestion.setId(id);
            completedQuestion.setUserId(userId);
            completedQuestionsRepository.save(completedQuestion);
        }
    }
}