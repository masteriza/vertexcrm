package ua.com.vertex.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.vertex.beans.User;
import ua.com.vertex.beans.UserFormRegistration;
import ua.com.vertex.logic.interfaces.RegistrationUserLogic;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private RegistrationUserLogic registrationUserLogic;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewRegistrationForm(ModelAndView modelAndView) {
        LOGGER.info("First request to registration.jsp, create model 'UserFormRegistration' and sent ModelAndView to registration.jsp");
        return new ModelAndView("registration", "userFormRegistration", new UserFormRegistration());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processRegistration(@Valid @ModelAttribute("userFormRegistration") UserFormRegistration userFormRegistration, BindingResult bindingResult, ModelAndView modelAndView) {
        LOGGER.info("i");
        LOGGER.debug("d");
        if (registrationUserLogic.checkEmailAlreadyExists(userFormRegistration) != 0) {
            LOGGER.info("First request to registration.jsp, create model 'UserFormRegistration' and sent ModelAndView to registration.jsp");
            bindingResult.rejectValue("email", "error.email", "User with that email is already registered!");
        }

        if (!registrationUserLogic.isMatchPassword(userFormRegistration)) {
            bindingResult.rejectValue("verifyPassword", "error.verifyPassword", "Passwords do not match!");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
            modelAndView.addObject("userFormRegistration", userFormRegistration);
            return modelAndView;
        }

        userFormRegistration = registrationUserLogic.encryptPassword(userFormRegistration);

        User user = registrationUserLogic.userFormRegistrationToUser(userFormRegistration);

        registrationUserLogic.registrationUser(user);

        return new ModelAndView("registrationSuccess", "userFormRegistration", userFormRegistration);
    }
}

