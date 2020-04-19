package persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Container {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String designation;

    @ManyToOne(targetEntity = ContainerType.class)
    @JoinColumn(name = "CONTAINER_TYPE_ID")
    private ContainerType containerType;

    @ManyToOne(targetEntity = Location.class)
    private Location location;

    private Integer capacity;

    @Column(name = "IS_ALWAYS_FULL_CONTAINER", nullable = false)
    private Boolean isAlwaysFullContainer;

    @OneToMany(mappedBy = "container")
    private List<Entry> entries;

    @Column(name = "deleted_at")
    private Date deletedAt;


    /* Boilerplate Code */

    public Container() {
        capacity = 10000;
        isAlwaysFullContainer = false;
    }

    public Container(String designation) {
        this();
        this.designation = designation;
    }

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

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getAlwaysFullContainer() {
        return isAlwaysFullContainer;
    }

    public void setAlwaysFullContainer(Boolean alwaysFullContainer) {
        isAlwaysFullContainer = alwaysFullContainer;
    }
}
