package persistence.model;

import javax.persistence.*;

@Entity
public class Container {

    @Id
    private Long id;

    @Column(unique = true)
    private String designation;

    @ManyToOne(targetEntity = ContainerType.class)
    @JoinColumn(name = "CONTAINER_TYPE_ID")
    private ContainerType containerType;

    @ManyToOne(targetEntity = Location.class)
    private Location location;

    private Integer capacity;

    /* Boilerplate Code */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
