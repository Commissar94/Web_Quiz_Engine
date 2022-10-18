package engine.repository;

import engine.CompletedQuestions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedQuestionsRepository extends JpaRepository<CompletedQuestions,Long> {


    @Query("SELECT c FROM CompletedQuestions c WHERE c.userId = :userId order by c.completedAt desc")
    Page<CompletedQuestions> findAllByUserIdWithPaginationOrderByCompletedAtDesc(long userId, Pageable pageable);

}
