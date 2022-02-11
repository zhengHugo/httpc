package httpc;

import httpc.entity.Request;
import httpc.entity.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class Client {

  String url = "http://httpbin.org/get?course=networking&assignment=1";
  String hostName =  "httpbin.org";
  int port = 80;
  URL urlObject = new URL(url);
  Socket socket = new Socket( hostName, port);
  PrintStream out = new PrintStream( socket.getOutputStream() );
  BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

  public Client() throws IOException {
  }


  public Response send(Request request) throws IOException {
    /*
    1. Connect to server via socket
    2. Write data to socket
    3. Read response from socket
    4. Compose the response object and return
     */


    if(request.httpMethod.toString().equals("Get")) {
      out.println("GET /" + urlObject.getFile() + " HTTP/1.0");
      out.println(request.getGetHeaderString());
      out.println();
      out.flush();
      this.readRequest();
    }


    if(request.httpMethod.toString().equals("Post")) {
      out.println("POST /" + urlObject.getFile() + " HTTP/1.0");
      out.println(request.getGetHeaderString());
      out.println();
      out.flush();
      this.readRequest();
    }


    return null;
  }

  public void readRequest() throws IOException {
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


}
