package dongyang.ac.kr.greennae.service;

import dongyang.ac.kr.greennae.domain.Image;
import dongyang.ac.kr.greennae.domain.Users;
import dongyang.ac.kr.greennae.repository.ImageRepository;
import dongyang.ac.kr.greennae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class ImageService {

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;

    @Transactional
    public void saveImage(Image image){

        imageRepository.save(image);
    }
}
