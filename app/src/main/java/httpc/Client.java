package httpc;

import httpc.entity.Request;
import httpc.entity.Response;
import httpc.model.HttpMethod;

import java.io.*;
import java.net.Socket;

public class Client {

  int port = 80;

  /*
  1. Connect to server via socket
  2. Write data to socket
  3. Read response from socket
  4. Compose the response object and return
   */

  public Response sendAndGetRes(Request request) throws IOException {
    Socket socket = new Socket(request.getUrlObject().getHost(), port);
    PrintStream out = new PrintStream(socket.getOutputStream());
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    String responseText = "";
    if (request.getHttpMethod().equals(HttpMethod.Get)) {
      out.println("GET /" + request.getUrlObject().getFile() + " HTTP/1.0");
      out.println(request.getHeader());
      out.println();
      out.flush();
      responseText = this.readAllResponse(in);
    } else if (request.getHttpMethod().equals(HttpMethod.Post)) {
      out.println("POST /" + request.getUrlObject().getFile() + " HTTP/1.0");
      out.println(request.getHeader());
      out.println(request.getBody());
      out.println();
      out.flush();
      responseText = this.readAllResponse(in);
    }
    in.close();
    out.close();
    socket.close();
    return new Response(responseText);
  }

  public String readAllResponse(BufferedReader in) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    String line = in.readLine();
    while (line != null) {
      stringBuilder.append(line);
      stringBuilder.append("\n");
      line = in.readLine();
    }
    return stringBuilder.toString();

  }

}
