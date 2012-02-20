package webGraph;

import javax.swing.JOptionPane;

public class CollectMain {
	public static void main(String[] args) {
		String url = (String) JOptionPane.showInputDialog(null, "URL:",
				"Collector", JOptionPane.PLAIN_MESSAGE, null, null,
				"http://www-stud.asta.hs-fulda.de/~fairshop");
		if (null == url)
			System.exit(1);

		Collector collector = new Collector(url);
		collector.collect();
//		collector.toStdOut();
	}
}
