package com.example.buoi1.restcontroller;

import com.example.buoi1.model.Company;
import com.example.buoi1.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class RestCompanyController {

    @Autowired
    private CompanyService companyService;

    // Lấy tất cả các công ty
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    // Lấy công ty theo id
    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    // Thêm hoặc cập nhật một công ty
    @PostMapping
    public void createOrUpdateCompany(@RequestBody Company company) {
        companyService.saveOrUpdate(company);
    }

    // Xóa công ty theo id
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompanyById(id);
    }
}
