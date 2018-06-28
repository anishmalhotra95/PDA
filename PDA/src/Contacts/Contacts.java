package Contacts;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Contacts  implements ActionListener
{    
	public static final int FRAME_WIDTH = 700;
	public static final int FRAME_HEIGHT = 600;
  	Font fLabel;
   	JFrame frame;
   	JScrollPane jsp;
   	JPanel panel,panelmain,panellabel,panelbutton;
   	JTable table;
   	JButton buttonSearch;
   	JButton buttonEdit;
   	JButton buttonDisplayAll;

   	JButton buttonNew;
  	JButton buttonDelete;
  	JButton buttonDeleteAll;
  	JButton buttonBack;
   	JLabel labelContacts;
   	int rowCount;
   	int rowSelect;
   	int columnSelect;
	JCheckBox jcb;
	public Contacts()
 	{
		AllContacts();	

	}
	public Contacts(String strName)
	{
		ContactsByName(strName);
	}	
	public Contacts(String strPhone,int i)
	{
		ContactsByPhone(strPhone);
	}	

	public Contacts(String strFromDate,String strToDate,int i)
	{
		ContactsByAnnDate(strFromDate,strToDate);
	}	
	public Contacts(String strFromDate,String strToDate)
	{
		ContactsByDOB(strFromDate,strToDate);
	}		
	boolean checkvar=false;
	public void actionPerformed(ActionEvent ee)
	{
		if(ee.getSource()==buttonNew)
		{
			NewContacts ac=new NewContacts();
			frame.dispose();
			
		}
		if(ee.getSource()==buttonSearch)
		{
			SearchContacts sc=new SearchContacts();
			frame.dispose();
		}
		
		if(ee.getSource()==buttonEdit)
		{
			try
			{
				rowSelect=table.getSelectedRow();
				Object objSNo=table.getValueAt(rowSelect,0);
				int sno=Integer.parseInt(objSNo.toString());
				EditTable et=new EditTable(sno);
				frame.dispose();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(frame,"Kindly Select Any Record!!");
			}
		}
		if(ee.getSource()==buttonDelete)
		{
			try
			{
				rowSelect=table.getSelectedRow();
				columnSelect=table.getSelectedColumn();    
				Object valueNo=table.getValueAt(rowSelect,0);			
				int confirm=JOptionPane.showConfirmDialog(frame,"Do You want to Delete Record??","Confirm Delete",JOptionPane.YES_NO_OPTION);
				if(confirm==0)
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	                	       	Connection con=DriverManager.getConnection("jdbc:odbc:mypda");			       	        
					PreparedStatement ps=con.prepareStatement("delete contacts  where s_no=?");
					ps.setString(1,(String)valueNo);
					int i=ps.executeUpdate();	
					if(i>0)
					{
						new Contacts();
						frame.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(frame," Record cannot be Saved ...");
					}
					checkvar=false;
				}
			}
	        	catch(ArrayIndexOutOfBoundsException pp)
			{ 
				JOptionPane.showMessageDialog(frame,"Kindly Select Any Record!!");
			}
			catch(Exception k)
			{
				JOptionPane.showMessageDialog(frame,k);
			}
		}
		if(ee.getSource()==buttonDeleteAll)
		{
			int confirm=JOptionPane.showConfirmDialog(frame,"Do You want to Delete All Records??","Confirm Delete",JOptionPane.YES_NO_OPTION);
			if(confirm==0)
			{
				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	                        	Connection con=DriverManager.getConnection("jdbc:odbc:mypda");			       	        
					PreparedStatement ps=con.prepareStatement("delete contacts");
					int i=ps.executeUpdate();
					if(i>0)
					{
						new Contacts();
						frame.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(frame,"Complete Record cannot be Deleted ...");
					}
				}
	        		catch(Exception pp)
				{ 
					JOptionPane.showMessageDialog(frame,pp);
				}
			}
		}
		if(ee.getSource()==buttonBack)
		{
			frame.dispose();
		}
		if(ee.getSource()==buttonDisplayAll)
		{
			new Contacts();
			frame.dispose();
		}
	}
    	class MyTableModel extends AbstractTableModel
	{
      		String col[]={"S.NO","Name","Address","Email-id","Phone","DateOfBirth","AnniversaryDate"};
		Object row[][]=new Object[rowCount][7];  	
       		public int getColumnCount() 
		{
			return col.length;
      		}
        	public int getRowCount() 
		{
   			return row.length;
		}
	     	public String getColumnName(int colName) 
		{
            		return col[colName];
        	}
       		public Object getValueAt(int rownm, int colnm)
		{
            		return row[rownm][colnm];
	        }
      		public boolean isCellEditable(int rownm, int colnm) 
		{
			if(!checkvar)
			{
				if (colnm <= 6) 	
				{
                			return false;
            			}
		 		else
		 		{
                			return true;
            			}
			}
			else
			{
				return true;
			}
       		}
		public void setValueAt(Object value, int rownm, int colnm) 
		{
           		row[rownm][colnm] = value;
            		fireTableCellUpdated(rownm,colnm);
		}
	}
	public void AllContacts()
	{
		initComponent();
	  	try
		{

		      	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		       	Connection conCount=DriverManager.getConnection("jdbc:odbc:mypda");
		       	Connection conShow=DriverManager.getConnection("jdbc:odbc:mypda");
		        PreparedStatement psShow=conShow.prepareStatement("select rtrim(s_no),rtrim(name),rtrim(address),rtrim(emailid),rtrim(phone),rtrim(dob),rtrim(anndate) from contacts order by s_no asc");
		        PreparedStatement psCount=conCount.prepareStatement("select count(*) from contacts ");
		        ResultSet rsCount=psCount.executeQuery();
		        ResultSet rsShow=psShow.executeQuery();
		        rsCount.next();
		        rowCount=Integer.parseInt(rsCount.getString(1));
			MyTableModel dataModel=new MyTableModel();
       		   	table=new JTable(dataModel);
		     	jsp=new JScrollPane(table);
		        panel.add(jsp);
		        for(int rownm=0;rownm<rowCount;rownm++)
		      	{
		   		if(rsShow.next())
				{
					table.setValueAt(rsShow.getString(1),rownm,0);
			       		table.setValueAt(rsShow.getString(2),rownm,1);
			       		table.setValueAt(rsShow.getString(3),rownm,2);
			       		table.setValueAt(rsShow.getString(4),rownm,3);
			       		table.setValueAt(rsShow.getString(5),rownm,4);
			       		table.setValueAt(rsShow.getString(6),rownm,5);
					table.setValueAt(rsShow.getString(7),rownm,6);
			 	}
		     	}
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jsp.setPreferredSize(new Dimension(688,488));
			frame.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);
		}
		catch(Exception pp)
		{
			JOptionPane.showMessageDialog(frame,pp);
		}
		
	}
	public void ContactsByName(String strName)
	{
		initComponent();
		try
		{

		      	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		       	Connection conCount=DriverManager.getConnection("jdbc:odbc:mypda");
		       	Connection conShow=DriverManager.getConnection("jdbc:odbc:mypda");
		        PreparedStatement psShow=conShow.prepareStatement("select rtrim(s_no),rtrim(name),rtrim(address),rtrim(emailid),rtrim(phone),rtrim(dob),rtrim(anndate) from contacts where name=? order by s_no asc");
		        PreparedStatement psCount=conCount.prepareStatement("select count(*) from contacts where name=?");
			psShow.setString(1,strName);
			psCount.setString(1,strName);
			
		        ResultSet rsCount=psCount.executeQuery();
		        ResultSet rsShow=psShow.executeQuery();
		        rsCount.next();
		        rowCount=Integer.parseInt(rsCount.getString(1));
			MyTableModel dataModel=new MyTableModel();
       		   	table=new JTable(dataModel);
		     	jsp=new JScrollPane(table);
		        panel.add(jsp);
		        for(int rownm=0;rownm<rowCount;rownm++)
		      	{
		   		if(rsShow.next())
				{
					table.setValueAt(rsShow.getString(1),rownm,0);
			       		table.setValueAt(rsShow.getString(2),rownm,1);
			       		table.setValueAt(rsShow.getString(3),rownm,2);
			       		table.setValueAt(rsShow.getString(4),rownm,3);
			       		table.setValueAt(rsShow.getString(5),rownm,4);
			       		table.setValueAt(rsShow.getString(6),rownm,5);
					table.setValueAt(rsShow.getString(7),rownm,6);
			 	}
		     	}
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jsp.setPreferredSize(new Dimension(688,488));
			frame.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);
			
		}
		catch(Exception pp)
		{
			JOptionPane.showMessageDialog(frame,pp);
		}
		
	}
	public void ContactsByPhone(String strPhone)
	{
		initComponent();
		try
		{

		      	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		       	Connection conCount=DriverManager.getConnection("jdbc:odbc:mypda");
		       	Connection conShow=DriverManager.getConnection("jdbc:odbc:mypda");
		        PreparedStatement psShow=conShow.prepareStatement("select rtrim(s_no),rtrim(name),rtrim(address),rtrim(emailid),rtrim(phone),rtrim(dob),rtrim(anndate) from contacts where phone=? order by s_no asc");
		        PreparedStatement psCount=conCount.prepareStatement("select count(*) from contacts where phone=?");
			psShow.setString(1,strPhone);
			psCount.setString(1,strPhone);
			
		        ResultSet rsCount=psCount.executeQuery();
		        ResultSet rsShow=psShow.executeQuery();
		        rsCount.next();
		        rowCount=Integer.parseInt(rsCount.getString(1));
			MyTableModel dataModel=new MyTableModel();
       		   	table=new JTable(dataModel);
		     	jsp=new JScrollPane(table);
		        panel.add(jsp);
		        for(int rownm=0;rownm<rowCount;rownm++)
		      	{
		   		if(rsShow.next())
				{
					table.setValueAt(rsShow.getString(1),rownm,0);
			       		table.setValueAt(rsShow.getString(2),rownm,1);
			       		table.setValueAt(rsShow.getString(3),rownm,2);
			       		table.setValueAt(rsShow.getString(4),rownm,3);
			       		table.setValueAt(rsShow.getString(5),rownm,4);
			       		table.setValueAt(rsShow.getString(6),rownm,5);
					table.setValueAt(rsShow.getString(7),rownm,6);
			 	}
		     	}
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jsp.setPreferredSize(new Dimension(688,488));
			frame.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);
			
		}
		catch(Exception pp)
		{
			JOptionPane.showMessageDialog(frame,pp);
		}
		
	}
	public void ContactsByAnnDate(String strFromDate,String strToDate)
	{

		initComponent();
	  	try
		{

		      	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		       	Connection conCount=DriverManager.getConnection("jdbc:odbc:mypda");
		       	Connection conShow=DriverManager.getConnection("jdbc:odbc:mypda");
		        PreparedStatement psShow=conShow.prepareStatement("select rtrim(s_no),rtrim(name),rtrim(address),rtrim(emailid),rtrim(phone),rtrim(dob),rtrim(anndate) from contacts where anndate between ? and ? order by s_no asc");
		        PreparedStatement psCount=conCount.prepareStatement("select count(*) from contacts where anndate between ? and ? ");
			psShow.setString(1,strFromDate);
			psShow.setString(2,strToDate);
			psCount.setString(1,strFromDate);
			psCount.setString(2,strToDate);
		        ResultSet rsCount=psCount.executeQuery();
		        ResultSet rsShow=psShow.executeQuery();
		        rsCount.next();
		        rowCount=Integer.parseInt(rsCount.getString(1));
			MyTableModel dataModel=new MyTableModel();
       		   	table=new JTable(dataModel);
		     	jsp=new JScrollPane(table);
		        panel.add(jsp);
		        for(int rownm=0;rownm<rowCount;rownm++)
		      	{
		   		if(rsShow.next())
				{
					table.setValueAt(rsShow.getString(1),rownm,0);
			       		table.setValueAt(rsShow.getString(2),rownm,1);
			       		table.setValueAt(rsShow.getString(3),rownm,2);
			       		table.setValueAt(rsShow.getString(4),rownm,3);
			       		table.setValueAt(rsShow.getString(5),rownm,4);
			       		table.setValueAt(rsShow.getString(6),rownm,5);
					table.setValueAt(rsShow.getString(7),rownm,6);
			 	}
		     	}
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jsp.setPreferredSize(new Dimension(688,488));
			frame.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);
		}
		catch(Exception pp)
		{
			JOptionPane.showMessageDialog(frame,pp);
		}	
	}
	public void ContactsByDOB(String strFromDate,String strToDate)
	{
		initComponent();

	  	try
		{

		      	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		       	Connection conCount=DriverManager.getConnection("jdbc:odbc:mypda");
		       	Connection conShow=DriverManager.getConnection("jdbc:odbc:mypda");
		        PreparedStatement psShow=conShow.prepareStatement("select rtrim(s_no),rtrim(name),rtrim(address),rtrim(emailid),rtrim(phone),rtrim(dob),rtrim(anndate) from contacts where dob between ? and ? order by s_no asc");
		        PreparedStatement psCount=conCount.prepareStatement("select count(*) from contacts where dob between ? and ?");
			psShow.setString(1,strFromDate);
			psShow.setString(2,strToDate);
			psCount.setString(1,strFromDate);
			psCount.setString(2,strToDate);
		       		        
			ResultSet rsCount=psCount.executeQuery();
		        ResultSet rsShow=psShow.executeQuery();
		        rsCount.next();
		        rowCount=Integer.parseInt(rsCount.getString(1));
			MyTableModel dataModel=new MyTableModel();
       		   	table=new JTable(dataModel);
		     	jsp=new JScrollPane(table);
		        panel.add(jsp);
		        for(int rownm=0;rownm<rowCount;rownm++)
		      	{
		   		if(rsShow.next())
				{
					table.setValueAt(rsShow.getString(1),rownm,0);
			       		table.setValueAt(rsShow.getString(2),rownm,1);
			       		table.setValueAt(rsShow.getString(3),rownm,2);
			       		table.setValueAt(rsShow.getString(4),rownm,3);
			       		table.setValueAt(rsShow.getString(5),rownm,4);
			       		table.setValueAt(rsShow.getString(6),rownm,5);
					table.setValueAt(rsShow.getString(7),rownm,6);
			 	}
		     	}
			jsp.setPreferredSize(new Dimension(688,488));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			frame.pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			frame.setResizable(false);
		}
		catch(Exception pp)
		{
			JOptionPane.showMessageDialog(frame,pp);
		}
	}
	public void initComponent()
	{
		fLabel=new Font("arial",Font.BOLD,30);
		frame=new JFrame("Contacts");
		panel=new JPanel();
                panelmain=new JPanel();
		panellabel=new JPanel();
		panelbutton=new JPanel();
		buttonNew=new JButton("New");
		buttonSearch=new JButton("Search");
		buttonEdit=new JButton("Edit");
		buttonDelete=new JButton("Delete");
		buttonDeleteAll=new JButton("Delete All");
		buttonBack=new JButton("Back");
		buttonDisplayAll=new JButton("Show All");
	
	 	jcb=new JCheckBox(" ");
		labelContacts=new JLabel("Contacts  "); 
		labelContacts.setForeground(Color.red); 
	
		frame.getContentPane().add(panelmain);
 
		panelmain.setLayout(new BorderLayout());
		panelmain.add(panel, BorderLayout.CENTER);
		panelmain.add(panellabel, BorderLayout.NORTH);
		panelmain.add(panelbutton, BorderLayout.SOUTH);

		panellabel.setBackground(Color.black);
		panelbutton.setBackground(Color.black);
		panel.setBackground(Color.white);
	  
	  	labelContacts.setFont(fLabel);
	  	panellabel.add(labelContacts);
	
		panelbutton.add(buttonNew);
		buttonNew.setBackground(Color.gray);
		panelbutton.add(buttonSearch);
		buttonSearch.setBackground(Color.gray);

		panelbutton.add(buttonEdit);
		buttonEdit.setBackground(Color.gray);

	
		panelbutton.add(buttonDelete);
		buttonDelete.setBackground(Color.gray);

		panelbutton.add(buttonDeleteAll);
		buttonDeleteAll.setBackground(Color.gray);

		panelbutton.add(buttonDisplayAll);
		buttonDisplayAll.setBackground(Color.gray);

		panelbutton.add(buttonBack);
		buttonBack.setBackground(Color.gray);
		

		buttonNew.addActionListener(this);
		buttonSearch.addActionListener(this);
		buttonEdit.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonDeleteAll.addActionListener(this);
		buttonBack.addActionListener(this);
		buttonDisplayAll.addActionListener(this);
		
	}
}