package gui;

import data.Constant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jfree.data.xy.XYSeries;

/**
 *
 * @author ToanHo
 */
public class MainWindowUi extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JLabel aDivisionInfoLabel;
    protected JLabel averageVoltageALabel;
    private JLabel averageVoltageALabel_;
    protected JLabel averageVoltageBLabel;
    private JLabel averageVoltageBLabel_;
    protected JLabel averageVoltageFilterLabel;
    private JLabel averageVoltageFilterLabel_;
    protected JLabel averageVoltageMathLabel;
    private JLabel averageVoltageMathLabel_;
    protected JLabel bDivisionInfoLabel;
    protected JButton browseButton;
    private JPanel canvasPanel_;
    protected JCheckBox channelACheckBox;
    private JPanel channelAPanel_;
    protected JCheckBox channelBCheckBox;
    private JPanel channelBPanel_;
    private JPanel channelCouplingPanel_;
    protected JToggleButton channelCouplingToggleButton;
    private JTabbedPane channelTabbedPane_;
    private JPanel channelVisibilityPanel_;
    protected JTextField csvFilePathTextField;
    protected JComboBox<String> cursorComboBox;
    private JLabel cursorLabel_;
    protected JLabel cursorVerticalValueLabel;
    private JPanel divisionInfoPanel_;
    protected JButton editExpressionButton;
    private JPanel expressionBottomPanel_;
    private JPanel expressionPannel_;
    protected JTextArea expressionTextArea;
    private JPanel expressionTopPanel_;
    protected JCheckBox filterChannelCheckBox;
    private JPanel filterChannelPanel_;
    protected JLabel filterDivisionInfoLabel;
    protected JButton forceTriggerButton;
    protected JLabel frequencyALabel;
    private JLabel frequencyALabel_;
    protected JLabel frequencyBLabel;
    private JLabel frequencyBLabel_;
    protected JLabel frequencyFilterLabel;
    private JLabel frequencyFilterLabel_;
    protected JLabel frequencyMathLabel;
    private JLabel frequencyMathLabel_;
    protected JCheckBox generatorCheckBox;
    private JPanel generatorControlBottomLeftPanel_;
    private JPanel generatorControlMiddlePanel_;
    private JPanel generatorControlPanel_;
    private JPanel generatorControlTopPanel_;
    protected JLabel generatorDivisionInfoLabel;
    private JPanel generatorPanel_;
    private JPanel horizontalALeftPanel_;
    private JPanel horizontalAPanel_;
    private JPanel horizontalARightPanel_;
    private JPanel horizontalBLeftPanel_;
    private JPanel horizontalBPanel_;
    private JPanel horizontalBRightPanel_;
    protected JLabel horizontalDivisionInfoLabel;
    private JPanel horizontalFilterLeftPanel_;
    private JPanel horizontalFilterPanel_;
    private JPanel horizontalFilterRightPanel_;
    private JPanel horizontalGeneratorLeftPanel_;
    private JPanel horizontalGeneratorPanel_;
    private JPanel horizontalGeneratorRightPanel_;
    private JPanel horizontalMathLeftPanel_;
    private JPanel horizontalMathPanel_;
    private JPanel horizontalMathRightPanel_;
    private JLabel horizontalOffsetALabel1_;
    private JLabel horizontalOffsetALabel2_;
    protected JSpinner horizontalOffsetASpinner;
    private JLabel horizontalOffsetBLabel1_;
    private JLabel horizontalOffsetBLabel2_;
    protected JSpinner horizontalOffsetBSpinner;
    private JLabel horizontalOffsetFilterLabel1_;
    private JLabel horizontalOffsetFilterLabel2_;
    protected JSpinner horizontalOffsetFilterSpinner;
    private JLabel horizontalOffsetGeneratorLabel1_;
    private JLabel horizontalOffsetGeneratorLabel2_;
    protected JSpinner horizontalOffsetGeneratorSpinner;
    private JLabel horizontalOffsetMathLabel1_;
    private JLabel horizontalOffsetMathLabel2_;
    protected JSpinner horizontalOffsetMathSpinner;
    protected JComboBox<String> horizontalOffsetUnitAComboBox;
    protected JComboBox<String> horizontalOffsetUnitBComboBox;
    protected JComboBox<String> horizontalOffsetUnitFilterComboBox;
    protected JComboBox<String> horizontalOffsetUnitGeneratorComboBox;
    protected JComboBox<String> horizontalOffsetUnitMathComboBox;
    protected JComboBox<String> horizontalRangeAComboBox;
    private JLabel horizontalRangeALabel1_;
    private JLabel horizontalRangeALabel2_;
    protected JComboBox<String> horizontalRangeBComboBox;
    private JLabel horizontalRangeBLabel1_;
    private JLabel horizontalRangeBLabel2_;
    protected JComboBox<String> horizontalRangeFilterComboBox;
    private JLabel horizontalRangeFilterLabel1_;
    private JLabel horizontalRangeFilterLabel2_;
    protected JComboBox<String> horizontalRangeGeneratorComboBox;
    private JLabel horizontalRangeGeneratorLabel1_;
    private JLabel horizontalRangeGeneratorLabel2_;
    protected JComboBox<String> horizontalRangeMathComboBox;
    private JLabel horizontalRangeMathLabel1_;
    private JLabel horizontalRangeMathLabel2_;
    private JPanel inputBottomPanel_;
    protected JComboBox<String> inputChannelComboBox;
    private JPanel inputPanel_;
    private JPanel inputTopPanel_;
    private JLabel jLabel4;
    private JPanel leftPanel_;
    protected JCheckBox mathChannelCheckBox;
    private JPanel mathChannelPanel_;
    protected JLabel mathDivisionInfoLabel;
    protected JLabel maxP2pVoltageALabel;
    private JLabel maxP2pVoltageALabel_;
    protected JLabel maxP2pVoltageBLabel;
    private JLabel maxP2pVoltageBLabel_;
    protected JLabel maxP2pVoltageFilterLabel;
    private JLabel maxP2pVoltageFilterLabel_;
    protected JLabel maxP2pVoltageMathLabel;
    private JLabel maxP2pVoltageMathLabel_;
    protected JLabel maxVoltageALabel;
    private JLabel maxVoltageALabel_;
    protected JLabel maxVoltageBLabel;
    private JLabel maxVoltageBLabel_;
    protected JLabel maxVoltageFilterLabel;
    private JLabel maxVoltageFilterLabel_;
    protected JLabel maxVoltageMathLabel;
    private JLabel maxVoltageMathLabel_;
    private JPanel measurementABottomLeftPanel_;
    private JPanel measurementABottomRightPanel_;
    private JPanel measurementAMiddleLeftPanel_;
    private JPanel measurementAMiddleRightPanel_;
    private JPanel measurementATopLeftPanel_;
    private JPanel measurementATopRightPanel_;
    private JPanel measurementBBottomLeftPanel_;
    private JPanel measurementBBottomRightPanel_;
    private JPanel measurementBMiddleLeftPanel_;
    private JPanel measurementBMiddleRightPanel_;
    private JPanel measurementBTopLeftPanel_;
    private JPanel measurementBTopRightPanel_;
    private JPanel measurementFilterBottomLeftPanel_;
    private JPanel measurementFilterBottomRightPanel_;
    private JPanel measurementFilterMiddleLeftPanel_;
    private JPanel measurementFilterMiddleRightPanel_;
    private JPanel measurementFilterTopLeftPanel_;
    private JPanel measurementFilterTopRightPanel_;
    private JPanel measurementMathBottomLeftPanel_;
    private JPanel measurementMathBottomRightPanel_;
    private JPanel measurementMathMiddleLeftPanel_;
    private JPanel measurementMathMiddleRightPanel_;
    private JPanel measurementMathTopLeftPanel_;
    private JPanel measurementMathTopRightPanel_;
    private JPanel measurementsAPanel_;
    private JPanel measurementsBPanel_;
    private JPanel measurementsFilterPanel_;
    private JPanel measurementsMathPanel_;
    protected JLabel minVoltageALabel;
    private JLabel minVoltageALabel_;
    protected JLabel minVoltageBLabel;
    private JLabel minVoltageBLabel_;
    protected JLabel minVoltageFilterLabel;
    private JLabel minVoltageFilterLabel_;
    protected JLabel minVoltageMathLabel;
    private JLabel minVoltageMathLabel_;
    protected JButton newExpressionButton;
    private JLabel noOfSamplesLabel_;
    protected JSpinner noOfSamplesSpinner;
    private JLabel outputLabel_;
    protected JToggleButton outputToggleButton;
    private JLabel p2pVoltageLabel2_;
    private JLabel p2pVoltageLabel_;
    protected JTextField p2pVoltageTextField;
    protected JComboBox<String> p2pVoltageUnitComboBox;
    protected JButton rearmTriggerButton;
    protected JButton removeExpressionButton;
    protected JButton removeFilterButton;
    private JPanel rightPanel_;
    private JScrollPane scrollPane;
    private JLabel spaceLabel_;
    protected JLabel standardDeviationVoltageALabel;
    private JLabel standardDeviationVoltageALabel_;
    protected JLabel standardDeviationVoltageBLabel;
    private JLabel standardDeviationVoltageBLabel_;
    protected JLabel standardDeviationVoltageFilterLabel;
    private JLabel standardDeviationVoltageFilterLabel_;
    protected JLabel standardDeviationVoltageMathLabel;
    private JLabel standardDeviationVoltageMathLabel_;
    private JToolBar toolBar_;
    private JPanel triggerBottomLeftPanel3_;
    private JPanel triggerBottomRightPanel3_;
    private JPanel triggerMiddleLeftPanel1_;
    private JPanel triggerMiddleLeftPanel2_;
    private JPanel triggerMiddleRightPanel1_;
    private JPanel triggerMiddleRightPanel2_;
    protected JComboBox<String> triggerModeComboBox;
    private JLabel triggerModeLabel_;
    private JPanel triggerPanel_;
    protected JLabel triggerStateLabel;
    private JLabel triggerStateLabel_;
    private JLabel triggerThresholdLabel_;
    protected JSpinner triggerThresholdSpinner;
    private JPanel triggerTopPanel_;
    protected JComboBox<String> triggerTypeComboBox;
    private JLabel triggerTypeLabel_;
    private JPanel verticalALeftPanel_;
    private JPanel verticalAPannel_;
    private JPanel verticalARightPanel_;
    private JPanel verticalBLeftPanel_;
    private JPanel verticalBPanel_;
    private JPanel verticalBRightPanel_;
    private JPanel verticalFilterLeftPanel_;
    private JPanel verticalFilterPanel_;
    private JPanel verticalFilterRightPanel_;
    private JPanel verticalGeneratorLeftPanel_;
    private JPanel verticalGeneratorPannel_;
    private JPanel verticalGeneratorRightPanel_;
    private JPanel verticalMathLeftPanel_;
    private JPanel verticalMathPanel_;
    private JPanel verticalMathRightPanel_;
    private JLabel verticalOffsetALabel1_;
    private JLabel verticalOffsetALabel2_;
    protected JSpinner verticalOffsetASpinner;
    private JLabel verticalOffsetBLabel1_;
    private JLabel verticalOffsetBLabel2_;
    protected JSpinner verticalOffsetBSpinner;
    private JLabel verticalOffsetFilterLabel1_;
    private JLabel verticalOffsetFilterLabel2_;
    protected JSpinner verticalOffsetFilterSpinner;
    private JLabel verticalOffsetGeneratorLabel1_;
    private JLabel verticalOffsetGeneratorLabel2_;
    protected JSpinner verticalOffsetGeneratorSpinner;
    private JLabel verticalOffsetMathLabel1_;
    private JLabel verticalOffsetMathLabel2_;
    protected JSpinner verticalOffsetMathSpinner;
    protected JComboBox<String> verticalOffsetUnitAComboBox;
    protected JComboBox<String> verticalOffsetUnitBComboBox;
    protected JComboBox<String> verticalOffsetUnitFilterComboBox;
    protected JComboBox<String> verticalOffsetUnitGeneratorComboBox;
    protected JComboBox<String> verticalOffsetUnitMathComboBox;
    protected JComboBox<String> verticalRangeAComboBox;
    private JLabel verticalRangeALabel1_;
    private JLabel verticalRangeALabel2_;
    protected JComboBox<String> verticalRangeBComboBox;
    private JLabel verticalRangeBLabel1_;
    private JLabel verticalRangeBLabel2_;
    protected JComboBox<String> verticalRangeFilterComboBox;
    private JLabel verticalRangeFilterLabel1_;
    private JLabel verticalRangeFilterLabel2_;
    protected JComboBox<String> verticalRangeGeneratorComboBox;
    private JLabel verticalRangeGeneratorLabel1_;
    private JLabel verticalRangeGeneratorLabel2_;
    protected JComboBox<String> verticalRangeMathComboBox;
    private JLabel verticalRangeMathLabel1_;
    private JLabel verticalRangeMathLabel2_;
    protected JComboBox<String> waveTypeComboBox;
    private JLabel waveTypeLabel_;

    protected Map<String, XYSeries> rawXYSeries;

    /**
     * Creates new form MainWindowUI
     */
    public MainWindowUi() {
        rawXYSeries = new HashMap<String, XYSeries>();
        initComponents();
        Constant.setApplicationIcon(this);
        validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

        leftPanel_ = new JPanel();
        channelCouplingPanel_ = new JPanel();
        channelCouplingToggleButton = new JToggleButton();
        channelVisibilityPanel_ = new JPanel();
        channelACheckBox = new JCheckBox();
        channelBCheckBox = new JCheckBox();
        mathChannelCheckBox = new JCheckBox();
        filterChannelCheckBox = new JCheckBox();
        generatorCheckBox = new JCheckBox();
        channelTabbedPane_ = new JTabbedPane();
        channelAPanel_ = new JPanel();
        horizontalAPanel_ = new JPanel();
        horizontalALeftPanel_ = new JPanel();
        horizontalRangeALabel1_ = new JLabel();
        horizontalRangeAComboBox = new JComboBox<String>();
        horizontalRangeALabel2_ = new JLabel();
        horizontalARightPanel_ = new JPanel();
        horizontalOffsetALabel1_ = new JLabel();
        horizontalOffsetASpinner = new JSpinner();
        horizontalOffsetALabel2_ = new JLabel();
        horizontalOffsetUnitAComboBox = new JComboBox<String>();
        verticalAPannel_ = new JPanel();
        verticalALeftPanel_ = new JPanel();
        verticalRangeALabel1_ = new JLabel();
        verticalRangeAComboBox = new JComboBox<String>();
        verticalRangeALabel2_ = new JLabel();
        verticalARightPanel_ = new JPanel();
        verticalOffsetALabel1_ = new JLabel();
        verticalOffsetASpinner = new JSpinner();
        verticalOffsetALabel2_ = new JLabel();
        verticalOffsetUnitAComboBox = new JComboBox<String>();
        measurementsAPanel_ = new JPanel();
        measurementATopLeftPanel_ = new JPanel();
        maxVoltageALabel_ = new JLabel();
        maxVoltageALabel = new JLabel();
        measurementATopRightPanel_ = new JPanel();
        minVoltageALabel_ = new JLabel();
        minVoltageALabel = new JLabel();
        measurementAMiddleLeftPanel_ = new JPanel();
        maxP2pVoltageALabel_ = new JLabel();
        maxP2pVoltageALabel = new JLabel();
        measurementAMiddleRightPanel_ = new JPanel();
        averageVoltageALabel_ = new JLabel();
        averageVoltageALabel = new JLabel();
        measurementABottomLeftPanel_ = new JPanel();
        standardDeviationVoltageALabel_ = new JLabel();
        standardDeviationVoltageALabel = new JLabel();
        measurementABottomRightPanel_ = new JPanel();
        frequencyALabel_ = new JLabel();
        frequencyALabel = new JLabel();
        channelBPanel_ = new JPanel();
        horizontalBPanel_ = new JPanel();
        horizontalBLeftPanel_ = new JPanel();
        horizontalRangeBLabel1_ = new JLabel();
        horizontalRangeBComboBox = new JComboBox<String>();
        horizontalRangeBLabel2_ = new JLabel();
        horizontalBRightPanel_ = new JPanel();
        horizontalOffsetBLabel1_ = new JLabel();
        horizontalOffsetBSpinner = new JSpinner();
        horizontalOffsetBLabel2_ = new JLabel();
        horizontalOffsetUnitBComboBox = new JComboBox<String>();
        verticalBPanel_ = new JPanel();
        verticalBLeftPanel_ = new JPanel();
        verticalRangeBLabel1_ = new JLabel();
        verticalRangeBComboBox = new JComboBox<String>();
        verticalRangeBLabel2_ = new JLabel();
        verticalBRightPanel_ = new JPanel();
        verticalOffsetBLabel1_ = new JLabel();
        verticalOffsetBSpinner = new JSpinner();
        verticalOffsetBLabel2_ = new JLabel();
        verticalOffsetUnitBComboBox = new JComboBox<String>();
        measurementsBPanel_ = new JPanel();
        measurementBTopLeftPanel_ = new JPanel();
        maxVoltageBLabel_ = new JLabel();
        maxVoltageBLabel = new JLabel();
        measurementBTopRightPanel_ = new JPanel();
        minVoltageBLabel_ = new JLabel();
        minVoltageBLabel = new JLabel();
        measurementBMiddleLeftPanel_ = new JPanel();
        maxP2pVoltageBLabel_ = new JLabel();
        maxP2pVoltageBLabel = new JLabel();
        measurementBMiddleRightPanel_ = new JPanel();
        averageVoltageBLabel_ = new JLabel();
        averageVoltageBLabel = new JLabel();
        measurementBBottomLeftPanel_ = new JPanel();
        standardDeviationVoltageBLabel_ = new JLabel();
        standardDeviationVoltageBLabel = new JLabel();
        measurementBBottomRightPanel_ = new JPanel();
        frequencyBLabel_ = new JLabel();
        frequencyBLabel = new JLabel();
        mathChannelPanel_ = new JPanel();
        expressionPannel_ = new JPanel();
        expressionTopPanel_ = new JPanel();
        scrollPane = new JScrollPane();
        expressionTextArea = new JTextArea();
        expressionBottomPanel_ = new JPanel();
        newExpressionButton = new JButton();
        editExpressionButton = new JButton();
        removeExpressionButton = new JButton();
        horizontalMathPanel_ = new JPanel();
        horizontalMathLeftPanel_ = new JPanel();
        horizontalRangeMathLabel1_ = new JLabel();
        horizontalRangeMathComboBox = new JComboBox<String>();
        horizontalRangeMathLabel2_ = new JLabel();
        horizontalMathRightPanel_ = new JPanel();
        horizontalOffsetMathLabel1_ = new JLabel();
        horizontalOffsetMathSpinner = new JSpinner();
        horizontalOffsetMathLabel2_ = new JLabel();
        horizontalOffsetUnitMathComboBox = new JComboBox<String>();
        verticalMathPanel_ = new JPanel();
        verticalMathLeftPanel_ = new JPanel();
        verticalRangeMathLabel1_ = new JLabel();
        verticalRangeMathComboBox = new JComboBox<String>();
        verticalRangeMathLabel2_ = new JLabel();
        verticalMathRightPanel_ = new JPanel();
        verticalOffsetMathLabel1_ = new JLabel();
        verticalOffsetMathSpinner = new JSpinner();
        verticalOffsetMathLabel2_ = new JLabel();
        verticalOffsetUnitMathComboBox = new JComboBox<String>();
        measurementsMathPanel_ = new JPanel();
        measurementMathTopLeftPanel_ = new JPanel();
        maxVoltageMathLabel_ = new JLabel();
        maxVoltageMathLabel = new JLabel();
        measurementMathTopRightPanel_ = new JPanel();
        minVoltageMathLabel_ = new JLabel();
        minVoltageMathLabel = new JLabel();
        measurementMathMiddleLeftPanel_ = new JPanel();
        maxP2pVoltageMathLabel_ = new JLabel();
        maxP2pVoltageMathLabel = new JLabel();
        measurementMathMiddleRightPanel_ = new JPanel();
        averageVoltageMathLabel_ = new JLabel();
        averageVoltageMathLabel = new JLabel();
        measurementMathBottomLeftPanel_ = new JPanel();
        standardDeviationVoltageMathLabel_ = new JLabel();
        standardDeviationVoltageMathLabel = new JLabel();
        measurementMathBottomRightPanel_ = new JPanel();
        frequencyMathLabel_ = new JLabel();
        frequencyMathLabel = new JLabel();
        filterChannelPanel_ = new JPanel();
        inputPanel_ = new JPanel();
        inputTopPanel_ = new JPanel();
        jLabel4 = new JLabel();
        inputChannelComboBox = new JComboBox<String>();
        removeFilterButton = new JButton();
        inputBottomPanel_ = new JPanel();
        csvFilePathTextField = new JTextField();
        browseButton = new JButton();
        horizontalFilterPanel_ = new JPanel();
        horizontalFilterLeftPanel_ = new JPanel();
        horizontalRangeFilterLabel1_ = new JLabel();
        horizontalRangeFilterComboBox = new JComboBox<String>();
        horizontalRangeFilterLabel2_ = new JLabel();
        horizontalFilterRightPanel_ = new JPanel();
        horizontalOffsetFilterLabel1_ = new JLabel();
        horizontalOffsetFilterSpinner = new JSpinner();
        horizontalOffsetFilterLabel2_ = new JLabel();
        horizontalOffsetUnitFilterComboBox = new JComboBox<String>();
        verticalFilterPanel_ = new JPanel();
        verticalFilterLeftPanel_ = new JPanel();
        verticalRangeFilterLabel1_ = new JLabel();
        verticalRangeFilterComboBox = new JComboBox<String>();
        verticalRangeFilterLabel2_ = new JLabel();
        verticalFilterRightPanel_ = new JPanel();
        verticalOffsetFilterLabel1_ = new JLabel();
        verticalOffsetFilterSpinner = new JSpinner();
        verticalOffsetFilterLabel2_ = new JLabel();
        verticalOffsetUnitFilterComboBox = new JComboBox<String>();
        measurementsFilterPanel_ = new JPanel();
        measurementFilterTopLeftPanel_ = new JPanel();
        maxVoltageFilterLabel_ = new JLabel();
        maxVoltageFilterLabel = new JLabel();
        measurementFilterTopRightPanel_ = new JPanel();
        minVoltageFilterLabel_ = new JLabel();
        minVoltageFilterLabel = new JLabel();
        measurementFilterMiddleLeftPanel_ = new JPanel();
        maxP2pVoltageFilterLabel_ = new JLabel();
        maxP2pVoltageFilterLabel = new JLabel();
        measurementFilterMiddleRightPanel_ = new JPanel();
        averageVoltageFilterLabel_ = new JLabel();
        averageVoltageFilterLabel = new JLabel();
        measurementFilterBottomLeftPanel_ = new JPanel();
        standardDeviationVoltageFilterLabel_ = new JLabel();
        standardDeviationVoltageFilterLabel = new JLabel();
        measurementFilterBottomRightPanel_ = new JPanel();
        frequencyFilterLabel_ = new JLabel();
        frequencyFilterLabel = new JLabel();
        generatorPanel_ = new JPanel();
        generatorControlPanel_ = new JPanel();
        generatorControlTopPanel_ = new JPanel();
        outputLabel_ = new JLabel();
        outputToggleButton = new JToggleButton();
        generatorControlMiddlePanel_ = new JPanel();
        waveTypeLabel_ = new JLabel();
        waveTypeComboBox = new JComboBox<String>();
        generatorControlBottomLeftPanel_ = new JPanel();
        p2pVoltageLabel_ = new JLabel();
        p2pVoltageTextField = new JTextField();
        p2pVoltageLabel2_ = new JLabel();
        p2pVoltageUnitComboBox = new JComboBox<String>();
        horizontalGeneratorPanel_ = new JPanel();
        horizontalGeneratorLeftPanel_ = new JPanel();
        horizontalRangeGeneratorLabel1_ = new JLabel();
        horizontalRangeGeneratorComboBox = new JComboBox<String>();
        horizontalRangeGeneratorLabel2_ = new JLabel();
        horizontalGeneratorRightPanel_ = new JPanel();
        horizontalOffsetGeneratorLabel1_ = new JLabel();
        horizontalOffsetGeneratorSpinner = new JSpinner();
        horizontalOffsetGeneratorLabel2_ = new JLabel();
        horizontalOffsetUnitGeneratorComboBox = new JComboBox<String>();
        verticalGeneratorPannel_ = new JPanel();
        verticalGeneratorLeftPanel_ = new JPanel();
        verticalRangeGeneratorLabel1_ = new JLabel();
        verticalRangeGeneratorComboBox = new JComboBox<String>();
        verticalRangeGeneratorLabel2_ = new JLabel();
        verticalGeneratorRightPanel_ = new JPanel();
        verticalOffsetGeneratorLabel1_ = new JLabel();
        verticalOffsetGeneratorSpinner = new JSpinner();
        verticalOffsetGeneratorLabel2_ = new JLabel();
        verticalOffsetUnitGeneratorComboBox = new JComboBox<String>();
        triggerPanel_ = new JPanel();
        triggerTopPanel_ = new JPanel();
        triggerStateLabel_ = new JLabel();
        triggerStateLabel = new JLabel();
        triggerMiddleLeftPanel1_ = new JPanel();
        triggerModeLabel_ = new JLabel();
        triggerModeComboBox = new JComboBox<String>();
        triggerMiddleRightPanel1_ = new JPanel();
        forceTriggerButton = new JButton();
        triggerMiddleLeftPanel2_ = new JPanel();
        triggerTypeLabel_ = new JLabel();
        triggerTypeComboBox = new JComboBox<String>();
        triggerMiddleRightPanel2_ = new JPanel();
        rearmTriggerButton = new JButton();
        triggerBottomLeftPanel3_ = new JPanel();
        noOfSamplesLabel_ = new JLabel();
        noOfSamplesSpinner = new JSpinner();
        triggerBottomRightPanel3_ = new JPanel();
        triggerThresholdLabel_ = new JLabel();
        triggerThresholdSpinner = new JSpinner();
        rightPanel_ = new JPanel();
        divisionInfoPanel_ = new JPanel();
        aDivisionInfoLabel = new JLabel();
        bDivisionInfoLabel = new JLabel();
        mathDivisionInfoLabel = new JLabel();
        filterDivisionInfoLabel = new JLabel();
        generatorDivisionInfoLabel = new JLabel();
        horizontalDivisionInfoLabel = new JLabel();
        canvasPanel_ = new JPanel();
        toolBar_ = new JToolBar();
        cursorLabel_ = new JLabel();
        cursorComboBox = new JComboBox<String>();
        spaceLabel_ = new JLabel();
        cursorVerticalValueLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Constant.APPLICATION_TITLE);
        setMinimumSize(new Dimension(1165, 735));

        leftPanel_.setBorder(BorderFactory.createTitledBorder(""));
        leftPanel_.setPreferredSize(new Dimension(400, 650));
        leftPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 3));

        channelCouplingPanel_.setBorder(BorderFactory.createTitledBorder("Channel Coupling"));
        channelCouplingPanel_.setPreferredSize(new Dimension(396, 50));
        channelCouplingPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));

        channelCouplingToggleButton.setText("AC");
        channelCouplingToggleButton.setPreferredSize(new Dimension(80, 23));
        channelCouplingPanel_.add(channelCouplingToggleButton);

        leftPanel_.add(channelCouplingPanel_);

        channelVisibilityPanel_.setBorder(BorderFactory.createTitledBorder("Channel Visibility"));
        channelVisibilityPanel_.setPreferredSize(new Dimension(396, 45));
        channelVisibilityPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 0));

        channelACheckBox.setText("A");
        channelACheckBox.setFocusable(false);
        channelACheckBox.setPreferredSize(new Dimension(55, 20));
        channelVisibilityPanel_.add(channelACheckBox);

        channelBCheckBox.setText("B");
        channelBCheckBox.setFocusable(false);
        channelBCheckBox.setPreferredSize(new Dimension(55, 20));
        channelVisibilityPanel_.add(channelBCheckBox);

        mathChannelCheckBox.setText("Math");
        mathChannelCheckBox.setFocusable(false);
        mathChannelCheckBox.setPreferredSize(new Dimension(70, 20));
        channelVisibilityPanel_.add(mathChannelCheckBox);

        filterChannelCheckBox.setText("Filter");
        filterChannelCheckBox.setFocusable(false);
        filterChannelCheckBox.setPreferredSize(new Dimension(70, 20));
        channelVisibilityPanel_.add(filterChannelCheckBox);

        generatorCheckBox.setText("Generator");
        generatorCheckBox.setFocusable(false);
        generatorCheckBox.setPreferredSize(new Dimension(90, 20));
        channelVisibilityPanel_.add(generatorCheckBox);

        leftPanel_.add(channelVisibilityPanel_);

        channelTabbedPane_.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        channelTabbedPane_.setPreferredSize(new Dimension(396, 360));

        horizontalAPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalAPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalAPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalALeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalALeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeALabel1_.setText("Range:");
        horizontalALeftPanel_.add(horizontalRangeALabel1_);

        horizontalRangeAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeAComboBox.setEnabled(false);
        horizontalRangeAComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalALeftPanel_.add(horizontalRangeAComboBox);

        horizontalRangeALabel2_.setText("/div");
        horizontalALeftPanel_.add(horizontalRangeALabel2_);

        horizontalAPanel_.add(horizontalALeftPanel_);

        horizontalARightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalARightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetALabel1_.setText("Offset:");
        horizontalARightPanel_.add(horizontalOffsetALabel1_);

        horizontalOffsetASpinner.setEnabled(false);
        horizontalOffsetASpinner.setPreferredSize(new Dimension(55, 20));
        horizontalARightPanel_.add(horizontalOffsetASpinner);

        horizontalOffsetALabel2_.setText("x");
        horizontalARightPanel_.add(horizontalOffsetALabel2_);

        horizontalOffsetUnitAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
        horizontalOffsetUnitAComboBox.setEnabled(false);
        horizontalOffsetUnitAComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalARightPanel_.add(horizontalOffsetUnitAComboBox);

        horizontalAPanel_.add(horizontalARightPanel_);

        channelAPanel_.add(horizontalAPanel_);

        verticalAPannel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalAPannel_.setPreferredSize(new Dimension(390, 55));
        verticalAPannel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalALeftPanel_.setPreferredSize(new Dimension(170, 30));
        verticalALeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeALabel1_.setText("Range:");
        verticalALeftPanel_.add(verticalRangeALabel1_);

        verticalRangeAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeAComboBox.setEnabled(false);
        verticalRangeAComboBox.setPreferredSize(new Dimension(75, 20));
        verticalALeftPanel_.add(verticalRangeAComboBox);

        verticalRangeALabel2_.setText("/div");
        verticalALeftPanel_.add(verticalRangeALabel2_);

        verticalAPannel_.add(verticalALeftPanel_);

        verticalARightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalARightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetALabel1_.setText("Offset:");
        verticalARightPanel_.add(verticalOffsetALabel1_);

        verticalOffsetASpinner.setEnabled(false);
        verticalOffsetASpinner.setPreferredSize(new Dimension(55, 20));
        verticalARightPanel_.add(verticalOffsetASpinner);

        verticalOffsetALabel2_.setText("x");
        verticalARightPanel_.add(verticalOffsetALabel2_);

        verticalOffsetUnitAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_OFFSET_UNITS));
        verticalOffsetUnitAComboBox.setEnabled(false);
        verticalOffsetUnitAComboBox.setPreferredSize(new Dimension(75, 20));
        verticalARightPanel_.add(verticalOffsetUnitAComboBox);

        verticalAPannel_.add(verticalARightPanel_);

        channelAPanel_.add(verticalAPannel_);

        measurementsAPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsAPanel_.setPreferredSize(new Dimension(390, 105));
        measurementsAPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementATopLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementATopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageALabel_.setText("Max Voltage:");
        measurementATopLeftPanel_.add(maxVoltageALabel_);

        maxVoltageALabel.setText("00.00");
        maxVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageALabel.setPreferredSize(new Dimension(60, 19));
        measurementATopLeftPanel_.add(maxVoltageALabel);

        measurementsAPanel_.add(measurementATopLeftPanel_);

        measurementATopRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementATopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        minVoltageALabel_.setText("Min Voltage:");
        measurementATopRightPanel_.add(minVoltageALabel_);

        minVoltageALabel.setText("00.00");
        minVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageALabel.setPreferredSize(new Dimension(60, 19));
        measurementATopRightPanel_.add(minVoltageALabel);

        measurementsAPanel_.add(measurementATopRightPanel_);

        measurementAMiddleLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementAMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageALabel_.setText("Max P2P Voltage:");
        measurementAMiddleLeftPanel_.add(maxP2pVoltageALabel_);

        maxP2pVoltageALabel.setText("00.00");
        maxP2pVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageALabel.setPreferredSize(new Dimension(60, 19));
        measurementAMiddleLeftPanel_.add(maxP2pVoltageALabel);

        measurementsAPanel_.add(measurementAMiddleLeftPanel_);

        measurementAMiddleRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementAMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        averageVoltageALabel_.setText("Average Voltage:");
        measurementAMiddleRightPanel_.add(averageVoltageALabel_);

        averageVoltageALabel.setText("00.00");
        averageVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageALabel.setPreferredSize(new Dimension(60, 19));
        measurementAMiddleRightPanel_.add(averageVoltageALabel);

        measurementsAPanel_.add(measurementAMiddleRightPanel_);

        measurementABottomLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementABottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageALabel_.setText("SD of Voltage:");
        measurementABottomLeftPanel_.add(standardDeviationVoltageALabel_);

        standardDeviationVoltageALabel.setText("00.00");
        standardDeviationVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageALabel.setPreferredSize(new Dimension(60, 19));
        measurementABottomLeftPanel_.add(standardDeviationVoltageALabel);

        measurementsAPanel_.add(measurementABottomLeftPanel_);

        measurementABottomRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementABottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        frequencyALabel_.setText("Frequency:");
        measurementABottomRightPanel_.add(frequencyALabel_);

        frequencyALabel.setText("00.00");
        frequencyALabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyALabel.setPreferredSize(new Dimension(60, 19));
        measurementABottomRightPanel_.add(frequencyALabel);

        measurementsAPanel_.add(measurementABottomRightPanel_);

        channelAPanel_.add(measurementsAPanel_);

        channelTabbedPane_.addTab("Channel A", channelAPanel_);

        horizontalBPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalBPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalBPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalBLeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalBLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeBLabel1_.setText("Range:");
        horizontalBLeftPanel_.add(horizontalRangeBLabel1_);

        horizontalRangeBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeBComboBox.setEnabled(false);
        horizontalRangeBComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalBLeftPanel_.add(horizontalRangeBComboBox);

        horizontalRangeBLabel2_.setText("/div");
        horizontalBLeftPanel_.add(horizontalRangeBLabel2_);

        horizontalBPanel_.add(horizontalBLeftPanel_);

        horizontalBRightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalBRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetBLabel1_.setText("Offset:");
        horizontalBRightPanel_.add(horizontalOffsetBLabel1_);

        horizontalOffsetBSpinner.setEnabled(false);
        horizontalOffsetBSpinner.setPreferredSize(new Dimension(55, 20));
        horizontalBRightPanel_.add(horizontalOffsetBSpinner);

        horizontalOffsetBLabel2_.setText("x");
        horizontalBRightPanel_.add(horizontalOffsetBLabel2_);

        horizontalOffsetUnitBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
        horizontalOffsetUnitBComboBox.setEnabled(false);
        horizontalOffsetUnitBComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalBRightPanel_.add(horizontalOffsetUnitBComboBox);

        horizontalBPanel_.add(horizontalBRightPanel_);

        channelBPanel_.add(horizontalBPanel_);

        verticalBPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalBPanel_.setPreferredSize(new Dimension(390, 55));
        verticalBPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalBLeftPanel_.setPreferredSize(new Dimension(170, 30));
        verticalBLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeBLabel1_.setText("Range:");
        verticalBLeftPanel_.add(verticalRangeBLabel1_);

        verticalRangeBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeBComboBox.setEnabled(false);
        verticalRangeBComboBox.setPreferredSize(new Dimension(75, 20));
        verticalBLeftPanel_.add(verticalRangeBComboBox);

        verticalRangeBLabel2_.setText("/div");
        verticalBLeftPanel_.add(verticalRangeBLabel2_);

        verticalBPanel_.add(verticalBLeftPanel_);

        verticalBRightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalBRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetBLabel1_.setText("Offset:");
        verticalBRightPanel_.add(verticalOffsetBLabel1_);

        verticalOffsetBSpinner.setEnabled(false);
        verticalOffsetBSpinner.setPreferredSize(new Dimension(55, 20));
        verticalBRightPanel_.add(verticalOffsetBSpinner);

        verticalOffsetBLabel2_.setText("x");
        verticalBRightPanel_.add(verticalOffsetBLabel2_);

        verticalOffsetUnitBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_OFFSET_UNITS));
        verticalOffsetUnitBComboBox.setEnabled(false);
        verticalOffsetUnitBComboBox.setPreferredSize(new Dimension(75, 20));
        verticalBRightPanel_.add(verticalOffsetUnitBComboBox);

        verticalBPanel_.add(verticalBRightPanel_);

        channelBPanel_.add(verticalBPanel_);

        measurementsBPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsBPanel_.setPreferredSize(new Dimension(390, 105));
        measurementsBPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementBTopLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementBTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageBLabel_.setText("Max Voltage:");
        measurementBTopLeftPanel_.add(maxVoltageBLabel_);

        maxVoltageBLabel.setText("00.00");
        maxVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageBLabel.setPreferredSize(new Dimension(60, 19));
        measurementBTopLeftPanel_.add(maxVoltageBLabel);

        measurementsBPanel_.add(measurementBTopLeftPanel_);

        measurementBTopRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementBTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        minVoltageBLabel_.setText("Min Voltage:");
        measurementBTopRightPanel_.add(minVoltageBLabel_);

        minVoltageBLabel.setText("00.00");
        minVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageBLabel.setPreferredSize(new Dimension(60, 19));
        measurementBTopRightPanel_.add(minVoltageBLabel);

        measurementsBPanel_.add(measurementBTopRightPanel_);

        measurementBMiddleLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementBMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageBLabel_.setText("Max P2P Voltage:");
        measurementBMiddleLeftPanel_.add(maxP2pVoltageBLabel_);

        maxP2pVoltageBLabel.setText("00.00");
        maxP2pVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageBLabel.setPreferredSize(new Dimension(60, 19));
        measurementBMiddleLeftPanel_.add(maxP2pVoltageBLabel);

        measurementsBPanel_.add(measurementBMiddleLeftPanel_);

        measurementBMiddleRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementBMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        averageVoltageBLabel_.setText("Average Voltage:");
        measurementBMiddleRightPanel_.add(averageVoltageBLabel_);

        averageVoltageBLabel.setText("00.00");
        averageVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageBLabel.setPreferredSize(new Dimension(60, 19));
        measurementBMiddleRightPanel_.add(averageVoltageBLabel);

        measurementsBPanel_.add(measurementBMiddleRightPanel_);

        measurementBBottomLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementBBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageBLabel_.setText("SD of Voltage:");
        measurementBBottomLeftPanel_.add(standardDeviationVoltageBLabel_);

        standardDeviationVoltageBLabel.setText("00.00");
        standardDeviationVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageBLabel.setPreferredSize(new Dimension(60, 19));
        measurementBBottomLeftPanel_.add(standardDeviationVoltageBLabel);

        measurementsBPanel_.add(measurementBBottomLeftPanel_);

        measurementBBottomRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementBBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        frequencyBLabel_.setText("Frequency:");
        measurementBBottomRightPanel_.add(frequencyBLabel_);

        frequencyBLabel.setText("00.00");
        frequencyBLabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyBLabel.setPreferredSize(new Dimension(60, 19));
        measurementBBottomRightPanel_.add(frequencyBLabel);

        measurementsBPanel_.add(measurementBBottomRightPanel_);

        channelBPanel_.add(measurementsBPanel_);

        channelTabbedPane_.addTab("Channel B", channelBPanel_);

        expressionPannel_.setBorder(BorderFactory.createTitledBorder("Expression"));
        expressionPannel_.setPreferredSize(new Dimension(390, 95));
        expressionPannel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        expressionTopPanel_.setPreferredSize(new Dimension(380, 40));
        expressionTopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(350, 35));

        expressionTextArea.setEditable(false);
        expressionTextArea.setColumns(20);
        expressionTextArea.setRows(5);
        scrollPane.setViewportView(expressionTextArea);

        expressionTopPanel_.add(scrollPane);

        expressionPannel_.add(expressionTopPanel_);

        expressionBottomPanel_.setPreferredSize(new Dimension(350, 30));
        expressionBottomPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 4));

        newExpressionButton.setText("New");
        newExpressionButton.setEnabled(false);
        newExpressionButton.setPreferredSize(new Dimension(85, 23));
        expressionBottomPanel_.add(newExpressionButton);

        editExpressionButton.setText("Edit");
        editExpressionButton.setEnabled(false);
        editExpressionButton.setPreferredSize(new Dimension(85, 23));
        expressionBottomPanel_.add(editExpressionButton);

        removeExpressionButton.setText("Remove");
        removeExpressionButton.setEnabled(false);
        removeExpressionButton.setPreferredSize(new Dimension(85, 23));
        expressionBottomPanel_.add(removeExpressionButton);

        expressionPannel_.add(expressionBottomPanel_);

        mathChannelPanel_.add(expressionPannel_);

        horizontalMathPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalMathPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalMathPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalMathLeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalMathLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeMathLabel1_.setText("Range:");
        horizontalMathLeftPanel_.add(horizontalRangeMathLabel1_);

        horizontalRangeMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeMathComboBox.setEnabled(false);
        horizontalRangeMathComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalMathLeftPanel_.add(horizontalRangeMathComboBox);

        horizontalRangeMathLabel2_.setText("/div");
        horizontalMathLeftPanel_.add(horizontalRangeMathLabel2_);

        horizontalMathPanel_.add(horizontalMathLeftPanel_);

        horizontalMathRightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalMathRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetMathLabel1_.setText("Offset:");
        horizontalMathRightPanel_.add(horizontalOffsetMathLabel1_);

        horizontalOffsetMathSpinner.setEnabled(false);
        horizontalOffsetMathSpinner.setPreferredSize(new Dimension(55, 20));
        horizontalMathRightPanel_.add(horizontalOffsetMathSpinner);

        horizontalOffsetMathLabel2_.setText("x");
        horizontalMathRightPanel_.add(horizontalOffsetMathLabel2_);

        horizontalOffsetUnitMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
        horizontalOffsetUnitMathComboBox.setEnabled(false);
        horizontalOffsetUnitMathComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalMathRightPanel_.add(horizontalOffsetUnitMathComboBox);

        horizontalMathPanel_.add(horizontalMathRightPanel_);

        mathChannelPanel_.add(horizontalMathPanel_);

        verticalMathPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalMathPanel_.setPreferredSize(new Dimension(390, 55));
        verticalMathPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalMathLeftPanel_.setPreferredSize(new Dimension(170, 30));
        verticalMathLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeMathLabel1_.setText("Range:");
        verticalMathLeftPanel_.add(verticalRangeMathLabel1_);

        verticalRangeMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeMathComboBox.setEnabled(false);
        verticalRangeMathComboBox.setPreferredSize(new Dimension(75, 20));
        verticalMathLeftPanel_.add(verticalRangeMathComboBox);

        verticalRangeMathLabel2_.setText("/div");
        verticalMathLeftPanel_.add(verticalRangeMathLabel2_);

        verticalMathPanel_.add(verticalMathLeftPanel_);

        verticalMathRightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalMathRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetMathLabel1_.setText("Offset:");
        verticalMathRightPanel_.add(verticalOffsetMathLabel1_);

        verticalOffsetMathSpinner.setEnabled(false);
        verticalOffsetMathSpinner.setPreferredSize(new Dimension(55, 20));
        verticalMathRightPanel_.add(verticalOffsetMathSpinner);

        verticalOffsetMathLabel2_.setText("x");
        verticalMathRightPanel_.add(verticalOffsetMathLabel2_);

        verticalOffsetUnitMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_OFFSET_UNITS));
        verticalOffsetUnitMathComboBox.setEnabled(false);
        verticalOffsetUnitMathComboBox.setPreferredSize(new Dimension(75, 20));
        verticalMathRightPanel_.add(verticalOffsetUnitMathComboBox);

        verticalMathPanel_.add(verticalMathRightPanel_);

        mathChannelPanel_.add(verticalMathPanel_);

        measurementsMathPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsMathPanel_.setPreferredSize(new Dimension(390, 105));
        measurementsMathPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementMathTopLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementMathTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageMathLabel_.setText("Max Voltage:");
        measurementMathTopLeftPanel_.add(maxVoltageMathLabel_);

        maxVoltageMathLabel.setText("00.00");
        maxVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageMathLabel.setPreferredSize(new Dimension(60, 19));
        measurementMathTopLeftPanel_.add(maxVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathTopLeftPanel_);

        measurementMathTopRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementMathTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        minVoltageMathLabel_.setText("Min Voltage:");
        measurementMathTopRightPanel_.add(minVoltageMathLabel_);

        minVoltageMathLabel.setText("00.00");
        minVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageMathLabel.setPreferredSize(new Dimension(60, 19));
        measurementMathTopRightPanel_.add(minVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathTopRightPanel_);

        measurementMathMiddleLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementMathMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageMathLabel_.setText("Max P2P Voltage:");
        measurementMathMiddleLeftPanel_.add(maxP2pVoltageMathLabel_);

        maxP2pVoltageMathLabel.setText("00.00");
        maxP2pVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageMathLabel.setPreferredSize(new Dimension(60, 19));
        measurementMathMiddleLeftPanel_.add(maxP2pVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathMiddleLeftPanel_);

        measurementMathMiddleRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementMathMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        averageVoltageMathLabel_.setText("Average Voltage:");
        measurementMathMiddleRightPanel_.add(averageVoltageMathLabel_);

        averageVoltageMathLabel.setText("00.00");
        averageVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageMathLabel.setPreferredSize(new Dimension(60, 19));
        measurementMathMiddleRightPanel_.add(averageVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathMiddleRightPanel_);

        measurementMathBottomLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementMathBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageMathLabel_.setText("SD of Voltage:");
        measurementMathBottomLeftPanel_.add(standardDeviationVoltageMathLabel_);

        standardDeviationVoltageMathLabel.setText("00.00");
        standardDeviationVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageMathLabel.setPreferredSize(new Dimension(60, 19));
        measurementMathBottomLeftPanel_.add(standardDeviationVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathBottomLeftPanel_);

        measurementMathBottomRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementMathBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        frequencyMathLabel_.setText("Frequency:");
        measurementMathBottomRightPanel_.add(frequencyMathLabel_);

        frequencyMathLabel.setText("00.00");
        frequencyMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyMathLabel.setPreferredSize(new Dimension(60, 19));
        measurementMathBottomRightPanel_.add(frequencyMathLabel);

        measurementsMathPanel_.add(measurementMathBottomRightPanel_);

        mathChannelPanel_.add(measurementsMathPanel_);

        channelTabbedPane_.addTab("Math Channel", mathChannelPanel_);

        inputPanel_.setBorder(BorderFactory.createTitledBorder("Input"));
        inputPanel_.setPreferredSize(new Dimension(390, 95));
        inputPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        inputTopPanel_.setPreferredSize(new Dimension(380, 35));
        inputTopPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        jLabel4.setText("Input:");
        inputTopPanel_.add(jLabel4);

        inputChannelComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Select channel" }));
        inputChannelComboBox.setEnabled(false);
        inputChannelComboBox.setPreferredSize(new Dimension(230, 20));
        inputTopPanel_.add(inputChannelComboBox);

        removeFilterButton.setText("Remove");
        removeFilterButton.setEnabled(false);
        removeFilterButton.setPreferredSize(new Dimension(85, 23));
        inputTopPanel_.add(removeFilterButton);

        inputPanel_.add(inputTopPanel_);

        inputBottomPanel_.setPreferredSize(new Dimension(380, 35));
        inputBottomPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        csvFilePathTextField.setEditable(false);
        csvFilePathTextField.setText("Choose CSV file");
        csvFilePathTextField.setToolTipText("");
        csvFilePathTextField.setPreferredSize(new Dimension(270, 23));
        inputBottomPanel_.add(csvFilePathTextField);

        browseButton.setText("Browse");
        browseButton.setEnabled(false);
        browseButton.setPreferredSize(new Dimension(85, 23));
        inputBottomPanel_.add(browseButton);

        inputPanel_.add(inputBottomPanel_);

        filterChannelPanel_.add(inputPanel_);

        horizontalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalFilterPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalFilterPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalFilterLeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalFilterLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeFilterLabel1_.setText("Range:");
        horizontalFilterLeftPanel_.add(horizontalRangeFilterLabel1_);

        horizontalRangeFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeFilterComboBox.setEnabled(false);
        horizontalRangeFilterComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalFilterLeftPanel_.add(horizontalRangeFilterComboBox);

        horizontalRangeFilterLabel2_.setText("/div");
        horizontalFilterLeftPanel_.add(horizontalRangeFilterLabel2_);

        horizontalFilterPanel_.add(horizontalFilterLeftPanel_);

        horizontalFilterRightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalFilterRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetFilterLabel1_.setText("Offset:");
        horizontalFilterRightPanel_.add(horizontalOffsetFilterLabel1_);

        horizontalOffsetFilterSpinner.setEnabled(false);
        horizontalOffsetFilterSpinner.setPreferredSize(new Dimension(55, 20));
        horizontalFilterRightPanel_.add(horizontalOffsetFilterSpinner);

        horizontalOffsetFilterLabel2_.setText("x");
        horizontalFilterRightPanel_.add(horizontalOffsetFilterLabel2_);

        horizontalOffsetUnitFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
        horizontalOffsetUnitFilterComboBox.setEnabled(false);
        horizontalOffsetUnitFilterComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalFilterRightPanel_.add(horizontalOffsetUnitFilterComboBox);

        horizontalFilterPanel_.add(horizontalFilterRightPanel_);

        filterChannelPanel_.add(horizontalFilterPanel_);

        verticalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalFilterPanel_.setPreferredSize(new Dimension(390, 55));
        verticalFilterPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalFilterLeftPanel_.setPreferredSize(new Dimension(170, 30));
        verticalFilterLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeFilterLabel1_.setText("Range:");
        verticalFilterLeftPanel_.add(verticalRangeFilterLabel1_);

        verticalRangeFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeFilterComboBox.setEnabled(false);
        verticalRangeFilterComboBox.setPreferredSize(new Dimension(75, 20));
        verticalFilterLeftPanel_.add(verticalRangeFilterComboBox);

        verticalRangeFilterLabel2_.setText("/div");
        verticalFilterLeftPanel_.add(verticalRangeFilterLabel2_);

        verticalFilterPanel_.add(verticalFilterLeftPanel_);

        verticalFilterRightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalFilterRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetFilterLabel1_.setText("Offset:");
        verticalFilterRightPanel_.add(verticalOffsetFilterLabel1_);

        verticalOffsetFilterSpinner.setEnabled(false);
        verticalOffsetFilterSpinner.setPreferredSize(new Dimension(55, 20));
        verticalFilterRightPanel_.add(verticalOffsetFilterSpinner);

        verticalOffsetFilterLabel2_.setText("x");
        verticalFilterRightPanel_.add(verticalOffsetFilterLabel2_);

        verticalOffsetUnitFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_OFFSET_UNITS));
        verticalOffsetUnitFilterComboBox.setEnabled(false);
        verticalOffsetUnitFilterComboBox.setPreferredSize(new Dimension(75, 20));
        verticalFilterRightPanel_.add(verticalOffsetUnitFilterComboBox);

        verticalFilterPanel_.add(verticalFilterRightPanel_);

        filterChannelPanel_.add(verticalFilterPanel_);

        measurementsFilterPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsFilterPanel_.setPreferredSize(new Dimension(390, 105));
        measurementsFilterPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementFilterTopLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementFilterTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageFilterLabel_.setText("Max Voltage:");
        measurementFilterTopLeftPanel_.add(maxVoltageFilterLabel_);

        maxVoltageFilterLabel.setText("00.00");
        maxVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageFilterLabel.setPreferredSize(new Dimension(60, 19));
        measurementFilterTopLeftPanel_.add(maxVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterTopLeftPanel_);

        measurementFilterTopRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementFilterTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        minVoltageFilterLabel_.setText("Min Voltage:");
        measurementFilterTopRightPanel_.add(minVoltageFilterLabel_);

        minVoltageFilterLabel.setText("00.00");
        minVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageFilterLabel.setPreferredSize(new Dimension(60, 19));
        measurementFilterTopRightPanel_.add(minVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterTopRightPanel_);

        measurementFilterMiddleLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementFilterMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageFilterLabel_.setText("Max P2P Voltage:");
        measurementFilterMiddleLeftPanel_.add(maxP2pVoltageFilterLabel_);

        maxP2pVoltageFilterLabel.setText("00.00");
        maxP2pVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageFilterLabel.setPreferredSize(new Dimension(60, 19));
        measurementFilterMiddleLeftPanel_.add(maxP2pVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterMiddleLeftPanel_);

        measurementFilterMiddleRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementFilterMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        averageVoltageFilterLabel_.setText("Average Voltage:");
        measurementFilterMiddleRightPanel_.add(averageVoltageFilterLabel_);

        averageVoltageFilterLabel.setText("00.00");
        averageVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageFilterLabel.setPreferredSize(new Dimension(60, 19));
        measurementFilterMiddleRightPanel_.add(averageVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterMiddleRightPanel_);

        measurementFilterBottomLeftPanel_.setPreferredSize(new Dimension(190, 25));
        measurementFilterBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageFilterLabel_.setText("SD of Voltage:");
        measurementFilterBottomLeftPanel_.add(standardDeviationVoltageFilterLabel_);

        standardDeviationVoltageFilterLabel.setText("00.00");
        standardDeviationVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageFilterLabel.setPreferredSize(new Dimension(60, 19));
        measurementFilterBottomLeftPanel_.add(standardDeviationVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterBottomLeftPanel_);

        measurementFilterBottomRightPanel_.setPreferredSize(new Dimension(185, 25));
        measurementFilterBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        frequencyFilterLabel_.setText("Frequency:");
        measurementFilterBottomRightPanel_.add(frequencyFilterLabel_);

        frequencyFilterLabel.setText("00.00");
        frequencyFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyFilterLabel.setPreferredSize(new Dimension(60, 19));
        measurementFilterBottomRightPanel_.add(frequencyFilterLabel);

        measurementsFilterPanel_.add(measurementFilterBottomRightPanel_);

        filterChannelPanel_.add(measurementsFilterPanel_);

        channelTabbedPane_.addTab("Filter Channel", filterChannelPanel_);

        generatorControlPanel_.setBorder(BorderFactory.createTitledBorder("Function Generator"));
        generatorControlPanel_.setPreferredSize(new Dimension(390, 115));
        generatorControlPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        generatorControlTopPanel_.setPreferredSize(new Dimension(380, 30));
        generatorControlTopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        outputLabel_.setText("Output");
        generatorControlTopPanel_.add(outputLabel_);

        outputToggleButton.setText("On");
        outputToggleButton.setPreferredSize(new Dimension(80, 23));
        generatorControlTopPanel_.add(outputToggleButton);

        generatorControlPanel_.add(generatorControlTopPanel_);

        generatorControlMiddlePanel_.setPreferredSize(new Dimension(380, 30));
        generatorControlMiddlePanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        waveTypeLabel_.setText("Wave Type:");
        generatorControlMiddlePanel_.add(waveTypeLabel_);

        waveTypeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.WAVE_TYPES));
        waveTypeComboBox.setPreferredSize(new Dimension(265, 20));
        generatorControlMiddlePanel_.add(waveTypeComboBox);

        generatorControlPanel_.add(generatorControlMiddlePanel_);

        generatorControlBottomLeftPanel_.setPreferredSize(new Dimension(260, 30));
        generatorControlBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        p2pVoltageLabel_.setText("P2P Voltage:");
        generatorControlBottomLeftPanel_.add(p2pVoltageLabel_);

        p2pVoltageTextField.setPreferredSize(new Dimension(70, 20));
        generatorControlBottomLeftPanel_.add(p2pVoltageTextField);

        p2pVoltageLabel2_.setText("x");
        generatorControlBottomLeftPanel_.add(p2pVoltageLabel2_);

        p2pVoltageUnitComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VOLTAGE_UNITS));
        p2pVoltageUnitComboBox.setEnabled(false);
        p2pVoltageUnitComboBox.setPreferredSize(new Dimension(75, 20));
        generatorControlBottomLeftPanel_.add(p2pVoltageUnitComboBox);

        generatorControlPanel_.add(generatorControlBottomLeftPanel_);

        generatorPanel_.add(generatorControlPanel_);

        horizontalGeneratorPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalGeneratorPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalGeneratorPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalGeneratorLeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalGeneratorLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeGeneratorLabel1_.setText("Range:");
        horizontalGeneratorLeftPanel_.add(horizontalRangeGeneratorLabel1_);

        horizontalRangeGeneratorComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeGeneratorComboBox.setEnabled(false);
        horizontalRangeGeneratorComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalGeneratorLeftPanel_.add(horizontalRangeGeneratorComboBox);

        horizontalRangeGeneratorLabel2_.setText("/div");
        horizontalGeneratorLeftPanel_.add(horizontalRangeGeneratorLabel2_);

        horizontalGeneratorPanel_.add(horizontalGeneratorLeftPanel_);

        horizontalGeneratorRightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalGeneratorRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetGeneratorLabel1_.setText("Offset:");
        horizontalGeneratorRightPanel_.add(horizontalOffsetGeneratorLabel1_);

        horizontalOffsetGeneratorSpinner.setEnabled(false);
        horizontalOffsetGeneratorSpinner.setPreferredSize(new Dimension(55, 20));
        horizontalGeneratorRightPanel_.add(horizontalOffsetGeneratorSpinner);

        horizontalOffsetGeneratorLabel2_.setText("x");
        horizontalGeneratorRightPanel_.add(horizontalOffsetGeneratorLabel2_);

        horizontalOffsetUnitGeneratorComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
        horizontalOffsetUnitGeneratorComboBox.setEnabled(false);
        horizontalOffsetUnitGeneratorComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalGeneratorRightPanel_.add(horizontalOffsetUnitGeneratorComboBox);

        horizontalGeneratorPanel_.add(horizontalGeneratorRightPanel_);

        generatorPanel_.add(horizontalGeneratorPanel_);

        verticalGeneratorPannel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalGeneratorPannel_.setPreferredSize(new Dimension(390, 55));
        verticalGeneratorPannel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalGeneratorLeftPanel_.setPreferredSize(new Dimension(170, 30));
        verticalGeneratorLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeGeneratorLabel1_.setText("Range:");
        verticalGeneratorLeftPanel_.add(verticalRangeGeneratorLabel1_);

        verticalRangeGeneratorComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeGeneratorComboBox.setEnabled(false);
        verticalRangeGeneratorComboBox.setPreferredSize(new Dimension(75, 20));
        verticalGeneratorLeftPanel_.add(verticalRangeGeneratorComboBox);

        verticalRangeGeneratorLabel2_.setText("/div");
        verticalGeneratorLeftPanel_.add(verticalRangeGeneratorLabel2_);

        verticalGeneratorPannel_.add(verticalGeneratorLeftPanel_);

        verticalGeneratorRightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalGeneratorRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetGeneratorLabel1_.setText("Offset:");
        verticalGeneratorRightPanel_.add(verticalOffsetGeneratorLabel1_);

        verticalOffsetGeneratorSpinner.setEnabled(false);
        verticalOffsetGeneratorSpinner.setPreferredSize(new Dimension(55, 20));
        verticalGeneratorRightPanel_.add(verticalOffsetGeneratorSpinner);

        verticalOffsetGeneratorLabel2_.setText("x");
        verticalGeneratorRightPanel_.add(verticalOffsetGeneratorLabel2_);

        verticalOffsetUnitGeneratorComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_OFFSET_UNITS));
        verticalOffsetUnitGeneratorComboBox.setEnabled(false);
        verticalOffsetUnitGeneratorComboBox.setPreferredSize(new Dimension(75, 20));
        verticalGeneratorRightPanel_.add(verticalOffsetUnitGeneratorComboBox);

        verticalGeneratorPannel_.add(verticalGeneratorRightPanel_);

        generatorPanel_.add(verticalGeneratorPannel_);

        channelTabbedPane_.addTab("Generator", generatorPanel_);

        leftPanel_.add(channelTabbedPane_);

        triggerPanel_.setBorder(BorderFactory.createTitledBorder("Trigger"));
        triggerPanel_.setPreferredSize(new Dimension(396, 140));
        triggerPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        triggerTopPanel_.setPreferredSize(new Dimension(350, 25));
        triggerTopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        triggerStateLabel_.setText("State:");
        triggerTopPanel_.add(triggerStateLabel_);

        triggerStateLabel.setForeground(Color.blue);
        triggerStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        triggerStateLabel.setText("Stop");
        triggerStateLabel.setBorder(BorderFactory.createTitledBorder(""));
        triggerStateLabel.setPreferredSize(new Dimension(80, 19));
        triggerTopPanel_.add(triggerStateLabel);

        triggerPanel_.add(triggerTopPanel_);

        triggerMiddleLeftPanel1_.setPreferredSize(new Dimension(225, 30));
        triggerMiddleLeftPanel1_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        triggerModeLabel_.setText("Mode:");
        triggerModeLabel_.setPreferredSize(new Dimension(40, 14));
        triggerMiddleLeftPanel1_.add(triggerModeLabel_);

        triggerModeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.TRIGGER_MODES));
        triggerModeComboBox.setPreferredSize(new Dimension(135, 20));
        triggerMiddleLeftPanel1_.add(triggerModeComboBox);

        triggerPanel_.add(triggerMiddleLeftPanel1_);

        triggerMiddleRightPanel1_.setPreferredSize(new Dimension(155, 30));
        triggerMiddleRightPanel1_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));

        forceTriggerButton.setText("Force Trigger");
        forceTriggerButton.setPreferredSize(new Dimension(130, 23));
        triggerMiddleRightPanel1_.add(forceTriggerButton);

        triggerPanel_.add(triggerMiddleRightPanel1_);

        triggerMiddleLeftPanel2_.setPreferredSize(new Dimension(225, 30));
        triggerMiddleLeftPanel2_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        triggerTypeLabel_.setText("Type:");
        triggerTypeLabel_.setPreferredSize(new Dimension(40, 14));
        triggerMiddleLeftPanel2_.add(triggerTypeLabel_);

        triggerTypeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.TRIGGER_TYPES));
        triggerTypeComboBox.setPreferredSize(new Dimension(135, 20));
        triggerMiddleLeftPanel2_.add(triggerTypeComboBox);

        triggerPanel_.add(triggerMiddleLeftPanel2_);

        triggerMiddleRightPanel2_.setPreferredSize(new Dimension(155, 30));
        triggerMiddleRightPanel2_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));

        rearmTriggerButton.setText("Re-arm Trigger");
        rearmTriggerButton.setPreferredSize(new Dimension(130, 23));
        triggerMiddleRightPanel2_.add(rearmTriggerButton);

        triggerPanel_.add(triggerMiddleRightPanel2_);

        triggerBottomLeftPanel3_.setPreferredSize(new Dimension(225, 30));
        triggerBottomLeftPanel3_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        noOfSamplesLabel_.setText("No. of Samples:");
        noOfSamplesLabel_.setPreferredSize(new Dimension(105, 14));
        triggerBottomLeftPanel3_.add(noOfSamplesLabel_);

        noOfSamplesSpinner.setPreferredSize(new Dimension(70, 20));
        triggerBottomLeftPanel3_.add(noOfSamplesSpinner);

        triggerPanel_.add(triggerBottomLeftPanel3_);

        triggerBottomRightPanel3_.setPreferredSize(new Dimension(155, 30));

        triggerThresholdLabel_.setText("Threshold:");
        triggerBottomRightPanel3_.add(triggerThresholdLabel_);

        triggerThresholdSpinner.setPreferredSize(new Dimension(65, 20));
        triggerBottomRightPanel3_.add(triggerThresholdSpinner);

        triggerPanel_.add(triggerBottomRightPanel3_);

        leftPanel_.add(triggerPanel_);

        getContentPane().add(leftPanel_, BorderLayout.WEST);

        rightPanel_.setBorder(BorderFactory.createTitledBorder(""));
        rightPanel_.setMinimumSize(new Dimension(780, 650));
        rightPanel_.setPreferredSize(new Dimension(780, 650));
        rightPanel_.setLayout(new BorderLayout());

        divisionInfoPanel_.setPreferredSize(new Dimension(776, 30));
        divisionInfoPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        aDivisionInfoLabel.setForeground(Constant.A_COLOR);
        aDivisionInfoLabel.setText("A: 20 mV/div");
        aDivisionInfoLabel.setBorder(BorderFactory.createTitledBorder(""));
        aDivisionInfoLabel.setEnabled(false);
        divisionInfoPanel_.add(aDivisionInfoLabel);

        bDivisionInfoLabel.setForeground(Constant.B_COLOR);
        bDivisionInfoLabel.setText("B: 20 mV/div");
        bDivisionInfoLabel.setBorder(BorderFactory.createTitledBorder(""));
        bDivisionInfoLabel.setEnabled(false);
        divisionInfoPanel_.add(bDivisionInfoLabel);

        mathDivisionInfoLabel.setForeground(Constant.MATH_COLOR);
        mathDivisionInfoLabel.setText("Math: 20 mV/div");
        mathDivisionInfoLabel.setBorder(BorderFactory.createTitledBorder(""));
        mathDivisionInfoLabel.setEnabled(false);
        divisionInfoPanel_.add(mathDivisionInfoLabel);

        filterDivisionInfoLabel.setForeground(Constant.FILTER_COLOR);
        filterDivisionInfoLabel.setText("Filter: 20 mV/div");
        filterDivisionInfoLabel.setBorder(BorderFactory.createTitledBorder(""));
        filterDivisionInfoLabel.setEnabled(false);
        divisionInfoPanel_.add(filterDivisionInfoLabel);

        generatorDivisionInfoLabel.setForeground(Constant.GENERATOR_COLOR);
        generatorDivisionInfoLabel.setText("Generator: 20 mV/div");
        generatorDivisionInfoLabel.setBorder(BorderFactory.createTitledBorder(""));
        generatorDivisionInfoLabel.setEnabled(false);
        divisionInfoPanel_.add(generatorDivisionInfoLabel);

        horizontalDivisionInfoLabel.setText("Horizontal: 1 us/div");
        horizontalDivisionInfoLabel.setBorder(BorderFactory.createTitledBorder(""));
        divisionInfoPanel_.add(horizontalDivisionInfoLabel);

        rightPanel_.add(divisionInfoPanel_, BorderLayout.SOUTH);

        canvasPanel_.setLayout(new BorderLayout());
        rightPanel_.add(canvasPanel_, BorderLayout.CENTER);

        toolBar_.setFloatable(false);
        toolBar_.setRollover(true);
        toolBar_.setPreferredSize(new Dimension(100, 40));

        cursorLabel_.setText(" Cursor on: ");
        toolBar_.add(cursorLabel_);

        cursorComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Disabled" }));
        cursorComboBox.setMaximumSize(new Dimension(120, 20));
        toolBar_.add(cursorComboBox);

        spaceLabel_.setText("  ");
        toolBar_.add(spaceLabel_);

        cursorVerticalValueLabel.setText("0 mV");
        cursorVerticalValueLabel.setBorder(BorderFactory.createTitledBorder(""));
        toolBar_.add(cursorVerticalValueLabel);

        rightPanel_.add(toolBar_, BorderLayout.NORTH);

        getContentPane().add(rightPanel_, BorderLayout.CENTER);

        setSize(new Dimension(1200, 735));
        setLocationRelativeTo(null);
    }

    /**
     * Show tab on channel tabbedpane
     *
     * @param tab Constant.TAB
     */
    protected void showTab(Constant.TAB tab) {
        switch (tab) {
            case CHANNEL_A:
                channelTabbedPane_.setSelectedComponent(channelAPanel_);
                break;
            case CHANNEL_B:
                channelTabbedPane_.setSelectedComponent(channelBPanel_);
                break;
            case MATH_CHANNEL:
                channelTabbedPane_.setSelectedComponent(mathChannelPanel_);
                break;
            case FILTER_CHANNEL:
                channelTabbedPane_.setSelectedComponent(filterChannelPanel_);
                break;
            case GENERATOR_CHANNEL:
                channelTabbedPane_.setSelectedComponent(generatorPanel_);
                break;
            default:
                channelTabbedPane_.setSelectedComponent(channelAPanel_);
        }
    }
    
    /**
     * Enable or disable all the components on the function generator channel panel
     *
     * @param enabled boolean
     */
    public void setEnabledGeneratorChannelControls(boolean enabled) {
        if (rawXYSeries.containsKey(Constant.GENERATOR_CHANNEL)) {
            horizontalOffsetGeneratorSpinner.setEnabled(enabled);
            horizontalOffsetUnitGeneratorComboBox.setEnabled(enabled);
            horizontalRangeGeneratorComboBox.setEnabled(enabled);
            verticalOffsetGeneratorSpinner.setEnabled(enabled);
            verticalOffsetUnitGeneratorComboBox.setEnabled(enabled);
            verticalRangeGeneratorComboBox.setEnabled(enabled);
            generatorDivisionInfoLabel.setEnabled(enabled);
        }
    }

    /**
     * Enable or disable all the components on the Math channel panel
     *
     * @param enabled boolean
     */
    public void setEnabledMathChannelControls(boolean enabled) {
        newExpressionButton.setEnabled(enabled);
        if (!expressionTextArea.getText().equals("")) {
            editExpressionButton.setEnabled(enabled);
        }
        if (rawXYSeries.containsKey(Constant.MATH_CHANNEL)) {
            horizontalOffsetMathSpinner.setEnabled(enabled);
            horizontalOffsetUnitMathComboBox.setEnabled(enabled);
            horizontalRangeMathComboBox.setEnabled(enabled);
            verticalOffsetMathSpinner.setEnabled(enabled);
            verticalOffsetUnitMathComboBox.setEnabled(enabled);
            verticalRangeMathComboBox.setEnabled(enabled);
            mathDivisionInfoLabel.setEnabled(enabled);
        }
    }

    /**
     * Enable or disable all the components on the Filter channel panel
     *
     * @param enabled boolean
     */
    public void setEnabledFilterChannelControls(boolean enabled) {
        inputChannelComboBox.setEnabled(enabled);
        browseButton.setEnabled(enabled);
        if (rawXYSeries.containsKey(Constant.FILTER_CHANNEL)) {
            horizontalOffsetFilterSpinner.setEnabled(enabled);
            horizontalOffsetUnitFilterComboBox.setEnabled(enabled);
            horizontalRangeFilterComboBox.setEnabled(enabled);
            verticalOffsetFilterSpinner.setEnabled(enabled);
            verticalOffsetUnitFilterComboBox.setEnabled(enabled);
            verticalRangeFilterComboBox.setEnabled(enabled);
            filterDivisionInfoLabel.setEnabled(enabled);
        }
    }

    /**
     * Enable or disable all the components on the channel A panel
     *
     * @param enabled boolean
     */
    public void setEnabledChannelAControls(boolean enabled) {
        if (rawXYSeries.containsKey(Constant.CHANNEL_A)) {
            horizontalOffsetASpinner.setEnabled(enabled);
            horizontalOffsetUnitAComboBox.setEnabled(enabled);
            horizontalRangeAComboBox.setEnabled(enabled);
            verticalOffsetASpinner.setEnabled(enabled);
            verticalOffsetUnitAComboBox.setEnabled(enabled);
            verticalRangeAComboBox.setEnabled(enabled);
            aDivisionInfoLabel.setEnabled(enabled);
        }
    }

    /**
     * Enable or disable all the components on the channel B panel
     *
     * @param enabled boolean
     */
    public void setEnabledChannelBControls(boolean enabled) {
        if (rawXYSeries.containsKey(Constant.CHANNEL_B)) {
            horizontalOffsetBSpinner.setEnabled(enabled);
            horizontalOffsetUnitBComboBox.setEnabled(enabled);
            horizontalRangeBComboBox.setEnabled(enabled);
            verticalOffsetBSpinner.setEnabled(enabled);
            verticalOffsetUnitBComboBox.setEnabled(enabled);
            verticalRangeBComboBox.setEnabled(enabled);
            bDivisionInfoLabel.setEnabled(enabled);
        }
    }
    
    /**
     * Enable or disable components on expression panel
     * @param enabled true or false
     */
    public void setEnabledExpressionControls(boolean enabled) {
        editExpressionButton.setEnabled(enabled);
        removeExpressionButton.setEnabled(enabled);
    }

    /**
     * Add component (e.g. ChartPanel) to canvas panel
     *
     * @param component Component
     */
    protected void addComponentToCanvasPanel(Component component) {
        canvasPanel_.removeAll();
        canvasPanel_.add(component, BorderLayout.CENTER);
        validate();
    }

    /**
     * Remove all components from the canvas panel
     */
    protected void removeComponentFromCanvasPanel() {
        canvasPanel_.removeAll();
        validate();
        repaint();
    }
}
