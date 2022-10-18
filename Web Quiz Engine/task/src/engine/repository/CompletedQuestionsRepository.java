package engine.repository;

import engine.CompletedQuestions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedQuestionsRepository extends JpaRepository<CompletedQuestions,Long> {


    @Query("SELECT u FROM CompletedQuestions u WHERE u.userId = ?1")
    Page<CompletedQuestions> findAllByUserIdWithPaginationOrderByCompletedAtDesc(long userId, Pageable pageable);

}
