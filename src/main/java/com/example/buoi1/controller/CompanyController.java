package com.example.buoi1.controller;

import com.example.buoi1.model.Company;
import com.example.buoi1.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/addCompany")
    public String addCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "addCompany";
    }

    @PostMapping("/addCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.saveOrUpdate(company);
        return "redirect:/listCompany";
    }

    @GetMapping("/listCompany")
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "listCompany";
    }

    @GetMapping("/editCompany/{id}")
    public String editCompany(@PathVariable("id") int id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "editCompany";
    }

    @PostMapping("/editCompany")
    public String updateCompany(@ModelAttribute("company") Company company) {
        companyService.saveOrUpdate(company);
        return "redirect:/listCompany";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        companyService.deleteCompanyById(id);
        return "redirect:/listCompany";
    }
}
