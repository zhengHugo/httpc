package httpc;

import httpc.entity.Request;
import httpc.entity.Response;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class Client {

  String url = "http://httpbin.org/get?course=networking&assignment=1";
  int port = 80;
  URL urlObject = new URL(url);
  String hostName =  urlObject.getHost();
  Socket socket = new Socket( hostName, port);
  PrintStream out = new PrintStream( socket.getOutputStream() );
  BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
  boolean hasV;

  public void setHasV(boolean hasV) {
    this.hasV = hasV;
  }
/*
    1. Connect to server via socket
    2. Write data to socket
    3. Read response from socket
    4. Compose the response object and return
     */

  public Client() throws IOException {
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


    if(request.httpMethod.toString().equals("Post")) {
      out.println("POST /" + urlObject.getFile() + " HTTP/1.0");
      out.println(request.getGetHeaderString());
      out.println();
      out.flush();
      this.readAllResponse();
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



  public void getPostBodyFromFile() {
    File file = new File(urlObject.getFile());
  }

}




