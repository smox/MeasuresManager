package persistence.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CONTAINER_AMOUNT_MAP")
public class ContainerAmountMap {

    @Id
    private String container;

    private int amount;


    public ContainerAmountMap(){

    }

    public ContainerAmountMap(String container, int amount){
        this.container = container;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }
}
