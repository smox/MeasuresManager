package persistence.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Entry {

    public static final String MEASURE_ID = "MEASURE_ID";

    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date realizedAt;

    private Date createdAt;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private String container;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
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
