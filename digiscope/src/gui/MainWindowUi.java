package gui;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jfree.data.xy.XYSeries;

import data.Constant;

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
    private JPanel channelCouplingAPanel_;
    protected JToggleButton channelCouplingAToggleButton;
    private JPanel channelCouplingBPanel_;
    protected JToggleButton channelCouplingBToggleButton;
    protected JTabbedPane channelTabbedPane;
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
    private JPanel generatorControlFifthPanel_;
    private JPanel generatorControlFirstPanel_;
    private JPanel generatorControlFourthPanel_;
    private JPanel generatorControlPanel_;
    private JPanel generatorControlSecondPanel_;
    private JPanel generatorControlThirdPanel_;
    protected JSpinner generatorFrequencySpinner;
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
    private JLabel horizontalOffsetMathLabel1_;
    private JLabel horizontalOffsetMathLabel2_;
    protected JSpinner horizontalOffsetMathSpinner;
    protected JComboBox<String> horizontalOffsetUnitAComboBox;
    protected JComboBox<String> horizontalOffsetUnitBComboBox;
    protected JComboBox<String> horizontalOffsetUnitFilterComboBox;
    protected JComboBox<String> horizontalOffsetUnitMathComboBox;
    protected JComboBox<String> horizontalRangeComboBox;
    private JLabel horizontalRangeLabel1_;
    private JLabel horizontalRangeLabel2_;
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
    protected JToggleButton measureAToggleButton;
    protected JToggleButton measureBToggleButton;
    protected JToggleButton measureFilterToggleButton;
    protected JToggleButton measureMathToggleButton;
    private JPanel measurementABottomLeftPanel_;
    private JPanel measurementABottomRightPanel_;
    private JPanel measurementAMiddleLeftPanel_;
    private JPanel measurementAMiddleRightPanel_;
    private JPanel measurementATopLeftPanel_;
    private JPanel measurementATopPanel_;
    private JPanel measurementATopRightPanel_;
    private JPanel measurementBBottomLeftPanel_;
    private JPanel measurementBBottomRightPanel_;
    private JPanel measurementBMiddleLeftPanel_;
    private JPanel measurementBMiddleRightPanel_;
    private JPanel measurementBTopLeftPanel_;
    private JPanel measurementBTopPanel_;
    private JPanel measurementBTopRightPanel_;
    private JPanel measurementFilterBottomLeftPanel_;
    private JPanel measurementFilterBottomRightPanel_;
    private JPanel measurementFilterMiddleLeftPanel_;
    private JPanel measurementFilterMiddleRightPanel_;
    private JPanel measurementFilterTopLeftPanel_;
    private JPanel measurementFilterTopPanel_;
    private JPanel measurementFilterTopRightPanel_;
    private JPanel measurementMathBottomLeftPanel_;
    private JPanel measurementMathBottomRightPanel_;
    private JPanel measurementMathMiddleLeftPanel_;
    private JPanel measurementMathMiddleRightPanel_;
    private JPanel measurementMathTopLeftPanel_;
    private JPanel measurementMathTopPanel_;
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
    protected JToggleButton outputToggleButton;
    private JLabel p2pVoltageLabel2_;
    private JLabel p2pVoltageLabel_;
    protected JSpinner p2pVoltageSpinner;
    protected JComboBox<String> p2pVoltageUnitComboBox;
    protected JButton rearmTriggerButton;
    protected JButton removeExpressionButton;
    protected JButton removeFilterButton;
    private JPanel rightPanel_;
    private JLabel sampleRateLabel_;
    protected JLabel sampleRateValueLabel;
    protected JToggleButton samplingModeToggleButton;
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
    private JLabel thresholdLabel2_;
    private JPanel toolBarLeftPanel_;
    private JPanel toolBarRightPanel_;
    private JPanel toolBarPanel_;
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
    protected JComboBox<String> triggerThresholdUnitComboBox;
    private JPanel triggerTopPanel_;
    private JPanel triggerTopRightPanel1_;
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
    private JLabel verticalOffsetGeneratorLabel1_1;
    private JLabel verticalOffsetGeneratorLabel2_;
    private JLabel verticalOffsetGeneratorLabel2_1;
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
        channelVisibilityPanel_ = new JPanel();
        channelACheckBox = new JCheckBox();
        channelBCheckBox = new JCheckBox();
        mathChannelCheckBox = new JCheckBox();
        filterChannelCheckBox = new JCheckBox();
        channelTabbedPane = new JTabbedPane();
        channelAPanel_ = new JPanel();
        channelCouplingAPanel_ = new JPanel();
        channelCouplingAToggleButton = new JToggleButton();
        horizontalAPanel_ = new JPanel();
        horizontalALeftPanel_ = new JPanel();
        horizontalRangeLabel1_ = new JLabel();
        horizontalRangeComboBox = new JComboBox<String>();
        horizontalRangeLabel2_ = new JLabel();
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
        measurementATopPanel_ = new JPanel();
        measureAToggleButton = new JToggleButton();
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
        triggerPanel_ = new JPanel();
        triggerTopPanel_ = new JPanel();
        triggerStateLabel_ = new JLabel();
        triggerStateLabel = new JLabel();
        triggerTopRightPanel1_ = new JPanel();
        sampleRateLabel_ = new JLabel();
        sampleRateValueLabel = new JLabel();
        samplingModeToggleButton = new JToggleButton();
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
        thresholdLabel2_ = new JLabel();
        triggerThresholdUnitComboBox = new JComboBox<String>();
        channelBPanel_ = new JPanel();
        channelCouplingBPanel_ = new JPanel();
        channelCouplingBToggleButton = new JToggleButton();
        horizontalBPanel_ = new JPanel();
        horizontalBLeftPanel_ = new JPanel();
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
        measurementBTopPanel_ = new JPanel();
        measureBToggleButton = new JToggleButton();
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
        measurementMathTopPanel_ = new JPanel();
        measureMathToggleButton = new JToggleButton();
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
        measurementFilterTopPanel_ = new JPanel();
        measureFilterToggleButton = new JToggleButton();
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
        generatorControlFirstPanel_ = new JPanel();
        outputToggleButton = new JToggleButton();
        generatorControlSecondPanel_ = new JPanel();
        waveTypeLabel_ = new JLabel();
        waveTypeComboBox = new JComboBox<String>();
        generatorControlThirdPanel_ = new JPanel();
        p2pVoltageLabel_ = new JLabel();
        p2pVoltageSpinner = new JSpinner();
        p2pVoltageLabel2_ = new JLabel();
        p2pVoltageUnitComboBox = new JComboBox<String>();
        generatorControlFourthPanel_ = new JPanel();
        verticalOffsetGeneratorLabel1_ = new JLabel();
        verticalOffsetGeneratorSpinner = new JSpinner();
        verticalOffsetGeneratorLabel2_ = new JLabel();
        verticalOffsetUnitGeneratorComboBox = new JComboBox<String>();
        generatorControlFifthPanel_ = new JPanel();
        verticalOffsetGeneratorLabel1_1 = new JLabel();
        generatorFrequencySpinner = new JSpinner();
        verticalOffsetGeneratorLabel2_1 = new JLabel();
        rightPanel_ = new JPanel();
        divisionInfoPanel_ = new JPanel();
        aDivisionInfoLabel = new JLabel();
        bDivisionInfoLabel = new JLabel();
        mathDivisionInfoLabel = new JLabel();
        filterDivisionInfoLabel = new JLabel();
        horizontalDivisionInfoLabel = new JLabel();
        canvasPanel_ = new JPanel();
        toolBarLeftPanel_ = new JPanel();
        toolBarRightPanel_ = new JPanel();
        toolBarPanel_ = new JPanel();
        cursorLabel_ = new JLabel();
        cursorComboBox = new JComboBox<String>();
        spaceLabel_ = new JLabel();
        cursorVerticalValueLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Constant.APPLICATION_TITLE);
        setMinimumSize(new Dimension(1165, 735));

        leftPanel_.setBorder(BorderFactory.createTitledBorder(""));
        leftPanel_.setPreferredSize(new Dimension(400, 650));
        leftPanel_.setLayout(new BorderLayout());

        channelVisibilityPanel_.setBorder(BorderFactory.createTitledBorder("Channel Visibility"));
        channelVisibilityPanel_.setPreferredSize(new Dimension(396, 45));
        channelVisibilityPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));

        channelACheckBox.setText("A");
        channelACheckBox.setFocusable(false);
        channelACheckBox.setPreferredSize(new Dimension(75, 20));
        channelVisibilityPanel_.add(channelACheckBox);

        channelBCheckBox.setText("B");
        channelBCheckBox.setFocusable(false);
        channelBCheckBox.setPreferredSize(new Dimension(75, 20));
        channelVisibilityPanel_.add(channelBCheckBox);

        mathChannelCheckBox.setText("Math");
        mathChannelCheckBox.setFocusable(false);
        mathChannelCheckBox.setPreferredSize(new Dimension(90, 20));
        channelVisibilityPanel_.add(mathChannelCheckBox);

        filterChannelCheckBox.setText("Filter");
        filterChannelCheckBox.setFocusable(false);
        filterChannelCheckBox.setPreferredSize(new Dimension(85, 20));
        channelVisibilityPanel_.add(filterChannelCheckBox);

        leftPanel_.add(channelVisibilityPanel_, BorderLayout.NORTH);

        channelTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        channelTabbedPane.setPreferredSize(new Dimension(396, 520));

        channelCouplingAPanel_.setPreferredSize(new Dimension(390, 30));
        channelCouplingAPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

        channelCouplingAToggleButton.setText("AC");
        channelCouplingAToggleButton.setFocusable(false);
        channelCouplingAToggleButton.setPreferredSize(new Dimension(80, 23));
        channelCouplingAPanel_.add(channelCouplingAToggleButton);

        channelAPanel_.add(channelCouplingAPanel_);

        horizontalAPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalAPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalAPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalALeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalALeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeLabel1_.setText("Range:");
        horizontalALeftPanel_.add(horizontalRangeLabel1_);

        horizontalRangeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeComboBox.setPreferredSize(new Dimension(75, 20));
        horizontalALeftPanel_.add(horizontalRangeComboBox);

        horizontalRangeLabel2_.setText("/div");
        horizontalALeftPanel_.add(horizontalRangeLabel2_);

        horizontalAPanel_.add(horizontalALeftPanel_);

        horizontalARightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalARightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetALabel1_.setText("Offset:");
        horizontalARightPanel_.add(horizontalOffsetALabel1_);

        horizontalOffsetASpinner.setPreferredSize(new Dimension(60, 20));
        horizontalARightPanel_.add(horizontalOffsetASpinner);

        horizontalOffsetALabel2_.setText("x");
        horizontalARightPanel_.add(horizontalOffsetALabel2_);

        horizontalOffsetUnitAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
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
        verticalRangeAComboBox.setPreferredSize(new Dimension(75, 20));
        verticalALeftPanel_.add(verticalRangeAComboBox);

        verticalRangeALabel2_.setText("/div");
        verticalALeftPanel_.add(verticalRangeALabel2_);

        verticalAPannel_.add(verticalALeftPanel_);

        verticalARightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalARightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetALabel1_.setText("Offset:");
        verticalARightPanel_.add(verticalOffsetALabel1_);

        verticalOffsetASpinner.setPreferredSize(new Dimension(60, 20));
        verticalARightPanel_.add(verticalOffsetASpinner);

        verticalOffsetALabel2_.setText("x");
        verticalARightPanel_.add(verticalOffsetALabel2_);

        verticalOffsetUnitAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VOLTAGE_UNITS));
        verticalOffsetUnitAComboBox.setPreferredSize(new Dimension(75, 20));
        verticalARightPanel_.add(verticalOffsetUnitAComboBox);

        verticalAPannel_.add(verticalARightPanel_);

        channelAPanel_.add(verticalAPannel_);

        measurementsAPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsAPanel_.setPreferredSize(new Dimension(390, 215));
        measurementsAPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementATopPanel_.setPreferredSize(new Dimension(380, 30));
        measurementATopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        measureAToggleButton.setText("OFF");
        measureAToggleButton.setFocusable(false);
        measureAToggleButton.setPreferredSize(new Dimension(80, 23));
        measurementATopPanel_.add(measureAToggleButton);

        measurementsAPanel_.add(measurementATopPanel_);

        measurementATopLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementATopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageALabel_.setText("Max Voltage:");
        measurementATopLeftPanel_.add(maxVoltageALabel_);

        maxVoltageALabel.setText("N/A");
        maxVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementATopLeftPanel_.add(maxVoltageALabel);

        measurementsAPanel_.add(measurementATopLeftPanel_);

        measurementATopRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementATopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        minVoltageALabel_.setText("Min Voltage:");
        measurementATopRightPanel_.add(minVoltageALabel_);

        minVoltageALabel.setText("N/A");
        minVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementATopRightPanel_.add(minVoltageALabel);

        measurementsAPanel_.add(measurementATopRightPanel_);

        measurementAMiddleLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementAMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageALabel_.setText("Max P2P Voltage:");
        measurementAMiddleLeftPanel_.add(maxP2pVoltageALabel_);

        maxP2pVoltageALabel.setText("N/A");
        maxP2pVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementAMiddleLeftPanel_.add(maxP2pVoltageALabel);

        measurementsAPanel_.add(measurementAMiddleLeftPanel_);

        measurementAMiddleRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementAMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        averageVoltageALabel_.setText("Average Voltage:");
        measurementAMiddleRightPanel_.add(averageVoltageALabel_);

        averageVoltageALabel.setText("N/A");
        averageVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementAMiddleRightPanel_.add(averageVoltageALabel);

        measurementsAPanel_.add(measurementAMiddleRightPanel_);

        measurementABottomLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementABottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageALabel_.setText("SD of Voltage:");
        measurementABottomLeftPanel_.add(standardDeviationVoltageALabel_);

        standardDeviationVoltageALabel.setText("N/A");
        standardDeviationVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementABottomLeftPanel_.add(standardDeviationVoltageALabel);

        measurementsAPanel_.add(measurementABottomLeftPanel_);

        measurementABottomRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementABottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        frequencyALabel_.setText("Frequency:");
        measurementABottomRightPanel_.add(frequencyALabel_);

        frequencyALabel.setText("N/A");
        frequencyALabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementABottomRightPanel_.add(frequencyALabel);

        measurementsAPanel_.add(measurementABottomRightPanel_);

        channelAPanel_.add(measurementsAPanel_);

        triggerPanel_.setBorder(BorderFactory.createTitledBorder("Trigger"));
        triggerPanel_.setPreferredSize(new Dimension(390, 175));
        triggerPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        triggerTopPanel_.setPreferredSize(new Dimension(220, 30));
        triggerTopPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        triggerStateLabel_.setText("State:");
        triggerStateLabel_.setPreferredSize(new Dimension(40, 14));
        triggerTopPanel_.add(triggerStateLabel_);

        triggerStateLabel.setForeground(Color.blue);
        triggerStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        triggerStateLabel.setText("ARMED");
        triggerStateLabel.setBorder(BorderFactory.createTitledBorder(""));
        triggerStateLabel.setPreferredSize(new Dimension(135, 19));
        triggerTopPanel_.add(triggerStateLabel);

        triggerPanel_.add(triggerTopPanel_);

        triggerTopRightPanel1_.setPreferredSize(new Dimension(155, 30));
        triggerTopRightPanel1_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));

        samplingModeToggleButton.setText("12-bit");
        samplingModeToggleButton.setFocusable(false);
        samplingModeToggleButton.setPreferredSize(new Dimension(130, 23));
        triggerTopRightPanel1_.add(samplingModeToggleButton);

        triggerPanel_.add(triggerTopRightPanel1_);

        triggerMiddleLeftPanel1_.setPreferredSize(new Dimension(220, 30));
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
        forceTriggerButton.setFocusable(false);
        forceTriggerButton.setPreferredSize(new Dimension(130, 23));
        triggerMiddleRightPanel1_.add(forceTriggerButton);

        triggerPanel_.add(triggerMiddleRightPanel1_);

        triggerMiddleLeftPanel2_.setPreferredSize(new Dimension(220, 30));
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

        rearmTriggerButton.setText("Re-arm");
        rearmTriggerButton.setEnabled(false);
        rearmTriggerButton.setFocusable(false);
        rearmTriggerButton.setPreferredSize(new Dimension(130, 23));
        triggerMiddleRightPanel2_.add(rearmTriggerButton);

        triggerPanel_.add(triggerMiddleRightPanel2_);

        triggerBottomLeftPanel3_.setPreferredSize(new Dimension(350, 30));
        triggerBottomLeftPanel3_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        noOfSamplesLabel_.setText("No. of Samples:");
        triggerBottomLeftPanel3_.add(noOfSamplesLabel_);

        noOfSamplesSpinner.setPreferredSize(new Dimension(100, 20));
        noOfSamplesSpinner.setValue(Constant.DEFAULT_NUMBER_OF_SAMPLES);
        triggerBottomLeftPanel3_.add(noOfSamplesSpinner);

        triggerPanel_.add(triggerBottomLeftPanel3_);

        triggerBottomRightPanel3_.setPreferredSize(new Dimension(350, 30));
        triggerBottomRightPanel3_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        triggerThresholdLabel_.setText("Threshold:");
        triggerBottomRightPanel3_.add(triggerThresholdLabel_);

        triggerThresholdSpinner.setPreferredSize(new Dimension(75, 20));
        triggerBottomRightPanel3_.add(triggerThresholdSpinner);

        thresholdLabel2_.setText("x");
        triggerBottomRightPanel3_.add(thresholdLabel2_);

        triggerThresholdUnitComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VOLTAGE_UNITS));
        triggerThresholdUnitComboBox.setPreferredSize(new Dimension(75, 20));
        triggerBottomRightPanel3_.add(triggerThresholdUnitComboBox);

        triggerPanel_.add(triggerBottomRightPanel3_);

        channelAPanel_.add(triggerPanel_);

        channelTabbedPane.addTab("Channel A", channelAPanel_);

        channelCouplingBPanel_.setPreferredSize(new Dimension(390, 30));
        channelCouplingBPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

        channelCouplingBToggleButton.setText("AC");
        channelCouplingBToggleButton.setFocusable(false);
        channelCouplingBToggleButton.setPreferredSize(new Dimension(80, 23));
        channelCouplingBPanel_.add(channelCouplingBToggleButton);

        channelBPanel_.add(channelCouplingBPanel_);

        horizontalBPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalBPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalBPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalBLeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalBLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));
        horizontalBPanel_.add(horizontalBLeftPanel_);

        horizontalBRightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalBRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetBLabel1_.setText("Offset:");
        horizontalBRightPanel_.add(horizontalOffsetBLabel1_);

        horizontalOffsetBSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalBRightPanel_.add(horizontalOffsetBSpinner);

        horizontalOffsetBLabel2_.setText("x");
        horizontalBRightPanel_.add(horizontalOffsetBLabel2_);

        horizontalOffsetUnitBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
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
        verticalRangeBComboBox.setPreferredSize(new Dimension(75, 20));
        verticalBLeftPanel_.add(verticalRangeBComboBox);

        verticalRangeBLabel2_.setText("/div");
        verticalBLeftPanel_.add(verticalRangeBLabel2_);

        verticalBPanel_.add(verticalBLeftPanel_);

        verticalBRightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalBRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetBLabel1_.setText("Offset:");
        verticalBRightPanel_.add(verticalOffsetBLabel1_);

        verticalOffsetBSpinner.setPreferredSize(new Dimension(60, 20));
        verticalBRightPanel_.add(verticalOffsetBSpinner);

        verticalOffsetBLabel2_.setText("x");
        verticalBRightPanel_.add(verticalOffsetBLabel2_);

        verticalOffsetUnitBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VOLTAGE_UNITS));
        verticalOffsetUnitBComboBox.setPreferredSize(new Dimension(75, 20));
        verticalBRightPanel_.add(verticalOffsetUnitBComboBox);

        verticalBPanel_.add(verticalBRightPanel_);

        channelBPanel_.add(verticalBPanel_);

        measurementsBPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsBPanel_.setPreferredSize(new Dimension(390, 215));
        measurementsBPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementBTopPanel_.setPreferredSize(new Dimension(380, 30));
        measurementBTopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        measureBToggleButton.setText("OFF");
        measureBToggleButton.setFocusable(false);
        measureBToggleButton.setPreferredSize(new Dimension(80, 23));
        measurementBTopPanel_.add(measureBToggleButton);

        measurementsBPanel_.add(measurementBTopPanel_);

        measurementBTopLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementBTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageBLabel_.setText("Max Voltage:");
        measurementBTopLeftPanel_.add(maxVoltageBLabel_);

        maxVoltageBLabel.setText("N/A");
        maxVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementBTopLeftPanel_.add(maxVoltageBLabel);

        measurementsBPanel_.add(measurementBTopLeftPanel_);

        measurementBTopRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementBTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        minVoltageBLabel_.setText("Min Voltage:");
        measurementBTopRightPanel_.add(minVoltageBLabel_);

        minVoltageBLabel.setText("N/A");
        minVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementBTopRightPanel_.add(minVoltageBLabel);

        measurementsBPanel_.add(measurementBTopRightPanel_);

        measurementBMiddleLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementBMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageBLabel_.setText("Max P2P Voltage:");
        measurementBMiddleLeftPanel_.add(maxP2pVoltageBLabel_);

        maxP2pVoltageBLabel.setText("N/A");
        maxP2pVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementBMiddleLeftPanel_.add(maxP2pVoltageBLabel);

        measurementsBPanel_.add(measurementBMiddleLeftPanel_);

        measurementBMiddleRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementBMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        averageVoltageBLabel_.setText("Average Voltage:");
        measurementBMiddleRightPanel_.add(averageVoltageBLabel_);

        averageVoltageBLabel.setText("N/A");
        averageVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementBMiddleRightPanel_.add(averageVoltageBLabel);

        measurementsBPanel_.add(measurementBMiddleRightPanel_);

        measurementBBottomLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementBBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageBLabel_.setText("SD of Voltage:");
        measurementBBottomLeftPanel_.add(standardDeviationVoltageBLabel_);

        standardDeviationVoltageBLabel.setText("N/A");
        standardDeviationVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementBBottomLeftPanel_.add(standardDeviationVoltageBLabel);

        measurementsBPanel_.add(measurementBBottomLeftPanel_);

        measurementBBottomRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementBBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        frequencyBLabel_.setText("Frequency:");
        measurementBBottomRightPanel_.add(frequencyBLabel_);

        frequencyBLabel.setText("N/A");
        frequencyBLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementBBottomRightPanel_.add(frequencyBLabel);

        measurementsBPanel_.add(measurementBBottomRightPanel_);

        channelBPanel_.add(measurementsBPanel_);

        channelTabbedPane.addTab("Channel B", channelBPanel_);

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
        newExpressionButton.setFocusable(false);
        newExpressionButton.setPreferredSize(new Dimension(85, 23));
        expressionBottomPanel_.add(newExpressionButton);

        editExpressionButton.setText("Edit");
        editExpressionButton.setEnabled(false);
        editExpressionButton.setFocusable(false);
        editExpressionButton.setPreferredSize(new Dimension(85, 23));
        expressionBottomPanel_.add(editExpressionButton);

        removeExpressionButton.setText("Remove");
        removeExpressionButton.setEnabled(false);
        removeExpressionButton.setFocusable(false);
        removeExpressionButton.setPreferredSize(new Dimension(85, 23));
        expressionBottomPanel_.add(removeExpressionButton);

        expressionPannel_.add(expressionBottomPanel_);

        mathChannelPanel_.add(expressionPannel_);

        horizontalMathPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalMathPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalMathPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalMathLeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalMathLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));
        horizontalMathPanel_.add(horizontalMathLeftPanel_);

        horizontalMathRightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalMathRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetMathLabel1_.setText("Offset:");
        horizontalMathRightPanel_.add(horizontalOffsetMathLabel1_);

        horizontalOffsetMathSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalMathRightPanel_.add(horizontalOffsetMathSpinner);

        horizontalOffsetMathLabel2_.setText("x");
        horizontalMathRightPanel_.add(horizontalOffsetMathLabel2_);

        horizontalOffsetUnitMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
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
        verticalRangeMathComboBox.setPreferredSize(new Dimension(75, 20));
        verticalMathLeftPanel_.add(verticalRangeMathComboBox);

        verticalRangeMathLabel2_.setText("/div");
        verticalMathLeftPanel_.add(verticalRangeMathLabel2_);

        verticalMathPanel_.add(verticalMathLeftPanel_);

        verticalMathRightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalMathRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetMathLabel1_.setText("Offset:");
        verticalMathRightPanel_.add(verticalOffsetMathLabel1_);

        verticalOffsetMathSpinner.setPreferredSize(new Dimension(60, 20));
        verticalMathRightPanel_.add(verticalOffsetMathSpinner);

        verticalOffsetMathLabel2_.setText("x");
        verticalMathRightPanel_.add(verticalOffsetMathLabel2_);

        verticalOffsetUnitMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VOLTAGE_UNITS));
        verticalOffsetUnitMathComboBox.setPreferredSize(new Dimension(75, 20));
        verticalMathRightPanel_.add(verticalOffsetUnitMathComboBox);

        verticalMathPanel_.add(verticalMathRightPanel_);

        mathChannelPanel_.add(verticalMathPanel_);

        measurementsMathPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsMathPanel_.setPreferredSize(new Dimension(390, 215));
        measurementsMathPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementMathTopPanel_.setPreferredSize(new Dimension(380, 30));
        measurementMathTopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        measureMathToggleButton.setText("OFF");
        measureMathToggleButton.setFocusable(false);
        measureMathToggleButton.setPreferredSize(new Dimension(80, 23));
        measurementMathTopPanel_.add(measureMathToggleButton);

        measurementsMathPanel_.add(measurementMathTopPanel_);

        measurementMathTopLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementMathTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageMathLabel_.setText("Max Voltage:");
        measurementMathTopLeftPanel_.add(maxVoltageMathLabel_);

        maxVoltageMathLabel.setText("N/A");
        maxVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementMathTopLeftPanel_.add(maxVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathTopLeftPanel_);

        measurementMathTopRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementMathTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        minVoltageMathLabel_.setText("Min Voltage:");
        measurementMathTopRightPanel_.add(minVoltageMathLabel_);

        minVoltageMathLabel.setText("N/A");
        minVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementMathTopRightPanel_.add(minVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathTopRightPanel_);

        measurementMathMiddleLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementMathMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageMathLabel_.setText("Max P2P Voltage:");
        measurementMathMiddleLeftPanel_.add(maxP2pVoltageMathLabel_);

        maxP2pVoltageMathLabel.setText("N/A");
        maxP2pVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementMathMiddleLeftPanel_.add(maxP2pVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathMiddleLeftPanel_);

        measurementMathMiddleRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementMathMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        averageVoltageMathLabel_.setText("Average Voltage:");
        measurementMathMiddleRightPanel_.add(averageVoltageMathLabel_);

        averageVoltageMathLabel.setText("N/A");
        averageVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementMathMiddleRightPanel_.add(averageVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathMiddleRightPanel_);

        measurementMathBottomLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementMathBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageMathLabel_.setText("SD of Voltage:");
        measurementMathBottomLeftPanel_.add(standardDeviationVoltageMathLabel_);

        standardDeviationVoltageMathLabel.setText("N/A");
        standardDeviationVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementMathBottomLeftPanel_.add(standardDeviationVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathBottomLeftPanel_);

        measurementMathBottomRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementMathBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        frequencyMathLabel_.setText("Frequency:");
        measurementMathBottomRightPanel_.add(frequencyMathLabel_);

        frequencyMathLabel.setText("N/A");
        frequencyMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementMathBottomRightPanel_.add(frequencyMathLabel);

        measurementsMathPanel_.add(measurementMathBottomRightPanel_);

        mathChannelPanel_.add(measurementsMathPanel_);

        channelTabbedPane.addTab("Math Channel", mathChannelPanel_);

        inputPanel_.setBorder(BorderFactory.createTitledBorder("Input"));
        inputPanel_.setPreferredSize(new Dimension(390, 95));
        inputPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        inputTopPanel_.setPreferredSize(new Dimension(380, 35));
        inputTopPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        jLabel4.setText("Input:");
        inputTopPanel_.add(jLabel4);

        inputChannelComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Select channel" })
        );
        inputChannelComboBox.setPreferredSize(new Dimension(230, 20));
        inputTopPanel_.add(inputChannelComboBox);

        removeFilterButton.setText("Remove");
        removeFilterButton.setEnabled(false);
        removeFilterButton.setFocusable(false);
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
        browseButton.setFocusable(false);
        browseButton.setPreferredSize(new Dimension(85, 23));
        inputBottomPanel_.add(browseButton);

        inputPanel_.add(inputBottomPanel_);

        filterChannelPanel_.add(inputPanel_);

        horizontalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalFilterPanel_.setPreferredSize(new Dimension(390, 55));
        horizontalFilterPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalFilterLeftPanel_.setPreferredSize(new Dimension(170, 30));
        horizontalFilterLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));
        horizontalFilterPanel_.add(horizontalFilterLeftPanel_);

        horizontalFilterRightPanel_.setPreferredSize(new Dimension(205, 30));
        horizontalFilterRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetFilterLabel1_.setText("Offset:");
        horizontalFilterRightPanel_.add(horizontalOffsetFilterLabel1_);

        horizontalOffsetFilterSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalFilterRightPanel_.add(horizontalOffsetFilterSpinner);

        horizontalOffsetFilterLabel2_.setText("x");
        horizontalFilterRightPanel_.add(horizontalOffsetFilterLabel2_);

        horizontalOffsetUnitFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_OFFSET_UNITS));
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
        verticalRangeFilterComboBox.setPreferredSize(new Dimension(75, 20));
        verticalFilterLeftPanel_.add(verticalRangeFilterComboBox);

        verticalRangeFilterLabel2_.setText("/div");
        verticalFilterLeftPanel_.add(verticalRangeFilterLabel2_);

        verticalFilterPanel_.add(verticalFilterLeftPanel_);

        verticalFilterRightPanel_.setPreferredSize(new Dimension(205, 30));
        verticalFilterRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetFilterLabel1_.setText("Offset:");
        verticalFilterRightPanel_.add(verticalOffsetFilterLabel1_);

        verticalOffsetFilterSpinner.setPreferredSize(new Dimension(60, 20));
        verticalFilterRightPanel_.add(verticalOffsetFilterSpinner);

        verticalOffsetFilterLabel2_.setText("x");
        verticalFilterRightPanel_.add(verticalOffsetFilterLabel2_);

        verticalOffsetUnitFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VOLTAGE_UNITS));
        verticalOffsetUnitFilterComboBox.setPreferredSize(new Dimension(75, 20));
        verticalFilterRightPanel_.add(verticalOffsetUnitFilterComboBox);

        verticalFilterPanel_.add(verticalFilterRightPanel_);

        filterChannelPanel_.add(verticalFilterPanel_);

        measurementsFilterPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsFilterPanel_.setPreferredSize(new Dimension(390, 215));
        measurementsFilterPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementFilterTopPanel_.setPreferredSize(new Dimension(380, 30));
        measurementFilterTopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        measureFilterToggleButton.setText("OFF");
        measureFilterToggleButton.setFocusable(false);
        measureFilterToggleButton.setPreferredSize(new Dimension(80, 23));
        measurementFilterTopPanel_.add(measureFilterToggleButton);

        measurementsFilterPanel_.add(measurementFilterTopPanel_);

        measurementFilterTopLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementFilterTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageFilterLabel_.setText("Max Voltage:");
        measurementFilterTopLeftPanel_.add(maxVoltageFilterLabel_);

        maxVoltageFilterLabel.setText("N/A");
        maxVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementFilterTopLeftPanel_.add(maxVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterTopLeftPanel_);

        measurementFilterTopRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementFilterTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        minVoltageFilterLabel_.setText("Min Voltage:");
        measurementFilterTopRightPanel_.add(minVoltageFilterLabel_);

        minVoltageFilterLabel.setText("N/A");
        minVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementFilterTopRightPanel_.add(minVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterTopRightPanel_);

        measurementFilterMiddleLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementFilterMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageFilterLabel_.setText("Max P2P Voltage:");
        measurementFilterMiddleLeftPanel_.add(maxP2pVoltageFilterLabel_);

        maxP2pVoltageFilterLabel.setText("N/A");
        maxP2pVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementFilterMiddleLeftPanel_.add(maxP2pVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterMiddleLeftPanel_);

        measurementFilterMiddleRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementFilterMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        averageVoltageFilterLabel_.setText("Average Voltage:");
        measurementFilterMiddleRightPanel_.add(averageVoltageFilterLabel_);

        averageVoltageFilterLabel.setText("N/A");
        averageVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementFilterMiddleRightPanel_.add(averageVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterMiddleRightPanel_);

        measurementFilterBottomLeftPanel_.setPreferredSize(new Dimension(380, 25));
        measurementFilterBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageFilterLabel_.setText("SD of Voltage:");
        measurementFilterBottomLeftPanel_.add(standardDeviationVoltageFilterLabel_);

        standardDeviationVoltageFilterLabel.setText("N/A");
        standardDeviationVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementFilterBottomLeftPanel_.add(standardDeviationVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterBottomLeftPanel_);

        measurementFilterBottomRightPanel_.setPreferredSize(new Dimension(380, 25));
        measurementFilterBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        frequencyFilterLabel_.setText("Frequency:");
        measurementFilterBottomRightPanel_.add(frequencyFilterLabel_);

        frequencyFilterLabel.setText("N/A");
        frequencyFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        measurementFilterBottomRightPanel_.add(frequencyFilterLabel);

        measurementsFilterPanel_.add(measurementFilterBottomRightPanel_);

        filterChannelPanel_.add(measurementsFilterPanel_);

        channelTabbedPane.addTab("Filter Channel", filterChannelPanel_);

        generatorControlPanel_.setBorder(BorderFactory.createTitledBorder("Function Generator"));
        generatorControlPanel_.setPreferredSize(new Dimension(390, 175));
        generatorControlPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        generatorControlFirstPanel_.setPreferredSize(new Dimension(380, 30));
        generatorControlFirstPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        outputToggleButton.setText("OFF");
        outputToggleButton.setFocusable(false);
        outputToggleButton.setPreferredSize(new Dimension(80, 23));
        generatorControlFirstPanel_.add(outputToggleButton);

        generatorControlPanel_.add(generatorControlFirstPanel_);

        generatorControlSecondPanel_.setPreferredSize(new Dimension(380, 30));
        generatorControlSecondPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        waveTypeLabel_.setText("Wave Type:");
        generatorControlSecondPanel_.add(waveTypeLabel_);

        waveTypeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.WAVE_TYPES));
        waveTypeComboBox.setEnabled(false);
        waveTypeComboBox.setPreferredSize(new Dimension(265, 20));
        generatorControlSecondPanel_.add(waveTypeComboBox);

        generatorControlPanel_.add(generatorControlSecondPanel_);

        generatorControlThirdPanel_.setPreferredSize(new Dimension(380, 30));
        generatorControlThirdPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        p2pVoltageLabel_.setText("P2P Voltage:");
        p2pVoltageLabel_.setPreferredSize(new Dimension(80, 14));
        generatorControlThirdPanel_.add(p2pVoltageLabel_);

        p2pVoltageSpinner.setEnabled(false);
        p2pVoltageSpinner.setPreferredSize(new Dimension(85, 20));
        generatorControlThirdPanel_.add(p2pVoltageSpinner);

        p2pVoltageLabel2_.setText("x");
        generatorControlThirdPanel_.add(p2pVoltageLabel2_);

        p2pVoltageUnitComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VOLTAGE_UNITS));
        p2pVoltageUnitComboBox.setEnabled(false);
        p2pVoltageUnitComboBox.setPreferredSize(new Dimension(75, 20));
        generatorControlThirdPanel_.add(p2pVoltageUnitComboBox);

        generatorControlPanel_.add(generatorControlThirdPanel_);

        generatorControlFourthPanel_.setPreferredSize(new Dimension(380, 30));
        generatorControlFourthPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalOffsetGeneratorLabel1_.setText("Offset:");
        verticalOffsetGeneratorLabel1_.setPreferredSize(new Dimension(80, 14));
        generatorControlFourthPanel_.add(verticalOffsetGeneratorLabel1_);

        verticalOffsetGeneratorSpinner.setEnabled(false);
        verticalOffsetGeneratorSpinner.setPreferredSize(new Dimension(85, 20));
        generatorControlFourthPanel_.add(verticalOffsetGeneratorSpinner);

        verticalOffsetGeneratorLabel2_.setText("x");
        generatorControlFourthPanel_.add(verticalOffsetGeneratorLabel2_);

        verticalOffsetUnitGeneratorComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VOLTAGE_UNITS));
        verticalOffsetUnitGeneratorComboBox.setEnabled(false);
        verticalOffsetUnitGeneratorComboBox.setPreferredSize(new Dimension(75, 20));
        generatorControlFourthPanel_.add(verticalOffsetUnitGeneratorComboBox);

        generatorControlPanel_.add(generatorControlFourthPanel_);

        generatorControlFifthPanel_.setPreferredSize(new Dimension(380, 30));
        generatorControlFifthPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalOffsetGeneratorLabel1_1.setText("Frequency:");
        verticalOffsetGeneratorLabel1_1.setPreferredSize(new Dimension(80, 14));
        generatorControlFifthPanel_.add(verticalOffsetGeneratorLabel1_1);

        generatorFrequencySpinner.setEnabled(false);
        generatorFrequencySpinner.setPreferredSize(new Dimension(85, 20));
        generatorControlFifthPanel_.add(generatorFrequencySpinner);

        verticalOffsetGeneratorLabel2_1.setText("x   Hz");
        generatorControlFifthPanel_.add(verticalOffsetGeneratorLabel2_1);

        generatorControlPanel_.add(generatorControlFifthPanel_);

        generatorPanel_.add(generatorControlPanel_);

        channelTabbedPane.addTab("Generator", generatorPanel_);

        leftPanel_.add(channelTabbedPane, BorderLayout.CENTER);

        getContentPane().add(leftPanel_, BorderLayout.WEST);

        rightPanel_.setBorder(BorderFactory.createTitledBorder(""));
        rightPanel_.setMinimumSize(new Dimension(780, 650));
        rightPanel_.setPreferredSize(new Dimension(780, 650));
        rightPanel_.setLayout(new BorderLayout());

        divisionInfoPanel_.setPreferredSize(new Dimension(776, 30));
        divisionInfoPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

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

        horizontalDivisionInfoLabel.setText("Horizontal: 1 us/div");
        horizontalDivisionInfoLabel.setBorder(BorderFactory.createTitledBorder(""));
        divisionInfoPanel_.add(horizontalDivisionInfoLabel);

        rightPanel_.add(divisionInfoPanel_, BorderLayout.SOUTH);

        canvasPanel_.setLayout(new BorderLayout());
        rightPanel_.add(canvasPanel_, BorderLayout.CENTER);

        toolBarPanel_.setPreferredSize(new Dimension(776, 40));
        toolBarPanel_.setLayout(new BorderLayout());
        
        toolBarLeftPanel_.setPreferredSize(new Dimension(380, 40));
        toolBarLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));

        spaceLabel_.setText("     ");
        toolBarLeftPanel_.add(spaceLabel_);

        cursorLabel_.setText(" Cursor on: ");
        toolBarLeftPanel_.add(cursorLabel_);

        cursorComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Disabled" }));
        cursorComboBox.setMaximumSize(new Dimension(120, 20));
        cursorComboBox.setPreferredSize(new Dimension(120, 20));
        toolBarLeftPanel_.add(cursorComboBox);

        cursorVerticalValueLabel.setText("N/A");
        cursorVerticalValueLabel.setBorder(BorderFactory.createTitledBorder(""));
        toolBarLeftPanel_.add(cursorVerticalValueLabel);
        
        toolBarPanel_.add(toolBarLeftPanel_, BorderLayout.WEST);
        
        toolBarRightPanel_.setPreferredSize(new Dimension(380, 40));
        toolBarRightPanel_.setLayout(new FlowLayout(FlowLayout.TRAILING, 8, 10));
        
        sampleRateLabel_.setText("Sample Rate:");
        toolBarRightPanel_.add(sampleRateLabel_);
        
        sampleRateValueLabel.setText("N/A");
        sampleRateValueLabel.setBorder(BorderFactory.createTitledBorder(""));
        toolBarRightPanel_.add(sampleRateValueLabel);

        toolBarPanel_.add(toolBarRightPanel_, BorderLayout.EAST);
        
        rightPanel_.add(toolBarPanel_, BorderLayout.NORTH);

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
			channelTabbedPane.setSelectedComponent(channelAPanel_);
			break;

		case CHANNEL_B:
			channelTabbedPane.setSelectedComponent(channelBPanel_);
			break;

		case MATH_CHANNEL:
			channelTabbedPane.setSelectedComponent(mathChannelPanel_);
			break;

		case FILTER_CHANNEL:
			channelTabbedPane.setSelectedComponent(filterChannelPanel_);
			break;

		case GENERATOR_CHANNEL:
			channelTabbedPane.setSelectedComponent(generatorPanel_);
			break;

		default:
			channelTabbedPane.setSelectedComponent(channelAPanel_);
		}
    }
    
    /**
     * Show trigger controls on specified tab
     * @param tab 
     */
    protected void showTriggerControls(Constant.TAB tab) {
		channelAPanel_.remove(triggerPanel_);
		channelBPanel_.remove(triggerPanel_);
		mathChannelPanel_.remove(triggerPanel_);
		filterChannelPanel_.remove(triggerPanel_);
		showHorizontalRangeControls(tab);
		switch (tab) {
		case CHANNEL_A:
			channelAPanel_.add(triggerPanel_);
			break;

		case CHANNEL_B:
			channelBPanel_.add(triggerPanel_);
			break;

		case MATH_CHANNEL:
			mathChannelPanel_.add(triggerPanel_);
			break;

		case FILTER_CHANNEL:
			filterChannelPanel_.add(triggerPanel_);
			break;

		default:
			break;
		}
		this.validate();
    }
    
        /**
     * Show horizontal range controls on specified tab
     * @param tab 
     */
    private void showHorizontalRangeControls(Constant.TAB tab) {
		horizontalALeftPanel_.removeAll();
		horizontalBLeftPanel_.removeAll();
		horizontalMathLeftPanel_.removeAll();
		horizontalFilterLeftPanel_.removeAll();
		switch (tab) {
		case CHANNEL_A:
			horizontalALeftPanel_.add(horizontalRangeLabel1_);
			horizontalALeftPanel_.add(horizontalRangeComboBox);
			horizontalALeftPanel_.add(horizontalRangeLabel2_);
			break;

		case CHANNEL_B:
			horizontalBLeftPanel_.add(horizontalRangeLabel1_);
			horizontalBLeftPanel_.add(horizontalRangeComboBox);
			horizontalBLeftPanel_.add(horizontalRangeLabel2_);
			break;

		case MATH_CHANNEL:
			horizontalMathLeftPanel_.add(horizontalRangeLabel1_);
			horizontalMathLeftPanel_.add(horizontalRangeComboBox);
			horizontalMathLeftPanel_.add(horizontalRangeLabel2_);
			break;

		case FILTER_CHANNEL:
			horizontalFilterLeftPanel_.add(horizontalRangeLabel1_);
			horizontalFilterLeftPanel_.add(horizontalRangeComboBox);
			horizontalFilterLeftPanel_.add(horizontalRangeLabel2_);
			break;

		default:
			break;
		}
    }
    
    /**
     * Enable or disable all the components on the function generator channel panel
     *
     * @param enabled boolean
     */
    public void setEnabledGeneratorChannelControls(boolean enabled) {
        verticalOffsetGeneratorSpinner.setEnabled(enabled);
        verticalOffsetUnitGeneratorComboBox.setEnabled(enabled);
        waveTypeComboBox.setEnabled(enabled);
        p2pVoltageSpinner.setEnabled(enabled);
        p2pVoltageUnitComboBox.setEnabled(enabled);
        generatorFrequencySpinner.setEnabled(enabled);
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
