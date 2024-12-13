package com.example.buoi1.controller;

import com.example.buoi1.model.UserDemo;
import com.example.buoi1.model.Company;
import com.example.buoi1.service.CompanyService;
import com.example.buoi1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NguoidungController {

    private final UserService userService;
    private final CompanyService companyService;

    public NguoidungController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }
    @GetMapping("/")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/user")
    public String trangChiTiet(Model model) {
        model.addAttribute("userName", "Nguyen Van b");
        return "user";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDemo());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") UserDemo user, Model model) {
        userService.saveOrUpdate(user);
        return "redirect:/listUser";
    }

    @GetMapping("/listUser")
    public String listUser(Model model) {
        List<UserDemo> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "listUser";
    }
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        UserDemo user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "editUser";
    }

    @PostMapping("/editUser")
    public String updateUser(@ModelAttribute("user") UserDemo user) {
        userService.saveOrUpdate(user);
        return "redirect:/listUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/listUser";
    }
}
