package lab_7;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class WebScanner {
public static final String URL_PREFIX = "<a href=\"http";
static LinkedList<URLDepthPair> findLink = new LinkedList<URLDepthPair>();
static LinkedList<URLDepthPair> resultLink = new LinkedList<URLDepthPair>();
public static void showResult(LinkedList<URLDepthPair> resultLink)
{
    for (URLDepthPair c : resultLink)
        System.out.println("Depth : "+c.getDepth() + "\tLink : "+c.toString());
}
public static boolean check(LinkedList<URLDepthPair> resultLink,URLDepthPair pair)
{
    boolean isAlready = true;
    for (URLDepthPair c : resultLink)
       if (c.toString().equals(pair.toString()))
           isAlready=false;
       return isAlready;
}
public static void request(PrintWriter out,URLDepthPair pair)
{
    out.println("GET " + pair.getPath() + " HTTP/1.1");
    out.println("Host: " + pair.getHost());
    out.println("Connection: close");
    out.println();
    out.flush();
}
public static void searchURL(String urlString, int maxDepth) {
    URLDepthPair urlPair = new URLDepthPair(urlString, 0);
    try {
        findLink.add(urlPair);
        while (!findLink.isEmpty()) {
            URLDepthPair currentPair = findLink.removeFirst();
            int depthPair = currentPair.getDepth();
            try {
                Socket socket = new Socket(currentPair.getHost(), 80);
                socket.setSoTimeout(1000);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                request(out, currentPair);
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.indexOf(URL_PREFIX) > 0 && depthPair < maxDepth) {
                        boolean isLinkFound = false;
                        StringBuilder currentLink = new StringBuilder();
                        char c = line.charAt(line.indexOf(URL_PREFIX) + 9);
                        currentLink.append(c);
                        for (int i = line.indexOf(URL_PREFIX) + 10; c != '"' && i < line.length() - 1; i++) {
                            c = line.charAt(i);
                            if (c == '"')
                                isLinkFound = true;
                            else
                                currentLink.append(c);
                        }
                        if (isLinkFound && depthPair < maxDepth) {
                            URLDepthPair newPair = new URLDepthPair(currentLink.toString(), depthPair + 1);
                            if (check(findLink, newPair)) {
                                findLink.add(newPair);
                            }
                        }
                    }
                }
                socket.close();
                if (check(resultLink, currentPair))
                    resultLink.add(currentPair);
            } catch (IOException e) {
            }
        }
        showResult(resultLink);
    }
    catch (NullPointerException e)
    {
        System.out.println("Not Link");
    }
}    

    public static void main(String[] args){
        String[] arg = new String[]{"http://www.mtuci.ru/","1"};
       if (args.length>2)
           System.out.println("usage: java Crawler <URL><depth>");
       else {
           boolean isDigit = true;
            for (int i = 0; i< arg[1].length()&&isDigit;i++) {
                isDigit = Character.isDigit(arg[1].charAt(i));
                if (isDigit) searchURL(arg[0], Integer.parseInt(arg[1]));
            }
        }
    }
}