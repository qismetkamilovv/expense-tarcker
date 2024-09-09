package az.keytd.expensetracker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id ;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;
    
    @Column(name = "type")
    private CategoryType type ;

    public Category (Integer id){
        this.id = id ;
    }

    public Category (){
        
    }

    public Integer getId() {
        return id;
    }

    public Category setId(Integer id) {
        this.id = id;
        return this ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

}
