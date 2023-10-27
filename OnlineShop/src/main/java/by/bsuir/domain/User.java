package by.bsuir.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class User extends BaseEntity {
    private String mail;
    private String password;
    private String name;
    private String surname;
    private LocalDate date;
    private String mobile;
    private String street;
    private String accommodationNumber;
    private String flatNumber;
    private List<Role> roles;

    /**
     * @return if user has admin role
     */
    public boolean hasAdminRole() {
        return getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
    }

    //@ToString.Exclude
    //@EqualsAndHashCode.Exclude
    //private List<Order> orders;
}
