package az.keytd.expensetracker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Expenses")
// todo rename to singular tables names should plural, Expense
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id ;

    @Column(name = "userId")
    private String userId ;
    
    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private long amount ;

    @Column (name = "accountId")
    private long accountId;

    @Column(name = "categoryId")
    private long categoryId;

    @Column(name = "expenseDate")
    private String expenseDate;

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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
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