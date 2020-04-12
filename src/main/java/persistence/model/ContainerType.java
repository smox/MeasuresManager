package persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "CONTAINER_TYPE")
public class ContainerType {

    @Id
    private Long id;

    private String name;

    @OneToMany(targetEntity = Container.class, mappedBy = "containerType")
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

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
}
