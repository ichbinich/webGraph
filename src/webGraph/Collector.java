package webGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Collector collects links from html page and Prints them.
 * 
 * uses htmlparser library -> under GNU-Licence
 */
public class Collector {
	String url;
	Parser parser;
	NodeFilter filter;
	NodeList list;
	LinkVisitor visitor;

	ArrayList<String> collection;

	public Collector(String url) {
		this.url = url;
		collection = new ArrayList<String>();
		visitor = new LinkVisitor(url, new HashSet<String>(), collection);
		/**
		 * Searches the website for Links an prints them
		 */
	}

	public void collect() {

		filter = new NodeClassFilter(LinkTag.class);

		try {
			parser = new Parser(url);
			parser.visitAllNodesWith(visitor);
			// list = parser.extractAllNodesThatMatch(filter);
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	public void toStdOut() {
		for (String s : collection)
			System.out.println(s);
	}

	public void writeToFile(String name) {
		try {
			// Create file
			FileWriter fwriter = new FileWriter(name);
			BufferedWriter bwriter = new BufferedWriter(fwriter);
			bwriter.write("digraph webGraph{");
			for (String s : collection) {
				bwriter.newLine();
				bwriter.write(s + ";");
			}
			bwriter.newLine();
			bwriter.write("}");
			// Close the output stream
			fwriter.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}
