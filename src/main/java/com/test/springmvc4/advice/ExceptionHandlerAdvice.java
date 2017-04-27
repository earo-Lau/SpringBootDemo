package com.test.springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lauearo on 26/04/2017.
 */
@ControllerAdvice   //declare a Advice for controller, will be registry as a Bean for spring container
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)  // Exception handler for global controller, using value to filter
    public ModelAndView exception(Exception ex, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");  //create result view
        modelAndView.addObject("errorMessage", ex.getMessage());    //bind model to result view

        return modelAndView;
    }

    @ModelAttribute //global setting, bind a key-value to model, all @RequestMapping action could access this attribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Extra Info");
    }

    @InitBinder //global setting, custom data binder for model
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("name");
    }
}
