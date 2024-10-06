package com.example.loaders;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CucumberRunnerConfigLoader {
    private final Map<String,String> config;
    private final List<String> options;

    public CucumberRunnerConfigLoader(String path) {

        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        config = yaml.load(inputStream);

        options = new ArrayList<>();

        for(String glue: getGlue()){
            options.add("--glue");
            options.add(glue);
        }

        for(String plugin: getPlugins()){
            options.add("--plugin");
            options.add(plugin);
        }

        options.add(getFeatures());
    }

    private String getFeatures(){
        return config.get("features");
    }

    private String[] getGlue(){
        return config.get("glue").split(";");
    }

    private String[] getPlugins(){
        return config.get("plugins").split(";");
    }
    public String[] getOptions(){
       return options.toArray(new String[0]);
    }
}
