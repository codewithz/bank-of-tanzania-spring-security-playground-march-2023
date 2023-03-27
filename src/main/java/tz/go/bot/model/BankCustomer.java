package tz.go.bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "BankCustomer")
@Table(name ="bank_customer")
public class BankCustomer {

    @Id
    @SequenceGenerator(
            name = "bank_customer_id_seq",
            sequenceName = "bank_customer_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bank_customer_id_seq"
    )
    @Column(
            name="id",
            nullable = false
    )
    private Integer id;
    @Column(
            name = "email"
    )
    private String email;
    @Column(
            name="password"
    )
    @JsonIgnore
    private String password;
    @Column(
            name="role"
    )
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private Set<Authority> authorities;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}


