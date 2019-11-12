package cori;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewBookingByDate extends JDialog
{
	JScrollPane jsp;
	java.sql.Date date;
	public ViewBookingByDate(java.sql.Date date) 
	{
		this.date=date;
		setBackground(new Color(0,255,0));
		getContentPane().setBackground(new Color(0,255,255));
		setTitle("Table View");
		String st="select bno, bdate, fname, fcity, tname,tcity from courierdetails where bdate=?";
		try {
			Connection con = DBConnection.connect();
			PreparedStatement ps = con.prepareStatement(st);
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			String columns[] = new String[]{"Booking No.","Booking Date","Consignee","City From","Consignor","City To"};
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columns);
			JTable table = new JTable();
			table.setFont(new Font("Tahoma",Font.BOLD,14));
			table.setBackground(new Color(255,228,181));
			table.setModel(model);
			table.setFillsViewportHeight(true);
			while(rs.next())
			{
				int bno = rs.getInt("bno");
				String fromname = rs.getString("fname");
				String fromcity = rs.getString("fcity");
				String toname = rs.getString("tname");
				String tocity = rs.getString("tcity");
				model.addRow(new Object[]{bno,date,fromname,fromcity,toname,tocity});
				jsp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				
			}
			
		} catch (SQLException se) 
		{
			// TODO: handle exception
			se.printStackTrace();
			dispose();
	
			return;
		}
		getContentPane().add(jsp);
		setSize(649,377);
		jsp.setBounds(44, 11, 550, 250);
		getContentPane().setLayout(null);
		JButton btnclose = new JButton("Close");
		btnclose.setForeground(new Color(255,255,255));
		btnclose.setBackground(new Color(255,0,0));
		btnclose.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		btnclose.setFont(new Font("Tahoma",Font.BOLD,20));
		btnclose.setBounds(251,282,119,33);
		setModal(true);
		setLocation(120,100);
		add(btnclose);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ViewBookingByDate(new java.sql.Date(new java.util.Date().getTime()));
	}
}
