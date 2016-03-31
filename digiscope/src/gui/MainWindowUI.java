package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author ToanHo
 */
public class MainWindowUI extends JFrame {

    private JButton aButton;
    protected JLabel averageVoltageLabel;
    private JButton bButton;
    private JButton browseButton;
    private JPanel canvasPanel;
    private JPanel channelAPanel_;
    private JPanel channelBPanel_;
    private JTabbedPane channelTabbedPane;
    private JTextField csvFilePathTextField;
    private JPanel cursorPanel_;
    private JButton divideButton;
    private JButton eButton;
    private JToggleButton enableFilterToggleButton;
    private JToggleButton enableMathToggleButton;
    private JPanel equationPannel_;
    private JTextField equationTextField;
    private JButton fButton;
    private JPanel filterChannelPanel_;
    private JButton forceTriggerButton;
    protected JLabel frequencyLabel;
    private JSpinner functionGeneratorOffsetSpinner;
    private JPanel functionGeneratorPanel_;
    private JPanel horizontalAPanel_;
    private JPanel horizontalBPanel_;
    private JPanel horizontalFilterPanel_;
    private JPanel horizontalMathPanel_;
    private JSpinner horizontalOffsetASpinner;
    private JSpinner horizontalOffsetBSpinner;
    private JSpinner horizontalOffsetFilterSpinner;
    private JSpinner horizontalOffsetMathSpinner;
    private JComboBox horizontalRangeAComboBox;
    private JComboBox horizontalRangeBComboBox;
    private JComboBox horizontalRangeFilterComboBox;
    private JLabel horizontalRangeLabel_1;
    private JLabel horizontalRangeLabel_2;
    private JLabel horizontalRangeLabel_20;
    private JLabel horizontalRangeLabel_29;
    private JLabel horizontalRangeLabel_3;
    private JLabel horizontalRangeLabel_30;
    private JLabel horizontalRangeLabel_31;
    private JLabel horizontalRangeLabel_32;
    private JLabel horizontalRangeLabel_33;
    private JLabel horizontalRangeLabel_34;
    private JLabel horizontalRangeLabel_35;
    private JLabel horizontalRangeLabel_36;
    private JLabel horizontalRangeLabel_37;
    private JLabel horizontalRangeLabel_38;
    private JLabel horizontalRangeLabel_39;
    private JLabel horizontalRangeLabel_40;
    private JLabel horizontalRangeLabel_41;
    private JLabel horizontalRangeLabel_42;
    private JLabel horizontalRangeLabel_43;
    private JLabel horizontalRangeLabel_44;
    private JLabel horizontalRangeLabel_45;
    private JLabel horizontalRangeLabel_46;
    private JLabel horizontalRangeLabel_47;
    private JLabel horizontalRangeLabel_48;
    private JLabel horizontalRangeLabel_49;
    private JLabel horizontalRangeLabel_50;
    private JLabel horizontalRangeLabel_51;
    private JLabel horizontalRangeLabel_52;
    private JLabel horizontalRangeLabel_53;
    private JLabel horizontalRangeLabel_54;
    private JLabel horizontalRangeLabel_55;
    private JLabel horizontalRangeLabel_56;
    private JLabel horizontalRangeLabel_57;
    private JLabel horizontalRangeLabel_58;
    private JComboBox horizontalRangeMathComboBox;
    private JComboBox inputChannelComboBox;
    private JPanel inputPanel_;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel16;
    private JLabel jLabel18;
    private JLabel jLabel2;
    private JLabel jLabel20;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JPanel jPanel14;
    private JPanel jPanel15;
    private JPanel jPanel16;
    private JPanel jPanel17;
    private JPanel jPanel18;
    private JPanel jPanel19;
    private JPanel jPanel20;
    private JPanel jPanel21;
    private JPanel jPanel22;
    private JPanel jPanel23;
    private JPanel jPanel24;
    private JPanel jPanel25;
    private JPanel jPanel26;
    private JPanel jPanel27;
    private JPanel jPanel28;
    private JPanel jPanel29;
    private JPanel jPanel30;
    private JPanel jPanel31;
    private JPanel jPanel32;
    private JPanel jPanel33;
    private JPanel jPanel34;
    private JPanel jPanel35;
    private JPanel jPanel36;
    private JPanel jPanel37;
    private JPanel jPanel38;
    private JPanel jPanel4;
    private JPanel jPanel40;
    private JPanel jPanel41;
    private JPanel jPanel42;
    private JPanel jPanel43;
    private JPanel jPanel44;
    private JPanel jPanel45;
    private JPanel jPanel46;
    private JPanel jPanel47;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel leftPanel_;
    private JButton leftParatheseButton;
    private JPanel mathChannelPanel_;
    protected JLabel maxP2pVoltageLabel;
    protected JLabel maxVoltageLabel;
    private JPanel measurementsPanel_;
    protected JLabel minVoltageLabel;
    private JButton minusButton;
    private JButton multiplyButton;
    private JToggleButton outputToggleButton;
    private JTextField p2pVoltageTextField;
    private JButton piButton;
    private JButton plusButton;
    private JButton powerButton;
    private JButton rearmTriggerButton;
    private JPanel rightPanel_;
    private JButton rightParatheseButton;
    protected JLabel standardDeviationVoltageLabel;
    private JToolBar toolBar_;
    private JComboBox triggerModeComboBox;
    private JPanel triggerPanel_;
    private JLabel triggerStateLabel;
    private JSpinner triggerThresholdSpinner;
    private JComboBox triggerTypeComboBox;
    private JPanel verticalAPannel_;
    private JPanel verticalBPanel_;
    private JPanel verticalFilterPanel_;
    private JPanel verticalMathPanel_;
    private JSpinner verticalOffsetASpinner;
    private JSpinner verticalOffsetBSpinner;
    private JSpinner verticalOffsetFilterSpinner;
    private JSpinner verticalOffsetMathSpinner;
    private JComboBox verticalRangeAComboBox;
    private JComboBox verticalRangeBComboBox;
    private JComboBox verticalRangeFilterComboBox;
    private JComboBox verticalRangeMathComboBox;
    private JComboBox waveTypeComboBox;
    /**
     * Creates new form MainWindowUI
     */
    public MainWindowUI() {
        initializeComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(dim.width + " x " + dim.height);
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
        jPanel5 = new JPanel();
        horizontalRangeLabel_3 = new JLabel();
        horizontalRangeAComboBox = new JComboBox();
        horizontalRangeLabel_1 = new JLabel();
        jPanel6 = new JPanel();
        horizontalRangeLabel_2 = new JLabel();
        horizontalOffsetASpinner = new JSpinner();
        horizontalRangeLabel_20 = new JLabel();
        verticalAPannel_ = new JPanel();
        jPanel36 = new JPanel();
        horizontalRangeLabel_55 = new JLabel();
        verticalRangeAComboBox = new JComboBox();
        horizontalRangeLabel_56 = new JLabel();
        jPanel37 = new JPanel();
        horizontalRangeLabel_57 = new JLabel();
        verticalOffsetASpinner = new JSpinner();
        horizontalRangeLabel_58 = new JLabel();
        channelBPanel_ = new JPanel();
        horizontalBPanel_ = new JPanel();
        jPanel14 = new JPanel();
        horizontalRangeLabel_29 = new JLabel();
        horizontalRangeBComboBox = new JComboBox();
        horizontalRangeLabel_30 = new JLabel();
        jPanel15 = new JPanel();
        horizontalRangeLabel_31 = new JLabel();
        horizontalOffsetBSpinner = new JSpinner();
        horizontalRangeLabel_32 = new JLabel();
        verticalBPanel_ = new JPanel();
        jPanel16 = new JPanel();
        horizontalRangeLabel_33 = new JLabel();
        verticalRangeBComboBox = new JComboBox();
        horizontalRangeLabel_34 = new JLabel();
        jPanel17 = new JPanel();
        horizontalRangeLabel_35 = new JLabel();
        verticalOffsetBSpinner = new JSpinner();
        horizontalRangeLabel_36 = new JLabel();
        mathChannelPanel_ = new JPanel();
        equationPannel_ = new JPanel();
        jPanel28 = new JPanel();
        equationTextField = new JTextField();
        enableMathToggleButton = new JToggleButton();
        jPanel35 = new JPanel();
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
        jPanel18 = new JPanel();
        horizontalRangeLabel_37 = new JLabel();
        horizontalRangeMathComboBox = new JComboBox();
        horizontalRangeLabel_38 = new JLabel();
        jPanel19 = new JPanel();
        horizontalRangeLabel_39 = new JLabel();
        horizontalOffsetMathSpinner = new JSpinner();
        horizontalRangeLabel_40 = new JLabel();
        verticalMathPanel_ = new JPanel();
        jPanel20 = new JPanel();
        horizontalRangeLabel_41 = new JLabel();
        verticalRangeMathComboBox = new JComboBox();
        horizontalRangeLabel_42 = new JLabel();
        jPanel21 = new JPanel();
        horizontalRangeLabel_43 = new JLabel();
        verticalOffsetMathSpinner = new JSpinner();
        horizontalRangeLabel_44 = new JLabel();
        filterChannelPanel_ = new JPanel();
        inputPanel_ = new JPanel();
        jPanel34 = new JPanel();
        jLabel4 = new JLabel();
        inputChannelComboBox = new JComboBox();
        enableFilterToggleButton = new JToggleButton();
        jPanel29 = new JPanel();
        csvFilePathTextField = new JTextField();
        browseButton = new JButton();
        horizontalFilterPanel_ = new JPanel();
        jPanel22 = new JPanel();
        horizontalRangeLabel_45 = new JLabel();
        horizontalRangeFilterComboBox = new JComboBox();
        horizontalRangeLabel_46 = new JLabel();
        jPanel23 = new JPanel();
        horizontalRangeLabel_47 = new JLabel();
        horizontalOffsetFilterSpinner = new JSpinner();
        horizontalRangeLabel_48 = new JLabel();
        verticalFilterPanel_ = new JPanel();
        jPanel24 = new JPanel();
        horizontalRangeLabel_49 = new JLabel();
        verticalRangeFilterComboBox = new JComboBox();
        horizontalRangeLabel_50 = new JLabel();
        jPanel25 = new JPanel();
        horizontalRangeLabel_51 = new JLabel();
        verticalOffsetFilterSpinner = new JSpinner();
        horizontalRangeLabel_52 = new JLabel();
        triggerPanel_ = new JPanel();
        jPanel26 = new JPanel();
        jLabel5 = new JLabel();
        triggerStateLabel = new JLabel();
        jPanel30 = new JPanel();
        jLabel1 = new JLabel();
        triggerModeComboBox = new JComboBox();
        jPanel31 = new JPanel();
        forceTriggerButton = new JButton();
        jPanel32 = new JPanel();
        jLabel3 = new JLabel();
        triggerTypeComboBox = new JComboBox();
        jPanel33 = new JPanel();
        rearmTriggerButton = new JButton();
        jPanel4 = new JPanel();
        jLabel2 = new JLabel();
        triggerThresholdSpinner = new JSpinner();
        functionGeneratorPanel_ = new JPanel();
        jPanel27 = new JPanel();
        jLabel7 = new JLabel();
        outputToggleButton = new JToggleButton();
        jPanel40 = new JPanel();
        jLabel10 = new JLabel();
        waveTypeComboBox = new JComboBox();
        jPanel38 = new JPanel();
        jLabel11 = new JLabel();
        p2pVoltageTextField = new JTextField();
        jPanel41 = new JPanel();
        horizontalRangeLabel_53 = new JLabel();
        functionGeneratorOffsetSpinner = new JSpinner();
        horizontalRangeLabel_54 = new JLabel();
        measurementsPanel_ = new JPanel();
        jPanel42 = new JPanel();
        jLabel12 = new JLabel();
        maxVoltageLabel = new JLabel();
        jPanel43 = new JPanel();
        jLabel13 = new JLabel();
        minVoltageLabel = new JLabel();
        jPanel44 = new JPanel();
        jLabel14 = new JLabel();
        maxP2pVoltageLabel = new JLabel();
        jPanel45 = new JPanel();
        jLabel16 = new JLabel();
        averageVoltageLabel = new JLabel();
        jPanel46 = new JPanel();
        jLabel18 = new JLabel();
        standardDeviationVoltageLabel = new JLabel();
        jPanel47 = new JPanel();
        jLabel20 = new JLabel();
        frequencyLabel = new JLabel();
        rightPanel_ = new JPanel();
        cursorPanel_ = new JPanel();
        canvasPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Digiscope");
        setMinimumSize(new java.awt.Dimension(1165, 735));

        toolBar_.setRollover(true);
        toolBar_.setPreferredSize(new java.awt.Dimension(1180, 40));
        getContentPane().add(toolBar_, java.awt.BorderLayout.NORTH);

        leftPanel_.setBorder(BorderFactory.createTitledBorder(""));
        leftPanel_.setPreferredSize(new java.awt.Dimension(370, 650));
        leftPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 5));

        channelTabbedPane.setPreferredSize(new java.awt.Dimension(366, 275));

        horizontalAPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalAPanel_.setPreferredSize(new java.awt.Dimension(360, 55));
        horizontalAPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel5.setPreferredSize(new java.awt.Dimension(190, 30));

        horizontalRangeLabel_3.setText("Range:");
        jPanel5.add(horizontalRangeLabel_3);

        horizontalRangeAComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        horizontalRangeAComboBox.setPreferredSize(new java.awt.Dimension(95, 20));
        jPanel5.add(horizontalRangeAComboBox);

        horizontalRangeLabel_1.setText("/div");
        jPanel5.add(horizontalRangeLabel_1);

        horizontalAPanel_.add(jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_2.setText("Offset:");
        jPanel6.add(horizontalRangeLabel_2);

        horizontalOffsetASpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel6.add(horizontalOffsetASpinner);

        horizontalRangeLabel_20.setText("s");
        jPanel6.add(horizontalRangeLabel_20);

        horizontalAPanel_.add(jPanel6);

        channelAPanel_.add(horizontalAPanel_);

        verticalAPannel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalAPannel_.setPreferredSize(new java.awt.Dimension(360, 55));
        verticalAPannel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel36.setPreferredSize(new java.awt.Dimension(190, 30));

        horizontalRangeLabel_55.setText("Range:");
        jPanel36.add(horizontalRangeLabel_55);

        verticalRangeAComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        verticalRangeAComboBox.setPreferredSize(new java.awt.Dimension(95, 20));
        jPanel36.add(verticalRangeAComboBox);

        horizontalRangeLabel_56.setText("/div");
        jPanel36.add(horizontalRangeLabel_56);

        verticalAPannel_.add(jPanel36);

        jPanel37.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel37.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_57.setText("Offset:");
        jPanel37.add(horizontalRangeLabel_57);

        verticalOffsetASpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel37.add(verticalOffsetASpinner);

        horizontalRangeLabel_58.setText("volts");
        jPanel37.add(horizontalRangeLabel_58);

        verticalAPannel_.add(jPanel37);

        channelAPanel_.add(verticalAPannel_);

        channelTabbedPane.addTab("Channel A", channelAPanel_);

        horizontalBPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalBPanel_.setPreferredSize(new java.awt.Dimension(360, 55));
        horizontalBPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel14.setPreferredSize(new java.awt.Dimension(190, 30));

        horizontalRangeLabel_29.setText("Range:");
        jPanel14.add(horizontalRangeLabel_29);

        horizontalRangeBComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        horizontalRangeBComboBox.setPreferredSize(new java.awt.Dimension(95, 20));
        jPanel14.add(horizontalRangeBComboBox);

        horizontalRangeLabel_30.setText("/div");
        jPanel14.add(horizontalRangeLabel_30);

        horizontalBPanel_.add(jPanel14);

        jPanel15.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_31.setText("Offset:");
        jPanel15.add(horizontalRangeLabel_31);

        horizontalOffsetBSpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel15.add(horizontalOffsetBSpinner);

        horizontalRangeLabel_32.setText("s");
        jPanel15.add(horizontalRangeLabel_32);

        horizontalBPanel_.add(jPanel15);

        channelBPanel_.add(horizontalBPanel_);

        verticalBPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalBPanel_.setPreferredSize(new java.awt.Dimension(360, 55));
        verticalBPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel16.setPreferredSize(new java.awt.Dimension(190, 30));

        horizontalRangeLabel_33.setText("Range:");
        jPanel16.add(horizontalRangeLabel_33);

        verticalRangeBComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        verticalRangeBComboBox.setPreferredSize(new java.awt.Dimension(95, 20));
        jPanel16.add(verticalRangeBComboBox);

        horizontalRangeLabel_34.setText("/div");
        jPanel16.add(horizontalRangeLabel_34);

        verticalBPanel_.add(jPanel16);

        jPanel17.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_35.setText("Offset:");
        jPanel17.add(horizontalRangeLabel_35);

        verticalOffsetBSpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel17.add(verticalOffsetBSpinner);

        horizontalRangeLabel_36.setText("volts");
        jPanel17.add(horizontalRangeLabel_36);

        verticalBPanel_.add(jPanel17);

        channelBPanel_.add(verticalBPanel_);

        channelTabbedPane.addTab("Channel B", channelBPanel_);

        equationPannel_.setBorder(BorderFactory.createTitledBorder("Equation"));
        equationPannel_.setPreferredSize(new java.awt.Dimension(360, 120));
        equationPannel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jPanel28.setPreferredSize(new java.awt.Dimension(340, 35));

        equationTextField.setPreferredSize(new java.awt.Dimension(260, 23));
        jPanel28.add(equationTextField);

        enableMathToggleButton.setText("Enable");
        jPanel28.add(enableMathToggleButton);

        equationPannel_.add(jPanel28);

        jPanel35.setPreferredSize(new java.awt.Dimension(300, 62));

        aButton.setText("A");
        aButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(aButton);

        bButton.setText("B");
        bButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(bButton);

        fButton.setText("F");
        fButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(fButton);

        powerButton.setText("^");
        jPanel35.add(powerButton);

        plusButton.setText("+");
        jPanel35.add(plusButton);

        minusButton.setText("-");
        minusButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(minusButton);

        piButton.setText("Pi");
        jPanel35.add(piButton);

        eButton.setText("e");
        eButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(eButton);

        leftParatheseButton.setText("(");
        leftParatheseButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(leftParatheseButton);

        rightParatheseButton.setText(")");
        rightParatheseButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(rightParatheseButton);

        multiplyButton.setText("*");
        multiplyButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(multiplyButton);

        divideButton.setText("/");
        divideButton.setPreferredSize(new java.awt.Dimension(41, 23));
        jPanel35.add(divideButton);

        equationPannel_.add(jPanel35);

        mathChannelPanel_.add(equationPannel_);

        horizontalMathPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalMathPanel_.setPreferredSize(new java.awt.Dimension(360, 55));
        horizontalMathPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel18.setPreferredSize(new java.awt.Dimension(190, 30));

        horizontalRangeLabel_37.setText("Range:");
        jPanel18.add(horizontalRangeLabel_37);

        horizontalRangeMathComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        horizontalRangeMathComboBox.setPreferredSize(new java.awt.Dimension(95, 20));
        jPanel18.add(horizontalRangeMathComboBox);

        horizontalRangeLabel_38.setText("/div");
        jPanel18.add(horizontalRangeLabel_38);

        horizontalMathPanel_.add(jPanel18);

        jPanel19.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_39.setText("Offset:");
        jPanel19.add(horizontalRangeLabel_39);

        horizontalOffsetMathSpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel19.add(horizontalOffsetMathSpinner);

        horizontalRangeLabel_40.setText("s");
        jPanel19.add(horizontalRangeLabel_40);

        horizontalMathPanel_.add(jPanel19);

        mathChannelPanel_.add(horizontalMathPanel_);

        verticalMathPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalMathPanel_.setPreferredSize(new java.awt.Dimension(360, 55));
        verticalMathPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel20.setPreferredSize(new java.awt.Dimension(190, 30));

        horizontalRangeLabel_41.setText("Range:");
        jPanel20.add(horizontalRangeLabel_41);

        verticalRangeMathComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        verticalRangeMathComboBox.setPreferredSize(new java.awt.Dimension(95, 20));
        jPanel20.add(verticalRangeMathComboBox);

        horizontalRangeLabel_42.setText("/div");
        jPanel20.add(horizontalRangeLabel_42);

        verticalMathPanel_.add(jPanel20);

        jPanel21.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_43.setText("Offset:");
        jPanel21.add(horizontalRangeLabel_43);

        verticalOffsetMathSpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel21.add(verticalOffsetMathSpinner);

        horizontalRangeLabel_44.setText("volts");
        jPanel21.add(horizontalRangeLabel_44);

        verticalMathPanel_.add(jPanel21);

        mathChannelPanel_.add(verticalMathPanel_);

        channelTabbedPane.addTab("Math Channel", mathChannelPanel_);

        inputPanel_.setBorder(BorderFactory.createTitledBorder("Input"));
        inputPanel_.setPreferredSize(new java.awt.Dimension(360, 95));
        inputPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jPanel34.setPreferredSize(new java.awt.Dimension(340, 35));

        jLabel4.setText("Input:");
        jPanel34.add(jLabel4);

        inputChannelComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputChannelComboBox.setPreferredSize(new java.awt.Dimension(222, 20));
        jPanel34.add(inputChannelComboBox);

        enableFilterToggleButton.setText("Enable");
        enableFilterToggleButton.setPreferredSize(new java.awt.Dimension(67, 23));
        jPanel34.add(enableFilterToggleButton);

        inputPanel_.add(jPanel34);

        jPanel29.setPreferredSize(new java.awt.Dimension(340, 35));

        csvFilePathTextField.setEditable(false);
        csvFilePathTextField.setText("Choose CSV file");
        csvFilePathTextField.setToolTipText("");
        csvFilePathTextField.setPreferredSize(new java.awt.Dimension(260, 23));
        jPanel29.add(csvFilePathTextField);

        browseButton.setText("Browse");
        jPanel29.add(browseButton);

        inputPanel_.add(jPanel29);

        filterChannelPanel_.add(inputPanel_);

        horizontalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        horizontalFilterPanel_.setPreferredSize(new java.awt.Dimension(360, 55));
        horizontalFilterPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel22.setPreferredSize(new java.awt.Dimension(190, 30));

        horizontalRangeLabel_45.setText("Range:");
        jPanel22.add(horizontalRangeLabel_45);

        horizontalRangeFilterComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        horizontalRangeFilterComboBox.setPreferredSize(new java.awt.Dimension(95, 20));
        jPanel22.add(horizontalRangeFilterComboBox);

        horizontalRangeLabel_46.setText("/div");
        jPanel22.add(horizontalRangeLabel_46);

        horizontalFilterPanel_.add(jPanel22);

        jPanel23.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_47.setText("Offset:");
        jPanel23.add(horizontalRangeLabel_47);

        horizontalOffsetFilterSpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel23.add(horizontalOffsetFilterSpinner);

        horizontalRangeLabel_48.setText("s");
        jPanel23.add(horizontalRangeLabel_48);

        horizontalFilterPanel_.add(jPanel23);

        filterChannelPanel_.add(horizontalFilterPanel_);

        verticalFilterPanel_.setBorder(BorderFactory.createTitledBorder("Vertical"));
        verticalFilterPanel_.setPreferredSize(new java.awt.Dimension(360, 55));
        verticalFilterPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jPanel24.setPreferredSize(new java.awt.Dimension(190, 30));

        horizontalRangeLabel_49.setText("Range:");
        jPanel24.add(horizontalRangeLabel_49);

        verticalRangeFilterComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        verticalRangeFilterComboBox.setPreferredSize(new java.awt.Dimension(95, 20));
        jPanel24.add(verticalRangeFilterComboBox);

        horizontalRangeLabel_50.setText("/div");
        jPanel24.add(horizontalRangeLabel_50);

        verticalFilterPanel_.add(jPanel24);

        jPanel25.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_51.setText("Offset:");
        jPanel25.add(horizontalRangeLabel_51);

        verticalOffsetFilterSpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel25.add(verticalOffsetFilterSpinner);

        horizontalRangeLabel_52.setText("volts");
        jPanel25.add(horizontalRangeLabel_52);

        verticalFilterPanel_.add(jPanel25);

        filterChannelPanel_.add(verticalFilterPanel_);

        channelTabbedPane.addTab("Filter Channel", filterChannelPanel_);

        leftPanel_.add(channelTabbedPane);

        triggerPanel_.setBorder(BorderFactory.createTitledBorder("Trigger"));
        triggerPanel_.setPreferredSize(new java.awt.Dimension(366, 145));
        triggerPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0));

        jPanel26.setPreferredSize(new java.awt.Dimension(350, 30));

        jLabel5.setText("State:");
        jPanel26.add(jLabel5);

        triggerStateLabel.setForeground(java.awt.Color.blue);
        //triggeretHorizontalAlignment(SwingConstants.CENTER);
        
        triggerStateLabel.setText("Stop");
        triggerStateLabel.setBorder(BorderFactory.createTitledBorder(""));
        triggerStateLabel.setPreferredSize(new java.awt.Dimension(80, 19));
        jPanel26.add(triggerStateLabel);

        triggerPanel_.add(jPanel26);

        jPanel30.setPreferredSize(new java.awt.Dimension(190, 30));

        jLabel1.setText("Mode:");
        jPanel30.add(jLabel1);

        triggerModeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        triggerModeComboBox.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel30.add(triggerModeComboBox);

        triggerPanel_.add(jPanel30);

        jPanel31.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel31.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 3));

        forceTriggerButton.setText("Force Trigger");
        forceTriggerButton.setPreferredSize(new java.awt.Dimension(120, 23));
        jPanel31.add(forceTriggerButton);

        triggerPanel_.add(jPanel31);

        jPanel32.setPreferredSize(new java.awt.Dimension(190, 30));

        jLabel3.setText("Type:");
        jLabel3.setPreferredSize(new java.awt.Dimension(30, 14));
        jPanel32.add(jLabel3);

        triggerTypeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        triggerTypeComboBox.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel32.add(triggerTypeComboBox);

        triggerPanel_.add(jPanel32);

        jPanel33.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel33.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 3));

        rearmTriggerButton.setText("Re-arm Trigger");
        rearmTriggerButton.setPreferredSize(new java.awt.Dimension(120, 23));
        jPanel33.add(rearmTriggerButton);

        triggerPanel_.add(jPanel33);

        jPanel4.setPreferredSize(new java.awt.Dimension(350, 30));

        jLabel2.setText("Trigger Threshold:");
        jPanel4.add(jLabel2);

        triggerThresholdSpinner.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel4.add(triggerThresholdSpinner);

        triggerPanel_.add(jPanel4);

        leftPanel_.add(triggerPanel_);

        functionGeneratorPanel_.setBorder(BorderFactory.createTitledBorder("Function Generator"));
        functionGeneratorPanel_.setPreferredSize(new java.awt.Dimension(366, 115));
        functionGeneratorPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0));

        jPanel27.setPreferredSize(new java.awt.Dimension(350, 30));
        jPanel27.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 3));

        jLabel7.setText("Output");
        jPanel27.add(jLabel7);

        outputToggleButton.setText("On");
        outputToggleButton.setPreferredSize(new java.awt.Dimension(65, 23));
        jPanel27.add(outputToggleButton);

        functionGeneratorPanel_.add(jPanel27);

        jPanel40.setPreferredSize(new java.awt.Dimension(350, 30));

        jLabel10.setText("Wave Type:");
        jLabel10.setPreferredSize(new java.awt.Dimension(60, 14));
        jPanel40.add(jLabel10);

        waveTypeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        waveTypeComboBox.setPreferredSize(new java.awt.Dimension(235, 20));
        jPanel40.add(waveTypeComboBox);

        functionGeneratorPanel_.add(jPanel40);

        jPanel38.setPreferredSize(new java.awt.Dimension(190, 30));

        jLabel11.setText("P2P Voltage:");
        jPanel38.add(jLabel11);

        p2pVoltageTextField.setPreferredSize(new java.awt.Dimension(75, 20));
        jPanel38.add(p2pVoltageTextField);

        functionGeneratorPanel_.add(jPanel38);

        jPanel41.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        horizontalRangeLabel_53.setText("Offset:");
        jPanel41.add(horizontalRangeLabel_53);

        functionGeneratorOffsetSpinner.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel41.add(functionGeneratorOffsetSpinner);

        horizontalRangeLabel_54.setText("volts");
        jPanel41.add(horizontalRangeLabel_54);

        functionGeneratorPanel_.add(jPanel41);

        leftPanel_.add(functionGeneratorPanel_);

        measurementsPanel_.setBorder(BorderFactory.createTitledBorder("Measurements"));
        measurementsPanel_.setPreferredSize(new java.awt.Dimension(366, 97));
        measurementsPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 15, 0));

        jPanel42.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel42.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 8, 3));

        jLabel12.setText("Max Voltage:");
        jPanel42.add(jLabel12);

        maxVoltageLabel.setText("00.00");
        maxVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxVoltageLabel.setPreferredSize(new java.awt.Dimension(35, 19));
        jPanel42.add(maxVoltageLabel);

        measurementsPanel_.add(jPanel42);

        jPanel43.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel43.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 3));

        jLabel13.setText("Min Voltage:");
        jPanel43.add(jLabel13);

        minVoltageLabel.setText("00.00");
        minVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        minVoltageLabel.setPreferredSize(new java.awt.Dimension(35, 19));
        jPanel43.add(minVoltageLabel);

        measurementsPanel_.add(jPanel43);

        jPanel44.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel44.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 8, 3));

        jLabel14.setText("Max P2P Voltage:");
        jPanel44.add(jLabel14);

        maxP2pVoltageLabel.setText("00.00");
        maxP2pVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        maxP2pVoltageLabel.setPreferredSize(new java.awt.Dimension(35, 19));
        jPanel44.add(maxP2pVoltageLabel);

        measurementsPanel_.add(jPanel44);

        jPanel45.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel45.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 3));

        jLabel16.setText("Average Voltage:");
        jPanel45.add(jLabel16);

        averageVoltageLabel.setText("00.00");
        averageVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        averageVoltageLabel.setPreferredSize(new java.awt.Dimension(35, 19));
        jPanel45.add(averageVoltageLabel);

        measurementsPanel_.add(jPanel45);

        jPanel46.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel46.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 8, 3));

        jLabel18.setText("SD of Voltage:");
        jPanel46.add(jLabel18);

        standardDeviationVoltageLabel.setText("00.00");
        standardDeviationVoltageLabel.setBorder(BorderFactory.createTitledBorder(""));
        standardDeviationVoltageLabel.setPreferredSize(new java.awt.Dimension(35, 19));
        jPanel46.add(standardDeviationVoltageLabel);

        measurementsPanel_.add(jPanel46);

        jPanel47.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel47.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 3));

        jLabel20.setText("Frequency:");
        jPanel47.add(jLabel20);

        frequencyLabel.setText("00.00");
        frequencyLabel.setBorder(BorderFactory.createTitledBorder(""));
        frequencyLabel.setPreferredSize(new java.awt.Dimension(35, 19));
        jPanel47.add(frequencyLabel);

        measurementsPanel_.add(jPanel47);

        leftPanel_.add(measurementsPanel_);

        getContentPane().add(leftPanel_, java.awt.BorderLayout.WEST);

        rightPanel_.setBorder(BorderFactory.createTitledBorder(""));
        rightPanel_.setMinimumSize(new java.awt.Dimension(780, 650));
        rightPanel_.setPreferredSize(new java.awt.Dimension(780, 650));
        rightPanel_.setLayout(new java.awt.BorderLayout());

        cursorPanel_.setPreferredSize(new java.awt.Dimension(776, 40));
        cursorPanel_.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 4));
        rightPanel_.add(cursorPanel_, java.awt.BorderLayout.SOUTH);
        rightPanel_.add(canvasPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(rightPanel_, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1166, 735));
        setLocationRelativeTo(null);
    }
}
