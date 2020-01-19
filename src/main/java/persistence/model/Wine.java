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

    private String vintage;

    @OneToMany(mappedBy = "wine")
    private List<Entry> entries;

    @ManyToOne
    private TypeOfWine typeOfWine;


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

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public TypeOfWine getTypeOfWine() {
        return typeOfWine;
    }

    public void setTypeOfWine(TypeOfWine typeOfWine) {
        this.typeOfWine = typeOfWine;
    }
}
