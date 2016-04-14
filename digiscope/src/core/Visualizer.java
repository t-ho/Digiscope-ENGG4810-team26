package core;

import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
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

	public Visualizer() {
		chart = createDefaultChart();
		xYPlot = chart.getXYPlot();
		datasets = new XYSeriesCollection[Constant.NUMBER_OF_CHANNELS];
		horizontalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		verticalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			datasets[i] = (XYSeriesCollection) xYPlot.getDataset(i);
			horizontalAxes[i] = (NumberAxis) xYPlot.getDomainAxis(i);
			verticalAxes[i] = (NumberAxis) xYPlot.getRangeAxis(i);
		}
		currentTheme.apply(chart);
	}

	public Visualizer(int channelIndex, XYSeries xYSeries) {
		this();
		addSeriesToDataset(channelIndex, xYSeries);
		setValuePerHorizontalGridSpacing(channelIndex, 1);
		setValuePerVerticalGridSpacing(channelIndex, 20);
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

	private JFreeChart createDefaultChart() {
		XYSeriesCollection[] datasets = new XYSeriesCollection[Constant.NUMBER_OF_CHANNELS];
		NumberAxis[] horizontalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		NumberAxis[] verticalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		XYItemRenderer[] renderers = new XYItemRenderer[Constant.NUMBER_OF_CHANNELS];
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			datasets[i] = new XYSeriesCollection();
			horizontalAxes[i] = new NumberAxis();
			verticalAxes[i] = new NumberAxis();
			renderers[i] = new XYLineAndShapeRenderer(true, false);
		}
		XYPlot xYPlot = new XYPlot();
		xYPlot.setOrientation(PlotOrientation.VERTICAL);
		xYPlot.setDomainAxes(horizontalAxes);
		xYPlot.setRangeAxes(verticalAxes);
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			xYPlot.setDataset(i, datasets[i]);
			xYPlot.setRenderer(i, renderers[i]);
			xYPlot.mapDatasetToDomainAxis(i, i);
			xYPlot.mapDatasetToRangeAxis(i, i);
		}
		// test
		xYPlot.mapDatasetToRangeAxis(1, 0);
		// endTest

		horizontalAxes[Constant.A_INDEX].setLabel("X");
		horizontalAxes[Constant.A_INDEX].setPositiveArrowVisible(true);
		verticalAxes[Constant.A_INDEX].setLabel("Y");
		verticalAxes[Constant.A_INDEX].setPositiveArrowVisible(true);
		verticalAxes[Constant.A_INDEX].setMinorTickCount(3);
		for (int i = 1; i < Constant.NUMBER_OF_CHANNELS; i++) {
			horizontalAxes[i].setTickLabelsVisible(false);
			horizontalAxes[i].setAxisLineVisible(false);
			horizontalAxes[i].setTickMarksVisible(false);
			verticalAxes[i].setTickLabelsVisible(false);
			verticalAxes[i].setAxisLineVisible(false);
			verticalAxes[i].setTickMarksVisible(false);
		}
		JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, xYPlot, true);
		return chart;
	}


	public void setValuePerHorizontalGridSpacing(int channelIndex, int value) {
		NumberTickUnit tickUnit = new NumberTickUnit(value);
		double lower = -((16 / 2) * value);
		double upper = ((16 / 2) * value);
		horizontalAxes[channelIndex].setTickUnit(tickUnit);
		horizontalAxes[channelIndex].setRange(lower, upper);
	}

	public void setValuePerVerticalGridSpacing(int channelIndex, int value) {
		NumberTickUnit tickUnit = new NumberTickUnit(value);
		double lower = -((Constant.VERTICAL_GRID_SPACINGS / 2) * value);
		double upper = ((Constant.VERTICAL_GRID_SPACINGS / 2) * value);
		verticalAxes[channelIndex].setTickUnit(tickUnit);
		verticalAxes[channelIndex].setRange(lower, upper);
	}

}
