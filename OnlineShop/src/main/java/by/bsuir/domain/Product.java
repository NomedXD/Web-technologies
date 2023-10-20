package by.bsuir.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class Product extends BaseEntity {
    private String name;
    private String description;
    private Integer categoryId;
    private float price;
    private String imagePath;
}
