package github.lemuelsousa.com.simpletodolist.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatusCode;

public class ApiErrorMessage {
    
    private HttpStatusCode statusCode;
    private List<String> errors;
    
    public ApiErrorMessage(HttpStatusCode statusCode, List<String> errors) {
        this.statusCode = statusCode;
        this.errors = errors;
    }
    
    public ApiErrorMessage(HttpStatusCode statusCode, String error) {
        this.statusCode = statusCode;
        errors = Arrays.asList(error);
    }
    
    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }
    public List<String> getErrors() {
        return errors;
    }
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}