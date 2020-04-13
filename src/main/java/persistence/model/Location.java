package persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(targetEntity = Container.class, mappedBy = "location")
    private List<Container> containers;

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
}
