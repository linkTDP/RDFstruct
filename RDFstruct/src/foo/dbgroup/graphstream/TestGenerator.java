package foo.dbgroup.graphstream;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.thread.ThreadProxyPipe;
import org.jfree.chart.plot.ThermometerPlot;

public class TestGenerator {

	public static void main(String[] args) {
		Graph g = new SingleGraph("RWP");
		ThreadProxyPipe pipe =new ThreadProxyPipe(); //fake function
		pipe.addSink(g);
		g.display(false);

		while (true) {
		  pipe.pump();
		  try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}

}
