package webGraph;

import java.util.ArrayList;
import java.util.HashSet;

import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;

/**
 * Visitor class, that prints all links of the "visited" page.
 * 
 * 
 * @author Marius
 * 
 */
public class LinkVisitor extends NodeVisitor {
	/**
	 * the actual Url of the visitor.
	 */
	private String homeUrl;
	/**
	 * A HashSet of Url's where the VIsitors has been, to prevent loops in
	 * recursion
	 */
	private HashSet<String> visited;
	/**
	 * collection of links
	 */
	private ArrayList<String> output;

	/**
	 * 
	 * @param homeUrl
	 * @param visited
	 */
	public LinkVisitor(String homeUrl, HashSet<String> visited,
			ArrayList<String> output) {
		this.visited = visited;
		this.output = output;
		visited.add(homeUrl);
		this.homeUrl = homeUrl;
	}

	/**
	 * overwritten from NodeVisitor, gets Called when calling
	 * visitAllNodesWith(visitor)
	 */
	public void visitTag(Tag tag) {
		if (tag instanceof LinkTag) {
			/*
			 * prints the Homeulr + Linkurl in Ndotz format //TODO: Print in
			 * File.
			 */
			output.add("\"" + homeUrl + "\"" + " -> " + "\""
					+ ((LinkTag) tag).getLink() + "\"");
			// System.out.println("\"" + homeUrl + "\"" + " -> " + "\""
			// + ((LinkTag) tag).getLink() + "\"");
			/*
			 * If not visited -> visitallNodes!
			 */
			if (!visited.contains(((LinkTag) tag).getLink())) {
				try {
					Parser parser = new Parser(((LinkTag) tag).getLink());
					LinkVisitor visitor = new LinkVisitor(
							((LinkTag) tag).getLink(), visited, output);
					parser.visitAllNodesWith(visitor);
				} catch (ParserException e) {

				}
			}
		}
	}
}
