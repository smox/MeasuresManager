package persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "CONTAINER_TYPE")
public class ContainerType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Container.class, mappedBy = "containerType")
    private List<Container> containers;

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

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
}
