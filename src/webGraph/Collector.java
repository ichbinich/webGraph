package webGraph;

import java.util.HashSet;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Collector collects links from html page and Prints them.
 */
public class Collector {
	String url;
	Parser parser;
	NodeFilter filter;
	NodeList list;
	LinkVisitor visitor;

	public Collector(String url) {
		this.url = url;
		visitor = new LinkVisitor(url, new HashSet<String>());
		/**
		 * Searches the website for Links an prints them
		 */
	}

	public void collect() {

		filter = new NodeClassFilter(LinkTag.class);

		try {
			parser = new Parser(url);
			parser.visitAllNodesWith (visitor);
			//list = parser.extractAllNodesThatMatch(filter);
			
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	public void toStdOut() {
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.elementAt(i).toHtml());
	}
}
