package tz.go.bot.model;

import javax.persistence.*;

@Entity
@Table(name="authorities")
public class Authority {

    @Id
    @SequenceGenerator(
            name = "authority_id_seq",
            sequenceName = "authority_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authority_id_seq"
    )
    @Column(
            name="id",
            nullable = false
    )
    private Integer id;

    @Column(
            name = "name"
    )
    private String name;

    @ManyToOne
    @JoinColumn(name = "bank_customer_id")
    private  BankCustomer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(BankCustomer customer) {
        this.customer = customer;
    }
}
