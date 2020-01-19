package persistence.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Represents a current setting.
 * Settings can be modified. Each modification leads to a new entry.
 * The old entry will be remarked as deleted by the deletedAt flag.
 */
@Entity
public class Setting {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String companyNumber;

    private Date deletedAt;

    protected Setting(){}


    /* Boilerplate Code */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
