package com.example.todobackver2.Exception;


import com.example.todobackver2.response.ErrorMessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value={UserServiceExceptions.class})
    public ResponseEntity<Object> handlerUserServiceException(UserServiceExceptions ex, WebRequest request){
        ErrorMessageResponse errorMessageResponse=new ErrorMessageResponse(ex.getStatus(),ex.getMessage());
        return  new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value={WorkspaceServiceException.class})
    public ResponseEntity<Object> handlerWorkspaceServiceException(WorkspaceServiceException ex, WebRequest request){
        ErrorMessageResponse errorMessageResponse=new ErrorMessageResponse(ex.getStatus(),ex.getMessage());
        return  new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value={ProjectServiceException.class})
    public ResponseEntity<Object> handlerProjectServiceException(ProjectServiceException ex, WebRequest request){
        ErrorMessageResponse errorMessageResponse=new ErrorMessageResponse(ex.getStatus(),ex.getMessage());
        return  new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
