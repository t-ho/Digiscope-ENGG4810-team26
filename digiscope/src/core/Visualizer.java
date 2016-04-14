package core;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import data.Constant;

public class Visualizer {

    /** The chart theme. */
    private static ChartTheme currentTheme = new StandardChartTheme("JFree");

	private JFreeChart chart;
	private XYPlot xYPlot;
	private XYSeriesCollection[] datasets;
	private NumberAxis[] horizontalAxes;
	private NumberAxis[] verticalAxes;
	private XYItemRenderer[] renderers;

	public Visualizer() {
		datasets = new XYSeriesCollection[Constant.NUMBER_OF_CHANNELS];
		horizontalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		verticalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		renderers = new XYItemRenderer[Constant.NUMBER_OF_CHANNELS];
		for(int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			datasets[i] = new XYSeriesCollection();
			horizontalAxes[i] = new NumberAxis();
			verticalAxes[i] = new NumberAxis();
			renderers[i] = new XYLineAndShapeRenderer(true, false);
		}
		horizontalAxes[Constant.A_INDEX].setLabel("X");
		verticalAxes[Constant.A_INDEX].setLabel("Y");
		xYPlot = new XYPlot();
		xYPlot.setOrientation(PlotOrientation.VERTICAL);
		xYPlot.setDomainAxes(horizontalAxes);
		xYPlot.setRangeAxes(verticalAxes);
		for(int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			xYPlot.setDataset(i, datasets[i]);
		}
		chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, xYPlot, true);
		currentTheme.apply(chart);
	}

	public Visualizer(int channelIndex, XYSeries xYSeries) {
		this();
		addSeriesToDataset(channelIndex, xYSeries);
	}
	
	public void addSeriesToDataset(int channelIndex, XYSeries xYSeries) {
		datasets[channelIndex].addSeries(xYSeries);
	}
	
	public void removeAllSeriesFromDataset(int channelIndex) {
		datasets[channelIndex].removeAllSeries();
	}
	
	public JFreeChart getChart() {
		return chart;
	}
}
