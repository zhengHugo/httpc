package httpc.entity;

import httpc.model.HttpMethod;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Header {

    Map<String, String> getHeaderHashMap = new HashMap<>();
    Map<String, String> postHeaderHashMap = new HashMap<>();


    public void addGetEntry(String key, String value) {
        this.getHeaderHashMap.put(key, value);
    }

    public void addPostEntry(String key, String value) {
        this.postHeaderHashMap.put(key, value);
    }




}
