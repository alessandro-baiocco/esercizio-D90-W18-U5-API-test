package application.U5D10.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorsList;
    public BadRequestException(String message){
        super(message);

    }

    public BadRequestException(List<ObjectError> errors){
        super("errore nel body");
        this.errorsList = errors;
    }
}