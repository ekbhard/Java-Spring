package com.sb.JavaWithSpring.service;

import com.sb.JavaWithSpring.repos.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

@Service
public class ImageService {

    public static BufferedImage constuctImage(byte[] bytes) throws IOException {
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        return bImageFromConvert;
    }

    /**
     * Read file into byte array
     *
     * @param imagePath
     *     path to a file
     * @return byte array out of file
     * @throws IOException
     *     File not found or could not be read
     */
    public static byte[] getBytesFromFile(String imagePath) throws IOException {
        File file = new File(imagePath);
        return Files.readAllBytes(file.toPath());
    }

}
