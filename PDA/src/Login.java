import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Login implements ActionListener
{    
	public static final int FRAME_WIDTH = 300;
	public static final int FRAME_HEIGHT = 300;

	Font fId,fLabel;
   	JFrame frame;
   	JPanel mainpanel,panel,labelpanel,buttonpanel;
   	JButton buttonSubmit;
   	JLabel labelid; 
   	JLabel labelAP; 
   	JLabel labelPassword;
   	JPasswordField password;
   	JTextField textid;
   	GridBagLayout gbl;
   	GridBagConstraints gbc;
   	public Login()
   	{
		fId=new Font("verdana",Font.BOLD,15);
		fLabel=new Font("arial",Font.BOLD,10);
	  	gbl=new GridBagLayout ();
		gbc=new GridBagConstraints();
		frame=new JFrame("PDA Login Form");
		mainpanel = new JPanel();
		labelpanel = new JPanel();
		buttonpanel = new JPanel();
		panel = new JPanel();
		buttonSubmit=new JButton("Submit");
		labelid=new JLabel("Login ID   "); 
	        labelid.setForeground(Color.black);
		labelAP=new JLabel("Personal Digital Assistant"); 
		labelAP.setForeground(Color.red);
		labelPassword=new JLabel("Password   ");
	        labelPassword.setForeground(Color.black);
		password=new JPasswordField(10);
		textid=new JTextField(10);
		frame.getContentPane().add(mainpanel);
		mainpanel.setLayout(new BorderLayout());
		mainpanel.add(panel, BorderLayout.CENTER);
		mainpanel.add(labelpanel, BorderLayout.NORTH);
		mainpanel.add(buttonpanel, BorderLayout.SOUTH);

	 	buttonpanel.setBackground(Color.black);
 		labelpanel.setBackground(Color.black);
	        panel.setLayout(gbl);
		panel.setBackground(Color.white);
		gbc.insets=new Insets(5, 5, 5, 5);
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.gridx=1;
		gbc.gridy=0;
		gbl.setConstraints(labelAP,gbc);
		labelAP.setFont(fId);
		labelpanel.add(labelAP);
	
		gbc.gridx=0;
		gbc.gridy=3;
		gbl.setConstraints(labelid,gbc);
		labelid.setFont(fLabel);
		panel.add(labelid);

		gbc.gridx=2;
		gbc.gridy=3;
		gbl.setConstraints(textid,gbc);
		panel.add(textid);
		
		gbc.gridx=0;
		gbc.gridy=4;
		gbl.setConstraints(labelPassword,gbc);
		labelPassword.setFont(fLabel);
		panel.add(labelPassword);

		gbc.gridx=2;
 		gbc.gridy=4;
		gbl.setConstraints(password,gbc);
		panel.add(password);
	
		gbc.gridx=1;
	 	gbc.gridy=6;
		gbl.setConstraints(buttonSubmit,gbc);
		buttonpanel.add(buttonSubmit);
		buttonSubmit.setBackground(Color.gray);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonSubmit.addActionListener(this);
		frame.pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setResizable(false);


   	}	
   	public void actionPerformed(ActionEvent ee)
   	{
		try
		{	
	  	      	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	       		Connection con=DriverManager.getConnection("jdbc:odbc:mypda");			       
			PreparedStatement ps;
			if(ee.getSource()==buttonSubmit)
			{
				String strId=textid.getText();
				String strPassword=new String(password.getPassword());
				if(strId.equals("")||strPassword.equals(""))
				{
					JOptionPane.showMessageDialog(frame,"please enter your COMPLETE ID...");
				}
				else
				{
					ps=con.prepareStatement("select * from login  where loginid=? and password=?");     		
					ps.setString(1,strId);
                                	ps.setString(2,strPassword);
			        	ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						new PersonalDA();
						frame.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"Access is Denied....");
					}
				}
	 		}
		}			
		catch(Exception pp)
		{
			JOptionPane.showMessageDialog(frame,pp);
		}
	}			
   	public static void main(String []arg)
   	{
		new Login();
   	}
}