package com.spring.learning.simpleapplication.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.learning.simpleapplication.dto.UserDto;
import com.spring.learning.simpleapplication.exceptions.UserAlreadyExistException;
import com.spring.learning.simpleapplication.services.UserService;

@Controller
@RequestMapping("/user")
public class RegistrationController {

	@Autowired
    private UserService userService;

    @GetMapping("/user/register")
    public String register(final Model model){
        model.addAttribute("userData", new UserDto());
        return "account/register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid  UserDto userDto, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userDto);
            return "account/register";
        }
        try {
            userService.register(userDto);
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("registrationForm", userDto);
            return "account/register";
        }
        return "redirect:/login";
    }
}
