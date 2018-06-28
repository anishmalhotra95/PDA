package ToDoList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class NewTask implements ActionListener
{    
	public static final int FRAME_WIDTH = 290;
	public static final int FRAME_HEIGHT = 190;
  	
	Font fLabel,fLabelAP;
   	JFrame frame;
	JPanel panelmain,panelAP,panelLabel,panelButton;
	JLabel labelAP,labelDate,labelTask;
	JTextField textDate,textTask;
	JButton buttonSave,buttonBack;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	public NewTask()
	{
		fLabel=new Font("arial",Font.BOLD,11);
		fLabelAP=new Font("arial",Font.BOLD,30);
		frame=new JFrame("New Task");
		panelmain=new JPanel(new BorderLayout());
		panelAP=new JPanel();
		panelLabel=new JPanel();
		panelButton=new JPanel();

		labelAP=new JLabel("New Task");
		labelAP.setFont(fLabelAP);
		labelAP.setForeground(Color.red);	
		labelTask=new JLabel("*Task");
		labelDate=new JLabel("*Date");

		textDate=new JTextField(15);
		textTask=new JTextField(20);
		
		buttonSave=new JButton("SAVE");
		buttonBack=new JButton("BACK");

		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();

		gbc.insets=new Insets(4,4,4,4);
		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=1;
		gbl.setConstraints(labelDate,gbc);
		labelDate.setFont(fLabel);
		panelLabel.add(labelDate);

		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=1;
		gbl.setConstraints(textDate,gbc);
		panelLabel.add(textDate);


		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=2;
		gbl.setConstraints(labelTask,gbc);
		labelTask.setFont(fLabel);
		panelLabel.add(labelTask);



		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=2;
		gbl.setConstraints(textTask,gbc);
		panelLabel.add(textTask);


		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=0;
		gbc.gridy=7;
		gbl.setConstraints(buttonSave,gbc);
		panelButton.add(buttonSave);
		buttonSave.setBackground(Color.gray);

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
				String strDate,strTask;
				strDate=textDate.getText();
				strTask=textTask.getText();
				if(strDate.equals("")||strTask.equals(""))
				{
					JOptionPane.showMessageDialog(frame,"Kindly fill all Required Fields!!");
				}
				else
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection conSave=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");
					PreparedStatement psSave;
					psSave=conSave.prepareStatement("insert into todolist(Date,Task,status) values(?,?,0)");	
					psSave.setString(1,strDate);
					psSave.setString(2,strTask);
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
			new TDL();
			frame.dispose();
		}
	}
	
}