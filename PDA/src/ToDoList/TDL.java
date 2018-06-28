package ToDoList;
import java.sql.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
public class TDL implements ActionListener
{  
	public static final int FRAME_WIDTH = 700;
	public static final int FRAME_HEIGHT = 600;
  
  	JFrame frame;
	JPanel panelMain,panelButton,panelTable,panelLabel; 
 	JButton buttonAddNote,buttonDone,buttonNotDone,buttonFind,buttonDelete,buttonSave,buttonShowAll,buttonDeleteAll;
	GridBagLayout gbl;
	GridBagConstraints gbc;
        JTable table;
       	MyTableModel mt;
	int i;
	JLabel labelDisplay;
	String str1,str2,str3;
	Font fLabel;
	
	public TDL()
	{
		initComponent();
		AllTask();
	}
	public TDL(String strDateFrom,String strDateTo)
	{
		initComponent();
		SearchByDate(strDateFrom,strDateTo);	
	}
	public TDL(String strTask)
	{
		initComponent();
		SearchByTask(strTask);
	}
	public TDL(boolean taskStatus)
	{
		initComponent();
		ConfirmTaskStatus(taskStatus);
	}
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==buttonAddNote)
		{
			NewTask obj=new NewTask();
			i++;
			frame.dispose(); 
  		}
		if(evt.getSource()==buttonDone)
		{
			new TDL(true);
                        frame.dispose();
		}
		if(evt.getSource()==buttonNotDone)
		{
			new TDL(false);
			frame.dispose();
		}
	        if(evt.getSource()==buttonFind)
		{
                  	new SearchTask();
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
					con2=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");
					PreparedStatement ps=con2.prepareStatement("delete todolist where s_no=?");
					ps.setString(1,value.toString());
					int i=ps.executeUpdate();
					if(i>0)
					{
						new TDL();
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
			new TDL();
                        frame.dispose();
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
					PreparedStatement ps=con2.prepareStatement("delete todolist");
					int status=ps.executeUpdate();
					if(status>0)
					{
						JOptionPane.showMessageDialog(frame,"All Records has been Deleted Successfully..!!");
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
		if(evt.getSource()==buttonSave)
		{
			try
			{
                		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con2;
				con2=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");
				int row_no=table.getRowCount();
				for(int temp1=0;temp1<row_no;temp1++)
				{
					PreparedStatement ps=con2.prepareStatement("update todolist set status=? where s_no=?");
					Object ob_sno=table.getValueAt(temp1,0);
					Object ob_bool=table.getValueAt(temp1,3);
					int sn=Integer.parseInt(ob_sno.toString());
					Boolean b=new Boolean(ob_bool.toString());
					ps.setInt(2,sn);
					ps.setString(1,ob_bool.toString());
					int i=ps.executeUpdate();
				}
				JOptionPane.showMessageDialog(frame,"Records Updated Successfully!!!");
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(frame,ee);
			}
		}
	}
	class MyTableModel extends AbstractTableModel 
	{
        	String [] col={"Task No.","Date","Task","Status"};
		Object row[][]=new Object[i][4];
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
		labelDisplay=new JLabel("To Do List");
		labelDisplay.setFont(fLabel);
		labelDisplay.setForeground(Color.red);
		frame=new JFrame("To Do List");
			
		panelButton=new JPanel();
		panelTable=new JPanel();
		panelLabel=new JPanel();
		panelLabel.add(labelDisplay);
		panelMain=new JPanel();
		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();
		buttonAddNote=new JButton("New Task");
		buttonDone=new JButton("Done");
		buttonNotDone=new JButton("Not Done");
		buttonFind=new JButton("Find");
		buttonDelete=new JButton("Delete");
		buttonSave=new JButton("Save Status");
		buttonShowAll=new JButton("Show All");
		buttonDeleteAll=new JButton("Delete All");
		buttonAddNote.setBackground(Color.gray);
		buttonDone.setBackground(Color.gray);
		buttonNotDone.setBackground(Color.gray);
		buttonFind.setBackground(Color.gray);
		buttonDelete.setBackground(Color.gray);
		buttonSave.setBackground(Color.gray);
		buttonShowAll.setBackground(Color.gray);
		buttonDeleteAll.setBackground(Color.gray);
		
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(new BorderLayout());
		panelMain.add(panelTable,BorderLayout.CENTER);
		panelMain.add(panelLabel,BorderLayout.NORTH);
		panelMain.add(panelButton,BorderLayout.SOUTH);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelButton.add(buttonAddNote);
		panelButton.add(buttonSave);
		panelButton.add(buttonFind);
		panelButton.add(buttonDone);
		panelButton.add(buttonNotDone);
		panelButton.add(buttonDelete);
		panelButton.add(buttonDeleteAll);
		panelButton.add(buttonShowAll);
		panelButton.setBackground(Color.black);
		panelTable.setBackground(Color.white);
		panelLabel.setBackground(Color.black);
		buttonAddNote.addActionListener(this);
	        buttonDone.addActionListener(this);
		buttonNotDone.addActionListener(this);		
		buttonFind.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonSave.addActionListener(this);
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
			PreparedStatement ps=con.prepareStatement("select * from todolist");
			PreparedStatement ps1=con1.prepareStatement("select count(*) from todolist");
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
				Boolean b=new Boolean(false);
				str1=rs.getString(1);
				str2=rs.getString(2);
				str3=rs.getString(3);
				b=new Boolean(rs.getBoolean(4));
                        	table.setValueAt(str1,k,0);
	          		table.setValueAt(str2,k,1);
	          		table.setValueAt(str3,k,2);
				table.setValueAt(b,k,3);
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
			JOptionPane.showMessageDialog(frame,"Sdfa"+ee);
		}
	}
	public void ConfirmTaskStatus(boolean taskStatus)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con,con1;
			con=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			con1=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			PreparedStatement ps=con.prepareStatement("select s_no,Date,Task,status from todolist where status=?");
			PreparedStatement ps1=con1.prepareStatement("select count(*) from todolist where status=?");
			ps.setBoolean(1,taskStatus);
			ps1.setBoolean(1,taskStatus);
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
				Boolean b=new Boolean(false);
				str1=rs.getString(1);
				str2=rs.getString(2);
				str3=rs.getString(3);
				b=new Boolean(rs.getBoolean(4));
                        	table.setValueAt(str1,k,0);
	          		table.setValueAt(str2,k,1);
	          		table.setValueAt(str3,k,2);
				table.setValueAt(b,k,3);
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
			PreparedStatement ps=con.prepareStatement("select s_no,Date,Task,status from todolist where date between ? and ?");
			PreparedStatement ps1=con1.prepareStatement("select count(*) from todolist where date between ? and ?");
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
				Boolean b=new Boolean(false);
				str1=rs.getString(1);
				str2=rs.getString(2);
				str3=rs.getString(3);
				b=new Boolean(rs.getBoolean(4));
                        	table.setValueAt(str1,k,0);
	          		table.setValueAt(str2,k,1);
	          		table.setValueAt(str3,k,2);
				table.setValueAt(b,k,3);
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
	public void SearchByTask(String strTask)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con,con1;
			con=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			con1=DriverManager.getConnection("jdbc:odbc:mypda","sa","minvan");	 
			PreparedStatement ps=con.prepareStatement("select s_no,Date,Task,status from todolist where task=?");
			PreparedStatement ps1=con1.prepareStatement("select count(*) from todolist where task=?");
			ps.setString(1,strTask);
			ps1.setString(1,strTask);
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
				Boolean b=new Boolean(false);
				str1=rs.getString(1);
				str2=rs.getString(2);
				str3=rs.getString(3);
				b=new Boolean(rs.getBoolean(4));
                        	table.setValueAt(str1,k,0);
	          		table.setValueAt(str2,k,1);
	          		table.setValueAt(str3,k,2);
				table.setValueAt(b,k,3);
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

 
    