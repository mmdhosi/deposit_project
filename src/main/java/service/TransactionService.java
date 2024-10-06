package service;

import Repository.TransactionRepositoryImpl;
import emun.TransactionType;
import entity.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import Repository.TransactionRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Transactional
public class TransactionService {

    @Inject
    private TransactionRepositoryImpl transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        validateTransaction(transaction);
        return transactionRepository.save(transaction);
    }

    public Transaction findTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public boolean deleteTransaction(Integer id) {
        return transactionRepository.deleteById(id);
    }

    public void updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public long countTransactions() {
        return transactionRepository.count();
    }

    public boolean existsTransactionById(Integer id) {
        return transactionRepository.existsById(id);
    }

    public void validateTransaction(Transaction transaction) {

        // Validate transaction type
        if (transaction.getTransactionType() == null) {
            throw new IllegalArgumentException("Transaction type cannot be null");
        }
        // Check fromDepositId and toDepositId
        if(transaction.getTransactionType() != TransactionType.DEPOSIT){
            if (transaction.getFromDepositId() == null) {
                throw new IllegalArgumentException("From deposit ID cannot be null");
            }
        }
        if (transaction.getToDepositId() == null) {
            throw new IllegalArgumentException("To deposit ID cannot be null");
        }

        // Validate amount
        if (transaction.getAmount() == null || transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }


        if (transaction.getTransactionFee() == null || transaction.getTransactionFee() < 0) {
            throw new IllegalArgumentException("Transaction fee must be zero or greater");
        }

        // Validate description (could be optional, but you may enforce it)
        if (transaction.getDescription() != null && transaction.getDescription().length() > 255) {
            throw new IllegalArgumentException("Description cannot exceed 255 characters");
        }

        // Validate version
//        if (transaction.getVersion() == null || transaction.getVersion() < 1) {
//            throw new IllegalArgumentException("Transaction version must be greater than 0");
//        }

        // Optional - You may want to check if transaction dates are set,
        // if this is required by your logic.
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(LocalDateTime.now()); // Set the date to now if not present
        }
    }
}