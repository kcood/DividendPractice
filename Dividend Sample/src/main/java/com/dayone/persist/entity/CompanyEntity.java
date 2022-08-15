package com.dayone.persist.entity;

import com.dayone.model.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import lombok.Setter;
=======
>>>>>>> c283280 (Initial commit)
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "COMPANY")
<<<<<<< HEAD
@Getter @Setter
@NoArgsConstructor
@ToString
=======
@Getter
@ToString
@NoArgsConstructor
>>>>>>> c283280 (Initial commit)
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ticker;

    private String name;

<<<<<<< HEAD
    public CompanyEntity(Company company){
        this.name = company.getName();
        this.ticker = company.getName();
=======
    public CompanyEntity(Company company) {
        this.ticker = company.getTicker();
        this.name = company.getName();
>>>>>>> c283280 (Initial commit)
    }
}
