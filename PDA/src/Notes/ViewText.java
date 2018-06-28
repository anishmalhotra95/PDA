package Notes;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
public class ViewText implements ActionListener
{
	public static final int FRAME_WIDTH = 450;
	public static final int FRAME_HEIGHT = 465;
  
	JFrame frame;
	JLabel label,labelname,labelHeading;
	JTextField textname;
	JPanel panel,panelmain,labelpanel,paneltext,panelButton,centerPanel;
	TextArea textArea;
	JButton buttonSave,buttonback;
	BorderLayout bl;	
	GridBagLayout gbl;
	GridBagConstraints gbc;
	Font fLabel;
	int sno;
	public ViewText(int sno)
	{
		this.sno=sno;
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:mypda");
		PreparedStatement psEdit=con.prepareStatement("select rtrim(NoteName),rtrim(Notes) from notes where S_no=?");
		psEdit.setInt(1,sno);
		ResultSet rsEdit=psEdit.executeQuery();
		rsEdit.next();
	
		fLabel=new Font("arial",Font.BOLD,30);
		frame=new JFrame("Add New Note");
		panel=new JPanel();
		centerPanel=new JPanel();
		panelButton=new JPanel();
		panelButton.setBackground(Color.black);
		labelname=new JLabel("Note Name");
		labelHeading=new JLabel("Add New Note");
		labelHeading.setFont(fLabel);
		labelHeading.setForeground(Color.red);
		textname=new JTextField(10);
		textname.setText(rsEdit.getString(1));
		labelpanel=new JPanel();
		panelmain=new JPanel();
		paneltext=new JPanel();
		label=new JLabel("Max(1000 letters)");
		textArea=new TextArea(20,60);
		textArea.setText(rsEdit.getString(2));
		bl=new BorderLayout();
		buttonSave=new JButton("Update");
		buttonback=new JButton("Back");


		
		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();
		frame.getContentPane().add(panelmain);
		
		panelmain.setLayout(bl);
		panelmain.add(centerPanel,BorderLayout.CENTER);
		panelmain.add(labelpanel,BorderLayout.NORTH);
		panelmain.add(panelButton,BorderLayout.SOUTH);
		panel.setLayout(gbl);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(panel,BorderLayout.NORTH);
		centerPanel.add(paneltext,BorderLayout.CENTER);
	
		gbc.insets=new Insets(6,6,4,4);
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbl.setConstraints(label,gbc);
		panel.add(label);
		
		gbc.gridx=1;
		gbc.gridy=0;
		gbl.setConstraints(labelname,gbc);
		panel.add(labelname);
		
		gbc.gridx=2;
		gbc.gridy=0;
		gbl.setConstraints(textname,gbc);
		panel.add(textname);

		panelButton.add(buttonSave);
		buttonSave.addActionListener(this);
		panelButton.add(buttonback);
		buttonback.addActionListener(this);
		paneltext.add(textArea);

		labelpanel.add(labelHeading);
		labelpanel.setBackground(Color.black);
		
		frame.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setResizable(false);	
		}
		catch(Exception ek)
		{
			JOptionPane.showMessageDialog(frame,ek);
		}
	}
	public void actionPerformed(ActionEvent ev)
	{
		try
			{
			if(ev.getSource()==buttonSave)
			{
				String str1=textname.getText();
				String str2=textArea.getText();
				if(str1.equals("")||str2.equals(""))
				{
					JOptionPane.showMessageDialog(frame,"Kindly enter values");
				}
				else
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");
					PreparedStatement ps=con.prepareStatement("update notes set date=getDate(),notename=?,notes=? where s_no=?");
					ps.setString(1,str1);
					ps.setString(2,str2);
					ps.setInt(3,sno);
					int i=ps.executeUpdate();
					if(i>0)
						JOptionPane.showMessageDialog(frame,"Values Updated successfullY");
				}
			}
			if(ev.getSource()==buttonback)
			{
				new PDANotes();
				frame.dispose();
			}
		}
		catch(Exception eee)
		{
			
		}
	}
}