package lab_7;
import java.util.LinkedList;

public class WebScanner {
public static final String URL_PREFIX = "<a href=\"http";
static LinkedList<URLDepthPair> findLink = new LinkedList<URLDepthPair>();
static LinkedList<URLDepthPair> resultLink = new LinkedList<URLDepthPair>();

public static void showResult(LinkedList<URLDepthPair> resultLink) {
    for (URLDepthPair c : resultLink)
        System.out.println("Depth : "+c.getDepth() + "\tLink : "+c.toString());
}
public static boolean checkDigit(String str) {
    boolean isDigit = true;
    for (int i = 0; i < str.length() && isDigit; i++)
    isDigit = Character.isDigit(str.charAt(i));
    return isDigit;     
}
 public static void main(String[] args) {
    args = new String[]{"http://mtuci.ru/", "1", "3"};
    if (args.length == 3&&checkDigit(args[1])&&checkDigit(args[2])) {
        String lineUrl = args[0];
        int numThreads = Integer.parseInt(args[2]);
        URLPool pool = new URLPool(Integer.parseInt(args[1]));
        pool.addPair(new URLDepthPair(lineUrl, 0));
        for (int i = 0; i < numThreads; i++) {
            CrawlerTask c = new CrawlerTask(pool);
            Thread t = new Thread(c);
            t.start();
        }
        while (pool.getWait() != numThreads) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Ignoring  InterruptedException");
            }
        }
        try {
            showResult(pool.getResult());;
        }
        catch (NullPointerException e) {
            System.out.println("Not Link");
        }
        System.exit(0);
    }
    else {
        System.out.println("usage: java Crawler <URL> <maximum_depth> <num_threads> or second/third not digit");
    }
}
}
