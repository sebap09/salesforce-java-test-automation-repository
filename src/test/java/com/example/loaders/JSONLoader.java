package com.example.loaders;

import org.json.JSONObject;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONLoader {
    private JSONObject JSON;

    public JSONLoader(String filePath, Logger logger) {
        try {
            logger.info("Loading json file...");
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSON = new JSONObject(content);
            logger.info(JSON);
        }catch (IOException exception){
            logger.error(exception);
        }
    }

    public JSONObject getJSON(){
        return this.JSON;
    }
}
