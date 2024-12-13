package com.example.buoi1.restcontroller;

import com.example.buoi1.model.Company;
import com.example.buoi1.model.UserDemo;
import com.example.buoi1.service.CompanyService;
import com.example.buoi1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class RestUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService; // Tiêm vào service của công ty

    // Đọc tất cả người dùng
    @GetMapping
    public List<UserDemo> getAllUsers() {
        return userService.getAllUsers();
    }

    // Đọc người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDemo> getUserById(@PathVariable int id) {
        Optional<UserDemo> user = Optional.ofNullable(userService.getUserById(id));
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Tạo mới người dùng
    @PostMapping
    public ResponseEntity<UserDemo> createUser(@RequestBody UserDemo userDemo) {
        // Kiểm tra xem email đã tồn tại hay chưa
        if (userService.getAllUsers().stream().anyMatch(user -> user.getEmail().equals(userDemo.getEmail()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Nếu email đã tồn tại
        }

        // Kiểm tra công ty có tồn tại không
        Company company = companyService.getCompanyById(userDemo.getCompany().getId());
        if (company == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Nếu công ty không tồn tại
        }

        userDemo.setCompany(company); // Gán công ty vào người dùng
        userService.saveOrUpdate(userDemo);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDemo); // Trả về người dùng đã tạo
    }

    // Cập nhật người dùng
    @PutMapping("/{id}")
    public ResponseEntity<UserDemo> updateUser(@PathVariable int id, @RequestBody UserDemo updatedUser) {
        Optional<UserDemo> userOpt = Optional.ofNullable(userService.getUserById(id));
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Nếu người dùng không tồn tại
        }

        // Kiểm tra công ty có tồn tại không
        Company company = companyService.getCompanyById(updatedUser.getCompany().getId());
        if (company == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Nếu công ty không tồn tại
        }
        updatedUser.setCompany(company); // Gán công ty vào người dùng

        updatedUser.setId(id);  // Đảm bảo ID của người dùng không thay đổi
        userService.saveOrUpdate(updatedUser);
        return ResponseEntity.ok(updatedUser); // Trả về người dùng đã cập nhật
    }

    // Xóa người dùng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Optional<UserDemo> userOpt = Optional.ofNullable(userService.getUserById(id));
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Nếu người dùng không tồn tại
        }

        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Trả về 204 No Content sau khi xóa
    }
}
