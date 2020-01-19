package persistence.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * A representation of a measure
 */
@Entity
public class Measure {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Date deletedAt;

    @ManyToMany(mappedBy = "measures")
    private List<Entry> entries;

    /* Boilerplate Code */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
