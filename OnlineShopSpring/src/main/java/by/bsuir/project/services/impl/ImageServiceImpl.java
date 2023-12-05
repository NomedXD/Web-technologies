package by.bsuir.project.services.impl;

import by.bsuir.project.domain.Image;
import by.bsuir.project.repositories.ImageRepository;
import by.bsuir.project.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image create(Image entity) {
        return imageRepository.create(entity);
    }

    @Override
    public List<Image> read() {
        return imageRepository.read();
    }

    @Override
    public Image update(Image entity) {
        return imageRepository.update(entity);
    }

    @Override
    public void delete(Integer id) {
        imageRepository.delete(id);
    }

    @Override
    public Optional<Image> getImageById(Integer id) {
        return imageRepository.findById(id);
    }
}
