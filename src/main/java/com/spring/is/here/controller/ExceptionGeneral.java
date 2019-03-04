/**
 * COPYRIGHTS AND OTHER STUFFS GOES HERE
 */
package com.spring.is.here.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This is a class for handling the exception generally.
 * 
 * @author csabe812
 *
 */
@ControllerAdvice
public class ExceptionGeneral {

	/**
	 * If an exception is thrown, the exception will be added to the model.
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler
	public String exception(Exception exception, Model model) {
		model.addAttribute("exception", exception);
		return "exceptionhandler";
	}
}
