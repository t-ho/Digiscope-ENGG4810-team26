package core;

import java.awt.Color;

import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import data.Constant;

/**
 *
 * @author ToanHo
 */
public class Visualizer {

	/** The chart theme. */
	private static ChartTheme currentTheme = new StandardChartTheme("JFree");

	private JFreeChart chart;
	private XYPlot xYPlot;
	private XYSeriesCollection[] datasets;
	private NumberAxis commonHorizontalAxis;
	private NumberAxis commonVerticalAxis;
	private NumberAxis[] verticalAxes;
	private XYItemRenderer[] renderers = new XYItemRenderer[Constant.NUMBER_OF_CHANNELS];

	public Visualizer() {
		chart = createDefaultChart();
		xYPlot = chart.getXYPlot();
		commonHorizontalAxis = (NumberAxis) xYPlot.getDomainAxis(0);
		commonVerticalAxis = (NumberAxis) xYPlot.getRangeAxis(0);
		datasets = new XYSeriesCollection[Constant.NUMBER_OF_CHANNELS];
		verticalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			datasets[i] = (XYSeriesCollection) xYPlot.getDataset(i);
			verticalAxes[i] = (NumberAxis) xYPlot.getRangeAxis(i + 1);
			renderers[i] = (XYItemRenderer) xYPlot.getRenderer(i);
		}
		currentTheme.apply(chart);
	}

	/**
	 * Add XYSeries to Dataset of the specified channel. The data will be visualized on the canvas
	 * @param channelIndex the index of specified channel
	 * @param xYSeries 
	 * @param noOfSamples
	 */
	public void addSeriesToDataset(int channelIndex, XYSeries xYSeries, int noOfSamples) {
		if(xYSeries != null) {
			datasets[channelIndex].removeAllSeries();
			datasets[channelIndex].addSeries(xYSeries);
			if(channelIndex == Constant.A_INDEX) {
				renderers[channelIndex].setSeriesPaint(0, Constant.A_COLOR);
				XYSeries marker = new XYSeries("Trigger Marker");
				double x = noOfSamples / 2;
				double y1 = - Constant.VERTICAL_GRID_SPACINGS;
				double y2 = Constant.VERTICAL_GRID_SPACINGS;
				marker.add(x, y1);
				marker.add(x, y2);
				datasets[channelIndex].addSeries(marker);
				renderers[channelIndex].setSeriesPaint(1, Color.CYAN);
				renderers[channelIndex].setSeriesVisibleInLegend(1, false);
			} else if(channelIndex == Constant.B_INDEX) {
				renderers[channelIndex].setSeriesPaint(0, Constant.B_COLOR);
				XYSeries marker = new XYSeries("Trigger Marker");
				double x = noOfSamples / 2;
				double y1 = - Constant.VERTICAL_GRID_SPACINGS;
				double y2 = Constant.VERTICAL_GRID_SPACINGS;
				marker.add(x, y1);
				marker.add(x, y2);
				datasets[channelIndex].addSeries(marker);
				renderers[channelIndex].setSeriesPaint(1, Color.CYAN);
				renderers[channelIndex].setSeriesVisibleInLegend(1, false);
			} else if(channelIndex == Constant.MATH_INDEX) {
				renderers[channelIndex].setSeriesPaint(0, Constant.MATH_COLOR);
			} else if(channelIndex == Constant.FILTER_INDEX) {
				renderers[channelIndex].setSeriesPaint(0, Constant.FILTER_COLOR);
			}
		}
	}

	/**
	 * Remove all the XYSeries from the Dataset of the specified channel
	 * @param channelIndex The index of the specified channel
	 */
	public void removeAllSeriesFromDataset(int channelIndex) {
		datasets[channelIndex].removeAllSeries();
	}
	
	/**
	 * get XYSeries data of the specified channel
	 * @param channelIndex The index of specified channel
	 * @return XYSeries data or null
	 */
	public XYSeries getSeries(int channelIndex) {
		if(datasets[channelIndex].getSeriesCount() != 0) {
			return datasets[channelIndex].getSeries(0);
		} else {
			return null;
		}
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
		NumberAxis commonHorizontalAxis = createDefaultHorizontalAxis();
		NumberAxis commonVerticalAxis = createDefaultVerticalAxis();
		NumberAxis[] verticalAxes = new NumberAxis[Constant.NUMBER_OF_CHANNELS];
		XYItemRenderer[] renderers = new XYItemRenderer[Constant.NUMBER_OF_CHANNELS];
		commonHorizontalAxis.setLabel("Time");
		commonHorizontalAxis.setPositiveArrowVisible(true);
		commonHorizontalAxis.setTickLabelsVisible(false);
		commonVerticalAxis.setLabel("Voltage");
		commonVerticalAxis.setPositiveArrowVisible(true);
		commonVerticalAxis.setTickLabelsVisible(false);
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			datasets[i] = new XYSeriesCollection();
			verticalAxes[i] = createDefaultVerticalAxis();
			renderers[i] = new XYLineAndShapeRenderer(true, false);
		}
		/* Hide 4 vertical axes of 4 channels */
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			verticalAxes[i].setTickLabelsVisible(false);
			verticalAxes[i].setAxisLineVisible(false);
			verticalAxes[i].setTickMarksVisible(false);
		}
		XYPlot xYPlot = new XYPlot();
		xYPlot.setOrientation(PlotOrientation.VERTICAL);
		xYPlot.setDomainAxis(0, commonHorizontalAxis); // commonHorizontalAxis's index = 0
		xYPlot.setRangeAxis(0, commonVerticalAxis); // commonVerticalAxis's index = 0
		//xYPlot.setDomainZeroBaselineVisible(true);
		xYPlot.setRangeZeroBaselineVisible(true);
		for (int i = 0; i < Constant.NUMBER_OF_CHANNELS; i++) {
			xYPlot.setRangeAxis(i + 1, verticalAxes[i]);
			xYPlot.setDataset(i, datasets[i]);
			xYPlot.setRenderer(i, renderers[i]);
			xYPlot.mapDatasetToDomainAxis(i, 0); // map dataset to commonHorizontalAxis
			xYPlot.mapDatasetToRangeAxis(i, i + 1); // map dataset to its verticalAxis
		}

		JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, xYPlot, true);
		return chart;
	}


	/**
	 * Set value for vertical grid spacings.
	 * The vertical axis has Constant.VERTICAL_GRID_SPACINGS.
	 * @param channelIndex The index of channel
	 * @param value The value in milivolts
	 */
	public void setValueForVerticalGridSpacing(int channelIndex, double value) {
		double lower = -((Constant.VERTICAL_GRID_SPACINGS / 2) * value);
		double upper = ((Constant.VERTICAL_GRID_SPACINGS / 2) * value);
		verticalAxes[channelIndex].setTickUnit(new NumberTickUnit(value));
		verticalAxes[channelIndex].setRange(lower, upper);
	}

	/**
	 * Set value for common vertical grid spacings.
	 * The common vertical axis has Constant.VERTICAL_GRID_SPACINGS.
	 * @param value The value in milivolts
	 */
	public void setValueForCommonVerticalGridSpacing(double value) {
		double lower = -((Constant.VERTICAL_GRID_SPACINGS / 2) * value);
		double upper = ((Constant.VERTICAL_GRID_SPACINGS / 2) * value);
		commonVerticalAxis.setTickUnit(new NumberTickUnit(value));
		commonVerticalAxis.setRange(lower, upper);
	}
	
	/**
	 * Create an vertical axis with default settings:
	 *  - The number of grid spacings = Constant.VERTICAL_GRID_SPACINGS 
	 *  - Each grid spacing range = Constant.DEFAULT_VERTICAL_RANGE
	 * @return
	 */
	private NumberAxis createDefaultVerticalAxis() {
		NumberAxis axis = new NumberAxis();
		double lower = -((Constant.VERTICAL_GRID_SPACINGS / 2) * Constant.DEFAULT_VERTICAL_RANGE);
		double upper = ((Constant.VERTICAL_GRID_SPACINGS / 2) * Constant.DEFAULT_VERTICAL_RANGE);
		axis.setAutoRange(false);
		axis.setTickUnit(new NumberTickUnit(Constant.DEFAULT_VERTICAL_RANGE));
		axis.setRange(lower, upper);
		return axis;
	}

	/**
	 * Create a horizontal axis with default settings:
	 *  - The number of grid spacings = Constant.HORIZONTAL_GRID_SPACINGS 
	 *  - Each grid spacing range = Constant.DEFAULT_HORIZONTAL_RANGE
	 * @return
	 */
	private NumberAxis createDefaultHorizontalAxis() {
		NumberAxis axis = new NumberAxis();
		double lower = (Constant.DEFAULT_NUMBER_OF_SAMPLES - Constant.HORIZONTAL_GRID_SPACINGS * Constant.DEFAULT_HORIZONTAL_RANGE) / 2;
		double upper = lower + Constant.HORIZONTAL_GRID_SPACINGS * Constant.DEFAULT_HORIZONTAL_RANGE;
		axis.setAutoRange(false);
		axis.setTickUnit(new NumberTickUnit(Constant.DEFAULT_HORIZONTAL_RANGE));
		axis.setRange(lower, upper);
		return axis;
	}

	/**
	 * Set value for horizontal grid spacings.
	 * The horizontal axis has Constant.HORIZONTAL_GRID_SPACINGS.
	 * @param value The value in microseconds
	 * @param noOfSamples 
	 */
	public void setValueForHorizontalGridSpacing(double value, int noOfSamples) {
		NumberTickUnit tickUnit = new NumberTickUnit(value);
		double lower = (noOfSamples - Constant.HORIZONTAL_GRID_SPACINGS * value) / 2;
		double upper = lower + Constant.HORIZONTAL_GRID_SPACINGS * value;
		commonHorizontalAxis.setTickUnit(tickUnit);
		commonHorizontalAxis.setRange(lower, upper);
	}
	
	public Range getHorizontalRange() {
		return commonHorizontalAxis.getRange();
	}
	
	public Range getVerticalRange(int channelIndex) {
		return verticalAxes[channelIndex].getRange();
	}
}
