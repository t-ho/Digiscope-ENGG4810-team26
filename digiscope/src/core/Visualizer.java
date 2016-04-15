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
	private NumberAxis commonHorizontalAxis;
	private NumberAxis commonVerticalAxis;
	private NumberAxis[] verticalAxes;

	public Visualizer() {
		chart = createDefaultChart();
		xYPlot = chart.getXYPlot();
		commonHorizontalAxis = (NumberAxis) xYPlot.getDomainAxis();
		commonVerticalAxis = (NumberAxis) xYPlot.getRangeAxis(4);
		datasets = new XYSeriesCollection[Constant.NUMBER_OF_CHANNELS];
		verticalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			datasets[i] = (XYSeriesCollection) xYPlot.getDataset(i);
			verticalAxes[i] = (NumberAxis) xYPlot.getRangeAxis(i);
		}
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

	/**
	 * Create a default chart which has:
	 *  - One common horizontal axis(visible) which has 16 grid spacings for four channels
	 *  - One common vertical axis(visible) which has 10 grid spacings
	 *  - Four vertical axes(invisible) for four channels
	 * @return JFreeChart
	 */
	private JFreeChart createDefaultChart() {
		XYSeriesCollection[] datasets = new XYSeriesCollection[Constant.NUMBER_OF_CHANNELS];
		NumberAxis commonHorizontalAxis = new NumberAxis("X");
		NumberAxis commonVerticalAxis = new NumberAxis("Y");
		NumberAxis[] verticalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		XYItemRenderer[] renderers = new XYItemRenderer[Constant.NUMBER_OF_CHANNELS];
		commonHorizontalAxis.setPositiveArrowVisible(true);
		commonVerticalAxis.setPositiveArrowVisible(true);
		// Set commonVerticalAxis = 10 grid spacings
		commonVerticalAxis.setTickUnit(new NumberTickUnit(20));
		commonVerticalAxis.setRange(-100, 100);
		// Set commonHorizontalAxis = 16 grid spacigns
		commonHorizontalAxis.setTickUnit(new NumberTickUnit(1));
		commonHorizontalAxis.setRange(0, 16);
		System.out.println("Created chart");
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			datasets[i] = new XYSeriesCollection();
			verticalAxes[i] = new NumberAxis();
			renderers[i] = new XYLineAndShapeRenderer(true, false);
		}
		XYPlot xYPlot = new XYPlot();
		xYPlot.setOrientation(PlotOrientation.VERTICAL);
		xYPlot.setDomainAxis(0, commonHorizontalAxis); // commonHorizontalAxis's index = 0
		xYPlot.setRangeAxes(verticalAxes);
		xYPlot.setRangeAxis(4, commonVerticalAxis); // commonVerticalAxis's index = 4
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			xYPlot.setDataset(i, datasets[i]);
			xYPlot.setRenderer(i, renderers[i]);
			xYPlot.mapDatasetToDomainAxis(i, 0); // map dataset to commonHorizontalAxis
			xYPlot.mapDatasetToRangeAxis(i, i); // map dataset to its verticalAxis
		}

		/* Hide 4 vertical axes of 4 channels */
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			verticalAxes[i].setTickLabelsVisible(false);
			verticalAxes[i].setAxisLineVisible(false);
			verticalAxes[i].setTickMarksVisible(false);
		}
		JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, xYPlot, true);
		return chart;
	}

	/**
	 * Set value for horizontal grid spacings.
	 * The horizontal axis has Constant.HORIZONTAL_GRID_SPACINGS.
	 * @param value The value in microseconds
	 */
	public void setValuePerHorizontalGridSpacing(int value) {
		NumberTickUnit tickUnit = new NumberTickUnit(value);
		double lower = 0;
		double upper = Constant.HORIZONTAL_GRID_SPACINGS * value;
		commonHorizontalAxis.setTickUnit(tickUnit);
		commonHorizontalAxis.setRange(lower, upper);
	}

	/**
	 * Set value for vertical grid spacings.
	 * The vertical axis has Constant.VERTICAL_GRID_SPACINGS.
	 * @param channelIndex The index of channel
	 * @param value The value in milivolts
	 */
	public void setValuePerVerticalGridSpacing(int channelIndex, int value) {
		NumberTickUnit tickUnit = new NumberTickUnit(value);
		double lower = -((Constant.VERTICAL_GRID_SPACINGS / 2) * value);
		double upper = ((Constant.VERTICAL_GRID_SPACINGS / 2) * value);
		verticalAxes[channelIndex].setTickUnit(tickUnit);
		verticalAxes[channelIndex].setRange(lower, upper);
		commonVerticalAxis.setTickUnit(tickUnit);
		commonVerticalAxis.setRange(lower, upper);
	}

}
