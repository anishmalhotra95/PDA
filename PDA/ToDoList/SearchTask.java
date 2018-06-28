package ToDoList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class SearchTask implements ActionListener
{    
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 190;

	Font fTitle,fLabel;
   	JFrame frame;
	JTabbedPane tabPane;
   	JPanel panel,panelmain,panelTab1,panelTab2,panelTab3,panelTab4,panellabel,panelbutton;
   	JButton buttonDisplayDateR;
	JButton buttonDisplayTaskR;
   	JButton buttonBack;
   	JLabel labelDateFrom,labelDateTo,labelTask; 
   	JLabel labelSC; 
   	JTable table;
   	JScrollPane jsp;
   	JTextField textDateFrom;
   	JTextField textDateTo;
   	JTextField textTask;
	
   	GridBagLayout gbl;
   	GridBagConstraints gbc;
	public SearchTask()
 	{
		fTitle=new Font("arial",Font.BOLD,30);
		fLabel=new Font("arial",Font.BOLD,11);
  		gbl=new GridBagLayout ();
		gbc=new GridBagConstraints();
		frame=new JFrame();
		panel=new JPanel();
		panelTab1=new JPanel();
		panelTab2=new JPanel();
		panelmain=new JPanel();
		panellabel=new JPanel();
		panelbutton=new JPanel();
		tabPane=new JTabbedPane();
		buttonDisplayDateR=new JButton("Display Records");
		buttonDisplayTaskR=new JButton("Display Records");
		buttonBack=new JButton("Back");
		labelDateFrom=new JLabel("TaskDate From"); 
		labelDateTo=new JLabel("To"); 
		labelTask=new JLabel("Task"); 
		labelSC=new JLabel("Search Task");
		labelSC.setForeground(Color.red);
		textTask=new JTextField(10);
		textDateFrom=new JTextField(10);
		textDateTo=new JTextField(10);
		frame.getContentPane().add(panelmain);
 		panelmain.setLayout(new BorderLayout());
		panelmain.add(panel, BorderLayout.CENTER);
		panelmain.add(panellabel, BorderLayout.NORTH);
		panelmain.add(panelbutton, BorderLayout.SOUTH);
		panel.setLayout(gbl);
		panel.setBackground(Color.white);
		panellabel.setBackground(Color.black);
		panelbutton.setBackground(Color.black);
	   	gbc.anchor=GridBagConstraints.NORTH;
	   
	  	labelSC.setFont(fTitle);
	  	panellabel.add(labelSC);
	
	  	labelDateFrom.setFont(fLabel);
	  	panelTab1.add(labelDateFrom);

		panelTab1.add(textDateFrom);
	
		labelDateTo.setFont(fLabel);
	  	panelTab1.add(labelDateTo);

		panelTab1.add(textDateTo);
	
		labelTask.setFont(fLabel);
	  	panelTab2.add(labelTask);

		panelTab2.add(textTask);

		panelTab1.add(buttonDisplayDateR);
		buttonDisplayDateR.setBackground(Color.gray);

	 	panelTab2.add(buttonDisplayTaskR);
		buttonDisplayTaskR.setBackground(Color.gray);

		panelbutton.add(buttonBack);
		buttonBack.setBackground(Color.gray);
		gbc.insets=new Insets(15,15,0,0);
	
		frame.setSize(800,600);
		frame.setVisible(true);
		buttonDisplayDateR.addActionListener(this);
		buttonDisplayTaskR.addActionListener(this);
		buttonBack.addActionListener(this);
		tabPane.addTab("BY DATE",null,panelTab1,"Search By Date");
		tabPane.addTab("BY TASK",null,panelTab2,"Search By Task"); 
		panel.add(tabPane);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	public void actionPerformed(ActionEvent ee)
	{
		if(ee.getSource()==buttonDisplayDateR)
		{
			new TDL(textDateFrom.getText(),textDateTo.getText());
			frame.dispose();
		}
		if(ee.getSource()==buttonDisplayTaskR)
		{
			new TDL(textTask.getText());
			frame.dispose();
		}
		if(ee.getSource()==buttonBack)
		{
			new TDL();
			frame.dispose();

		}
	}
}