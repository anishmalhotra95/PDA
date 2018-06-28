package Contacts;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class SearchContacts implements ActionListener
{    
	public static final int FRAME_WIDTH = 700;
	public static final int FRAME_HEIGHT = 200;

	Font fTitle,fLabel;
   	JFrame frame;
	JTabbedPane tabPane;
   	JPanel panel,panelmain,panelTab1,panelTab2,panelTab3,panelTab4,panellabel,panelbutton;
   	JButton buttonDisplayRecord;
   	JButton buttonDisplayBirthR,buttonDisplayBirthM;
	JButton buttonDisplayAnvR,buttonDisplayAnvM;
   	JButton buttonDisplayPhone;
   	JButton buttonBack;
   	JLabel labelName,labelPhone; 
   	JLabel labelBirthDateFrom; 
   	JLabel labelBirthDateTo; 
   	JLabel labelAnvDateFrom; 
   	JLabel labelAnvDateTo; 
   	JLabel labelSC; 
   	JTable table;
   	JScrollPane jsp;
   	JTextField textName;
   	JTextField textAnvDateFrom;
   	JTextField textAnvDateTo;
   	JTextField textBirthDateTo;
   	JTextField textBirthDateFrom;
	JTextField textPhone;
	
   	GridBagLayout gbl;
   	GridBagConstraints gbc;
	public SearchContacts()
 	{
		fTitle=new Font("arial",Font.BOLD,30);
		fLabel=new Font("arial",Font.BOLD,11);
  		gbl=new GridBagLayout ();
		gbc=new GridBagConstraints();
		frame=new JFrame();
		panel=new JPanel();
		panelTab1=new JPanel();
		panelTab2=new JPanel();
		panelTab3=new JPanel();
		panelTab4=new JPanel();
		panelmain=new JPanel();
		panellabel=new JPanel();
		panelbutton=new JPanel();
		tabPane=new JTabbedPane();
		buttonDisplayRecord=new JButton("Display Records");
		buttonDisplayBirthM=new JButton("Display Pop-Ups");
		buttonDisplayAnvM=new JButton("Display Pop-Ups");
		buttonDisplayPhone=new JButton("Display Records");
		buttonDisplayBirthR=new JButton("Display Records");
		buttonDisplayAnvR=new JButton("Display Records");
		buttonBack=new JButton("Back");
		labelName=new JLabel("Name"); 
		labelPhone=new JLabel("Contact Number"); 
		labelBirthDateFrom=new JLabel("BirthDate From"); 
		labelBirthDateTo=new JLabel("To"); 
		labelAnvDateTo=new JLabel("To"); 
		labelAnvDateFrom=new JLabel("AnniversaryDate  From"); 
		labelSC=new JLabel("Search Contacts");
		labelSC.setForeground(Color.red);
		textName=new JTextField(10);
		textPhone=new JTextField(10);
		textBirthDateFrom=new JTextField(10);
		textBirthDateTo=new JTextField(10);
		textAnvDateTo=new JTextField(10);
		textAnvDateFrom=new JTextField(10);
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
	
	  	labelName.setFont(fLabel);
	  	panelTab1.add(labelName);

		panelTab1.add(textName);
	
		labelAnvDateFrom.setFont(fLabel);
	  	panelTab2.add(labelAnvDateFrom);

		panelTab2.add(textAnvDateFrom);
	
		labelAnvDateTo.setFont(fLabel);
	  	panelTab2.add(labelAnvDateTo);

		panelTab2.add(textAnvDateTo);

		labelBirthDateFrom.setFont(fLabel);
	  	panelTab3.add(labelBirthDateFrom);
		panelTab3.add(textBirthDateFrom);

		labelBirthDateTo.setFont(fLabel);
	  	panelTab3.add(labelBirthDateTo);

		panelTab3.add(textBirthDateTo);
	
		panelTab4.add(labelPhone);
		panelTab4.add(textPhone);
		panelTab4.add(buttonDisplayPhone);
		buttonDisplayPhone.setBackground(Color.gray);

		panelTab1.add(buttonDisplayRecord);
		buttonDisplayRecord.setBackground(Color.gray);

		 panelTab3.add(buttonDisplayBirthM);
		buttonDisplayBirthM.setBackground(Color.gray);

	 	panelTab2.add(buttonDisplayAnvM);
		buttonDisplayAnvM.setBackground(Color.gray);

		panelTab3.add(buttonDisplayBirthR);
		buttonDisplayBirthR.setBackground(Color.gray);

	 	panelTab2.add(buttonDisplayAnvR);
		buttonDisplayAnvR.setBackground(Color.gray);

		panelbutton.add(buttonBack);
		buttonBack.setBackground(Color.gray);
		gbc.insets=new Insets(15,15,0,0);
	
		frame.setSize(800,600);
		frame.setVisible(true);
		buttonDisplayRecord.addActionListener(this);
		buttonDisplayBirthM.addActionListener(this);
		buttonDisplayAnvM.addActionListener(this);
		buttonDisplayBirthR.addActionListener(this);
		buttonDisplayAnvR.addActionListener(this);
		buttonBack.addActionListener(this);
		buttonDisplayPhone.addActionListener(this);
	
		tabPane.addTab("BY NAME",null,panelTab1,"Search By Name");
		tabPane.addTab("BY CONTACT INFORMATION",null,panelTab4,"Search By Contact Information"); 
		tabPane.addTab("BY BIRTHDAY",null,panelTab3,"Search By Birthday");
		tabPane.addTab("BY ANNIVERSARY",null,panelTab2,"Search By Anniversary");
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
		if(ee.getSource()==buttonDisplayRecord)
		{
		  	new Contacts(textName.getText());
			frame.dispose();
		}
		if(ee.getSource()==buttonDisplayPhone)
		{
			new Contacts(textPhone.getText(),0);
			frame.dispose();
		}
		if(ee.getSource()==buttonBack)
		{
			new Contacts();
			frame.dispose();

		}
		try
		{
		       	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:mypda");
			PreparedStatement ps;
			ResultSet rs;
			if(ee.getSource()==buttonDisplayBirthM)
			{
		        	ps=con.prepareStatement("select * from contacts where dob between ? and ?");
			        String strBdFrom=textBirthDateFrom.getText();
			        String strBdTo=textBirthDateTo.getText();
				if(strBdFrom.equals("")||strBdTo.equals(""))
		       		{
					JOptionPane.showMessageDialog(frame,"Please enter time range for BirthBay.....");
		         	}
			        ps.setString(1,strBdFrom.toString());
			        ps.setString(2,strBdTo.toString());
			        rs=ps.executeQuery();
			        while(rs.next())
			        {
					JOptionPane.showMessageDialog(frame,"Birthday of     "+rs.getString(2)+"  on  "+rs.getString(6));
			        }
			}
			if(ee.getSource()==buttonDisplayAnvM)
			{
			       	String strAnFrom=textAnvDateFrom.getText();
				String strAnTo=textAnvDateTo.getText();
				if(strAnFrom.equals("")||strAnTo.equals(""))
		       		{
					JOptionPane.showMessageDialog(frame,"Please enter time range for Anniversary.....");
		         	}
				ps=con.prepareStatement("select * from contacts where annDate between ? and ?");
			        ps.setString(1,strAnFrom.toString());
			        ps.setString(2,strAnTo.toString());
			        rs=ps.executeQuery();
			        while(rs.next())
			        {
					JOptionPane.showMessageDialog(frame,"Anniversary of    "+rs.getString(2)+"  on  "+rs.getString(7));
			        }
			}
			if(ee.getSource()==buttonDisplayBirthR)
			{
				new Contacts(textBirthDateFrom.getText(),textBirthDateTo.getText());
				frame.dispose();
			}
			if(ee.getSource()==buttonDisplayAnvR)
			{
				new Contacts(textAnvDateFrom.getText(),textAnvDateTo.getText(),0);
				frame.dispose();
			}
		}
		catch(Exception ept)
		{
			JOptionPane.showMessageDialog(frame,ept);			
		}
	}
}