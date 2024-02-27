package az.keytd.expensetracker.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "accountId")
    private Long accountId;

    @Column(name = "incomeGroup")
    private String incomeGroup;

    @Column(name = "categoryId")
    private Long categoryId;

    @Column(name = "incomeDate")
    private String incomeDate;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getIncomeGroup() {
        return incomeGroup;
    }

    public void setIncomeGroup(String incomeGroup) {
        this.incomeGroup = incomeGroup;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
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