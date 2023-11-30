package by.bsuir.project.services;

import by.bsuir.project.domain.DiscountCode;

import java.util.Optional;

public interface DiscountCodeService extends BaseService<DiscountCode>{
    Optional<DiscountCode> getDiscountCodeByName(String name);
    Optional<DiscountCode> getDiscountCodeById(Integer id);
}
