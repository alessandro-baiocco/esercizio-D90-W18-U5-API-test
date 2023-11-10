package application.U5D10.exceptions;

import application.U5D10.payloads.ErrorsResponseWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {




    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseWithListDTO handleBadRequest(BadRequestException e){
        if(e.getErrorsList() != null) {
            List<String> errorsList = e.getErrorsList().stream().map(objectError -> objectError.getDefaultMessage()).toList();
            return new ErrorsResponseWithListDTO(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsResponseWithListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(NotUserFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotUserFoundException e){
        return new ErrorsPayload(e.getMessage(), new Date());
    }
    @ExceptionHandler(NotDeviceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotDeviceFoundException e){
        return new ErrorsPayload(e.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGeneric(Exception e){
        e.printStackTrace();
        return new ErrorsPayload("Problema lato server...fatti i cavoli tuoi", new Date());
    }




}
