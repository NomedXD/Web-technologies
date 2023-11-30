package by.bsuir.project.repositories;

import by.bsuir.project.domain.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Integer> {
    Optional<DiscountCode> findDiscountCodeByName(String name);
}
