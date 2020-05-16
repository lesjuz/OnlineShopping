package edu.miu.onlineshopping.util;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static <T> List<T> iterableToCollection(Iterable<T> iterable) {
        List<T> collection = new ArrayList<>();
        iterable.forEach(collection::add);
        return collection;
    }

    public static String getExtension(String fileName) {
        String extension = ".png";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i);
        }
        return extension;
    }
    public static boolean isItImage(String extension){
        List<String> extensions = new ArrayList<>();
        extensions.add(".jpg");
        extensions.add(".png");
        extensions.add(".jpeg");
        extensions.add(".gif");
        return extensions.contains(extension.toLowerCase());
    }

    public static String getImagesFolder(ServletContext servletContext) {
        String rootDirectory = servletContext.getRealPath("/");
        File dir = new File(rootDirectory + "images" );
        if(!dir.exists()){
            dir.mkdir();
        }
        return dir.toString();
    }

}