package httpc;

import httpc.entity.Request;
import httpc.entity.Response;
import httpc.model.HttpMethod;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class Client {


  int port = 80;
  URL urlObject;
  String body;
  String hostName =  urlObject.getHost();
  Socket socket = new Socket( hostName, port);
  PrintStream out = new PrintStream( socket.getOutputStream() );
  BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
  boolean hasV;
  boolean hasF;
  boolean hasD;

  public void setHasV(boolean hasV) {
    this.hasV = hasV;
  }
  public void setHasF(boolean hasF) { this.hasF = hasF; }
  public void setHasD(boolean hasF) { this.hasD = hasF; }
/*
    1. Connect to server via socket
    2. Write data to socket
    3. Read response from socket
    4. Compose the response object and return
     */

  public Client(URL urlObject) throws IOException {
    this.urlObject = urlObject;
  }

  public Client(URL urlObject, String body) throws IOException {
    this.urlObject = urlObject;
    this.body = body;
  }

  public void sendAndGetRes(Request request) throws IOException {

    if(request.httpMethod.toString().equals("Get")) {
      out.println("GET /" + urlObject.getFile() + " HTTP/1.0");
      out.println(request.getGetHeaderString());
      out.println();
      out.flush();
      if (this.hasV == true){
      this.readAllResponse();}
      else {
        this.getBodyResponse();
      }
    }


    if(request.httpMethod.equals(HttpMethod.Post)) {
      out.println("POST /" + urlObject.getFile() + " HTTP/1.0");
      out.println(request.getPostHeaderString());
      if (hasD == true) {
      out.println(body);}
      if(this.hasF == true) {
      this.getPostBodyFromFile();}
      out.println();
      out.flush();
      if (this.hasV == true){
      this.readAllResponse();}
      else {
        this.getBodyResponse();
      }
    }


  }

  public void readAllResponse() throws IOException {
    String line = in.readLine();
    while( line != null )
    {
      System.out.println( line );
      line = in.readLine();
    }

    in.close();
    out.close();
    socket.close();
  }



  public void getBodyResponse() throws IOException {
    String line = in.readLine();
    boolean isHeader = false;
    while( line.isEmpty())
    {
      line = in.readLine();
    }
    while (line != null) {
       System.out.println( line );
      line = in.readLine();
    }

    in.close();
    out.close();
    socket.close();
  }

  public void getPostBodyFromInline() {

  }



  public void getPostBodyFromFile() throws FileNotFoundException {
    Scanner input = new Scanner(new File(urlObject.getFile()));

    while (input.hasNextLine())
    {
      out.println(input.nextLine());
    }
  }


}




