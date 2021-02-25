package com.rafaelamaral.exception;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rafaelamaral.service.exception.ClienteJaCadastradoException;

@ControllerAdvice
public class ApiRequestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiException erro = ApiException.builder()
				.messageUser(messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale()))
				.messageDeveloper(ex.getMessage()).httpStatus(status).timesStamp(ZonedDateTime.now()).build();

		return handleExceptionInternal(ex, erro, headers, status, request);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ApiException> errosApiExceptions = listaErros(ex.getBindingResult());

		return handleExceptionInternal(ex, errosApiExceptions, headers, HttpStatus.BAD_REQUEST, request);
	}
	


	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex , 
					WebRequest request ){
		
		
		ApiException erroApiException = ApiException.builder()
				.messageDeveloper(ex.getMessage())
				.messageUser(messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale()))
				.httpStatus(HttpStatus.BAD_REQUEST)
				.timesStamp(ZonedDateTime.now())
				.build();
		
							
		return handleExceptionInternal(ex, erroApiException, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler({ClienteJaCadastradoException.class})
	public ResponseEntity<Object> handleClienteJaCadastradoException(ClienteJaCadastradoException ex , 
					WebRequest request ){
		
		
		ApiException erroApiException = ApiException.builder()
				.messageDeveloper(ex.getMessage())
				.messageUser(messageSource.getMessage("recurso.ja-cadastrado", null, LocaleContextHolder.getLocale()))
				.httpStatus(HttpStatus.BAD_REQUEST)
				.timesStamp(ZonedDateTime.now())
				.build();
		
							
		return handleExceptionInternal(ex, erroApiException, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private List<ApiException> listaErros(BindingResult bindingResult) {
		List<ApiException> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String messagesUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String messagesDeveloper = fieldError.toString();

			erros.add(
					new ApiException.ApiExceptionBuilder().messageDeveloper(messagesDeveloper).messageUser(messagesUser)
							.httpStatus(HttpStatus.BAD_REQUEST).timesStamp(ZonedDateTime.now()).build());
		}

		return erros;
	}

}
