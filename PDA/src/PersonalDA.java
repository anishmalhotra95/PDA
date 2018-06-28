import ToDoList.*;
import Contacts.*;
import Notes.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.io.*;
class PersonalDA implements ActionListener
{
	JFrame frame;
	JMenuBar mb;
	JMenu PDA,Utility;
	JMenuItem buttonContacts,buttonNotes,buttonToDo;
	JMenuItem miCalc,miMSPaint,miNotepad;
	JPanel panelMain,panel1,panel2;
	JLabel l1,l2,l3,l4,l5;
	Font f;
	GridBagLayout gbl;
	GridBagConstraints gbc;
        PreparedStatement ps;
        Connection con;  
	public PersonalDA()
	{
		frame=new JFrame("PDA");
		panelMain=new JPanel(new BorderLayout());
		panel1=new JPanel();		
		panel2=new JPanel();					
		l1=new JLabel(" ");
		l2=new JLabel(" ");
		l3=new JLabel("               ");
		l4=new JLabel(" ");
		l5=new JLabel("MAIN MENU");
		f=new Font("arial",Font.BOLD,11);
		panel2.setBackground(Color.pink);
		mb=new JMenuBar();
		PDA=new JMenu("PDA");
		Utility=new JMenu("Utility");
		PDA.setFont(f);
		Utility.setFont(f);
		buttonContacts=new JMenuItem("Contacts");
		buttonNotes=new JMenuItem("Notes");
		buttonToDo=new JMenuItem("To Do List");
		miCalc=new JMenuItem("Calculator");
		miMSPaint=new JMenuItem("Paint");
		miNotepad=new JMenuItem("Notepad");
		mb.add(PDA);
		mb.add(Utility);
		PDA.add(buttonContacts);
		PDA.add(buttonNotes);
		PDA.add(buttonToDo);
		Utility.add(miCalc);
		Utility.add(miMSPaint);
		Utility.add(miNotepad);
		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();
		
		frame.getContentPane().add(panelMain);
		panelMain.add(panel1,BorderLayout.NORTH);
		panelMain.add(panel2,BorderLayout.CENTER);
		panel1.add(l5);
		frame.setJMenuBar(mb);
		panel2.add(l1);
		buttonToDo.addActionListener(this);
		buttonContacts.addActionListener(this);
		buttonNotes.addActionListener(this);
		miCalc.addActionListener(this);
		miMSPaint.addActionListener(this);
		miNotepad.addActionListener(this);
		panel2.add(l3);	
		buttonContacts.setFont(f);		
		buttonNotes.setFont(f);
		buttonToDo.setFont(f);
		miCalc.setFont(f);
		miMSPaint.setFont(f);
		miNotepad.setFont(f);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screen);
		frame.setVisible(true);
		//JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
 
	}
	public void actionPerformed(ActionEvent evt)
	{
                
		if(evt.getSource()==buttonToDo)
		{
			TDL objTDL=new TDL();
		}
		else if(evt.getSource()==buttonContacts)
		{
			new Contacts();
			//frame.dispose();
		}
		else if(evt.getSource()==buttonNotes)
		{
			PDANotes objnpda=new PDANotes();
			//frame.dispose();
		}
		  else if(evt.getSource()==miCalc)
		{
			try
			{
				Runtime.getRuntime().exec("Calc.exe");
			}
			catch(Exception ee)
			{
			}
		}
                 else if(evt.getSource()==miMSPaint)
		{
			try
			{
				Runtime.getRuntime().exec("mspaint.exe");
			}
			catch(Exception ee)
			{
			}
		}
                 else if(evt.getSource()==miNotepad)
		{
			try
			{
				Runtime.getRuntime().exec("Notepad.exe");
			}
			catch(Exception ee)
			{
			}
		}
                 
	}
	
}			