package persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Container {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String designation;

    @ManyToOne(targetEntity = ContainerType.class)
    @JoinColumn(name = "CONTAINER_TYPE_ID")
    private ContainerType containerType;

    @ManyToOne(targetEntity = Location.class)
    private Location location;

    private Integer capacity;

    @OneToMany(mappedBy = "container")
    private List<Entry> entries;

    @Column(name = "deleted_at")
    private Date deletedAt;

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
}
