package persistence.model;

import org.hibernate.exception.spi.TemplatedViolatedConstraintNameExtracter;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;


/**
 * A representation of a measure
 */
@Entity
public class Measure {

    private static final String PARENT_ID = "PARENT_ID";
    private static final String MEASURE_TYPE = "MEASURE_TYPE";

    /**
     * Describes the type of a measure.
     * 0: NO_SPECIAL describes a measure that needs no special action.
     * 1: POUR will be used if the vintner pour a wine from on container to another container.
     *    If a measure with this type will be added, this will result in n entries (one for each container).
     */
    public enum MeasureType { NO_SPECIAL, POUR }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "measure")
    private List<Entry> entries;

    @ManyToOne
    @JoinColumn(name = PARENT_ID)
    private Measure parent;

    @Column(name = MEASURE_TYPE)
    private MeasureType measureType;

    @OneToMany(mappedBy = "parent")
    private List<Measure> children = new ArrayList<>(0);

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measure measure = (Measure) o;
        return Objects.equals(id, measure.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public Measure getParent() {
        return parent;
    }

    public void setParent(Measure parent) {
        this.parent = parent;
    }

    public List<Measure> getChildren() {
        return children;
    }

    public MeasureType getMeasureType() {
        return measureType;
    }

    public void setMeasureType(MeasureType measureType) {
        this.measureType = measureType;
    }

    public Measure(Long id, String name, Measure parent, MeasureType measureType) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.measureType = measureType;
    }

    public Measure() {

    }
}
