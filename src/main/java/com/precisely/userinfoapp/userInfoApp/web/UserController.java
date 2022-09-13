package com.precisely.userinfoapp.userInfoApp.web;

import com.precisely.userinfoapp.userInfoApp.domain.User;
import com.precisely.userinfoapp.userInfoApp.repositories.DataTablesUserRepository;
import com.precisely.userinfoapp.userInfoApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final DataTablesUserRepository dataTablesUserRepository;

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());

        System.out.println("This is the /index get mapping");

        return "index";

    }

    @GetMapping("/jpaDatatable")
    public String tableView() {

        return "jpaDatatable";
    }

    @GetMapping("/")
    public String homeView() {

        return "home";
    }

    @RequestMapping(value = "/jpa", method = RequestMethod.GET)
    public DataTablesOutput<User> list(@Valid DataTablesInput input) {
        return dataTablesUserRepository.findAll(input);
    }

    //shows the signup sheet to add users
    @GetMapping("/signup")
    public String showSignUpForm(User user) {

        System.out.println("This is the /signup get mapping");

        return "add-user";
    }

    //adds the user to the database
    @PostMapping("/addUser")
    public String addUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(user);
        System.out.println("Saving user");
        return "redirect:/index";
    }


    //User editing page
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }

    //Updates users
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    //deleting users
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/index";
    }

}
