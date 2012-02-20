package webGraph;

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
	private String homeUrl;
    private HashSet<String> visited;
	public LinkVisitor(String homeUrl, HashSet<String> visited) {
		this.visited = visited;
		visited.add(homeUrl);
		this.homeUrl = homeUrl;
	}

	public void visitTag(Tag tag) {
		if (tag instanceof LinkTag) {

			System.out.println("\"" + homeUrl + "\"" + " -> " + "\"" +((LinkTag) tag).getLink() + "\"");
			if (!visited.contains(((LinkTag) tag).getLink())) {
				try {
					Parser parser = new Parser(((LinkTag) tag).getLink());
					LinkVisitor visitor = new LinkVisitor(((LinkTag) tag).getLink(), visited);
					parser.visitAllNodesWith(visitor);
				} catch (ParserException e) {

				}
			}
		}
	}
}
