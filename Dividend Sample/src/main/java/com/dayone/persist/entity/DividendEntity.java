package com.dayone.persist.entity;

import com.dayone.model.Dividend;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import lombok.Setter;
=======
>>>>>>> c283280 (Initial commit)
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "DIVIDEND")
<<<<<<< HEAD
@Getter @Setter
@NoArgsConstructor
@ToString
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"companyId", "date"}
=======
@Getter
@ToString
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = { "companyId", "date" }
>>>>>>> c283280 (Initial commit)
                )
        }
)
public class DividendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    private LocalDateTime date;

    private String dividend;

<<<<<<< HEAD
    public DividendEntity(Long companyId, Dividend dividend){
=======
    public DividendEntity(Long companyId, Dividend dividend) {
>>>>>>> c283280 (Initial commit)
        this.companyId = companyId;
        this.date = dividend.getDate();
        this.dividend = dividend.getDividend();
    }
<<<<<<< HEAD

=======
>>>>>>> c283280 (Initial commit)
}
