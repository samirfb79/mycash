package org.bootcamp.mycash.web;

import javax.persistence.EntityNotFoundException;

import org.bootcamp.mycash.exception.UsuarioException;
import org.bootcamp.mycash.web.dto.error.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMensagem("Erro de validacao");
		apiError.addValidationErrors(ex.getFieldErrors());
		
		return ResponseEntity
				.status(apiError.getStatus())
				.body(apiError);
	}
	
	@ExceptionHandler(UsuarioException.class)
	public ResponseEntity<ApiError> handleUsuarioException(UsuarioException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMensagem(ex.getMessage());
		
		return ResponseEntity
				.status(apiError.getStatus())
				.body(apiError);
		
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiError> handleEntityNotFoundException(
			EntityNotFoundException ex) {
		
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMensagem("Recurso nao encontrado");
		
		return ResponseEntity
				.status(apiError.getStatus())
				.body(apiError);
		
	}
	
}
