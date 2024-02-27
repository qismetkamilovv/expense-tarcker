package az.keytd.expensetracker.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "userId")
    private String userId ;
    
    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private Double amount ;

    @Column (name = "accountId")
    private Long accountId;

    @Column(name = "categoryId")
    private Long categoryId;

    @Column(name = "expenseDate")
    private LocalDateTime expenseDate;

    @Column(name = "createAt")
    private LocalDateTime createAt;

    @Column(name = "uptadedAt")
    private LocalDateTime uptadedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDateTime expenseDate) {
        this.expenseDate = expenseDate;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUptadedAt() {
        return uptadedAt;
    }

    public void setUptadedAt(LocalDateTime uptadedAt) {
        this.uptadedAt = uptadedAt;
    }

}