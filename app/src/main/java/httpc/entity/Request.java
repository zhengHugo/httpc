package httpc.entity;

import httpc.model.HttpMethod;

import java.lang.reflect.Array;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {
  public HttpMethod httpMethod;
  public Header header = new Header();
  HttpMethod Get;
  URL urlObject;

  public Request(HttpMethod httpMethod, URL urlObject) throws MalformedURLException {
    this.httpMethod = httpMethod;
    this.urlObject = urlObject;
  }


  public String getGetHeaderString() {
    header.addGetEntry("Host", "httpbin.org");

    return convertToStringWithStream(header.getHeaderHashMap);
  }

  public String getPostHeaderString() {
    header.addPostEntry("Host", urlObject.getHost());
    return convertToStringWithStream(header.postHeaderHashMap);
  }

  public String convertToStringWithStream(Map<String, String> map) {
    String mapAsString = map.keySet().stream()
            .map(key -> key + ": " + map.get(key))
            .collect(Collectors.joining("\n", "", "\n"));
    return mapAsString;
  }





}
