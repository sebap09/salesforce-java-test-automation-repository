package com.example.sharedstate;

import java.util.HashMap;
import java.util.Map;

public class SharedData {

    private final Map<String, Object> sharedData;

    public SharedData(){
        sharedData = new HashMap<>();
    }

    public void setData(SharedDataKeys key, Object value) {
        sharedData.put(key.toString(), value);
    }

    public Object getData(SharedDataKeys key){
        return sharedData.get(key.toString());
    }

    public Boolean contains(SharedDataKeys key){
        return sharedData.containsKey(key.toString());
    }

}
