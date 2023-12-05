package by.bsuir.project.repositories;

import by.bsuir.project.domain.DiscountCode;

import java.util.Optional;

public interface DiscountCodeRepository extends BaseRepository<DiscountCode> {
    Optional<DiscountCode> findByName(String name);

    Optional<DiscountCode> findById(Integer id);
}
