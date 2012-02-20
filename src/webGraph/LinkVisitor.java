package webGraph;

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

	public LinkVisitor(String homeUrl) {
		super(true, true);
		this.homeUrl = homeUrl;
	}

	public void visitTag(Tag tag) {
		if (tag instanceof LinkTag) {

			System.out.println(homeUrl + " -> " + ((LinkTag) tag).getLink());
			if (0 != ((LinkTag) tag).getLink().compareTo(homeUrl)) {
				try {
					Parser parser = new Parser(homeUrl);
					LinkVisitor visitor = new LinkVisitor(homeUrl);
					parser.visitAllNodesWith(visitor);
				} catch (ParserException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
