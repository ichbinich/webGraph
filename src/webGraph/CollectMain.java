package webGraph;

import javax.swing.JOptionPane;

/**
 * Main Class of the webGraph prog
 * 
 * @author Marius
 * 
 */
public class CollectMain {
	public static void main(String[] args) {
		String url;
		if (args.length == 0)
			url = (String) JOptionPane.showInputDialog(null, "URL:",
					"Collector", JOptionPane.PLAIN_MESSAGE, null, null,
					"http://www-stud.asta.hs-fulda.de/~fairshop");
		else
			url = args[0];
		if (null == url)
			System.exit(1);

		Collector collector = new Collector(url);
		collector.collect();
		collector.toStdOut();

		String name = (String) JOptionPane.showInputDialog(null, "Filename:",
				"Collector", JOptionPane.PLAIN_MESSAGE, null, null, "output");
		collector.writeToFile(name);
	}
}
