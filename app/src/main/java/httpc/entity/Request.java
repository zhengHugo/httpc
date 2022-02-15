package httpc.entity;

import httpc.model.HttpMethod;

import java.net.URL;

public class Request {
  private final HttpMethod httpMethod;
  private final Header header;
  private final URL urlObject;
  private final String body;

  public Request(HttpMethod httpMethod, URL urlObject) {
    this.httpMethod = httpMethod;
    this.urlObject = urlObject;
    this.header = new Header();
    this.body = "";
    this.header.addEntry("Host", urlObject.getHost());
  }

  public Request(HttpMethod httpMethod, URL urlObject, String body) {
    this.httpMethod = httpMethod;
    this.urlObject = urlObject;
    this.header = new Header();
    this.body = body;
  }

  public HttpMethod getHttpMethod() {
    return this.httpMethod;
  }

  public URL getUrlObject() {
    return this.urlObject;
  }

  public String getBody() {
    return body;
  }
  public String getHeader() {
    return header.toString();
  }






}
