package persistence.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Entry {

    public static final String MEASURE_ID = "MEASURE_ID";

    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date realizedAt;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Container container;

    @Column(name = "ADDITIONAL_INFORMATION")
    private String additionalInformation;

    @ManyToOne
    @JoinColumn(name = MEASURE_ID)
    private Measure measure;

    @ManyToOne
    private Wine wine;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(id, entry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /* Boilerplate Code */
    public Long getId() {
        return id;
    }

    public Date getRealizedAt() {
        return realizedAt;
    }

    public void setRealizedAt(Date realizedAt) {
        this.realizedAt = realizedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public Entry() {
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
