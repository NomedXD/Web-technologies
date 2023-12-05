package by.bsuir.project.repositories;

import by.bsuir.project.domain.Image;

import java.util.Optional;

public interface ImageRepository extends BaseRepository<Image> {
    Optional<Image> findById(Integer id);
}
