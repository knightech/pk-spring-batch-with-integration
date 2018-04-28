package com.example.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.function.Predicate;

public class FileValidator {
    Logger logger = LoggerFactory.getLogger(FileValidator.class);

    public boolean filter(File file){
        Predicate<File> fileValidator = this::isValid;
        logger.info("Validating file: {}", file.getName());
        return fileValidator.test(file);
    }

    /**
     * business logic to filter files
     * @param file input file to validate
     * @return true if file valid, false otherwise
     */
    private boolean isValid(File file) {
        final boolean isInvalid = file.getName().startsWith("2");
        if(isInvalid){
            logger.error("FileValidation failed on {}, skipping.", file.getAbsolutePath());
            return false;
        }
        return true;
    }
}
