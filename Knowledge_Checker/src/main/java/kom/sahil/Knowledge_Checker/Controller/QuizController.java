package kom.sahil.Knowledge_Checker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kom.sahil.Knowledge_Checker.Model.QuestionWrapper;
import kom.sahil.Knowledge_Checker.Model.Response;
import kom.sahil.Knowledge_Checker.Service.QuizService;

@RestController
@RequestMapping("/quiz") //this is another controller handling diff requests 
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }
    @GetMapping("/get/{id}") //id refers to the Q_id ie question id , which will change dynamically
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        //we just dont want to send the right answer to client 
    	//question wrapper checks the answer
    	//we dont get the answers bcoz it doesnt have answer at its member of class
    	return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){ //request body will dynamically send the state of the object of that Response object
    	//once we get the object we are going to verify with database
        return quizService.calculateResult(id, responses);
    }


}