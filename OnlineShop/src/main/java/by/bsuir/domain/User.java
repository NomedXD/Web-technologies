package by.bsuir.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class User extends BaseEntity{
    private String mail;
    private String password;
    private String name;
    private String surname;
    private LocalDate date;
    private String mobile;
    private String street;
    private String accommodationNumber;
    private String flatNumber;

    //@ToString.Exclude
    //@EqualsAndHashCode.Exclude
    //private List<Order> orders;
}
