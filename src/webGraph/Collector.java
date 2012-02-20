package webGraph;



import javax.swing.JOptionPane;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
* Collector collects links from html page and Prints them.
*/
public class Collector
{
 /**
  * Searches the website for Links an prints them
  */
 public static void main (String[] args)
 {
     String url;
     Parser parser;
     NodeFilter filter;
     NodeList list;

     url = (String)JOptionPane.showInputDialog (
       null,
       "URL:",
       "Collector",
       JOptionPane.PLAIN_MESSAGE,
       null,
       null,
       "http://www-stud.asta.hs-fulda.de/~fairshop");
     if (null == url)
       System.exit (1);


     filter = new NodeClassFilter (LinkTag.class);

     try
     {
         parser = new Parser (url);
         list = parser.extractAllNodesThatMatch (filter);
         for (int i = 0; i < list.size (); i++)
             System.out.println (list.elementAt (i).toHtml ());
     }
     catch (ParserException e)
     {
         e.printStackTrace ();
     }
     System.exit (0);
 }
}

