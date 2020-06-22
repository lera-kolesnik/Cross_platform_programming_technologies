package lab_7;
import java.util.LinkedList;

public class URLPool {
    LinkedList<URLDepthPair> findUrls;
    LinkedList<URLDepthPair> resultUrls;
    int maxDepthPair;
    int cWait;

public URLPool(int maxDepth)
{
    this.maxDepthPair = maxDepth;
    findUrls = new LinkedList<URLDepthPair>();
    resultUrls = new LinkedList<URLDepthPair>();
    cWait = 0;
}

private static boolean check(LinkedList<URLDepthPair> resultLink,URLDepthPair pair)
{
    boolean isAlready = true;
    for (URLDepthPair c : resultLink)
        if (c.toString().equals(pair.toString()))
            isAlready=false;
    return isAlready;
}
public synchronized URLDepthPair getPair()
{
    while (findUrls.size() == 0) {
        cWait++;
        try
        {
            wait();
        }
        catch (InterruptedException e)
        {
            System.out.println("Ignoring InterruptedException");
        }
        cWait--;
    }
    URLDepthPair nextPair = findUrls.getFirst();
    findUrls.removeFirst();
    return nextPair;
}

public synchronized void addPair(URLDepthPair pair)
{
    if(check(resultUrls,pair))
    {
        resultUrls.add(pair);
        if (pair.getDepth() < maxDepthPair)
        {
            findUrls.add(pair);
            notify();
        }
    }
}

public synchronized int getWait()
{
    return cWait;
}

public LinkedList<URLDepthPair> getResult()
{
    return resultUrls;
}
}
