package Contacts;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class NewContacts implements ActionListener
{    
	public static final int FRAME_WIDTH = 370;
	public static final int FRAME_HEIGHT = 260;
  	
	Font fLabel,fLabelAP;
   	JFrame frame;
	JPanel panelmain,panelAP,panelLabel,panelButton;
	JLabel labelAP,labelName,labelAddress,labelEmailID,labelPhone,labelDOB,labelAnnDate;
	JTextField textName,textAddress,textEmailID,textPhone,textDOB,textAnnDate;
	JButton buttonSave,buttonBack;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	public NewContacts()
	{
		fLabel=new Font("arial",Font.BOLD,11);
		fLabelAP=new Font("arial",Font.BOLD,30);
		frame=new JFrame("New Contact");
		panelmain=new JPanel(new BorderLayout());
		panelAP=new JPanel();
		panelLabel=new JPanel();
		panelButton=new JPanel();

		labelAP=new JLabel("New Contact");
		labelAP.setFont(fLabelAP);
		labelAP.setForeground(Color.red);	
		labelName=new JLabel("*Name");
		labelAddress=new JLabel("*Address");
		labelEmailID=new JLabel("E-mailID");
		labelPhone=new JLabel("*Phone");
		labelDOB=new JLabel("*Date Of Birth");
		labelAnnDate=new JLabel("Anniversary Date");

		textName=new JTextField(15);
		textAddress=new JTextField(20);
		textEmailID=new JTextField(20);
		textPhone=new JTextField(12);
		textDOB=new JTextField(8);
		textAnnDate=new JTextField(8);

		buttonSave=new JButton("SAVE");
		buttonBack=new JButton("BACK");

		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();

		gbc.insets=new Insets(2,2,2,2);
		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=1;
		gbl.setConstraints(labelName,gbc);
		labelName.setFont(fLabel);
		panelLabel.add(labelName);

		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=1;
		gbl.setConstraints(textName,gbc);
		panelLabel.add(textName);


		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=2;
		gbl.setConstraints(labelAddress,gbc);
		labelAddress.setFont(fLabel);
		panelLabel.add(labelAddress);



		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=2;
		gbl.setConstraints(textAddress,gbc);
		panelLabel.add(textAddress);


		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=3;
		gbl.setConstraints(labelEmailID,gbc);
		labelEmailID.setFont(fLabel);
		panelLabel.add(labelEmailID);

		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=3;
		gbl.setConstraints(textEmailID,gbc);
		panelLabel.add(textEmailID);

		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=4;
		gbl.setConstraints(labelPhone,gbc);
		labelPhone.setFont(fLabel);
		panelLabel.add(labelPhone);

		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=4;
		gbl.setConstraints(textPhone,gbc);
		panelLabel.add(textPhone);

		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=5;
		gbl.setConstraints(labelDOB,gbc);
		labelDOB.setFont(fLabel);
		panelLabel.add(labelDOB);

		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=5;
		gbl.setConstraints(textDOB,gbc);
		panelLabel.add(textDOB);

		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=6;
		gbl.setConstraints(labelAnnDate,gbc);
		labelAnnDate.setFont(fLabel);
		panelLabel.add(labelAnnDate);

		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=6;
		gbl.setConstraints(textAnnDate,gbc);
		panelLabel.add(textAnnDate);

		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=7;
		gbl.setConstraints(buttonSave,gbc);
		panelButton.add(buttonSave);
		buttonSave.setBackground(Color.gray);

		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=7;
		gbl.setConstraints(buttonBack,gbc);
		panelButton.add(buttonBack);
		buttonBack.setBackground(Color.gray);
		
		panelAP.add(labelAP);
		panelAP.setBackground(Color.black);
		panelAP.setForeground(Color.red);
		panelLabel.setBackground(Color.white);
		panelButton.setBackground(Color.black);

		frame.getContentPane().add(panelmain);
		panelmain.add(panelAP,BorderLayout.NORTH);
		panelmain.add(panelLabel,BorderLayout.CENTER);
		panelmain.add(panelButton,BorderLayout.SOUTH);
		panelLabel.setLayout(gbl);

		buttonSave.addActionListener(this);
		buttonBack.addActionListener(this);

		frame.setResizable(false);
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==buttonSave)
		{
			try
			{
				String strName,strAddress,strEmailID,strPhone,strDOB,strAnnDate;
				strName=textName.getText();
				strAddress=textAddress.getText();
				strEmailID=textEmailID.getText();
				strPhone=textPhone.getText();
				strDOB=textDOB.getText();
				strAnnDate=textAnnDate.getText();
				if(strName.equals("")||strAddress.equals("")||strPhone.equals("")||strDOB.equals(""))
				{
					JOptionPane.showMessageDialog(frame,"Kindly fill all Required Fields!!");
				}
				else
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection conSave=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");
					PreparedStatement psSave;
					if(strAnnDate.equals(""))
					{
						strAnnDate=null;
					}	
					if(strEmailID.equals(""))
					{
						strEmailID=null;
					}
					psSave=conSave.prepareStatement("insert into contacts(Name,address,emailid,phone,dob,anndate) values(?,?,?,?,?,?)");	
					psSave.setString(1,strName);
					psSave.setString(2,strAddress);
					psSave.setString(3,strEmailID);
					psSave.setString(4,strPhone);
					psSave.setString(5,strDOB);
					psSave.setString(6,strAnnDate);
					int result=psSave.executeUpdate();
					if(result>0)
					{
						JOptionPane.showMessageDialog(frame,"Values Submitted Successfully!!");
	
					}
					else
					{
					 	JOptionPane.showMessageDialog(frame,"Server is Busy!!");
					}
					
				}
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(frame,ee);
			}
		}
		else if(evt.getSource()==buttonBack)
		{
			new Contacts();
			frame.dispose();
		}
	}
	
}