package Notes;
import java.sql.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
public class PDANotes implements ActionListener
{  
	public static final int FRAME_WIDTH = 700;
	public static final int FRAME_HEIGHT = 600;
  
  	JFrame frame;
	JPanel panelMain,panelButton,panelTable,panelLabel; 
 	JButton buttonAddNote,buttonFind,buttonDelete,buttonShowAll,buttonDeleteAll;
	GridBagLayout gbl;
	GridBagConstraints gbc;
        JTable table;
       	MyTableModel mt;
	int i;
	JLabel labelDisplay;
	String str1,str2,str3;
	Font fLabel;
	
	public PDANotes()
	{
		initComponent();
		AllTask();
	}
	public PDANotes(String strDateFrom,String strDateTo)
	{
		initComponent();
		SearchByDate(strDateFrom,strDateTo);	
	}
	public PDANotes(String strName)
	{
		initComponent();
		SearchByName(strName);
	}
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==buttonAddNote)
		{
			newText obj=new newText();
			i++;
			frame.dispose(); 
  		}
	        if(evt.getSource()==buttonFind)
		{
                  	new SearchNote();
               		frame.dispose(); 
  		}
		if(evt.getSource()==buttonDelete)
		{
			try
			{
				int SNo=table.getSelectedRow();
				Object value=table.getValueAt(SNo,0);
               			int confirm=JOptionPane.showConfirmDialog(frame,"Do You want to Delete Record??","Confirm Delete",JOptionPane.YES_NO_OPTION);
				if(confirm==0)
				{
			
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con2;
					con2=DriverManager.getConnection("jdbc:odbc:mypda");
					PreparedStatement ps=con2.prepareStatement("delete notes where s_no=?");
					ps.setString(1,value.toString());
					int i=ps.executeUpdate();
					if(i>0)
					{
						new PDANotes();
						frame.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"Problem in Deleting Record..!!");
					}
				}
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(frame,"Select any Record..!!");
			}
		}
		if(evt.getSource()==buttonShowAll)
		{
			try
			{
				int rowSelect=table.getSelectedRow();
				Object objSNo=table.getValueAt(rowSelect,0);
				int sno=Integer.parseInt(objSNo.toString());
				ViewText et=new ViewText(sno);
				frame.dispose();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(frame,"Kindly Select Any Record!!");
			}
		}
		if(evt.getSource()==buttonDeleteAll)
		{
			int confirm=JOptionPane.showConfirmDialog(frame,"Do You want to Delete All Records??","Confirm Delete",JOptionPane.YES_NO_OPTION);
			if(confirm==0)
			{
				try
				{
                			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con2;
					con2=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");
					PreparedStatement ps=con2.prepareStatement("delete notes");
					int status=ps.executeUpdate();
					if(status>0)
					{
						new PDANotes();
						frame.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"No Record Found..!!");
					}
				}
				catch(Exception ek)
				{
					JOptionPane.showMessageDialog(frame,ek);
				}
					
			}
		}
	}
	class MyTableModel extends AbstractTableModel 
	{
        	String [] col={"SNo.","Date","Note Name"};
		Object row[][]=new Object[i][3];
		//row[i][2]=new Boolean(true);
	        public int getColumnCount() 
		{
        		return col.length;
        	}
	        public int getRowCount() 
		{
            		return row.length;
        	}
	        public String getColumnName(int cols) 
		{
        		return col[cols];
        	}
	        public Object getValueAt(int rows, int cols) 
		{
        	    	return row[rows][cols];
        	}
	        public boolean isCellEditable(int rows, int cols) 
		{
			if(cols < 3)
			{
                		return false;
            		} 
			else 
			{
                		return true;
            		}
        	}
	 	public Class getColumnClass(int c) 
		{
            		return getValueAt(0, c).getClass();
        	}
        	public void setValueAt(Object value, int rows, int cols) 
		{
            		row[rows][cols] = value;
            		fireTableCellUpdated(rows, cols);
        	}		
	}
	public void initComponent()
	{
		fLabel=new Font("arial",Font.BOLD,30);
		labelDisplay=new JLabel("PDA Notes");
		labelDisplay.setFont(fLabel);
		labelDisplay.setForeground(Color.red);
		frame=new JFrame("PDA Notes");
			
		panelButton=new JPanel();
		panelTable=new JPanel();
		panelLabel=new JPanel();
		panelLabel.add(labelDisplay);
		panelMain=new JPanel();
		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();
		buttonAddNote=new JButton("New Note");
		buttonFind=new JButton("Find");
		buttonDelete=new JButton("Delete");
		buttonShowAll=new JButton("View Note");
		buttonDeleteAll=new JButton("Delete All");
		buttonAddNote.setBackground(Color.gray);
		buttonFind.setBackground(Color.gray);
		buttonDelete.setBackground(Color.gray);
		buttonShowAll.setBackground(Color.gray);
		buttonDeleteAll.setBackground(Color.gray);
		
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(new BorderLayout());
		panelMain.add(panelTable,BorderLayout.CENTER);
		panelMain.add(panelLabel,BorderLayout.NORTH);
		panelMain.add(panelButton,BorderLayout.SOUTH);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelButton.add(buttonAddNote);
		panelButton.add(buttonFind);
		panelButton.add(buttonShowAll);
		panelButton.add(buttonDelete);
		panelButton.add(buttonDeleteAll);
		panelButton.setBackground(Color.black);
		panelTable.setBackground(Color.white);
		panelLabel.setBackground(Color.black);
		buttonAddNote.addActionListener(this);
	        buttonFind.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonShowAll.addActionListener(this);
		buttonDeleteAll.addActionListener(this);
			
	}
	public void AllTask()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con,con1;
			con=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			con1=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			PreparedStatement ps=con.prepareStatement("select s_no,date,notename from notes");
			PreparedStatement ps1=con1.prepareStatement("select count(*) from notes");
			ResultSet rs=ps.executeQuery();
			ResultSet rs1=ps1.executeQuery();
			rs1.next();
			i=Integer.parseInt(rs1.getString(1));
			mt=new MyTableModel();
	                table=new JTable(mt);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.sizeColumnsToFit(2);
			JScrollPane jsp=new JScrollPane(table);
			for(int k=0;k<i;k++)
			{
				rs.next();
				str1=rs.getString(1);
				str2=rs.getString(2);
				str3=rs.getString(3);
                        	table.setValueAt(str1,k,0);
	          		table.setValueAt(str2,k,1);
	          		table.setValueAt(str3,k,2);
				
			}
			jsp.setPreferredSize(new Dimension(688,488));
			jsp.setBackground(Color.white);
			table.setBackground(Color.white);
			jsp.setForeground(Color.white);
			 panelTable.add(jsp);	
			frame.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);	
		}	
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(frame,ee);
		}
	}
	public void SearchByDate(String strDateFrom,String strDateTo)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con,con1;
			con=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			con1=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			PreparedStatement ps=con.prepareStatement("select s_no,Date,notename from notes where date between ? and ?");
			PreparedStatement ps1=con1.prepareStatement("select count(*) from notes where date between ? and ?");
			ps.setString(1,strDateFrom);
			ps.setString(2,strDateTo);
			ps1.setString(1,strDateFrom);
			ps1.setString(2,strDateTo);
			ResultSet rs=ps.executeQuery();
			ResultSet rs1=ps1.executeQuery();
			rs1.next();
			i=Integer.parseInt(rs1.getString(1));
			mt=new MyTableModel();
	                table=new JTable(mt);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.sizeColumnsToFit(2);
			JScrollPane jsp=new JScrollPane(table);
			for(int k=0;k<i;k++)
			{
				rs.next();
				str1=rs.getString(1);
				str2=rs.getString(2);
				str3=rs.getString(3);
	                	table.setValueAt(str1,k,0);
	          		table.setValueAt(str2,k,1);
	          		table.setValueAt(str3,k,2);
			}
			jsp.setPreferredSize(new Dimension(688,488));
			jsp.setBackground(Color.white);
			table.setBackground(Color.white);
			jsp.setForeground(Color.white);
			 panelTable.add(jsp);	
			frame.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);	
		}	
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(frame,ee);
		}
	}	
	public void SearchByName(String strName)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con,con1;
			con=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			con1=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			PreparedStatement ps=con.prepareStatement("select s_no,Date,notename from notes where notename=?");
			PreparedStatement ps1=con1.prepareStatement("select count(*) from notes where notename=?");
			ps.setString(1,strName);
			ps1.setString(1,strName);
			ResultSet rs=ps.executeQuery();
			ResultSet rs1=ps1.executeQuery();
			rs1.next();
			i=Integer.parseInt(rs1.getString(1));
			mt=new MyTableModel();
	                table=new JTable(mt);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.sizeColumnsToFit(2);
			JScrollPane jsp=new JScrollPane(table);
			for(int k=0;k<i;k++)
			{
				rs.next();
				str1=rs.getString(1);
				str2=rs.getString(2);
				str3=rs.getString(3);
				table.setValueAt(str1,k,0);
	          		table.setValueAt(str2,k,1);
	          		table.setValueAt(str3,k,2);
			}
			jsp.setPreferredSize(new Dimension(688,488));
			jsp.setBackground(Color.white);
			table.setBackground(Color.white);
			jsp.setForeground(Color.white);
			 panelTable.add(jsp);	
			frame.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);	
		}	
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(frame,ee);
		}
	}
}  

 
    