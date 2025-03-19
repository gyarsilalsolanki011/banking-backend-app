package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.dto.AdminDto;
import com.gyarsilalsolanki011.banking.entity.Account;
import com.gyarsilalsolanki011.banking.entity.Admin;
import com.gyarsilalsolanki011.banking.entity.Transaction;
import com.gyarsilalsolanki011.banking.entity.User;
import com.gyarsilalsolanki011.banking.enums.AdminRole;
import com.gyarsilalsolanki011.banking.enums.OnlineBankingStatus;
import com.gyarsilalsolanki011.banking.enums.TransactionStatus;
import com.gyarsilalsolanki011.banking.enums.TransactionType;
import com.gyarsilalsolanki011.banking.mapper.AdminMapper;
import com.gyarsilalsolanki011.banking.repository.AccountRepository;
import com.gyarsilalsolanki011.banking.repository.AdminRepository;
import com.gyarsilalsolanki011.banking.repository.TransactionRepository;
import com.gyarsilalsolanki011.banking.repository.UserRepository;
import com.gyarsilalsolanki011.banking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AdminDto createAdmin(String username, String email, String password, AdminRole role) {
        Admin admin = new Admin(
                username,
                email,
                passwordEncoder.encode(password),
                role
        );
        adminRepository.save(admin);
        return AdminMapper.mapToAdminDto(admin);
    }

    @Override
    public AdminDto getAdminById(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return AdminMapper.mapToAdminDto(admin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(AdminMapper::mapToAdminDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdmin(Long adminId) {
        if (!adminRepository.existsById(adminId)) {
            throw new RuntimeException("Admin not found");
        }
        adminRepository.deleteById(adminId);
    }

    @Override
    public void approveWithdrawal(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (transaction.getTransactionType() != TransactionType.WITHDRAWAL || transaction.getStatus() != TransactionStatus.PENDING) {
            throw new RuntimeException("Invalid transaction for approval");
        }

        Account account = transaction.getAccount();
        account.setBalance(account.getBalance() - transaction.getAmount());
        accountRepository.save(account);

        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);
    }

    // Approve Online Banking Activation
    public String approveOnlineBanking(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        User user = optionalUser.get();
        if (user.getOnlineBankingStatus() != OnlineBankingStatus.PENDING_FOR_ACTIVATION) {
            return "User has not requested online banking activation!";
        }

        user.setOnlineBankingStatus(OnlineBankingStatus.ACTIVE);
        userRepository.save(user);

        return "User's online banking access has been activated!";
    }

    // Deactivate Online Banking
    public String deactivateOnlineBanking(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        User user = optionalUser.get();
        if (user.getOnlineBankingStatus() == OnlineBankingStatus.NOT_ACTIVE) {
            return "Online Banking is already deactivated!";
        }

        user.setOnlineBankingStatus(OnlineBankingStatus.NOT_ACTIVE);
        userRepository.save(user);

        return "User's online banking access has been deactivated!";
    }

    // ✅ Admin Update Any User's Information (Including Role)
    public String updateAdmin(Long adminId, String username, String email, String password, AdminRole role) {
        Optional<Admin> optionalUser = adminRepository.findById(adminId);
        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        Admin admin = optionalUser.get();
        if (username != null && !username.isBlank()) admin.setUsername(username);
        if (email != null && !email.isBlank()) admin.setEmail(email);
        if (password != null && !password.isBlank()) admin.setPassword(passwordEncoder.encode(password));
        if (role != null) admin.setRole(role);

        adminRepository.save(admin);
        return "User details updated by Admin successfully!";
    }
}
