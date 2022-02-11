package httpc.entity;

import httpc.model.HttpMethod;

import java.lang.reflect.Array;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {
  public HttpMethod httpMethod;
  public Header header;
  HttpMethod Get;
  String url = "http://httpbin.org/get?course=networking&assignment=1";
  URL urlObject = new URL(url);

  public Request(HttpMethod httpMethod) throws MalformedURLException {
    this.httpMethod = httpMethod;
  }


  public String getGetHeaderString() {
    header.addGetEntry("Host", urlObject.getHost());
    return convertToStringWithStream(header.getHeaderHashMap);
  }

  public String getPostHeaderString() {
    header.addPostEntry("Host", urlObject.getHost());
    return convertToStringWithStream(header.postHeaderHashMap);
  }

  public String convertToStringWithStream(Map<String, String> map) {
    String mapAsString = map.keySet().stream()
            .map(key -> key + " : " + map.get(key))
            .collect(Collectors.joining(", ", "{", "}"));
    return mapAsString;
  }





}
