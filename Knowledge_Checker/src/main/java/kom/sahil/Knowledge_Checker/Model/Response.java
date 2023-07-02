package kom.sahil.Knowledge_Checker.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor //parameter's default constructor
public class Response {
	//the id refers to the question's id 
    private Integer id;
    //the response member's value is assigned from Db
    private String response;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
    
    
}