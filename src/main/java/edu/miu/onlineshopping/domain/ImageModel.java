package edu.miu.onlineshopping.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@AllArgsConstructor
public class ImageModel {
    private long id;

    private String imgName;

    private MultipartFile image;
    public ImageModel(long id, String imgName) {
        this.id = id;
        this.imgName = imgName;
    }
}
