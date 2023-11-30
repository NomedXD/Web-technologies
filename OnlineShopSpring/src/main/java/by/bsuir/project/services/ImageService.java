package by.bsuir.project.services;

import by.bsuir.project.domain.Image;

import java.util.Optional;

public interface ImageService extends BaseService<Image>{
    Optional<Image> getImageById(Integer id);
}
