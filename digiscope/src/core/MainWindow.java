package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RectangleEdge;

import data.Constant;
import data.FilterFile;
import gui.FileChooserUi;
import gui.MainWindowUi;

/**
 *
 * @author ToanHo
 */
public class MainWindow extends MainWindowUi implements ChartMouseListener{

	private static final long serialVersionUID = 1L;
	private LaunchWindow launchWindow_;
	private Visualizer visualizer_;
	private ChartPanel chartPanel_;
	private Crosshair timeCrosshair_;
	private Crosshair voltageCrosshair_;
	private int measuredChannelIndex_; // used for cursor measurement
	private FilterFile filterFile_;
	private Socket socket_;
	private PacketWriter packetWriter_;
	private PacketReader packetReader_;

	public MainWindow(LaunchWindow launchWindow) {
		super();
		initialize();
		addListenersToComponents();
		setLaunchWindow(launchWindow);
		addComponentToCanvasPanel(chartPanel_);
	}
	
	public MainWindow(LaunchWindow launchWindow, Socket socket) throws IOException {
		super();
		initialize();
		setLaunchWindow(launchWindow);
		setSocket(socket);
		packetWriter_ = new PacketWriter(socket.getOutputStream());
		packetReader_ = new PacketReader(socket.getInputStream());
		// Create a new thread to handle input stream
		InputStreamHandler inputStreamHandler = new InputStreamHandler(this, packetReader_, packetWriter_);
		inputStreamHandler.run();
		addComponentToCanvasPanel(chartPanel_);
		addListenersToComponents();
	}

	private void initialize() {
		visualizer_ = new Visualizer();
		chartPanel_ = createDefaultChartPanel(visualizer_.getChart());
		filterFile_ = new FilterFile();
		// test
		// Channel A
		XYSeries aSeries = new XYSeries(Constant.CHANNEL_A);
		for(double i = -25000; i <= 25000; i = i + 0.1) {
			aSeries.add(i, 1 * Math.sin(i));
		}
// Test Filter 
//		aSeries = new XYSeries(Constant.CHANNEL_A);
//		aSeries.add(0, 3);
//		aSeries.add(1, 4);
//		aSeries.add(2, 5);
//		aSeries.add(3, 6);

		rawXYSeries.put(Constant.CHANNEL_A, aSeries);
		

		// Channel B
		XYSeries bSeries = new XYSeries(Constant.CHANNEL_B);
		for(double i = -25000; i <= 25000; i = i + 0.1) {
			bSeries.add(i, 1.5 * Math.sin(i));
		}
		rawXYSeries.put(Constant.CHANNEL_B, bSeries);

//		//Math Channel
//		XYSeries mathSeries = new XYSeries(Constant.MATH_CHANNEL);
//		for(double i = -20; i <= 20; i = i + 0.1) {
//			mathSeries.add(i, 800 * Math.sin(i));
//		}
//		rawXYSeries.put(Constant.MATH_CHANNEL, mathSeries);
		// endTest

//		// Filter Channel
//		XYSeries filterSeries = new XYSeries(Constant.FILTER_CHANNEL);
//		for(double i = -20; i <= 20; i = i + 0.1) {
//			filterSeries.add(i, 280 * Math.sin(i));
//		}
//		rawXYSeries_.put(Constant.FILTER_CHANNEL, filterSeries);

		//Math Channel
		XYSeries generatorSeries = new XYSeries(Constant.GENERATOR_CHANNEL);
		for(double i = -20; i <= 20; i = i + 0.1) {
			generatorSeries.add(i, 0.8 * Math.sin(i));
		}
		rawXYSeries.put(Constant.GENERATOR_CHANNEL, generatorSeries);
	}

	private void addListenersToComponents() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event) {
				mainWindowClosed();
			}
		});
		
		channelACheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				channelACheckboxItemStateChanged();
			}
		});

		channelBCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				channelBCheckboxItemStateChanged();
			}
		});
		
		mathChannelCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				mathChannelCheckboxItemStateChanged();
			}
		});
		
		filterChannelCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				filterChannelCheckboxItemStateChanged();
			}
		});

		generatorCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				generatorCheckboxItemStateChanged();
			}
		});

		horizontalRangeAComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeAComboBoxItemStateChanged();
			}
		});

		horizontalRangeBComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeBComboBoxItemStateChanged();
			}
		});

		horizontalRangeMathComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeMathComboBoxItemStateChanged();
			}
		});

		horizontalRangeFilterComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeFilterComboBoxItemStateChanged();
			}
		});
		
		horizontalRangeGeneratorComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeGeneratorComboBoxItemStateChanged();
			}
		});
		
		horizontalRangeAComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeAComboBoxActionPerformed();
			}
		});
		
		horizontalRangeBComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeBComboBoxActionPerformed();
			}
		});

		horizontalRangeMathComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeMathComboBoxActionPerformed();
			}
		});
		
		horizontalRangeFilterComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeFilterComboBoxActionPerformed();
			}
		});

		horizontalRangeGeneratorComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeGeneratorComboBoxActionPerformed();
			}
		});

		verticalRangeAComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeAComboBoxItemStateChanged();
			}
		});

		verticalRangeBComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeBComboBoxItemStateChanged();
			}
		});

		verticalRangeMathComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeMathComboBoxItemStateChanged();
			}
		});

		verticalRangeFilterComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeFilterComboBoxItemStateChanged();
			}
		});

		verticalRangeGeneratorComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeGeneratorComboBoxItemStateChanged();
			}
		});

		cursorComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				cursorComboBoxItemStateChanged();
			}
		});
		
		verticalOffsetASpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				verticalOffsetASpinnerStateChanged();
			}
		});

		verticalOffsetBSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				verticalOffsetBSpinnerStateChanged();
			}
		});

		verticalOffsetMathSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				verticalOffsetMathSpinnerStateChanged();
			}
		});

		verticalOffsetFilterSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				verticalOffsetFilterSpinnerStateChanged();
			}
		});

		verticalOffsetGeneratorSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				verticalOffsetGeneratorSpinnerStateChanged();
			}
		});

		horizontalOffsetASpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				horizontalOffsetASpinnerStateChanged();
			}
		});

		horizontalOffsetBSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				horizontalOffsetBSpinnerStateChanged();
			}
		});

		horizontalOffsetMathSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				horizontalOffsetMathSpinnerStateChanged();
			}
		});

		horizontalOffsetFilterSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				horizontalOffsetFilterSpinnerStateChanged();
			}
		});
		
		horizontalOffsetGeneratorSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				horizontalOffsetGeneratorSpinnerStateChanged();
			}
		});

		verticalOffsetUnitAComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalOffsetUnitAComboBoxItemStateChanged();
			}
		});

		verticalOffsetUnitBComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalOffsetUnitBComboBoxItemStateChanged();
			}
		});

		verticalOffsetUnitMathComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalOffsetUnitMathComboBoxItemStateChanged();
			}
		});

		verticalOffsetUnitFilterComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalOffsetUnitFilterComboBoxItemStateChanged();
			}
		});
		
		verticalOffsetUnitGeneratorComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalOffsetUnitGeneratorComboBoxItemStateChanged();
			}
		});
		
		horizontalOffsetUnitAComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalOffsetUnitAComboBoxItemStateChanged();
			}
		});

		horizontalOffsetUnitBComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalOffsetUnitBComboBoxItemStateChanged();
			}
		});

		horizontalOffsetUnitMathComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalOffsetUnitMathComboBoxItemStateChanged();
			}
		});

		horizontalOffsetUnitFilterComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalOffsetUnitFilterComboBoxItemStateChanged();
			}
		});
		
		horizontalOffsetUnitGeneratorComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalOffsetUnitGeneratorComboBoxItemStateChanged();
			}
		});
		
		newExpressionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				newExpressionButtonActionPerformed();
			}
		});

		editExpressionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				editExpressionButtonActionPerformed();
			}
		});

		removeExpressionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				removeExpressionButtonActionPerformed();
			}
		});
		
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				browseButtonActionPerformed();
			}
		});
		
		inputChannelComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				inputChannelComboBoxActionPerformed();
			}
		});
		
		removeFilterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				removeFilterButtonActionPerformed();
			}
		});
		
		channelCouplingToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				channelCouplingToggleButtonActionPerformed();
			}
		});
	}

	protected void channelCouplingToggleButtonActionPerformed() {
		// TODO
		if(channelCouplingToggleButton.isSelected()) {
			channelCouplingToggleButton.setText("DC");
		} else {
			channelCouplingToggleButton.setText("AC");
		}
	}

	protected void removeFilterButtonActionPerformed() {
		// TODO:
		String expression = expressionTextArea.getText().trim();
		if (expression.contains("F")) {
			int response = JOptionPane.showConfirmDialog(this,
					"The MATH channel is derived from this channel.\n"
							+ "Removing this channel will also remove the MATH channel.\n" + 
							"Do you want to continue?",
							"Remove Filter Channel", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				expressionTextArea.setText("");
				mathChannelCheckBox.setSelected(false);
				rawXYSeries.remove(Constant.MATH_CHANNEL);
			} else {
				return;
			}
		}
		setEnabledFilterChannelControls(false);
		browseButton.setEnabled(true);
		inputChannelComboBox.setEnabled(true);
		removeChannelPlotFromChartPanel(Constant.FILTER_CHANNEL);
		rawXYSeries.remove(Constant.FILTER_CHANNEL);
		inputChannelComboBox.setSelectedIndex(0);
		filterFile_.setValid(false);
		csvFilePathTextField.setText("Choose CSV file");
	}

	protected void inputChannelComboBoxActionPerformed() {
		// TODO
		calculateFilterChannel();
		String inputChannel = (String) inputChannelComboBox.getSelectedItem();
		if(inputChannel != null) {
			if(filterFile_.isValid() && (!inputChannel.equals("Select channel"))) {
				setEnabledFilterChannelControls(true);
			}
		}
	}

	protected void browseButtonActionPerformed() {
		// TODO
		String iconPath = "/icons/csv_icon_16x16.png";
		String decription = "Comma-separated-values file (*." + Constant.CSV_FILE_EXTENSION + ")";
		FileChooserUi fileChooser = new FileChooserUi(JFileChooser.FILES_ONLY,
				Constant.CSV_FILE_EXTENSION, iconPath, decription);
		int status = fileChooser.showOpenDialog(this);
		if(status == JFileChooser.APPROVE_OPTION) {
			File csvFile = fileChooser.getSelectedFile();
			if(filterFile_.loadCsvFile(csvFile.getAbsolutePath())) {
				csvFilePathTextField.setForeground(Color.BLACK);
				csvFilePathTextField.setText(csvFile.getName());
				csvFilePathTextField.setToolTipText(csvFile.getAbsolutePath());
				calculateFilterChannel();
				String inputChannel = (String) inputChannelComboBox.getSelectedItem();
				if(inputChannel != null) {
					if(filterFile_.isValid() && (!inputChannel.equals("Select channel"))) {
						setEnabledFilterChannelControls(true);
					}
				}
			} else {
				csvFilePathTextField.setForeground(Color.RED);
				csvFilePathTextField.setText("The choosen file is not valid!");
				csvFilePathTextField.setToolTipText("");
			}
		}
	}

	protected void removeExpressionButtonActionPerformed() {
		// TODO
		if(inputChannelComboBox.getSelectedItem().equals(Constant.MATH_CHANNEL)) {
			int response = JOptionPane.showConfirmDialog(this, 
					"The FILTER channel is derived from this channel.\n" + 
					"Removing this channel will also remove the FILTER channel.\n" +
					"Do you want to continue?", "Remove Math Channel", 
					JOptionPane.YES_NO_OPTION);
			if(response == JOptionPane.YES_OPTION) {
				filterChannelCheckBox.setSelected(false);
				rawXYSeries.remove(Constant.FILTER_CHANNEL);
				inputChannelComboBox.setSelectedIndex(0);
				filterFile_.setValid(false);
				csvFilePathTextField.setText("Choose CSV file");
			} else {
				return;
			}
		}
		expressionTextArea.setText("");
		setEnabledExpressionControls(false);
		setEnabledMathChannelControls(false);
		newExpressionButton.setEnabled(true);
		removeChannelPlotFromChartPanel(Constant.MATH_CHANNEL);
		rawXYSeries.remove(Constant.MATH_CHANNEL);
		updateInputChannelComboBox();
	}

	protected void editExpressionButtonActionPerformed() {
		// TODO
		String expression = expressionTextArea.getText();
		ExpressionDialog expressionDialog = new ExpressionDialog(this, expression);
		expressionDialog.setVisible(true);
		calculateMathChannel();
	}

	private void newExpressionButtonActionPerformed() {
		// TODO
		ExpressionDialog expressionDialog = new ExpressionDialog(this);
		expressionDialog.setVisible(true);
	}

	protected void verticalOffsetUnitAComboBoxItemStateChanged() {
		verticalOffsetASpinner.setValue(0);
	}

	protected void verticalOffsetUnitBComboBoxItemStateChanged() {
		verticalOffsetBSpinner.setValue(0);
	}

	protected void verticalOffsetUnitMathComboBoxItemStateChanged() {
		verticalOffsetMathSpinner.setValue(0);
	}

	protected void verticalOffsetUnitFilterComboBoxItemStateChanged() {
		verticalOffsetFilterSpinner.setValue(0);
	}

	protected void verticalOffsetUnitGeneratorComboBoxItemStateChanged() {
		verticalOffsetGeneratorSpinner.setValue(0);
	}

	protected void horizontalOffsetUnitAComboBoxItemStateChanged() {
		horizontalOffsetASpinner.setValue(0);
	}

	protected void horizontalOffsetUnitBComboBoxItemStateChanged() {
		horizontalOffsetBSpinner.setValue(0);
	}

	protected void horizontalOffsetUnitMathComboBoxItemStateChanged() {
		horizontalOffsetMathSpinner.setValue(0);
	}

	protected void horizontalOffsetUnitFilterComboBoxItemStateChanged() {
		horizontalOffsetFilterSpinner.setValue(0);
	}

	protected void horizontalOffsetUnitGeneratorComboBoxItemStateChanged() {
		horizontalOffsetGeneratorSpinner.setValue(0);
	}

	private void verticalOffsetASpinnerStateChanged() {
		refreshChannelPlotOnChartPanel(Constant.CHANNEL_A);
	}
	
	private void verticalOffsetBSpinnerStateChanged() {
		refreshChannelPlotOnChartPanel(Constant.CHANNEL_B);
	}

	private void verticalOffsetMathSpinnerStateChanged() {
		refreshChannelPlotOnChartPanel(Constant.MATH_CHANNEL);
	}

	private void verticalOffsetFilterSpinnerStateChanged() {
		refreshChannelPlotOnChartPanel(Constant.FILTER_CHANNEL);
	}

	private void verticalOffsetGeneratorSpinnerStateChanged() {
		// TODO
		int horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetGeneratorSpinner.getValue(),
				(String) horizontalOffsetUnitGeneratorComboBox.getSelectedItem());
		double verticalOffset = getVerticalOffsetValue((int) verticalOffsetGeneratorSpinner.getValue(),
				(String) verticalOffsetUnitGeneratorComboBox.getSelectedItem());
		XYSeries generatorSeries = createXYSeriesWithOffsets(Constant.GENERATOR_CHANNEL,
				rawXYSeries.get(Constant.GENERATOR_CHANNEL), horizontalOffset, verticalOffset);
		visualizer_.addSeriesToDataset(Constant.GENERATOR_INDEX, generatorSeries);
	}

	private void horizontalOffsetASpinnerStateChanged() {
		refreshChannelPlotOnChartPanel(Constant.CHANNEL_A);
	}

	private void horizontalOffsetBSpinnerStateChanged() {
		refreshChannelPlotOnChartPanel(Constant.CHANNEL_B);
	}

	private void horizontalOffsetMathSpinnerStateChanged() {
		refreshChannelPlotOnChartPanel(Constant.MATH_CHANNEL);
	}

	private void horizontalOffsetFilterSpinnerStateChanged() {
		refreshChannelPlotOnChartPanel(Constant.FILTER_CHANNEL);
	}

	private void horizontalOffsetGeneratorSpinnerStateChanged() {
		// TODO
		int horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetGeneratorSpinner.getValue(),
				(String) horizontalOffsetUnitGeneratorComboBox.getSelectedItem());
		double verticalOffset = getVerticalOffsetValue((int) verticalOffsetGeneratorSpinner.getValue(),
				(String) verticalOffsetUnitGeneratorComboBox.getSelectedItem());
		XYSeries generatorSeries = createXYSeriesWithOffsets(Constant.GENERATOR_CHANNEL,
				rawXYSeries.get(Constant.GENERATOR_CHANNEL), horizontalOffset, verticalOffset);
		visualizer_.addSeriesToDataset(Constant.GENERATOR_INDEX, generatorSeries);
	}

	private void cursorComboBoxItemStateChanged() {
		// TODO
		String selectChannel = (String) cursorComboBox.getSelectedItem();
		if(selectChannel == Constant.CHANNEL_A) {
			showCursorMeasurement(Constant.A_INDEX);
			cursorVerticalValueLabel.setForeground(Constant.A_COLOR);
		} else if(selectChannel == Constant.CHANNEL_B) {
			showCursorMeasurement(Constant.B_INDEX);
			cursorVerticalValueLabel.setForeground(Constant.B_COLOR);
		} else if(selectChannel == Constant.MATH_CHANNEL) {
			showCursorMeasurement(Constant.MATH_INDEX);
			cursorVerticalValueLabel.setForeground(Constant.MATH_COLOR);
		} else if(selectChannel == Constant.FILTER_CHANNEL) {
			showCursorMeasurement(Constant.FILTER_INDEX);
			cursorVerticalValueLabel.setForeground(Constant.FILTER_COLOR);
		} else if(selectChannel == Constant.GENERATOR_CHANNEL) {
			showCursorMeasurement(Constant.GENERATOR_INDEX);
			cursorVerticalValueLabel.setForeground(Constant.GENERATOR_COLOR);
		} else {
			hideCursorMeasurement();
			cursorVerticalValueLabel.setForeground(Color.BLACK);
		}
	}

	private void horizontalRangeAComboBoxActionPerformed() {
		// TODO 
		int selectedIndex = horizontalRangeAComboBox.getSelectedIndex();
		horizontalRangeBComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeMathComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeFilterComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeGeneratorComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeBComboBoxActionPerformed() {
		// TODO 
		int selectedIndex = horizontalRangeBComboBox.getSelectedIndex();
		horizontalRangeAComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeMathComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeFilterComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeGeneratorComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeMathComboBoxActionPerformed() {
		// TODO
		int selectedIndex = horizontalRangeMathComboBox.getSelectedIndex();
		horizontalRangeAComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeBComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeFilterComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeGeneratorComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeFilterComboBoxActionPerformed() {
		// TODO
		int selectedIndex = horizontalRangeFilterComboBox.getSelectedIndex();
		horizontalRangeAComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeBComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeMathComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeGeneratorComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeGeneratorComboBoxActionPerformed() {
		// TODO
		int selectedIndex = horizontalRangeGeneratorComboBox.getSelectedIndex();
		horizontalRangeAComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeBComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeMathComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeFilterComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeAComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) horizontalRangeAComboBox.getSelectedItem();
		int horizontalRange = convertTimeStringToMicroSeconds(selectedItem);
		visualizer_.setValueForHorizontalGridSpacing(horizontalRange);
		showMeasurementResults(Constant.A_INDEX);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void horizontalRangeBComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) horizontalRangeBComboBox.getSelectedItem();
		int horizontalRange = convertTimeStringToMicroSeconds(selectedItem);
		visualizer_.setValueForHorizontalGridSpacing(horizontalRange);
		showMeasurementResults(Constant.B_INDEX);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void horizontalRangeMathComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) horizontalRangeMathComboBox.getSelectedItem();
		int horizontalRange = convertTimeStringToMicroSeconds(selectedItem);
		visualizer_.setValueForHorizontalGridSpacing(horizontalRange);
		showMeasurementResults(Constant.MATH_INDEX);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void horizontalRangeFilterComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) horizontalRangeFilterComboBox.getSelectedItem();
		int horizontalRange = convertTimeStringToMicroSeconds(selectedItem);
		visualizer_.setValueForHorizontalGridSpacing(horizontalRange);
		showMeasurementResults(Constant.FILTER_INDEX);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void horizontalRangeGeneratorComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) horizontalRangeGeneratorComboBox.getSelectedItem();
		int horizontalRange = convertTimeStringToMicroSeconds(selectedItem);
		visualizer_.setValueForHorizontalGridSpacing(horizontalRange);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void verticalRangeAComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) verticalRangeAComboBox.getSelectedItem();
		double verticalRange = convertVoltageStringToVolts(selectedItem);
		visualizer_.setValueForVerticalGridSpacing(Constant.A_INDEX, verticalRange);
		showMeasurementResults(Constant.A_INDEX);
		if(measuredChannelIndex_ == Constant.A_INDEX) {
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
		}
		aDivisionInfoLabel.setText("A: " + selectedItem + "/div");
	}

	private void verticalRangeBComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) verticalRangeBComboBox.getSelectedItem();
		double verticalRange = convertVoltageStringToVolts(selectedItem);
		visualizer_.setValueForVerticalGridSpacing(Constant.B_INDEX, verticalRange);
		showMeasurementResults(Constant.B_INDEX);
		if(measuredChannelIndex_ == Constant.B_INDEX) {
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
		}
		bDivisionInfoLabel.setText("B: " + selectedItem + "/div");
	}

	private void verticalRangeMathComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) verticalRangeMathComboBox.getSelectedItem();
		double verticalRange = convertVoltageStringToVolts(selectedItem);
		visualizer_.setValueForVerticalGridSpacing(Constant.MATH_INDEX, verticalRange);
		showMeasurementResults(Constant.MATH_INDEX);
		if(measuredChannelIndex_ == Constant.MATH_INDEX) {
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
		}
		mathDivisionInfoLabel.setText("Math: " + selectedItem + "/div");
	}

	private void verticalRangeFilterComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) verticalRangeFilterComboBox.getSelectedItem();
		double verticalRange = convertVoltageStringToVolts(selectedItem);
		visualizer_.setValueForVerticalGridSpacing(Constant.FILTER_INDEX, verticalRange);
		showMeasurementResults(Constant.FILTER_INDEX);
		if(measuredChannelIndex_ == Constant.FILTER_INDEX) {
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
		}
		filterDivisionInfoLabel.setText("Filter: " + selectedItem + "/div");
	}

	private void verticalRangeGeneratorComboBoxItemStateChanged() {
		// TODO
		String selectedItem = (String) verticalRangeGeneratorComboBox.getSelectedItem();
		double verticalRange = convertVoltageStringToVolts(selectedItem);
		visualizer_.setValueForVerticalGridSpacing(Constant.GENERATOR_INDEX, verticalRange);
		if(measuredChannelIndex_ == Constant.GENERATOR_INDEX) {
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
		}
		generatorDivisionInfoLabel.setText("Generator: " + selectedItem + "/div");
	}

	private void channelACheckboxItemStateChanged() {
		// TODO
		if (channelACheckBox.isSelected()) {
			setEnabledChannelAControls(true);
			showTab(Constant.TAB.CHANNEL_A);
			showChannelPlotOnChartPanel(Constant.CHANNEL_A);
		} else {
			setEnabledChannelAControls(false);
			removeChannelPlotFromChartPanel(Constant.CHANNEL_A);
		}
	}	
	
	private void channelBCheckboxItemStateChanged() {
		// TODO
		if(channelBCheckBox.isSelected()) {
			setEnabledChannelBControls(true);
			showTab(Constant.TAB.CHANNEL_B);
			showChannelPlotOnChartPanel(Constant.CHANNEL_B);
		} else {
			setEnabledChannelBControls(false);
			removeChannelPlotFromChartPanel(Constant.CHANNEL_B);
		}
	}

	private void mathChannelCheckboxItemStateChanged() {
		// TODO
		if(mathChannelCheckBox.isSelected()) {
			setEnabledMathChannelControls(true);
			showTab(Constant.TAB.MATH_CHANNEL);
			showChannelPlotOnChartPanel(Constant.MATH_CHANNEL);
		} else {
			setEnabledMathChannelControls(false);
			removeChannelPlotFromChartPanel(Constant.MATH_CHANNEL);
		}
	}
	
	private void filterChannelCheckboxItemStateChanged() {
		// TODO
		if(filterChannelCheckBox.isSelected()) {
			setEnabledFilterChannelControls(true);
			showTab(Constant.TAB.FILTER_CHANNEL);
			updateInputChannelComboBox();
			showChannelPlotOnChartPanel(Constant.FILTER_CHANNEL);
		} else {
			setEnabledFilterChannelControls(false);
			removeChannelPlotFromChartPanel(Constant.FILTER_CHANNEL);
		}
	}

	private void generatorCheckboxItemStateChanged() {
		// TODO
		if(generatorCheckBox.isSelected()) {
			setEnabledGeneratorChannelControls(true);
			showTab(Constant.TAB.GENERATOR_CHANNEL);
			showChannelPlotOnChartPanel(Constant.GENERATOR_CHANNEL);
		} else {
			setEnabledGeneratorChannelControls(false);
			removeChannelPlotFromChartPanel(Constant.GENERATOR_CHANNEL);
		}
	}
	
	/**
	 * Update inputChannelCombox's items for filter channel by adding 
	 * available channels and remove unavailable ones to/from the list.
	 */
	public void updateInputChannelComboBox() {
			String selectedItem = (String) inputChannelComboBox.getSelectedItem();
			inputChannelComboBox.removeAllItems();
			inputChannelComboBox.addItem("Select channel");
			if(rawXYSeries.containsKey(Constant.CHANNEL_A)) {
				inputChannelComboBox.addItem(Constant.CHANNEL_A);
			}
			if(rawXYSeries.containsKey(Constant.CHANNEL_B)) {
				inputChannelComboBox.addItem(Constant.CHANNEL_B);
			}
			if(rawXYSeries.containsKey(Constant.MATH_CHANNEL)) {
				String expression = expressionTextArea.getText().trim();
				if(! expression.contains("F")) {
					inputChannelComboBox.addItem(Constant.MATH_CHANNEL);
				}
			}
			inputChannelComboBox.setSelectedItem(selectedItem);
	}
	
	/**
	 * Get the available derived channel for Math channel
	 * @return List of channels available for Math channel
	 */
	public ArrayList<String> getAvailableDerivedChannelForMath() {
		ArrayList<String> result = new ArrayList<String>();
		if (rawXYSeries.containsKey(Constant.CHANNEL_A)) {
			result.add(Constant.CHANNEL_A);
		}
		if (rawXYSeries.containsKey(Constant.CHANNEL_B)) {
			result.add(Constant.CHANNEL_B);
		}
		if (rawXYSeries.containsKey(Constant.FILTER_CHANNEL)) {
			String inputChannelForMath = (String) inputChannelComboBox.getSelectedItem();
			if(!inputChannelForMath.equals(Constant.MATH_CHANNEL)) {
				result.add(Constant.FILTER_CHANNEL);
			}
		}
		return result;
	}

	/**
	 * Convert the voltage string to volts
	 * @param voltString The voltage string. e.g. 200 mV, 1 V,...
	 * @return the voltage in volts
	 */
	private double convertVoltageStringToVolts(String voltString) {
		double value = 0;
		switch(voltString) {
			case Constant.TWENTY_MILIVOLTS:
				value = 0.02;
				break;
			case Constant.FIFTY_MILIVOLTS:
				value = 0.05;
				break;
			case Constant.ONE_HUNDRED_MILIVOLTS:
				value = 0.1;
				break;
			case Constant.TWO_HUNDRED_MILIVOLTS:
				value = 0.2;
				break;
			case Constant.FIVE_HUNDRED_MILIVOLTS:
				value = 0.5;
				break;
			case Constant.ONE_VOLT:
				value = 1;
				break;
			case Constant.TWO_VOLTS:
				value = 2;
				break;
			default:
				// Cannot reach here
				value = 0.02;
		}
		return value;
	}

	/**
	 * Change time string to micro seconds
	 * @param timeString The time string. e.g. 1 us, 500 ms
	 * @return the microseconds
	 */
	private int convertTimeStringToMicroSeconds(String timeString) {
		int value = 0;
		switch(timeString) {
			case Constant.ONE_MICROSECOND:
				value = 1;
				break;
			case Constant.TWO_MICROSECONDS:
				value = 2;
				break;
			case Constant.FIVE_MICROSECONDS:
				value = 5;
				break;
			case Constant.TEN_MICROSECONDS:
				value = 10;
				break;
			case Constant.TWENTY_MICROSECONDS:
				value = 20;
				break;
			case Constant.FIFTY_MICROSECONDS:
				value = 50;
				break;
			case Constant.ONE_HUNDRED_MICROSECONDS:
				value = 100;
				break;
			case Constant.TWO_HUNDRED_MICROSECONDS:
				value = 200;
				break;
			case Constant.FIVE_HUNDRED_MICROSECONDS:
				value = 500;
				break;
			case Constant.ONE_MILISECOND:
				value = 1000;
				break;
			case Constant.TWO_MILISECONDS:
				value = 2000;
				break;
			case Constant.FIVE_MILISECONDS:
				value = 5000;
				break;
			case Constant.TEN_MILISECONDS:
				value = 10000;
				break;
			case Constant.TWENTY_MILISECONDS:
				value = 20000;
				break;
			case Constant.FIFTY_MILISECONDS:
				value = 50000;
				break;
			case Constant.ONE_HUNDRED_MILISECONDS:
				value = 100000;
				break;
			case Constant.TWO_HUNDRED_MILISECONDS:
				value = 200000;
				break;
			case Constant.FIVE_HUNDRED_MILISECONDS:
				value = 500000;
				break;
			case Constant.ONE_SECOND:
				value = 1000000;
				break;
			default:
				// Cannot reach here
				value = 1;
		}
		return value;
	}
	
	/**
	 * Create default chart panel
	 * @param chart 
	 * @return ChartPanel instance
	 */
	private ChartPanel createDefaultChartPanel(JFreeChart chart) {
		ChartPanel chartPanel = new ChartPanel(chart);
        CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
        this.measuredChannelIndex_ = 0;
        this.timeCrosshair_ = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        this.timeCrosshair_.setLabelVisible(true);
        this.timeCrosshair_.setLabelBackgroundPaint(Constant.A_COLOR);
        this.voltageCrosshair_ = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
        this.voltageCrosshair_.setLabelVisible(true);
        crosshairOverlay.addDomainCrosshair(timeCrosshair_);
        crosshairOverlay.addRangeCrosshair(voltageCrosshair_);
        chartPanel.addOverlay(crosshairOverlay);
		chartPanel.setMouseZoomable(false);
		return chartPanel;
	}

	private void mainWindowClosed() {
		getLaunchWindow().setStatus("To connect, please enter the IP address!", Constant.NORMAL);
		getLaunchWindow().setVisible(true);
	}

	public LaunchWindow getLaunchWindow() {
		return launchWindow_;
	}

	public void setLaunchWindow(LaunchWindow launchWindow) {
		this.launchWindow_ = launchWindow;
	}
	
	public Map<String, XYSeries> getRawXYSeries(){
		return rawXYSeries;
	}
	
	public void setExpressionForMathChannel(String expression) {
		expressionTextArea.setText(expression);
	}
	
	/**
	 * Show cursor measurement and set channel index which will be measured
	 * @param channelIndex the index of the measured channel
	 */
	private void showCursorMeasurement(int channelIndex) {
		chartPanel_.removeChartMouseListener(this);
		measuredChannelIndex_ = channelIndex;
		if(channelIndex == Constant.A_INDEX) {
			String selectedItem = (String) verticalRangeAComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.A_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.A_LIGHT_COLOR);
		} else if(channelIndex == Constant.B_INDEX) {
			String selectedItem = (String) verticalRangeBComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.B_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.B_LIGHT_COLOR);
		} else if(channelIndex == Constant.MATH_INDEX) {
			String selectedItem = (String) verticalRangeMathComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.MATH_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.MATH_LIGHT_COLOR);
		} else if(channelIndex == Constant.FILTER_INDEX) {
			String selectedItem = (String) verticalRangeFilterComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.FILTER_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.FILTER_LIGHT_COLOR);
		} else if(channelIndex == Constant.GENERATOR_INDEX) {
			String seletectItem = (String) verticalRangeGeneratorComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(seletectItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.GENERATOR_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.GENERATOR_LIGHT_COLOR);
		}
		chartPanel_.addChartMouseListener(this);
	}
	
	/**
	 * Hide the cursor measurement
	 */
	private void hideCursorMeasurement() {
		this.chartPanel_.removeChartMouseListener(this);
		timeCrosshair_.setValue(Double.NaN);
		voltageCrosshair_.setValue(Double.NaN);
		cursorVerticalValueLabel.setText("N/A");
	}
	
	/**
	 * Convert voltage value to string
	 * @param voltage the value to be converted
	 * @return voltage string
	 */
	private String convertVoltsToVoltageString(double voltage) {
		String result = Constant.round(voltage, 4) + " V";
//		if (voltage <= -0.9999 || voltage >= 0.9999) {
//			result = Constant.round(voltage, 4) + " V";
//		} else {
//			result = Constant.round(voltage * 1000, 2) + " mV";
//		}
		return result;
	}

	/**
	 * Create a XYSeries with given offset
	 * @param channelName The name of the channel
	 * @param xYSeries The raw XYSeries
	 * @param horizontalOffset The horizontal offset
	 * @param verticalOffset The vertical offset
	 * @return a XYSeries with given offset or null if the given xYSeries is null.
	 */
	private XYSeries createXYSeriesWithOffsets(String channelName, XYSeries xYSeries,
			int horizontalOffset, double verticalOffset) {
		XYSeries result = new XYSeries(channelName);
		if(xYSeries != null) {
			for(int i = 0; i < xYSeries.getItemCount(); i++) {
				double xValue = xYSeries.getDataItem(i).getXValue() + horizontalOffset;
				double yValue = xYSeries.getDataItem(i).getYValue() + verticalOffset;
				result.add(xValue, yValue);
			}
		} else {
			result = null;
		}
		return result;
	}

	/**
	 * Show the plot of specified channel on the chart panel
	 * @param channelName The channel's name
	 */
	private void showChannelPlotOnChartPanel(String channelName) {
		refreshChannelPlotOnChartPanel(channelName);
		if (rawXYSeries.containsKey(channelName)) {
			// remove item if it exists in the combo box before adding
			cursorComboBox.removeItem(channelName);
			cursorComboBox.addItem(channelName);
		}
	}
	
	/**
	 * Refresh the plot of specified channel on the chart panel
	 * @param channelName The channel's name
	 */
	private void refreshChannelPlotOnChartPanel(String channelName) {
		XYSeries rawSeries = rawXYSeries.get(channelName);
		if (rawSeries != null) {
			int horizontalOffset = 0;
			double verticalOffset = 0;
			int channelIndex = 0;
			if (channelName == Constant.CHANNEL_A) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetASpinner.getValue(),
						(String) horizontalOffsetUnitAComboBox.getSelectedItem());
				verticalOffset = getVerticalOffsetValue((int) verticalOffsetASpinner.getValue(),
						(String) verticalOffsetUnitAComboBox.getSelectedItem());
				channelIndex = Constant.A_INDEX;
			} else if (channelName == Constant.CHANNEL_B) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetBSpinner.getValue(),
						(String) horizontalOffsetUnitBComboBox.getSelectedItem());
				verticalOffset = getVerticalOffsetValue((int) verticalOffsetBSpinner.getValue(),
						(String) verticalOffsetUnitBComboBox.getSelectedItem());
				channelIndex = Constant.B_INDEX;
			} else if (channelName == Constant.MATH_CHANNEL) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetMathSpinner.getValue(),
						(String) horizontalOffsetUnitMathComboBox.getSelectedItem());
				verticalOffset = getVerticalOffsetValue((int) verticalOffsetMathSpinner.getValue(),
						(String) verticalOffsetUnitMathComboBox.getSelectedItem());
				channelIndex = Constant.MATH_INDEX;
			} else if (channelName == Constant.FILTER_CHANNEL) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetFilterSpinner.getValue(),
						(String) horizontalOffsetUnitFilterComboBox.getSelectedItem());
				verticalOffset = getVerticalOffsetValue((int) verticalOffsetFilterSpinner.getValue(),
						(String) verticalOffsetUnitFilterComboBox.getSelectedItem());
				channelIndex = Constant.FILTER_INDEX;
			} else if (channelName == Constant.GENERATOR_CHANNEL) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetGeneratorSpinner.getValue(),
						(String) horizontalOffsetUnitGeneratorComboBox.getSelectedItem());
				verticalOffset = getVerticalOffsetValue((int) verticalOffsetGeneratorSpinner.getValue(),
						(String) verticalOffsetUnitGeneratorComboBox.getSelectedItem());
				channelIndex = Constant.GENERATOR_INDEX;
			}
			XYSeries xYSeries = createXYSeriesWithOffsets(channelName, rawSeries, horizontalOffset, verticalOffset);
			visualizer_.addSeriesToDataset(channelIndex, xYSeries);
			showMeasurementResults(channelIndex);
		}
	}
	
	/**
	 * Remove the plot of specified channel from the chart panel
	 * @param channelName The channel's name
	 */
	private void removeChannelPlotFromChartPanel(String channelName) {
		int channelIndex = 0;
		if(channelName == Constant.CHANNEL_A) {
			channelIndex = Constant.A_INDEX;
		} else if(channelName == Constant.CHANNEL_B) {
			channelIndex = Constant.B_INDEX;
		} else if(channelName == Constant.MATH_CHANNEL) {
			channelIndex = Constant.MATH_INDEX;
		} else if(channelName == Constant.FILTER_CHANNEL) {
			channelIndex = Constant.FILTER_INDEX;
		} else if(channelName == Constant.GENERATOR_CHANNEL) {
			channelIndex = Constant.GENERATOR_INDEX;
		}
		visualizer_.removeAllSeriesFromDataset(channelIndex);
		hideMeasurementResults(channelIndex);
		cursorComboBox.removeItem(channelName);
	}

	/**
	 * Get vertical offset value
	 * @param offset the value from verticalOffset spinner
	 * @param unit the selected unit from verticalOffsetUnit combobox
	 * @return vertical offset value in milivolts
	 */
	private double getVerticalOffsetValue(int offset, String unit) {
		if(unit.equals(Constant.TEN_MILIVOLTS)) {
			return offset * 0.01;
		} else if(unit.equals(Constant.ONE_HUNDRED_MILIVOLTS)) {
			return offset * 0.1;
		} else if(unit.equals(Constant.ONE_VOLT)) {
			return offset * 1;
		}else { // unit == Constant.ONE_MILIVOLT
			return offset * 0.001;
		}
	}
	
	/**
	 * Get horizontal offset value
	 * @param offset the value from horizontalOffset spinner
	 * @param unit the selected unit from horizontalOffsetUnit combobox
	 * @return horizontal offset value in micro-seconds
	 */
	private int getHorizontalOffsetValue(int offset, String unit) {
		if(unit.equals(Constant.TEN_MICROSECONDS)) {
			return offset * 10;
		} else if(unit.equals(Constant.ONE_HUNDRED_MICROSECONDS)) {
			return offset * 100;
		} else if(unit.equals(Constant.ONE_MILISECOND)) {
			return offset * 1000;
		} else if(unit.equals(Constant.TEN_MILISECONDS)) {
			return offset * 10000;
		} else if(unit.equals(Constant.ONE_HUNDRED_MILISECONDS)) {
			return offset * 100000;
		} else if(unit.equals(Constant.ONE_SECOND)){
			return offset * 1000000;
		} else { // unit == Constant.ONE_MICROSECOND 
			return offset;
		}
	}
	
	/**
	 * Calculate the MATH channel
	 */
	public void calculateMathChannel() {
		String expression = expressionTextArea.getText().trim();
		if (!expression.equals("")) {
			Map<String, String> channelNames = new HashMap<String, String>();
			if (expression.contains("A")) {
				channelNames.put("A", Constant.CHANNEL_A);
			}
			if (expression.contains("B")) {
				channelNames.put("B", Constant.CHANNEL_B);
			}
			if (expression.contains("F")) {
				channelNames.put("F", Constant.FILTER_CHANNEL);
			}
			int noOfItems = getMinNoOfItems(channelNames);
			if (noOfItems != Integer.MAX_VALUE) {
				Evaluator evaluator = new Evaluator();
				XYSeries mathSeries = new XYSeries(Constant.MATH_CHANNEL);
				Double x = 0.0;
				for (int i = 0; i < noOfItems; i++) {
					for (Map.Entry<String, String> entry : channelNames.entrySet()) {
						XYDataItem dataItem = rawXYSeries.get(entry.getValue()).getDataItem(i);
						evaluator.setVariableValue(entry.getKey(), dataItem.getYValue());
						x = dataItem.getXValue();
					}
					mathSeries.add(x, evaluator.evaluate(expression, evaluator.getVariables()));
				}
				rawXYSeries.put(Constant.MATH_CHANNEL, mathSeries);
				if(mathChannelCheckBox.isSelected()) {
					showChannelPlotOnChartPanel(Constant.MATH_CHANNEL);
				}
			} else {
				System.out.print("There is no input channels (A, B or Filter) in the expression");
			}
		}

	}
	
	/**
	 * Calculate the FILTER channel
	 */
	public void calculateFilterChannel() {
		String inputChannel = (String) inputChannelComboBox.getSelectedItem();
		if (inputChannel != null) {
			if (filterFile_.isValid() && (!inputChannel.equals("Select channel"))) {
				ArrayList<Double> firstColumn = filterFile_.getFirstColumn();
				XYSeries derivedSeries = rawXYSeries.get(inputChannel);
				XYSeries filterSeries = new XYSeries(Constant.FILTER_CHANNEL);
				if (filterFile_.getType() == Constant.FIR) {
					for (int n = 0; n < derivedSeries.getItemCount(); n++) {
						Double result = 0.0;
						for (int i = 0; i < firstColumn.size(); i++) {
							Double x = 0.0;
							if (n - i >= 0) {
								x = derivedSeries.getDataItem(n - i).getYValue();
							}
							result = result + firstColumn.get(i) * x;
						}
						filterSeries.add(derivedSeries.getDataItem(n).getX(), result);
					}
				} else if (filterFile_.getType() == Constant.IIR) {
					// TODO:
					ArrayList<Double> secondColumn = filterFile_.getSecondColumn();
					for (int n = 0; n < derivedSeries.getItemCount(); n++) {
						Double firstSum = 0.0;
						Double secondSum = 0.0;
						for (int i = 0; i < firstColumn.size(); i++) {
							Double x = 0.0;
							if (n - i >= 0) {
								x = derivedSeries.getDataItem(n - i).getYValue();
							}
							firstSum += firstColumn.get(i) * x;
						}
						for (int j = 1; j < secondColumn.size(); j++) {
							Double y = 0.0;
							if (n - j >= 0) {
								y = filterSeries.getDataItem(n - j).getYValue();
							}
							secondSum += secondColumn.get(j) * y;
						}
						Double result = (1 / secondColumn.get(0)) * (firstSum - secondSum);
						filterSeries.add(derivedSeries.getDataItem(n).getX(), result);
					}
				}
				rawXYSeries.put(Constant.FILTER_CHANNEL, filterSeries);
				if (filterChannelCheckBox.isSelected()) {
					showChannelPlotOnChartPanel(Constant.FILTER_CHANNEL);
				}
			}
		}
	}
	
	/**
	 * get the minimum number of items 
	 * @param channelNames
	 * @return
	 */
	private int getMinNoOfItems(Map<String, String> channelNames) {
		int min = Integer.MAX_VALUE;
		for(String name: channelNames.values()) {
			if(rawXYSeries.get(name).getItemCount() < min) {
				min = rawXYSeries.get(name).getItemCount();
			}
		}
		return min;
	}
	
	/**
	 * Determine the max voltage, min voltage, max peak to peak,
	 * average voltage, standard deviation of the specified channel.
	 * @param channelIndex the index of specified channel
	 * @return Map<String, Double> containing results or null
	 */
	private Map<String, Double> measureChannel(int channelIndex) {
		// TODO:
		Map<String, Double> result = new HashMap<String, Double>();
		XYSeries xYSeries = visualizer_.getSeries(channelIndex);
		if (xYSeries != null) {
			Range horizontalRange = visualizer_.getHorizontalRange();
			Range verticalRange = visualizer_.getVerticalRange(channelIndex);
			double maxVoltage = Double.MIN_VALUE;
			double minVoltage = Double.MAX_VALUE;
			double totalVoltage = 0;
			int nSamples = 0;
			for (int i = 0; i < xYSeries.getItemCount(); i++) {
				double time = xYSeries.getDataItem(i).getXValue();
				double voltage = xYSeries.getDataItem(i).getYValue();
				if (time <= horizontalRange.getUpperBound()) {
					if ((voltage <= verticalRange.getUpperBound()) && (voltage >= verticalRange.getLowerBound())) {
						if (maxVoltage < voltage) {
							maxVoltage = voltage;
						}
						if (minVoltage > voltage) {
							minVoltage = voltage;
						}
						totalVoltage += voltage;
						nSamples++;
					}
				} else {
					break;
				}
			}
			if (nSamples > 0) {
				double averageVoltage = totalVoltage / nSamples;
				double sumOfSquare = 0;
				for (int i = 0; i < nSamples; i++) {
					double voltage = xYSeries.getDataItem(i).getYValue();
					sumOfSquare += Math.pow((voltage - averageVoltage), 2);
				}
				double deviation = Math.sqrt(sumOfSquare / (nSamples - 1));
				result.put(Constant.MAX_VOLTAGE, maxVoltage);
				result.put(Constant.MIN_VOLTAGE, minVoltage);
				result.put(Constant.AVERAGE_VOLTAGE, averageVoltage);
				result.put(Constant.MAX_P2P_VOLTAGE, maxVoltage - minVoltage);
				result.put(Constant.STANDARD_DEVIATION_VOLTAGE, deviation);
			} else {
				result = null;
			}
		} else {
			result = null;
		}
		return result;
	}
	
	/**
	 * Show measurement results of the specified channel
	 * @param channelIndex the index of specified channel
	 */
	private void showMeasurementResults(int channelIndex) {
		Map<String, Double> results = measureChannel(channelIndex);
		if (results != null) {
			if (channelIndex == Constant.A_INDEX) {
				maxVoltageALabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_VOLTAGE)));
				minVoltageALabel.setText(convertVoltsToVoltageString(results.get(Constant.MIN_VOLTAGE)));
				maxP2pVoltageALabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_P2P_VOLTAGE)));
				averageVoltageALabel.setText(convertVoltsToVoltageString(results.get(Constant.AVERAGE_VOLTAGE)));
				Double deviation = Constant.round(results.get(Constant.STANDARD_DEVIATION_VOLTAGE), 4);
				standardDeviationVoltageALabel.setText(deviation.toString());
			} else if (channelIndex == Constant.B_INDEX) {
				maxVoltageBLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_VOLTAGE)));
				minVoltageBLabel.setText(convertVoltsToVoltageString(results.get(Constant.MIN_VOLTAGE)));
				maxP2pVoltageBLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_P2P_VOLTAGE)));
				averageVoltageBLabel.setText(convertVoltsToVoltageString(results.get(Constant.AVERAGE_VOLTAGE)));
				Double deviation = Constant.round(results.get(Constant.STANDARD_DEVIATION_VOLTAGE), 4);
				standardDeviationVoltageBLabel.setText(deviation.toString());
			} else if (channelIndex == Constant.MATH_INDEX) {
				maxVoltageMathLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_VOLTAGE)));
				minVoltageMathLabel.setText(convertVoltsToVoltageString(results.get(Constant.MIN_VOLTAGE)));
				maxP2pVoltageMathLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_P2P_VOLTAGE)));
				averageVoltageMathLabel.setText(convertVoltsToVoltageString(results.get(Constant.AVERAGE_VOLTAGE)));
				Double deviation = Constant.round(results.get(Constant.STANDARD_DEVIATION_VOLTAGE), 4);
				standardDeviationVoltageMathLabel.setText(deviation.toString());
			} else if (channelIndex == Constant.FILTER_INDEX) {
				maxVoltageFilterLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_VOLTAGE)));
				minVoltageFilterLabel.setText(convertVoltsToVoltageString(results.get(Constant.MIN_VOLTAGE)));
				maxP2pVoltageFilterLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_P2P_VOLTAGE)));
				averageVoltageFilterLabel.setText(convertVoltsToVoltageString(results.get(Constant.AVERAGE_VOLTAGE)));
				Double deviation = Constant.round(results.get(Constant.STANDARD_DEVIATION_VOLTAGE), 4);
				standardDeviationVoltageFilterLabel.setText(deviation.toString());
			}
		} else {
			hideMeasurementResults(channelIndex);
		}
	}

	/**
	 * Hide measurement results of the specified channel
	 * @param channelIndex The index of specified channel
	 */
	private void hideMeasurementResults(int channelIndex) {
		if(channelIndex == Constant.A_INDEX) {
			maxVoltageALabel.setText("N/A");
			minVoltageALabel.setText("N/A");
			maxP2pVoltageALabel.setText("N/A");
			averageVoltageALabel.setText("N/A");
			standardDeviationVoltageALabel.setText("N/A");
		} else if(channelIndex == Constant.B_INDEX) {
			maxVoltageBLabel.setText("N/A");
			minVoltageBLabel.setText("N/A");
			maxP2pVoltageBLabel.setText("N/A");
			averageVoltageBLabel.setText("N/A");
			standardDeviationVoltageBLabel.setText("N/A");
		} else if(channelIndex == Constant.MATH_INDEX) {
			maxVoltageMathLabel.setText("N/A");
			minVoltageMathLabel.setText("N/A");
			maxP2pVoltageMathLabel.setText("N/A");
			averageVoltageMathLabel.setText("N/A");
			standardDeviationVoltageMathLabel.setText("N/A");
		} else if(channelIndex == Constant.FILTER_INDEX) {
			maxVoltageFilterLabel.setText("N/A");
			minVoltageFilterLabel.setText("N/A");
			maxP2pVoltageFilterLabel.setText("N/A");
			averageVoltageFilterLabel.setText("N/A");
			standardDeviationVoltageFilterLabel.setText("N/A");
		}
	}

	@Override
	public void chartMouseClicked(ChartMouseEvent event) {
		// TODO
	}

	@Override
	public void chartMouseMoved(ChartMouseEvent event) {
		// TODO
        Rectangle2D dataArea = this.chartPanel_.getScreenDataArea();
        JFreeChart chart = event.getChart();
        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis xAxis = plot.getDomainAxis();
        double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea, RectangleEdge.BOTTOM);
        double y = DatasetUtilities.findYValue(plot.getDataset(measuredChannelIndex_), 0, x);
        if(! Double.isNaN(y)) {
        	this.timeCrosshair_.setValue(x);
        	this.voltageCrosshair_.setValue(y);
        	this.cursorVerticalValueLabel.setText(convertVoltsToVoltageString(y));
        }
	}

	public Socket getSocket() {
		return socket_;
	}

	public void setSocket(Socket socket_) {
		this.socket_ = socket_;
	}

	public PacketWriter getPacketWriter() {
		return packetWriter_;
	}

	public void setPacketWriter(PacketWriter packetWriter_) {
		this.packetWriter_ = packetWriter_;
	}

	public PacketReader getPacketReader() {
		return packetReader_;
	}

	public void setPacketReader(PacketReader packetReader_) {
		this.packetReader_ = packetReader_;
	}
}
