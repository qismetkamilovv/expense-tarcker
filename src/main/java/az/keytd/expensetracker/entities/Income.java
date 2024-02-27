package az.keytd.expensetracker.entities;

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
    private long id;

    @Column(name = "userId")
    private long userId ;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private double amount ;

    @Column(name = "accountId")
    private long accountId ;

    @Column(name = "incomeGroup")
    private String incomeGroup;

    @Column(name = "categoryId")
    private long categoryId ;

    @Column(name = "incomeDate")
    private String incomeDate;

    @Column(name = "createAt")
    private String createAt;

    @Column(name = "uptadedAt")
    private String uptadedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getIncomeGroup() {
        return incomeGroup;
    }

    public void setIncomeGroup(String incomeGroup) {
        this.incomeGroup = incomeGroup;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUptadedAt() {
        return uptadedAt;
    }

    public void setUptadedAt(String uptadedAt) {
        this.uptadedAt = uptadedAt;
    }

}