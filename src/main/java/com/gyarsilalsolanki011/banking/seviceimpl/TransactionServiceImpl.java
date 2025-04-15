package com.gyarsilalsolanki011.banking.seviceimpl;

import com.gyarsilalsolanki011.banking.dto.TransactionDto;
import com.gyarsilalsolanki011.banking.entity.Account;
import com.gyarsilalsolanki011.banking.entity.Transaction;
import com.gyarsilalsolanki011.banking.enums.TransactionStatus;
import com.gyarsilalsolanki011.banking.enums.TransactionType;
import com.gyarsilalsolanki011.banking.mapper.TransactionMapper;
import com.gyarsilalsolanki011.banking.repository.AccountRepository;
import com.gyarsilalsolanki011.banking.repository.TransactionRepository;
import com.gyarsilalsolanki011.banking.service.TransactionService;
import com.gyarsilalsolanki011.banking.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private static final double WITHDRAWAL_APPROVAL_LIMIT = 5000;

    @Override
    @Transactional
    public TransactionDto deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(account, "selfAccount", amount, TransactionType.DEPOSIT, TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);

        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    @Transactional
    public TransactionDto withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < 0 || account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        TransactionStatus status = (account.getBalance() > WITHDRAWAL_APPROVAL_LIMIT) ? TransactionStatus.COMPLETED : TransactionStatus.PENDING;

        Transaction transaction = new Transaction(account, "selfAccount", amount, TransactionType.WITHDRAWAL, status);
        transactionRepository.save(transaction);

        if (status == TransactionStatus.COMPLETED){
            String phone = account.getUser().getPhone();
            String message = "₹" + amount + " withdrawn from A/C XX" + account.getAccountNumber().substring(account.getAccountNumber().length() - 4) +". Avl Bal: ₹"+transaction.getAccount().getBalance();
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
            smsUtil.sendSms(message, phone);
        }
        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Transactional
    @Override
    public TransactionDto transfer(Long fromAccountId, String toAccountNumber, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));


        if (fromAccount.getBalance() < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction(fromAccount, toAccountNumber, amount, TransactionType.TRANSFER, TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);

        String phone = fromAccount.getUser().getPhone();
        String message = "₹" + amount + " Transfer from A/C XX" + fromAccount.getAccountNumber().substring(fromAccount.getAccountNumber().length() - 4)+" to A/C XX" + toAccount.getAccountNumber().substring(toAccount.getAccountNumber().length() - 4) +". Avl Bal: ₹"+transaction.getAccount().getBalance();
        smsUtil.sendSms(message, phone);
        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }
}
