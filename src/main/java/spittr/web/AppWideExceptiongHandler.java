package spittr.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptiongHandler {
	
	@ExceptionHandler(DuplicateSpittleException.class)
	public String duplicateSpittleHandler() {
		return "error/duplicate";
	}
}