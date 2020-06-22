package lab_7;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CrawlerTask implements Runnable {
    URLPool urlPool;
    public static final String URL_PREFIX = "<a href=\"http";
    public static final int SHIFT_INDEX = URL_PREFIX.indexOf("\"")+1;

    public CrawlerTask(URLPool pool) {
        this.urlPool = pool;
    }
    public static void request(PrintWriter out,URLDepthPair pair) {
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();
    }
    public static void buildNewUrl(String str,int depth,URLPool pool) {
        try {
            String currentLink = str.substring(str.indexOf(URL_PREFIX)+SHIFT_INDEX,
            str.indexOf("\"", str.indexOf(URL_PREFIX)+SHIFT_INDEX+1));
            pool.addPair(new URLDepthPair(currentLink, depth + 1));
        }
        catch (StringIndexOutOfBoundsException e) {
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                URLDepthPair depthPair = urlPool.getPair();
                int currDepth = depthPair.getDepth();
                try {
                    Socket s = new Socket(depthPair.getHost(), 80);
                    s.setSoTimeout(1000);
                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                    BufferedReader in =  new BufferedReader(new InputStreamReader(s.getInputStream()));
                    request(out,depthPair);
                    String line;
                    while ((line = in.readLine()) != null) {
                        if (line.indexOf(URL_PREFIX) != -1) {
                            buildNewUrl(line,currDepth,urlPool);
                        }
                    }
                    s.close();
                }
                catch (IOException e) {
                }
            }
            catch (NullPointerException e) {
            }
        }
    }
}