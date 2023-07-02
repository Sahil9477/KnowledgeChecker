package kom.sahil.Knowledge_Checker.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kom.sahil.Knowledge_Checker.Model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    //we are wanting to pick random questions and hence using a custom query 
    //dynamic values are gotten by using : as prefix 
    //we use RANDOM() to get random tuples from table 
    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :num_of_ques", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int num_of_ques);
}

