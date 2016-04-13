package gui;

import data.Constant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author ToanHo
 */
public class MainWindowUi extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JButton aButton;
    protected JLabel averageVoltageALabel;
    private JLabel averageVoltageALabel_;
    protected JLabel averageVoltageBLabel;
    private JLabel averageVoltageBLabel_;
    protected JLabel averageVoltageFilterLabel;
    private JLabel averageVoltageFilterLabel_;
    protected JLabel averageVoltageMathLabel;
    private JLabel averageVoltageMathLabel_;
    protected JButton bButton;
    protected JButton browseButton;
    private JPanel canvasPanel_;
    protected JCheckBox channelACheckBox;
    private JPanel channelAPanel_;
    protected JCheckBox channelBCheckBox;
    private JPanel channelBPanel_;
    private JTabbedPane channelTabbedPane_;
    private JPanel channelVisibilityPanel_;
    protected JTextField csvFilePathTextField;
    private JPanel cursorPanel_;
    protected JButton divideButton;
    protected JButton eButton;
    private JPanel equationBottomPanel_;
    private JPanel equationPannel_;
    protected JTextField equationTextField;
    private JPanel equationTopPanel_;
    protected JButton fButton;
    protected JCheckBox filterChannelCheckBox;
    private JPanel filterChannelPanel_;
    protected JButton forceTriggerButton;
    protected JLabel frequencyALabel;
    private JLabel frequencyALabel_;
    protected JLabel frequencyBLabel;
    private JLabel frequencyBLabel_;
    protected JLabel frequencyFilterLabel;
    private JLabel frequencyFilterLabel_;
    protected JLabel frequencyMathLabel;
    private JLabel frequencyMathLabel_;
    private JPanel functionGeneratorBottomLeftPanel_;
    private JPanel functionGeneratorBottomRightPanel_;
    private JPanel functionGeneratorMiddlePanel_;
    private JLabel functionGeneratorOffsetLabel_;
    protected JSpinner functionGeneratorOffsetSpinner;
    private JPanel functionGeneratorPanel_;
    private JPanel functionGeneratorTopPanel_;
    private JPanel horizontalALeftPanel_;
    private JPanel horizontalAPanel_;
    private JPanel horizontalARightPanel_;
    private JPanel horizontalBLeftPanel_;
    private JPanel horizontalBPanel_;
    private JPanel horizontalBRightPanel_;
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
    protected JComboBox<String> horizontalRangeAComboBox;
    private JLabel horizontalRangeALabel1_;
    private JLabel horizontalRangeALabel2_;
    protected JComboBox<String> horizontalRangeBComboBox;
    private JLabel horizontalRangeBLabel1_;
    private JLabel horizontalRangeBLabel2_;
    protected JComboBox<String> horizontalRangeFilterComboBox;
    private JLabel horizontalRangeFilterLabel1_;
    private JLabel horizontalRangeFilterLabel2_;
    private JLabel horizontalRangeLabel_54;
    protected JComboBox<String> horizontalRangeMathComboBox;
    private JLabel horizontalRangeMathLabel1_;
    private JLabel horizontalRangeMathLabel2_;
    private JPanel inputBottomPanel_;
    protected JComboBox<String> inputChannelComboBox;
    private JPanel inputPanel_;
    private JPanel inputTopPanel_;
    private JLabel jLabel4;
    private JToolBar jToolBar1;
    private JPanel leftPanel_;
    protected JButton leftParatheseButton;
    protected JCheckBox mathChannelCheckBox;
    private JPanel mathChannelPanel_;
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
    protected JButton minusButton;
    protected JButton multiplyButton;
    private JLabel outputLabel_;
    protected JToggleButton outputToggleButton;
    private JLabel p2pVoltageLabel_;
    protected JTextField p2pVoltageTextField;
    protected JButton piButton;
    protected JButton plusButton;
    protected JButton powerButton;
    protected JButton rearmTriggerButton;
    private JPanel rightPanel_;
    protected JButton rightParatheseButton;
    protected JLabel standardDeviationVoltageALabel;
    private JLabel standardDeviationVoltageALabel_;
    protected JLabel standardDeviationVoltageBLabel;
    private JLabel standardDeviationVoltageBLabel_;
    protected JLabel standardDeviationVoltageFilterLabel;
    private JLabel standardDeviationVoltageFilterLabel_;
    protected JLabel standardDeviationVoltageMathLabel;
    private JLabel standardDeviationVoltageMathLabel_;
    private JPanel triggerBottomPanel;
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
    private JLabel verticalOffsetMathLabel1_;
    private JLabel verticalOffsetMathLabel2_;
    protected JSpinner verticalOffsetMathSpinner;
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

    /**
     * Creates new form MainWindowUI
     */
    public MainWindowUi() {
        initializeComponents();
        Constant.setApplicationIcon(this);
        validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initializeComponents() {

        leftPanel_ = new JPanel();
        channelVisibilityPanel_ = new JPanel();
        channelACheckBox = new JCheckBox();
        channelBCheckBox = new JCheckBox();
        mathChannelCheckBox = new JCheckBox();
        filterChannelCheckBox = new JCheckBox();
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
        verticalAPannel_ = new JPanel();
        verticalALeftPanel_ = new JPanel();
        verticalRangeALabel1_ = new JLabel();
        verticalRangeAComboBox = new JComboBox<String>();
        verticalRangeALabel2_ = new JLabel();
        verticalARightPanel_ = new JPanel();
        verticalOffsetALabel1_ = new JLabel();
        verticalOffsetASpinner = new JSpinner();
        verticalOffsetALabel2_ = new JLabel();
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
        verticalBPanel_ = new JPanel();
        verticalBLeftPanel_ = new JPanel();
        verticalRangeBLabel1_ = new JLabel();
        verticalRangeBComboBox = new JComboBox<String>();
        verticalRangeBLabel2_ = new JLabel();
        verticalBRightPanel_ = new JPanel();
        verticalOffsetBLabel1_ = new JLabel();
        verticalOffsetBSpinner = new JSpinner();
        verticalOffsetBLabel2_ = new JLabel();
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
        equationPannel_ = new JPanel();
        equationTopPanel_ = new JPanel();
        equationTextField = new JTextField();
        equationBottomPanel_ = new JPanel();
        aButton = new JButton();
        bButton = new JButton();
        fButton = new JButton();
        powerButton = new JButton();
        plusButton = new JButton();
        minusButton = new JButton();
        piButton = new JButton();
        eButton = new JButton();
        leftParatheseButton = new JButton();
        rightParatheseButton = new JButton();
        multiplyButton = new JButton();
        divideButton = new JButton();
        horizontalMathPanel_ = new JPanel();
        horizontalMathLeftPanel_ = new JPanel();
        horizontalRangeMathLabel1_ = new JLabel();
        horizontalRangeMathComboBox = new JComboBox<String>();
        horizontalRangeMathLabel2_ = new JLabel();
        horizontalMathRightPanel_ = new JPanel();
        horizontalOffsetMathLabel1_ = new JLabel();
        horizontalOffsetMathSpinner = new JSpinner();
        horizontalOffsetMathLabel2_ = new JLabel();
        verticalMathPanel_ = new JPanel();
        verticalMathLeftPanel_ = new JPanel();
        verticalRangeMathLabel1_ = new JLabel();
        verticalRangeMathComboBox = new JComboBox<String>();
        verticalRangeMathLabel2_ = new JLabel();
        verticalMathRightPanel_ = new JPanel();
        verticalOffsetMathLabel1_ = new JLabel();
        verticalOffsetMathSpinner = new JSpinner();
        verticalOffsetMathLabel2_ = new JLabel();
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
        verticalFilterPanel_ = new JPanel();
        verticalFilterLeftPanel_ = new JPanel();
        verticalRangeFilterLabel1_ = new JLabel();
        verticalRangeFilterComboBox = new JComboBox<String>();
        verticalRangeFilterLabel2_ = new JLabel();
        verticalFilterRightPanel_ = new JPanel();
        verticalOffsetFilterLabel1_ = new JLabel();
        verticalOffsetFilterSpinner = new JSpinner();
        verticalOffsetFilterLabel2_ = new JLabel();
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
        triggerBottomPanel = new JPanel();
        triggerThresholdLabel_ = new JLabel();
        triggerThresholdSpinner = new JSpinner();
        functionGeneratorPanel_ = new JPanel();
        functionGeneratorTopPanel_ = new JPanel();
        outputLabel_ = new JLabel();
        outputToggleButton = new JToggleButton();
        functionGeneratorMiddlePanel_ = new JPanel();
        waveTypeLabel_ = new JLabel();
        waveTypeComboBox = new JComboBox<String>();
        functionGeneratorBottomLeftPanel_ = new JPanel();
        p2pVoltageLabel_ = new JLabel();
        p2pVoltageTextField = new JTextField();
        functionGeneratorBottomRightPanel_ = new JPanel();
        functionGeneratorOffsetLabel_ = new JLabel();
        functionGeneratorOffsetSpinner = new JSpinner();
        horizontalRangeLabel_54 = new JLabel();
        rightPanel_ = new JPanel();
        cursorPanel_ = new JPanel();
        canvasPanel_ = new JPanel();
        jToolBar1 = new JToolBar();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Constant.APPLICATION_TITLE);
        setMinimumSize(new Dimension(1165, 735));

        leftPanel_.setBorder(BorderFactory.createTitledBorder(""));
        leftPanel_.setPreferredSize(new Dimension(370, 650));
        leftPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 3));

        channelVisibilityPanel_.setBorder(BorderFactory.createTitledBorder("Channel Visibility"));
        channelVisibilityPanel_.setPreferredSize(new Dimension(366, 45));
        channelVisibilityPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 0));

        channelACheckBox.setSelected(true);
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
        mathChannelCheckBox.setPreferredSize(new Dimension(75, 20));
        channelVisibilityPanel_.add(mathChannelCheckBox);

        filterChannelCheckBox.setText("Filter");
        filterChannelCheckBox.setFocusable(false);
        filterChannelCheckBox.setPreferredSize(new Dimension(75, 20));
        channelVisibilityPanel_.add(filterChannelCheckBox);

        leftPanel_.add(channelVisibilityPanel_);

        channelTabbedPane_.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        channelTabbedPane_.setPreferredSize(new Dimension(366, 380));

        horizontalAPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalAPanel_.setPreferredSize(new Dimension(360, 55));
        horizontalAPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalALeftPanel_.setPreferredSize(new Dimension(190, 30));
        horizontalALeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeALabel1_.setText("Range:");
        horizontalALeftPanel_.add(horizontalRangeALabel1_);

        horizontalRangeAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeAComboBox.setPreferredSize(new Dimension(95, 20));
        horizontalALeftPanel_.add(horizontalRangeAComboBox);

        horizontalRangeALabel2_.setText("/div");
        horizontalALeftPanel_.add(horizontalRangeALabel2_);

        horizontalAPanel_.add(horizontalALeftPanel_);

        horizontalARightPanel_.setPreferredSize(new Dimension(150, 30));
        horizontalARightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetALabel1_.setText("Offset:");
        horizontalARightPanel_.add(horizontalOffsetALabel1_);

        horizontalOffsetASpinner.setPreferredSize(new Dimension(60, 20));
        horizontalARightPanel_.add(horizontalOffsetASpinner);

        horizontalOffsetALabel2_.setText("s");
        horizontalARightPanel_.add(horizontalOffsetALabel2_);

        horizontalAPanel_.add(horizontalARightPanel_);

        channelAPanel_.add(horizontalAPanel_);

        verticalAPannel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalAPannel_.setPreferredSize(new Dimension(360, 55));
        verticalAPannel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalALeftPanel_.setPreferredSize(new Dimension(190, 30));
        verticalALeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeALabel1_.setText("Range:");
        verticalALeftPanel_.add(verticalRangeALabel1_);

        verticalRangeAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeAComboBox.setPreferredSize(new Dimension(95, 20));
        verticalALeftPanel_.add(verticalRangeAComboBox);

        verticalRangeALabel2_.setText("/div");
        verticalALeftPanel_.add(verticalRangeALabel2_);

        verticalAPannel_.add(verticalALeftPanel_);

        verticalARightPanel_.setPreferredSize(new Dimension(150, 30));
        verticalARightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetALabel1_.setText("Offset:");
        verticalARightPanel_.add(verticalOffsetALabel1_);

        verticalOffsetASpinner.setPreferredSize(new Dimension(60, 20));
        verticalARightPanel_.add(verticalOffsetASpinner);

        verticalOffsetALabel2_.setText("volts");
        verticalARightPanel_.add(verticalOffsetALabel2_);

        verticalAPannel_.add(verticalARightPanel_);

        channelAPanel_.add(verticalAPannel_);

        measurementsAPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsAPanel_.setPreferredSize(new Dimension(360, 105));
        measurementsAPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementATopLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementATopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageALabel_.setText("Max Voltage:");
        measurementATopLeftPanel_.add(maxVoltageALabel_);

        maxVoltageALabel.setText("00.00");
        maxVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageALabel.setPreferredSize(new Dimension(45, 19));
        measurementATopLeftPanel_.add(maxVoltageALabel);

        measurementsAPanel_.add(measurementATopLeftPanel_);

        measurementATopRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementATopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        minVoltageALabel_.setText("Min Voltage:");
        measurementATopRightPanel_.add(minVoltageALabel_);

        minVoltageALabel.setText("00.00");
        minVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageALabel.setPreferredSize(new Dimension(45, 19));
        measurementATopRightPanel_.add(minVoltageALabel);

        measurementsAPanel_.add(measurementATopRightPanel_);

        measurementAMiddleLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementAMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageALabel_.setText("Max P2P Voltage:");
        measurementAMiddleLeftPanel_.add(maxP2pVoltageALabel_);

        maxP2pVoltageALabel.setText("00.00");
        maxP2pVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageALabel.setPreferredSize(new Dimension(45, 19));
        measurementAMiddleLeftPanel_.add(maxP2pVoltageALabel);

        measurementsAPanel_.add(measurementAMiddleLeftPanel_);

        measurementAMiddleRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementAMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        averageVoltageALabel_.setText("Average Voltage:");
        measurementAMiddleRightPanel_.add(averageVoltageALabel_);

        averageVoltageALabel.setText("00.00");
        averageVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageALabel.setPreferredSize(new Dimension(45, 19));
        measurementAMiddleRightPanel_.add(averageVoltageALabel);

        measurementsAPanel_.add(measurementAMiddleRightPanel_);

        measurementABottomLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementABottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageALabel_.setText("SD of Voltage:");
        measurementABottomLeftPanel_.add(standardDeviationVoltageALabel_);

        standardDeviationVoltageALabel.setText("00.00");
        standardDeviationVoltageALabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageALabel.setPreferredSize(new Dimension(45, 19));
        measurementABottomLeftPanel_.add(standardDeviationVoltageALabel);

        measurementsAPanel_.add(measurementABottomLeftPanel_);

        measurementABottomRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementABottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        frequencyALabel_.setText("Frequency:");
        measurementABottomRightPanel_.add(frequencyALabel_);

        frequencyALabel.setText("00.00");
        frequencyALabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyALabel.setPreferredSize(new Dimension(45, 19));
        measurementABottomRightPanel_.add(frequencyALabel);

        measurementsAPanel_.add(measurementABottomRightPanel_);

        channelAPanel_.add(measurementsAPanel_);

        channelTabbedPane_.addTab("Channel A", channelAPanel_);

        horizontalBPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalBPanel_.setPreferredSize(new Dimension(360, 55));
        horizontalBPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalBLeftPanel_.setPreferredSize(new Dimension(190, 30));
        horizontalBLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeBLabel1_.setText("Range:");
        horizontalBLeftPanel_.add(horizontalRangeBLabel1_);

        horizontalRangeBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeBComboBox.setEnabled(false);
        horizontalRangeBComboBox.setPreferredSize(new Dimension(95, 20));
        horizontalBLeftPanel_.add(horizontalRangeBComboBox);

        horizontalRangeBLabel2_.setText("/div");
        horizontalBLeftPanel_.add(horizontalRangeBLabel2_);

        horizontalBPanel_.add(horizontalBLeftPanel_);

        horizontalBRightPanel_.setPreferredSize(new Dimension(150, 30));
        horizontalBRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetBLabel1_.setText("Offset:");
        horizontalBRightPanel_.add(horizontalOffsetBLabel1_);

        horizontalOffsetBSpinner.setEnabled(false);
        horizontalOffsetBSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalBRightPanel_.add(horizontalOffsetBSpinner);

        horizontalOffsetBLabel2_.setText("s");
        horizontalBRightPanel_.add(horizontalOffsetBLabel2_);

        horizontalBPanel_.add(horizontalBRightPanel_);

        channelBPanel_.add(horizontalBPanel_);

        verticalBPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalBPanel_.setPreferredSize(new Dimension(360, 55));
        verticalBPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalBLeftPanel_.setPreferredSize(new Dimension(190, 30));
        verticalBLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeBLabel1_.setText("Range:");
        verticalBLeftPanel_.add(verticalRangeBLabel1_);

        verticalRangeBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeBComboBox.setEnabled(false);
        verticalRangeBComboBox.setPreferredSize(new Dimension(95, 20));
        verticalBLeftPanel_.add(verticalRangeBComboBox);

        verticalRangeBLabel2_.setText("/div");
        verticalBLeftPanel_.add(verticalRangeBLabel2_);

        verticalBPanel_.add(verticalBLeftPanel_);

        verticalBRightPanel_.setPreferredSize(new Dimension(150, 30));
        verticalBRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetBLabel1_.setText("Offset:");
        verticalBRightPanel_.add(verticalOffsetBLabel1_);

        verticalOffsetBSpinner.setEnabled(false);
        verticalOffsetBSpinner.setPreferredSize(new Dimension(60, 20));
        verticalBRightPanel_.add(verticalOffsetBSpinner);

        verticalOffsetBLabel2_.setText("volts");
        verticalBRightPanel_.add(verticalOffsetBLabel2_);

        verticalBPanel_.add(verticalBRightPanel_);

        channelBPanel_.add(verticalBPanel_);

        measurementsBPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsBPanel_.setPreferredSize(new Dimension(360, 105));
        measurementsBPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementBTopLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementBTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageBLabel_.setText("Max Voltage:");
        measurementBTopLeftPanel_.add(maxVoltageBLabel_);

        maxVoltageBLabel.setText("00.00");
        maxVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageBLabel.setPreferredSize(new Dimension(45, 19));
        measurementBTopLeftPanel_.add(maxVoltageBLabel);

        measurementsBPanel_.add(measurementBTopLeftPanel_);

        measurementBTopRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementBTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        minVoltageBLabel_.setText("Min Voltage:");
        measurementBTopRightPanel_.add(minVoltageBLabel_);

        minVoltageBLabel.setText("00.00");
        minVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageBLabel.setPreferredSize(new Dimension(45, 19));
        measurementBTopRightPanel_.add(minVoltageBLabel);

        measurementsBPanel_.add(measurementBTopRightPanel_);

        measurementBMiddleLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementBMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageBLabel_.setText("Max P2P Voltage:");
        measurementBMiddleLeftPanel_.add(maxP2pVoltageBLabel_);

        maxP2pVoltageBLabel.setText("00.00");
        maxP2pVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageBLabel.setPreferredSize(new Dimension(45, 19));
        measurementBMiddleLeftPanel_.add(maxP2pVoltageBLabel);

        measurementsBPanel_.add(measurementBMiddleLeftPanel_);

        measurementBMiddleRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementBMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        averageVoltageBLabel_.setText("Average Voltage:");
        measurementBMiddleRightPanel_.add(averageVoltageBLabel_);

        averageVoltageBLabel.setText("00.00");
        averageVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageBLabel.setPreferredSize(new Dimension(45, 19));
        measurementBMiddleRightPanel_.add(averageVoltageBLabel);

        measurementsBPanel_.add(measurementBMiddleRightPanel_);

        measurementBBottomLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementBBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageBLabel_.setText("SD of Voltage:");
        measurementBBottomLeftPanel_.add(standardDeviationVoltageBLabel_);

        standardDeviationVoltageBLabel.setText("00.00");
        standardDeviationVoltageBLabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageBLabel.setPreferredSize(new Dimension(45, 19));
        measurementBBottomLeftPanel_.add(standardDeviationVoltageBLabel);

        measurementsBPanel_.add(measurementBBottomLeftPanel_);

        measurementBBottomRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementBBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        frequencyBLabel_.setText("Frequency:");
        measurementBBottomRightPanel_.add(frequencyBLabel_);

        frequencyBLabel.setText("00.00");
        frequencyBLabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyBLabel.setPreferredSize(new Dimension(45, 19));
        measurementBBottomRightPanel_.add(frequencyBLabel);

        measurementsBPanel_.add(measurementBBottomRightPanel_);

        channelBPanel_.add(measurementsBPanel_);

        channelTabbedPane_.addTab("Channel B", channelBPanel_);

        equationPannel_.setBorder(BorderFactory.createTitledBorder("Equation"));
        equationPannel_.setPreferredSize(new Dimension(360, 115));
        equationPannel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        equationTopPanel_.setPreferredSize(new Dimension(350, 30));
        equationTopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        equationTextField.setEnabled(false);
        equationTextField.setPreferredSize(new Dimension(320, 23));
        equationTopPanel_.add(equationTextField);

        equationPannel_.add(equationTopPanel_);

        equationBottomPanel_.setPreferredSize(new Dimension(320, 62));

        aButton.setText("A");
        aButton.setEnabled(false);
        aButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(aButton);

        bButton.setText("B");
        bButton.setEnabled(false);
        bButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(bButton);

        fButton.setText("F");
        fButton.setEnabled(false);
        fButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(fButton);

        powerButton.setText("^");
        powerButton.setEnabled(false);
        powerButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(powerButton);

        plusButton.setText("+");
        plusButton.setEnabled(false);
        plusButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(plusButton);

        minusButton.setText("-");
        minusButton.setEnabled(false);
        minusButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(minusButton);

        piButton.setText("Pi");
        piButton.setEnabled(false);
        piButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(piButton);

        eButton.setText("e");
        eButton.setEnabled(false);
        eButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(eButton);

        leftParatheseButton.setText("(");
        leftParatheseButton.setEnabled(false);
        leftParatheseButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(leftParatheseButton);

        rightParatheseButton.setText(")");
        rightParatheseButton.setEnabled(false);
        rightParatheseButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(rightParatheseButton);

        multiplyButton.setText("*");
        multiplyButton.setEnabled(false);
        multiplyButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(multiplyButton);

        divideButton.setText("/");
        divideButton.setEnabled(false);
        divideButton.setPreferredSize(new Dimension(45, 23));
        equationBottomPanel_.add(divideButton);

        equationPannel_.add(equationBottomPanel_);

        mathChannelPanel_.add(equationPannel_);

        horizontalMathPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalMathPanel_.setPreferredSize(new Dimension(360, 55));
        horizontalMathPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalMathLeftPanel_.setPreferredSize(new Dimension(190, 30));
        horizontalMathLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeMathLabel1_.setText("Range:");
        horizontalMathLeftPanel_.add(horizontalRangeMathLabel1_);

        horizontalRangeMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeMathComboBox.setEnabled(false);
        horizontalRangeMathComboBox.setPreferredSize(new Dimension(95, 20));
        horizontalMathLeftPanel_.add(horizontalRangeMathComboBox);

        horizontalRangeMathLabel2_.setText("/div");
        horizontalMathLeftPanel_.add(horizontalRangeMathLabel2_);

        horizontalMathPanel_.add(horizontalMathLeftPanel_);

        horizontalMathRightPanel_.setPreferredSize(new Dimension(150, 30));
        horizontalMathRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetMathLabel1_.setText("Offset:");
        horizontalMathRightPanel_.add(horizontalOffsetMathLabel1_);

        horizontalOffsetMathSpinner.setEnabled(false);
        horizontalOffsetMathSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalMathRightPanel_.add(horizontalOffsetMathSpinner);

        horizontalOffsetMathLabel2_.setText("s");
        horizontalMathRightPanel_.add(horizontalOffsetMathLabel2_);

        horizontalMathPanel_.add(horizontalMathRightPanel_);

        mathChannelPanel_.add(horizontalMathPanel_);

        verticalMathPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalMathPanel_.setPreferredSize(new Dimension(360, 55));
        verticalMathPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalMathLeftPanel_.setPreferredSize(new Dimension(190, 30));
        verticalMathLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeMathLabel1_.setText("Range:");
        verticalMathLeftPanel_.add(verticalRangeMathLabel1_);

        verticalRangeMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeMathComboBox.setEnabled(false);
        verticalRangeMathComboBox.setPreferredSize(new Dimension(95, 20));
        verticalMathLeftPanel_.add(verticalRangeMathComboBox);

        verticalRangeMathLabel2_.setText("/div");
        verticalMathLeftPanel_.add(verticalRangeMathLabel2_);

        verticalMathPanel_.add(verticalMathLeftPanel_);

        verticalMathRightPanel_.setPreferredSize(new Dimension(150, 30));
        verticalMathRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetMathLabel1_.setText("Offset:");
        verticalMathRightPanel_.add(verticalOffsetMathLabel1_);

        verticalOffsetMathSpinner.setEnabled(false);
        verticalOffsetMathSpinner.setPreferredSize(new Dimension(60, 20));
        verticalMathRightPanel_.add(verticalOffsetMathSpinner);

        verticalOffsetMathLabel2_.setText("volts");
        verticalMathRightPanel_.add(verticalOffsetMathLabel2_);

        verticalMathPanel_.add(verticalMathRightPanel_);

        mathChannelPanel_.add(verticalMathPanel_);

        measurementsMathPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsMathPanel_.setPreferredSize(new Dimension(360, 105));
        measurementsMathPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementMathTopLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementMathTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageMathLabel_.setText("Max Voltage:");
        measurementMathTopLeftPanel_.add(maxVoltageMathLabel_);

        maxVoltageMathLabel.setText("00.00");
        maxVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageMathLabel.setPreferredSize(new Dimension(45, 19));
        measurementMathTopLeftPanel_.add(maxVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathTopLeftPanel_);

        measurementMathTopRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementMathTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        minVoltageMathLabel_.setText("Min Voltage:");
        measurementMathTopRightPanel_.add(minVoltageMathLabel_);

        minVoltageMathLabel.setText("00.00");
        minVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageMathLabel.setPreferredSize(new Dimension(45, 19));
        measurementMathTopRightPanel_.add(minVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathTopRightPanel_);

        measurementMathMiddleLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementMathMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageMathLabel_.setText("Max P2P Voltage:");
        measurementMathMiddleLeftPanel_.add(maxP2pVoltageMathLabel_);

        maxP2pVoltageMathLabel.setText("00.00");
        maxP2pVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageMathLabel.setPreferredSize(new Dimension(45, 19));
        measurementMathMiddleLeftPanel_.add(maxP2pVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathMiddleLeftPanel_);

        measurementMathMiddleRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementMathMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        averageVoltageMathLabel_.setText("Average Voltage:");
        measurementMathMiddleRightPanel_.add(averageVoltageMathLabel_);

        averageVoltageMathLabel.setText("00.00");
        averageVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageMathLabel.setPreferredSize(new Dimension(45, 19));
        measurementMathMiddleRightPanel_.add(averageVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathMiddleRightPanel_);

        measurementMathBottomLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementMathBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageMathLabel_.setText("SD of Voltage:");
        measurementMathBottomLeftPanel_.add(standardDeviationVoltageMathLabel_);

        standardDeviationVoltageMathLabel.setText("00.00");
        standardDeviationVoltageMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageMathLabel.setPreferredSize(new Dimension(45, 19));
        measurementMathBottomLeftPanel_.add(standardDeviationVoltageMathLabel);

        measurementsMathPanel_.add(measurementMathBottomLeftPanel_);

        measurementMathBottomRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementMathBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        frequencyMathLabel_.setText("Frequency:");
        measurementMathBottomRightPanel_.add(frequencyMathLabel_);

        frequencyMathLabel.setText("00.00");
        frequencyMathLabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyMathLabel.setPreferredSize(new Dimension(45, 19));
        measurementMathBottomRightPanel_.add(frequencyMathLabel);

        measurementsMathPanel_.add(measurementMathBottomRightPanel_);

        mathChannelPanel_.add(measurementsMathPanel_);

        channelTabbedPane_.addTab("Math Channel", mathChannelPanel_);

        inputPanel_.setBorder(BorderFactory.createTitledBorder("Input"));
        inputPanel_.setPreferredSize(new Dimension(360, 95));
        inputPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        inputTopPanel_.setPreferredSize(new Dimension(350, 35));
        inputTopPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        jLabel4.setText("Input:");
        inputTopPanel_.add(jLabel4);

        inputChannelComboBox.setModel(new DefaultComboBoxModel<String>(Constant.INPUT_CHANNELS));
        inputChannelComboBox.setEnabled(false);
        inputChannelComboBox.setPreferredSize(new Dimension(210, 20));
        inputTopPanel_.add(inputChannelComboBox);

        inputPanel_.add(inputTopPanel_);

        inputBottomPanel_.setPreferredSize(new Dimension(350, 35));
        inputBottomPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        csvFilePathTextField.setEditable(false);
        csvFilePathTextField.setText("Choose CSV file");
        csvFilePathTextField.setToolTipText("");
        csvFilePathTextField.setPreferredSize(new Dimension(250, 23));
        inputBottomPanel_.add(csvFilePathTextField);

        browseButton.setText("Browse");
        browseButton.setEnabled(false);
        browseButton.setPreferredSize(new Dimension(80, 23));
        inputBottomPanel_.add(browseButton);

        inputPanel_.add(inputBottomPanel_);

        filterChannelPanel_.add(inputPanel_);

        horizontalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalFilterPanel_.setPreferredSize(new Dimension(360, 55));
        horizontalFilterPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        horizontalFilterLeftPanel_.setPreferredSize(new Dimension(190, 30));
        horizontalFilterLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        horizontalRangeFilterLabel1_.setText("Range:");
        horizontalFilterLeftPanel_.add(horizontalRangeFilterLabel1_);

        horizontalRangeFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeFilterComboBox.setEnabled(false);
        horizontalRangeFilterComboBox.setPreferredSize(new Dimension(95, 20));
        horizontalFilterLeftPanel_.add(horizontalRangeFilterComboBox);

        horizontalRangeFilterLabel2_.setText("/div");
        horizontalFilterLeftPanel_.add(horizontalRangeFilterLabel2_);

        horizontalFilterPanel_.add(horizontalFilterLeftPanel_);

        horizontalFilterRightPanel_.setPreferredSize(new Dimension(150, 30));
        horizontalFilterRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetFilterLabel1_.setText("Offset:");
        horizontalFilterRightPanel_.add(horizontalOffsetFilterLabel1_);

        horizontalOffsetFilterSpinner.setEnabled(false);
        horizontalOffsetFilterSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalFilterRightPanel_.add(horizontalOffsetFilterSpinner);

        horizontalOffsetFilterLabel2_.setText("s");
        horizontalFilterRightPanel_.add(horizontalOffsetFilterLabel2_);

        horizontalFilterPanel_.add(horizontalFilterRightPanel_);

        filterChannelPanel_.add(horizontalFilterPanel_);

        verticalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalFilterPanel_.setPreferredSize(new Dimension(360, 55));
        verticalFilterPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        verticalFilterLeftPanel_.setPreferredSize(new Dimension(190, 30));
        verticalFilterLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        verticalRangeFilterLabel1_.setText("Range:");
        verticalFilterLeftPanel_.add(verticalRangeFilterLabel1_);

        verticalRangeFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeFilterComboBox.setEnabled(false);
        verticalRangeFilterComboBox.setPreferredSize(new Dimension(95, 20));
        verticalFilterLeftPanel_.add(verticalRangeFilterComboBox);

        verticalRangeFilterLabel2_.setText("/div");
        verticalFilterLeftPanel_.add(verticalRangeFilterLabel2_);

        verticalFilterPanel_.add(verticalFilterLeftPanel_);

        verticalFilterRightPanel_.setPreferredSize(new Dimension(150, 30));
        verticalFilterRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetFilterLabel1_.setText("Offset:");
        verticalFilterRightPanel_.add(verticalOffsetFilterLabel1_);

        verticalOffsetFilterSpinner.setEnabled(false);
        verticalOffsetFilterSpinner.setPreferredSize(new Dimension(60, 20));
        verticalFilterRightPanel_.add(verticalOffsetFilterSpinner);

        verticalOffsetFilterLabel2_.setText("volts");
        verticalFilterRightPanel_.add(verticalOffsetFilterLabel2_);

        verticalFilterPanel_.add(verticalFilterRightPanel_);

        filterChannelPanel_.add(verticalFilterPanel_);

        measurementsFilterPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsFilterPanel_.setPreferredSize(new Dimension(360, 105));
        measurementsFilterPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 1));

        measurementFilterTopLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementFilterTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxVoltageFilterLabel_.setText("Max Voltage:");
        measurementFilterTopLeftPanel_.add(maxVoltageFilterLabel_);

        maxVoltageFilterLabel.setText("00.00");
        maxVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageFilterLabel.setPreferredSize(new Dimension(45, 19));
        measurementFilterTopLeftPanel_.add(maxVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterTopLeftPanel_);

        measurementFilterTopRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementFilterTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        minVoltageFilterLabel_.setText("Min Voltage:");
        measurementFilterTopRightPanel_.add(minVoltageFilterLabel_);

        minVoltageFilterLabel.setText("00.00");
        minVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageFilterLabel.setPreferredSize(new Dimension(45, 19));
        measurementFilterTopRightPanel_.add(minVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterTopRightPanel_);

        measurementFilterMiddleLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementFilterMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        maxP2pVoltageFilterLabel_.setText("Max P2P Voltage:");
        measurementFilterMiddleLeftPanel_.add(maxP2pVoltageFilterLabel_);

        maxP2pVoltageFilterLabel.setText("00.00");
        maxP2pVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageFilterLabel.setPreferredSize(new Dimension(45, 19));
        measurementFilterMiddleLeftPanel_.add(maxP2pVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterMiddleLeftPanel_);

        measurementFilterMiddleRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementFilterMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        averageVoltageFilterLabel_.setText("Average Voltage:");
        measurementFilterMiddleRightPanel_.add(averageVoltageFilterLabel_);

        averageVoltageFilterLabel.setText("00.00");
        averageVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageFilterLabel.setPreferredSize(new Dimension(45, 19));
        measurementFilterMiddleRightPanel_.add(averageVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterMiddleRightPanel_);

        measurementFilterBottomLeftPanel_.setPreferredSize(new Dimension(175, 25));
        measurementFilterBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));

        standardDeviationVoltageFilterLabel_.setText("SD of Voltage:");
        measurementFilterBottomLeftPanel_.add(standardDeviationVoltageFilterLabel_);

        standardDeviationVoltageFilterLabel.setText("00.00");
        standardDeviationVoltageFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageFilterLabel.setPreferredSize(new Dimension(45, 19));
        measurementFilterBottomLeftPanel_.add(standardDeviationVoltageFilterLabel);

        measurementsFilterPanel_.add(measurementFilterBottomLeftPanel_);

        measurementFilterBottomRightPanel_.setPreferredSize(new Dimension(170, 25));
        measurementFilterBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 4));

        frequencyFilterLabel_.setText("Frequency:");
        measurementFilterBottomRightPanel_.add(frequencyFilterLabel_);

        frequencyFilterLabel.setText("00.00");
        frequencyFilterLabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyFilterLabel.setPreferredSize(new Dimension(45, 19));
        measurementFilterBottomRightPanel_.add(frequencyFilterLabel);

        measurementsFilterPanel_.add(measurementFilterBottomRightPanel_);

        filterChannelPanel_.add(measurementsFilterPanel_);

        channelTabbedPane_.addTab("Filter Channel", filterChannelPanel_);

        leftPanel_.add(channelTabbedPane_);

        triggerPanel_.setBorder(BorderFactory.createTitledBorder("Trigger"));
        triggerPanel_.setPreferredSize(new Dimension(366, 140));
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

        triggerMiddleLeftPanel1_.setPreferredSize(new Dimension(210, 30));
        triggerMiddleLeftPanel1_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        triggerModeLabel_.setText("Mode:");
        triggerModeLabel_.setPreferredSize(new Dimension(40, 14));
        triggerMiddleLeftPanel1_.add(triggerModeLabel_);

        triggerModeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.TRIGGER_MODES));
        triggerModeComboBox.setPreferredSize(new Dimension(130, 20));
        triggerMiddleLeftPanel1_.add(triggerModeComboBox);

        triggerPanel_.add(triggerMiddleLeftPanel1_);

        triggerMiddleRightPanel1_.setPreferredSize(new Dimension(140, 30));
        triggerMiddleRightPanel1_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));

        forceTriggerButton.setText("Force Trigger");
        forceTriggerButton.setPreferredSize(new Dimension(130, 23));
        triggerMiddleRightPanel1_.add(forceTriggerButton);

        triggerPanel_.add(triggerMiddleRightPanel1_);

        triggerMiddleLeftPanel2_.setPreferredSize(new Dimension(210, 30));
        triggerMiddleLeftPanel2_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        triggerTypeLabel_.setText("Type:");
        triggerTypeLabel_.setPreferredSize(new Dimension(40, 14));
        triggerMiddleLeftPanel2_.add(triggerTypeLabel_);

        triggerTypeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.TRIGGER_TYPES));
        triggerTypeComboBox.setPreferredSize(new Dimension(130, 20));
        triggerMiddleLeftPanel2_.add(triggerTypeComboBox);

        triggerPanel_.add(triggerMiddleLeftPanel2_);

        triggerMiddleRightPanel2_.setPreferredSize(new Dimension(140, 30));
        triggerMiddleRightPanel2_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));

        rearmTriggerButton.setText("Re-arm Trigger");
        rearmTriggerButton.setPreferredSize(new Dimension(130, 23));
        triggerMiddleRightPanel2_.add(rearmTriggerButton);

        triggerPanel_.add(triggerMiddleRightPanel2_);

        triggerBottomPanel.setPreferredSize(new Dimension(350, 30));

        triggerThresholdLabel_.setText("Trigger Threshold:");
        triggerBottomPanel.add(triggerThresholdLabel_);

        triggerThresholdSpinner.setPreferredSize(new Dimension(60, 20));
        triggerBottomPanel.add(triggerThresholdSpinner);

        triggerPanel_.add(triggerBottomPanel);

        leftPanel_.add(triggerPanel_);

        functionGeneratorPanel_.setBorder(BorderFactory.createTitledBorder("Function Generator"));
        functionGeneratorPanel_.setPreferredSize(new Dimension(366, 115));
        functionGeneratorPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        functionGeneratorTopPanel_.setPreferredSize(new Dimension(350, 30));
        functionGeneratorTopPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        outputLabel_.setText("Output");
        functionGeneratorTopPanel_.add(outputLabel_);

        outputToggleButton.setText("On");
        outputToggleButton.setPreferredSize(new Dimension(80, 23));
        functionGeneratorTopPanel_.add(outputToggleButton);

        functionGeneratorPanel_.add(functionGeneratorTopPanel_);

        functionGeneratorMiddlePanel_.setPreferredSize(new Dimension(350, 30));
        functionGeneratorMiddlePanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        waveTypeLabel_.setText("Wave Type:");
        functionGeneratorMiddlePanel_.add(waveTypeLabel_);

        waveTypeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.WAVE_TYPES));
        waveTypeComboBox.setPreferredSize(new Dimension(235, 20));
        functionGeneratorMiddlePanel_.add(waveTypeComboBox);

        functionGeneratorPanel_.add(functionGeneratorMiddlePanel_);

        functionGeneratorBottomLeftPanel_.setPreferredSize(new Dimension(190, 30));
        functionGeneratorBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 5));

        p2pVoltageLabel_.setText("P2P Voltage:");
        functionGeneratorBottomLeftPanel_.add(p2pVoltageLabel_);

        p2pVoltageTextField.setPreferredSize(new Dimension(75, 20));
        functionGeneratorBottomLeftPanel_.add(p2pVoltageTextField);

        functionGeneratorPanel_.add(functionGeneratorBottomLeftPanel_);

        functionGeneratorBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        functionGeneratorOffsetLabel_.setText("Offset:");
        functionGeneratorBottomRightPanel_.add(functionGeneratorOffsetLabel_);

        functionGeneratorOffsetSpinner.setPreferredSize(new Dimension(60, 20));
        functionGeneratorBottomRightPanel_.add(functionGeneratorOffsetSpinner);

        horizontalRangeLabel_54.setText("volts");
        functionGeneratorBottomRightPanel_.add(horizontalRangeLabel_54);

        functionGeneratorPanel_.add(functionGeneratorBottomRightPanel_);

        leftPanel_.add(functionGeneratorPanel_);

        getContentPane().add(leftPanel_, BorderLayout.WEST);

        rightPanel_.setBorder(BorderFactory.createTitledBorder(""));
        rightPanel_.setMinimumSize(new Dimension(780, 650));
        rightPanel_.setPreferredSize(new Dimension(780, 650));
        rightPanel_.setLayout(new BorderLayout());

        cursorPanel_.setPreferredSize(new Dimension(776, 40));
        cursorPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 4));
        rightPanel_.add(cursorPanel_, BorderLayout.SOUTH);

        canvasPanel_.setLayout(new BorderLayout());
        rightPanel_.add(canvasPanel_, BorderLayout.CENTER);

        jToolBar1.setRollover(true);
        jToolBar1.setPreferredSize(new Dimension(100, 40));
        rightPanel_.add(jToolBar1, BorderLayout.NORTH);

        getContentPane().add(rightPanel_, BorderLayout.CENTER);

        setSize(new Dimension(1170, 735));
        setLocationRelativeTo(null);
    }

    /**
     * Show tab on channel tabbedpane
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
            default:
                channelTabbedPane_.setSelectedComponent(channelAPanel_);
        }
    }

    /**
     * Enable or disable all the components on the Math channel panel
     * @param enabled boolean
     */
    protected void setEnabledMathChannel(boolean enabled) {
        equationTextField.setEnabled(enabled);
        aButton.setEnabled(enabled);
        bButton.setEnabled(enabled);
        fButton.setEnabled(enabled);
        powerButton.setEnabled(enabled);
        plusButton.setEnabled(enabled);
        minusButton.setEnabled(enabled);
        piButton.setEnabled(enabled);
        eButton.setEnabled(enabled);
        leftParatheseButton.setEnabled(enabled);
        rightParatheseButton.setEnabled(enabled);
        multiplyButton.setEnabled(enabled);
        divideButton.setEnabled(enabled);
        horizontalOffsetMathSpinner.setEnabled(enabled);
        horizontalRangeMathComboBox.setEnabled(enabled);
        verticalOffsetMathSpinner.setEnabled(enabled);
        verticalRangeMathComboBox.setEnabled(enabled);
    }

    /**
     * Enable or disable all the components on the Filter channel panel
     * @param enabled boolean
     */
    protected void setEnabledFilterChannel(boolean enabled) {
        inputChannelComboBox.setEnabled(enabled);
        browseButton.setEnabled(enabled);
        horizontalOffsetFilterSpinner.setEnabled(enabled);
        horizontalRangeFilterComboBox.setEnabled(enabled);
        verticalOffsetFilterSpinner.setEnabled(enabled);
        verticalRangeFilterComboBox.setEnabled(enabled);
    }

    /**
     * Enable or disable all the components on the channel A panel
     * @param enabled boolean
     */
    protected void setEnabledChannelA(boolean enabled) {
        horizontalOffsetASpinner.setEnabled(enabled);
        horizontalRangeAComboBox.setEnabled(enabled);
        verticalOffsetASpinner.setEnabled(enabled);
        verticalRangeAComboBox.setEnabled(enabled);
    }

    /**
     * Enable or disable all the components on the channel B panel
     * @param enabled boolean
     */
    protected void setEnabledChannelB(boolean enabled) {
        horizontalOffsetBSpinner.setEnabled(enabled);
        horizontalRangeBComboBox.setEnabled(enabled);
        verticalOffsetBSpinner.setEnabled(enabled);
        verticalRangeBComboBox.setEnabled(enabled);
    }
    
    /**
     * Add component (e.g. ChartPanel) to canvas panel
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
