package by.bsuir.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Order extends BaseEntity{
    private Float price;
    private LocalDate date;
    private Integer userId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Product> productList;
    private String creditCardNumber;
    private String shippingType;
    private Float shippingCost;
    private DiscountCode discountCode;
    private String address;
    private String customerNotes;
}
