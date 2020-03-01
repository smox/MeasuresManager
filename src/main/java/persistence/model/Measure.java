package persistence.model;

import org.hibernate.exception.spi.TemplatedViolatedConstraintNameExtracter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * A representation of a measure
 */
@Entity
public class Measure {

    private static final String PARENT_ID = "PARENT_ID";
    private static final String MEASURE_TYPE = "MEASURE_TYPE";
    private static final String TEXT = "TEXT";

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

    @Column(name = TEXT)
    private String text;

    @Column(name = MEASURE_TYPE)
    private MeasureType measureType;

    @OneToMany(mappedBy = "parent")
    private Set<Measure> children = new HashSet<>(0);

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

    public Set<Measure> getChildren() {
        return children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MeasureType getMeasureType() {
        return measureType;
    }

    public void setMeasureType(MeasureType measureType) {
        this.measureType = measureType;
    }
}
