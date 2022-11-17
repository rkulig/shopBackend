package com.rkulig.shop.admin.product.service;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

class UploadedFilesNameUtils {
    public static String slugifyFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        Slugify slg = Slugify.builder().customReplacement("_","-").build();
        String changedName = slg.slugify(name);
        return changedName + "." + FilenameUtils.getExtension(fileName);
    }
}
