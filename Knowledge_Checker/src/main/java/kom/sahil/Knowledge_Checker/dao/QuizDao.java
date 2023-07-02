package kom.sahil.Knowledge_Checker.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import  kom.sahil.Knowledge_Checker.Model.Quiz;
public interface QuizDao extends JpaRepository<Quiz,Integer> {
}