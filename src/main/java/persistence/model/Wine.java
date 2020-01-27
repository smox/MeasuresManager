package persistence.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * A list of all possible Wines
 */
@Entity
public class Wine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "wine")
    private List<Entry> entries;

    @Override
    public String toString() {
        return name;
    }

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

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
