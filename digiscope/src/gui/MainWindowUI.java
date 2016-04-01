package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import data.Constant;

/**
 *
 * @author ToanHo
 */
public class MainWindowUI extends javax.swing.JFrame {

    protected JButton aButton;
    protected JLabel averageVoltageLabel;
    private JLabel averageVoltageLabel_;
    protected JButton bButton;
    protected JButton browseButton;
    private JPanel canvasPanel;
    private JPanel channelAPanel_;
    private JPanel channelBPanel_;
    protected JTextField csvFilePathTextField;
    private JPanel cursorPanel_;
    protected JButton divideButton;
    protected JButton eButton;
    protected JToggleButton enableFilterToggleButton;
    protected JToggleButton enableMathToggleButton;
    private JPanel equationBottomPanel_;
    private JPanel equationPannel_;
    protected JTextField equationTextField;
    private JPanel equationTopPanel_;
    protected JButton fButton;
    private JPanel filterChannelPanel_;
    protected JButton forceTriggerButton;
    protected JLabel frequencyLabel;
    private JLabel frequencyLabel_;
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
    private JPanel horizontalFilterLeftPanel_;
    private JPanel horizontalFilterPanel_;
    private JPanel horizontalFilterRightPanel_;
    private JPanel horizontalMathLeftPanel_;
    private JPanel horizontalMathPanel_;
    private JTabbedPane channelTabbedPane;
    private JLabel horizontalOffsetALabel1_;
    private JLabel horizontalOffsetALabel2_;
    protected JSpinner horizontalOffsetASpinner;
    private JLabel horizontalOffsetBLabel1_;
    private JLabel horizontalOffsetBLabel2_;
    protected JSpinner horizontalOffsetBSpinner;
    private JLabel horizontalOffsetFilterLabel1_;
    private JLabel horizontalOffsetFilterLabel2_;
    private JSpinner horizontalOffsetFilterSpinner;
    private JLabel horizontalOffsetMathLabel1_;
    private JLabel horizontalOffsetMathLabel2_;
    protected JSpinner horizontalOffsetMathSpinner;
    protected JComboBox<String> horizontalRangeAComboBox;
    private JLabel horizontalRangeALabel1_;
    private JLabel horizontalRangeALabel2_;
    protected JComboBox<String> horizontalRangeBComboBox;
    private JLabel horizontalRangeBLabel1_;
    private JLabel horizontalRangeBLabel2_;
    private JComboBox<String> horizontalRangeFilterComboBox;
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
    private JPanel horizontalBRightPanel_;
    private JPanel horizontalMathRightPanel_;
    private JPanel leftPanel_;
    protected JButton leftParatheseButton;
    private JPanel mathChannelPanel_;
    protected JLabel maxP2pVoltageLabel;
    private JLabel maxP2pVoltageLabel_;
    protected JLabel maxVoltageLabel;
    private JLabel maxVoltageLabel_;
    private JPanel measurementBottomLeftPanel_;
    private JPanel measurementBottomRightPanel_;
    private JPanel measurementMiddleLeftPanel_;
    private JPanel measurementMiddleRightPanel_;
    private JPanel measurementTopLeftPanel_;
    private JPanel measurementTopRightPanel_;
    private JPanel measurementsPanel_;
    protected JLabel minVoltageLabel;
    private JLabel minVoltageLabel_;
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
    protected JLabel standardDeviationVoltageLabel;
    private JLabel standardDeviationVoltageLabel_;
    private JToolBar toolBar_;
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
    private JSpinner verticalOffsetFilterSpinner;
    private JLabel verticalOffsetMathLabel1_;
    private JLabel verticalOffsetMathLabel2_;
    protected JSpinner verticalOffsetMathSpinner;
    protected JComboBox<String> verticalRangeAComboBox;
    private JLabel verticalRangeALabel1_;
    private JLabel verticalRangeALabel2_;
    protected JComboBox<String> verticalRangeBComboBox;
    private JLabel verticalRangeBLabel1_;
    private JLabel verticalRangeBLabel2_;
    private JComboBox<String> verticalRangeFilterComboBox;
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
    public MainWindowUI() {
        initializeComponents();
        validate();
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println(dim.width + " x " + dim.height);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initializeComponents() {

        toolBar_ = new JToolBar();
        leftPanel_ = new JPanel();
        channelTabbedPane = new JTabbedPane();
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
        mathChannelPanel_ = new JPanel();
        equationPannel_ = new JPanel();
        equationTopPanel_ = new JPanel();
        equationTextField = new JTextField();
        enableMathToggleButton = new JToggleButton();
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
        filterChannelPanel_ = new JPanel();
        inputPanel_ = new JPanel();
        inputTopPanel_ = new JPanel();
        jLabel4 = new JLabel();
        inputChannelComboBox = new JComboBox<String>();
        enableFilterToggleButton = new JToggleButton();
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
        measurementsPanel_ = new JPanel();
        measurementTopLeftPanel_ = new JPanel();
        maxVoltageLabel_ = new JLabel();
        maxVoltageLabel = new JLabel();
        measurementTopRightPanel_ = new JPanel();
        minVoltageLabel_ = new JLabel();
        minVoltageLabel = new JLabel();
        measurementMiddleLeftPanel_ = new JPanel();
        maxP2pVoltageLabel_ = new JLabel();
        maxP2pVoltageLabel = new JLabel();
        measurementMiddleRightPanel_ = new JPanel();
        averageVoltageLabel_ = new JLabel();
        averageVoltageLabel = new JLabel();
        measurementBottomLeftPanel_ = new JPanel();
        standardDeviationVoltageLabel_ = new JLabel();
        standardDeviationVoltageLabel = new JLabel();
        measurementBottomRightPanel_ = new JPanel();
        frequencyLabel_ = new JLabel();
        frequencyLabel = new JLabel();
        rightPanel_ = new JPanel();
        cursorPanel_ = new JPanel();
        canvasPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Constant.APPLICATION_TITLE);
        setMinimumSize(new Dimension(1165, 735));

        toolBar_.setRollover(true);
        toolBar_.setPreferredSize(new Dimension(1180, 40));
        getContentPane().add(toolBar_, BorderLayout.NORTH);

        leftPanel_.setBorder(BorderFactory.createTitledBorder(""));
        leftPanel_.setPreferredSize(new Dimension(370, 650));
        leftPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 5));

        channelTabbedPane.setPreferredSize(new Dimension(366, 275));

        horizontalAPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalAPanel_.setPreferredSize(new Dimension(360, 55));
        horizontalAPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        horizontalALeftPanel_.setPreferredSize(new Dimension(190, 30));

        horizontalRangeALabel1_.setText("Range:");
        horizontalALeftPanel_.add(horizontalRangeALabel1_);

        horizontalRangeAComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
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
        verticalAPannel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        verticalALeftPanel_.setPreferredSize(new Dimension(190, 30));

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

        channelTabbedPane.addTab("Channel A", channelAPanel_);

        horizontalBPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalBPanel_.setPreferredSize(new Dimension(360, 55));
        horizontalBPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        horizontalBLeftPanel_.setPreferredSize(new Dimension(190, 30));

        horizontalRangeBLabel1_.setText("Range:");
        horizontalBLeftPanel_.add(horizontalRangeBLabel1_);

        horizontalRangeBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeBComboBox.setPreferredSize(new Dimension(95, 20));
        horizontalBLeftPanel_.add(horizontalRangeBComboBox);

        horizontalRangeBLabel2_.setText("/div");
        horizontalBLeftPanel_.add(horizontalRangeBLabel2_);

        horizontalBPanel_.add(horizontalBLeftPanel_);

        horizontalBRightPanel_.setPreferredSize(new Dimension(150, 30));
        horizontalBRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetBLabel1_.setText("Offset:");
        horizontalBRightPanel_.add(horizontalOffsetBLabel1_);

        horizontalOffsetBSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalBRightPanel_.add(horizontalOffsetBSpinner);

        horizontalOffsetBLabel2_.setText("s");
        horizontalBRightPanel_.add(horizontalOffsetBLabel2_);

        horizontalBPanel_.add(horizontalBRightPanel_);

        channelBPanel_.add(horizontalBPanel_);

        verticalBPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalBPanel_.setPreferredSize(new Dimension(360, 55));
        verticalBPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        verticalBLeftPanel_.setPreferredSize(new Dimension(190, 30));

        verticalRangeBLabel1_.setText("Range:");
        verticalBLeftPanel_.add(verticalRangeBLabel1_);

        verticalRangeBComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeBComboBox.setPreferredSize(new Dimension(95, 20));
        verticalBLeftPanel_.add(verticalRangeBComboBox);

        verticalRangeBLabel2_.setText("/div");
        verticalBLeftPanel_.add(verticalRangeBLabel2_);

        verticalBPanel_.add(verticalBLeftPanel_);

        verticalBRightPanel_.setPreferredSize(new Dimension(150, 30));
        verticalBRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetBLabel1_.setText("Offset:");
        verticalBRightPanel_.add(verticalOffsetBLabel1_);

        verticalOffsetBSpinner.setPreferredSize(new Dimension(60, 20));
        verticalBRightPanel_.add(verticalOffsetBSpinner);

        verticalOffsetBLabel2_.setText("volts");
        verticalBRightPanel_.add(verticalOffsetBLabel2_);

        verticalBPanel_.add(verticalBRightPanel_);

        channelBPanel_.add(verticalBPanel_);

        channelTabbedPane.addTab("Channel B", channelBPanel_);

        equationPannel_.setBorder(BorderFactory.createTitledBorder("Equation"));
        equationPannel_.setPreferredSize(new Dimension(360, 120));
        equationPannel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        equationTopPanel_.setPreferredSize(new Dimension(340, 35));

        equationTextField.setPreferredSize(new Dimension(260, 23));
        equationTopPanel_.add(equationTextField);

        enableMathToggleButton.setText("Enable");
        equationTopPanel_.add(enableMathToggleButton);

        equationPannel_.add(equationTopPanel_);

        equationBottomPanel_.setPreferredSize(new Dimension(300, 62));

        aButton.setText("A");
        aButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(aButton);

        bButton.setText("B");
        bButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(bButton);

        fButton.setText("F");
        fButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(fButton);

        powerButton.setText("^");
        equationBottomPanel_.add(powerButton);

        plusButton.setText("+");
        equationBottomPanel_.add(plusButton);

        minusButton.setText("-");
        minusButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(minusButton);

        piButton.setText("Pi");
        equationBottomPanel_.add(piButton);

        eButton.setText("e");
        eButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(eButton);

        leftParatheseButton.setText("(");
        leftParatheseButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(leftParatheseButton);

        rightParatheseButton.setText(")");
        rightParatheseButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(rightParatheseButton);

        multiplyButton.setText("*");
        multiplyButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(multiplyButton);

        divideButton.setText("/");
        divideButton.setPreferredSize(new Dimension(41, 23));
        equationBottomPanel_.add(divideButton);

        equationPannel_.add(equationBottomPanel_);

        mathChannelPanel_.add(equationPannel_);

        horizontalMathPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalMathPanel_.setPreferredSize(new Dimension(360, 55));
        horizontalMathPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        horizontalMathLeftPanel_.setPreferredSize(new Dimension(190, 30));

        horizontalRangeMathLabel1_.setText("Range:");
        horizontalMathLeftPanel_.add(horizontalRangeMathLabel1_);

        horizontalRangeMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeMathComboBox.setPreferredSize(new Dimension(95, 20));
        horizontalMathLeftPanel_.add(horizontalRangeMathComboBox);

        horizontalRangeMathLabel2_.setText("/div");
        horizontalMathLeftPanel_.add(horizontalRangeMathLabel2_);

        horizontalMathPanel_.add(horizontalMathLeftPanel_);

        horizontalMathRightPanel_.setPreferredSize(new Dimension(150, 30));
        horizontalMathRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetMathLabel1_.setText("Offset:");
        horizontalMathRightPanel_.add(horizontalOffsetMathLabel1_);

        horizontalOffsetMathSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalMathRightPanel_.add(horizontalOffsetMathSpinner);

        horizontalOffsetMathLabel2_.setText("s");
        horizontalMathRightPanel_.add(horizontalOffsetMathLabel2_);

        horizontalMathPanel_.add(horizontalMathRightPanel_);

        mathChannelPanel_.add(horizontalMathPanel_);

        verticalMathPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalMathPanel_.setPreferredSize(new Dimension(360, 55));
        verticalMathPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        verticalMathLeftPanel_.setPreferredSize(new Dimension(190, 30));

        verticalRangeMathLabel1_.setText("Range:");
        verticalMathLeftPanel_.add(verticalRangeMathLabel1_);

        verticalRangeMathComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeMathComboBox.setPreferredSize(new Dimension(95, 20));
        verticalMathLeftPanel_.add(verticalRangeMathComboBox);

        verticalRangeMathLabel2_.setText("/div");
        verticalMathLeftPanel_.add(verticalRangeMathLabel2_);

        verticalMathPanel_.add(verticalMathLeftPanel_);

        verticalMathRightPanel_.setPreferredSize(new Dimension(150, 30));
        verticalMathRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetMathLabel1_.setText("Offset:");
        verticalMathRightPanel_.add(verticalOffsetMathLabel1_);

        verticalOffsetMathSpinner.setPreferredSize(new Dimension(60, 20));
        verticalMathRightPanel_.add(verticalOffsetMathSpinner);

        verticalOffsetMathLabel2_.setText("volts");
        verticalMathRightPanel_.add(verticalOffsetMathLabel2_);

        verticalMathPanel_.add(verticalMathRightPanel_);

        mathChannelPanel_.add(verticalMathPanel_);

        channelTabbedPane.addTab("Math Channel", mathChannelPanel_);

        inputPanel_.setBorder(BorderFactory.createTitledBorder("Input"));
        inputPanel_.setPreferredSize(new Dimension(360, 95));
        inputPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));

        inputTopPanel_.setPreferredSize(new Dimension(340, 35));

        jLabel4.setText("Input:");
        inputTopPanel_.add(jLabel4);

        inputChannelComboBox.setModel(new DefaultComboBoxModel<String>(Constant.INPUT_CHANNELS));
        inputChannelComboBox.setPreferredSize(new Dimension(222, 20));
        inputTopPanel_.add(inputChannelComboBox);

        enableFilterToggleButton.setText("Enable");
        enableFilterToggleButton.setPreferredSize(new Dimension(67, 23));
        inputTopPanel_.add(enableFilterToggleButton);

        inputPanel_.add(inputTopPanel_);

        inputBottomPanel_.setPreferredSize(new Dimension(340, 35));

        csvFilePathTextField.setEditable(false);
        csvFilePathTextField.setText("Choose CSV file");
        csvFilePathTextField.setToolTipText("");
        csvFilePathTextField.setPreferredSize(new Dimension(260, 23));
        inputBottomPanel_.add(csvFilePathTextField);

        browseButton.setText("Browse");
        inputBottomPanel_.add(browseButton);

        inputPanel_.add(inputBottomPanel_);

        filterChannelPanel_.add(inputPanel_);

        horizontalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalFilterPanel_.setPreferredSize(new Dimension(360, 55));
        horizontalFilterPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        horizontalFilterLeftPanel_.setPreferredSize(new Dimension(190, 30));

        horizontalRangeFilterLabel1_.setText("Range:");
        horizontalFilterLeftPanel_.add(horizontalRangeFilterLabel1_);

        horizontalRangeFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.HORIZONTAL_RANGE_VALUES));
        horizontalRangeFilterComboBox.setPreferredSize(new Dimension(95, 20));
        horizontalFilterLeftPanel_.add(horizontalRangeFilterComboBox);

        horizontalRangeFilterLabel2_.setText("/div");
        horizontalFilterLeftPanel_.add(horizontalRangeFilterLabel2_);

        horizontalFilterPanel_.add(horizontalFilterLeftPanel_);

        horizontalFilterRightPanel_.setPreferredSize(new Dimension(150, 30));
        horizontalFilterRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        horizontalOffsetFilterLabel1_.setText("Offset:");
        horizontalFilterRightPanel_.add(horizontalOffsetFilterLabel1_);

        horizontalOffsetFilterSpinner.setPreferredSize(new Dimension(60, 20));
        horizontalFilterRightPanel_.add(horizontalOffsetFilterSpinner);

        horizontalOffsetFilterLabel2_.setText("s");
        horizontalFilterRightPanel_.add(horizontalOffsetFilterLabel2_);

        horizontalFilterPanel_.add(horizontalFilterRightPanel_);

        filterChannelPanel_.add(horizontalFilterPanel_);

        verticalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalFilterPanel_.setPreferredSize(new Dimension(360, 55));
        verticalFilterPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        verticalFilterLeftPanel_.setPreferredSize(new Dimension(190, 30));

        verticalRangeFilterLabel1_.setText("Range:");
        verticalFilterLeftPanel_.add(verticalRangeFilterLabel1_);

        verticalRangeFilterComboBox.setModel(new DefaultComboBoxModel<String>(Constant.VERTICAL_RANGE_VALUES));
        verticalRangeFilterComboBox.setPreferredSize(new Dimension(95, 20));
        verticalFilterLeftPanel_.add(verticalRangeFilterComboBox);

        verticalRangeFilterLabel2_.setText("/div");
        verticalFilterLeftPanel_.add(verticalRangeFilterLabel2_);

        verticalFilterPanel_.add(verticalFilterLeftPanel_);

        verticalFilterRightPanel_.setPreferredSize(new Dimension(150, 30));
        verticalFilterRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING));

        verticalOffsetFilterLabel1_.setText("Offset:");
        verticalFilterRightPanel_.add(verticalOffsetFilterLabel1_);

        verticalOffsetFilterSpinner.setPreferredSize(new Dimension(60, 20));
        verticalFilterRightPanel_.add(verticalOffsetFilterSpinner);

        verticalOffsetFilterLabel2_.setText("volts");
        verticalFilterRightPanel_.add(verticalOffsetFilterLabel2_);

        verticalFilterPanel_.add(verticalFilterRightPanel_);

        filterChannelPanel_.add(verticalFilterPanel_);

        channelTabbedPane.addTab("Filter Channel", filterChannelPanel_);

        leftPanel_.add(channelTabbedPane);

        triggerPanel_.setBorder(BorderFactory.createTitledBorder("Trigger"));
        triggerPanel_.setPreferredSize(new Dimension(366, 145));
        triggerPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        triggerTopPanel_.setPreferredSize(new Dimension(350, 30));

        triggerStateLabel_.setText("State:");
        triggerTopPanel_.add(triggerStateLabel_);

        triggerStateLabel.setForeground(Color.blue);
        triggerStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        triggerStateLabel.setText("Stop");
        triggerStateLabel.setBorder(BorderFactory.createTitledBorder(""));
        triggerStateLabel.setPreferredSize(new Dimension(80, 19));
        triggerTopPanel_.add(triggerStateLabel);

        triggerPanel_.add(triggerTopPanel_);

        triggerMiddleLeftPanel1_.setPreferredSize(new Dimension(190, 30));

        triggerModeLabel_.setText("Mode:");
        triggerMiddleLeftPanel1_.add(triggerModeLabel_);

        triggerModeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.TRIGGER_MODES));
        triggerModeComboBox.setPreferredSize(new Dimension(110, 20));
        triggerMiddleLeftPanel1_.add(triggerModeComboBox);

        triggerPanel_.add(triggerMiddleLeftPanel1_);

        triggerMiddleRightPanel1_.setPreferredSize(new Dimension(150, 30));
        triggerMiddleRightPanel1_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        forceTriggerButton.setText("Force Trigger");
        forceTriggerButton.setPreferredSize(new Dimension(120, 23));
        triggerMiddleRightPanel1_.add(forceTriggerButton);

        triggerPanel_.add(triggerMiddleRightPanel1_);

        triggerMiddleLeftPanel2_.setPreferredSize(new Dimension(190, 30));

        triggerTypeLabel_.setText("Type:");
        triggerTypeLabel_.setPreferredSize(new Dimension(30, 14));
        triggerMiddleLeftPanel2_.add(triggerTypeLabel_);

        triggerTypeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.TRIGGER_TYPES));
        triggerTypeComboBox.setPreferredSize(new Dimension(110, 20));
        triggerMiddleLeftPanel2_.add(triggerTypeComboBox);

        triggerPanel_.add(triggerMiddleLeftPanel2_);

        triggerMiddleRightPanel2_.setPreferredSize(new Dimension(150, 30));
        triggerMiddleRightPanel2_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

        rearmTriggerButton.setText("Re-arm Trigger");
        rearmTriggerButton.setPreferredSize(new Dimension(120, 23));
        triggerMiddleRightPanel2_.add(rearmTriggerButton);

        triggerPanel_.add(triggerMiddleRightPanel2_);

        triggerBottomPanel.setPreferredSize(new Dimension(350, 30));

        triggerThresholdLabel_.setText("Trigger Threshold:");
        triggerBottomPanel.add(triggerThresholdLabel_);

        triggerThresholdSpinner.setPreferredSize(new Dimension(50, 20));
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
        outputToggleButton.setPreferredSize(new Dimension(65, 23));
        functionGeneratorTopPanel_.add(outputToggleButton);

        functionGeneratorPanel_.add(functionGeneratorTopPanel_);

        functionGeneratorMiddlePanel_.setPreferredSize(new Dimension(350, 30));

        waveTypeLabel_.setText("Wave Type:");
        waveTypeLabel_.setPreferredSize(new Dimension(60, 14));
        functionGeneratorMiddlePanel_.add(waveTypeLabel_);

        waveTypeComboBox.setModel(new DefaultComboBoxModel<String>(Constant.WAVE_TYPES));
        waveTypeComboBox.setPreferredSize(new Dimension(235, 20));
        functionGeneratorMiddlePanel_.add(waveTypeComboBox);

        functionGeneratorPanel_.add(functionGeneratorMiddlePanel_);

        functionGeneratorBottomLeftPanel_.setPreferredSize(new Dimension(190, 30));

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

        measurementsPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsPanel_.setPreferredSize(new Dimension(366, 97));
        measurementsPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 0));

        measurementTopLeftPanel_.setPreferredSize(new Dimension(150, 25));
        measurementTopLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 3));

        maxVoltageLabel_.setText("Max Voltage:");
        measurementTopLeftPanel_.add(maxVoltageLabel_);

        maxVoltageLabel.setText("00.00");
        maxVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageLabel.setPreferredSize(new Dimension(35, 19));
        measurementTopLeftPanel_.add(maxVoltageLabel);

        measurementsPanel_.add(measurementTopLeftPanel_);

        measurementTopRightPanel_.setPreferredSize(new Dimension(150, 25));
        measurementTopRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 3));

        minVoltageLabel_.setText("Min Voltage:");
        measurementTopRightPanel_.add(minVoltageLabel_);

        minVoltageLabel.setText("00.00");
        minVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageLabel.setPreferredSize(new Dimension(35, 19));
        measurementTopRightPanel_.add(minVoltageLabel);

        measurementsPanel_.add(measurementTopRightPanel_);

        measurementMiddleLeftPanel_.setPreferredSize(new Dimension(150, 25));
        measurementMiddleLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 3));

        maxP2pVoltageLabel_.setText("Max P2P Voltage:");
        measurementMiddleLeftPanel_.add(maxP2pVoltageLabel_);

        maxP2pVoltageLabel.setText("00.00");
        maxP2pVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageLabel.setPreferredSize(new Dimension(35, 19));
        measurementMiddleLeftPanel_.add(maxP2pVoltageLabel);

        measurementsPanel_.add(measurementMiddleLeftPanel_);

        measurementMiddleRightPanel_.setPreferredSize(new Dimension(150, 25));
        measurementMiddleRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 3));

        averageVoltageLabel_.setText("Average Voltage:");
        measurementMiddleRightPanel_.add(averageVoltageLabel_);

        averageVoltageLabel.setText("00.00");
        averageVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageLabel.setPreferredSize(new Dimension(35, 19));
        measurementMiddleRightPanel_.add(averageVoltageLabel);

        measurementsPanel_.add(measurementMiddleRightPanel_);

        measurementBottomLeftPanel_.setPreferredSize(new Dimension(150, 25));
        measurementBottomLeftPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 3));

        standardDeviationVoltageLabel_.setText("SD of Voltage:");
        measurementBottomLeftPanel_.add(standardDeviationVoltageLabel_);

        standardDeviationVoltageLabel.setText("00.00");
        standardDeviationVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageLabel.setPreferredSize(new Dimension(35, 19));
        measurementBottomLeftPanel_.add(standardDeviationVoltageLabel);

        measurementsPanel_.add(measurementBottomLeftPanel_);

        measurementBottomRightPanel_.setPreferredSize(new Dimension(150, 25));
        measurementBottomRightPanel_.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 3));

        frequencyLabel_.setText("Frequency:");
        measurementBottomRightPanel_.add(frequencyLabel_);

        frequencyLabel.setText("00.00");
        frequencyLabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyLabel.setPreferredSize(new Dimension(35, 19));
        measurementBottomRightPanel_.add(frequencyLabel);

        measurementsPanel_.add(measurementBottomRightPanel_);

        leftPanel_.add(measurementsPanel_);

        getContentPane().add(leftPanel_, BorderLayout.WEST);

        rightPanel_.setBorder(BorderFactory.createTitledBorder(""));
        rightPanel_.setMinimumSize(new Dimension(780, 650));
        rightPanel_.setPreferredSize(new Dimension(780, 650));
        rightPanel_.setLayout(new BorderLayout());

        cursorPanel_.setPreferredSize(new Dimension(776, 40));
        cursorPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 4));
        rightPanel_.add(cursorPanel_, BorderLayout.SOUTH);
        rightPanel_.add(canvasPanel, BorderLayout.CENTER);

        getContentPane().add(rightPanel_, BorderLayout.CENTER);

        setSize(new Dimension(1166, 735));
        setLocationRelativeTo(null);
    }

}
