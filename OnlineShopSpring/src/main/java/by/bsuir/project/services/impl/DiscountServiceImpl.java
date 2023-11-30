package by.bsuir.project.services.impl;

import by.bsuir.project.domain.DiscountCode;
import by.bsuir.project.repositories.DiscountCodeRepository;
import by.bsuir.project.services.DiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DiscountServiceImpl implements DiscountCodeService {
    private final DiscountCodeRepository discountCodeRepository;

    @Autowired
    public DiscountServiceImpl(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public DiscountCode create(DiscountCode entity) {
        return discountCodeRepository.save(entity);
    }

    @Override
    public List<DiscountCode> read() {
        return discountCodeRepository.findAll();
    }

    @Override
    public DiscountCode update(DiscountCode entity) {
        return discountCodeRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        discountCodeRepository.deleteById(id);
    }

    @Override
    public Optional<DiscountCode> getDiscountCodeByName(String name) {
        return discountCodeRepository.findDiscountCodeByName(name);
    }

    @Override
    public Optional<DiscountCode> getDiscountCodeById(Integer id) {
        return discountCodeRepository.findById(id);
    }
}
