package engine.repository;

import engine.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends CrudRepository<Question,Long> {

}
