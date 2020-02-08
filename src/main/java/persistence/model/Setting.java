package persistence.model;


import javax.persistence.*;
import java.sql.Date;

/**
 * Represents a current setting.
 * Settings can be modified. Each modification leads to a new entry.
 * The old entry will be remarked as deleted by the deletedAt flag.
 */
@Entity
public class Setting {

    public static final String ID = "ID";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String COMPANY_NUMBER = "COMPANY_NUMBER";
    public static final String CURRENT_YEAR = "CURRENT_YEAR";
    public static final String DELETED_AT = "DELETED_AT";
    public static final String HOME_DIRECTORY = "HOME_DIRECTORY";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = COMPANY_NAME, nullable = false)
    private String companyName;

    @Column(name = COMPANY_NUMBER, nullable = false)
    private String companyNumber;

    @Column(name = CURRENT_YEAR)
    private String currentYear;

    @Column(name = DELETED_AT)
    private Date deletedAt;

    @Column(name = HOME_DIRECTORY)
    private String homeDirectory;

    public Setting(){}


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

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
