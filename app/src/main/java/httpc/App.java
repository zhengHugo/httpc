package httpc;

import httpc.entity.Request;
import httpc.entity.Response;
import httpc.model.HttpMethod;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import picocli.CommandLine;

import java.io.IOException;
import java.net.URL;

@CommandLine.Command(name = "httpc")
public class App {


  @CommandLine.Command(name = "help")
  public Integer help() {
    System.out.println("httpc is a curl-like application but supports HTTP protocol only.");
    System.out.println("Usage:");
    System.out.println("       httpc command [arguments]");
    System.out.println("The commands are:");
    System.out.println("""
        get   executes a HTTP GET request and prints the response.
        post   executes a HTTP POST request and prints the response.
        help   prints this screen""".indent(7));
    System.out.println("Use \"httpc help [command]\" for more information about a command.");
    System.out.println();
    return 0;
  }


  @CommandLine.Command(name = "help get")
  public Integer helpGet() {
    System.out.println("Usage:");
    System.out.println("       httpc get [-v] [-h key:value] URL");
    System.out.println("Get executes a HTTP GET request for a given URL.");
    System.out.println("""
        get   executes a HTTP GET request and prints the response.
        -v    Prints the detail of the response such as protocol, status, and headers.
        -h key:value    Associates headers to HTTP Request with the format 'key:value'""".indent(7));
    System.out.println();

    return 0;
  }


  @CommandLine.Command(name = "help post")
  public Integer helpPost() {
    System.out.println("Usage:  httpc post [-v] [-h key:value] [-d inline-data] [-f file] URL");
    System.out.println("Post executes a HTTP POST request for a given URL with inline data or from file.");
    System.out.println("""
          -v     Prints the detail of the response such as protocol, status, and headers.
        -h key:value    Associates headers to HTTP Request with the format 'key:value'
        -d string       Associates an inline data to the body HTTP POST request.
        -f file         Associates the content of a file to the body HTTP POST request.""".indent(
        7));
    System.out.println();

    return 0;
  }


  @CommandLine.Command(name = "get")
  public Integer getResponse(
          @CommandLine.Option(names = "-v") boolean verbose,
          @CommandLine.Parameters (index = "0") String urlStr
  ) throws IOException {

    URL url = new URL(urlStr.substring(1, urlStr.length() - 1));
    Client client =  new Client();
    Request request = new Request(HttpMethod.Get, url);
    Response response = client.sendAndGetRes(request);
    if (verbose) {
      System.out.println(response.getWholeText());
    } else {
      System.out.println(response.getBody());
    }
    return 0;

  }


  @CommandLine.Command(name = "post")
  public Integer postResponse(
          @CommandLine.Option(names = "-v") boolean verbose,
          @CommandLine.Option(names = "-d") String inLineBody,
          @CommandLine.Option(names = "-f") String filePath,
          @CommandLine.Parameters (index = "0") URL url
  ) throws IOException {

      Client client = new Client();
      String body;
      if (inLineBody.equals("")) {
        body = readStringFromFile(filePath);
      } else {
        body = inLineBody;
      }
      Request request = new Request(HttpMethod.Post, url, body);
      Response response = client.sendAndGetRes(request);
      if (verbose) {
        System.out.println(response.getWholeText());
      } else {
        System.out.println(response.getBody());
      }
    return 0;
  }

  private String readStringFromFile(String path) throws FileNotFoundException {
    File body = new File(path);
    Scanner input = new Scanner(body);

    StringBuilder stringBuilder = new StringBuilder();
    while (input.hasNextLine()) {
      stringBuilder.append(input.nextLine());
    }
    return stringBuilder.toString();
  }


  public static void main(String[] args) {
    int rc = new CommandLine(new App()).execute(args);
    System.exit(rc);
  }
}
