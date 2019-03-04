/**
 * COPYRIGHTS AND OTHER STUFFS GOES HERE
 */
package com.spring.is.here.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * The controller is created to handle every kind of errors.
 * 
 * @author csabe812
 *
 */
@Controller
public class ErrorPageController implements ErrorController {

	private static final String ERR_PATH = "/error";

	private ErrorAttributes errorAttributes;

	/**
	 * Loosely-coupled error attribute
	 * 
	 * @param errorAttributes
	 */
	@Autowired
	public ErrorPageController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	/**
	 * An error with details such as timestamp (date), error, message (not found),
	 * path (user/userid), status (200, 400, 500)
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(ERR_PATH)
	public String error(Model model, HttpServletRequest request) {
		WebRequest webRequest = new ServletWebRequest(request);
		Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(webRequest, true);
		model.addAttribute("timestamp", errorMap.get("timestamp"));
		model.addAttribute("error", errorMap.get("error"));
		model.addAttribute("message", errorMap.get("message"));
		model.addAttribute("path", errorMap.get("path"));
		model.addAttribute("status", errorMap.get("status"));
		return "detailedError";
	}

	/**
	 * Returns with the error path (/error)
	 */
	@Override
	public String getErrorPath() {
		return ERR_PATH;
	}

}
