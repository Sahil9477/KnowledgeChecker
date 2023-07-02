package kom.sahil.Knowledge_Checker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kom.sahil.Knowledge_Checker.Model.Question;
import kom.sahil.Knowledge_Checker.Model.QuestionWrapper;
import kom.sahil.Knowledge_Checker.Model.Quiz;
import kom.sahil.Knowledge_Checker.Model.Response;
import kom.sahil.Knowledge_Checker.dao.QuestionDao;
import kom.sahil.Knowledge_Checker.dao.QuizDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int num_of_ques, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, num_of_ques);
        //creating the quiz based on the questions retireved on the basis of user's category and no of questions
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        
    	Optional<Quiz> quiz = quizDao.findById(id); //if id is invalid thats why we use optional
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
        	//we are filtering the member's values from the question to initialise questionwrapper object thus filtering out the answer 
        	//thus we arent having answers in ques wrapper obj 
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw); //inserting the question for the user
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        //we can also write-> 
    	//Optional<Quiz> quiz = quizdDao.findById(id); 
    	Quiz quiz = quizDao.findById(id).get();//only using findByID(id) will give optional data(if the id is invalid), but of we guranteed data we use get()
        //if we get null data iit still will be given to Quiz object
    	List<Question> questions = quiz.getQuestions();
        int right = 0; //intially
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}