package persistence.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * A list of all possible Wines
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"name", "year"})
})
public class Wine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "wine")
    private List<Entry> entries;

    @Column(nullable = false)
    private String year;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
