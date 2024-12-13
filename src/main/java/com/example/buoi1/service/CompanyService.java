package com.example.buoi1.service;

import com.example.buoi1.model.Company;
import com.example.buoi1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void saveOrUpdate(Company company) {
        companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElse(null);  // Trả về null nếu không tìm thấy company
    }

    public void deleteCompanyById(int id) {
        companyRepository.deleteById(id);
    }
}
