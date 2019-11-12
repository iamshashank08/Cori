package cori;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Deliver extends JDialog implements ActionListener {
	private JTextField DDate;
	private JTextField Dbid;
	private JTextField Dto;
	private JTextField RemTF;
	private JButton btnsub;
	int bid;
	java.sql.Date date;
	private JButton btnCancel;
	boolean pr=false;
	public Deliver() {
		try {
			String st=JOptionPane.showInputDialog(this,"Enter Booking ID");
			bid=Integer.parseInt(st);
			showData();
		}
		catch(NumberFormatException|NullPointerException pr) {
			pr.printStackTrace();
			dispose();
			return;
			
		}
		if(!pr)
		{
			dispose();
			return;
		}
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("DELIVERY DETAILS");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(434, 42, 261, 29);
		getContentPane().add(lblNewLabel);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDate.setBounds(185, 153, 80, 22);
		getContentPane().add(lblDate);
		date=new java.sql.Date(new java.util.Date().getTime());
		
		DDate = new JTextField(date.toString());
		DDate.setBackground(Color.WHITE);
		DDate.setEditable(false);
		DDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DDate.setBounds(336, 153, 161, 29);
		getContentPane().add(DDate);
		DDate.setColumns(10);

		JLabel lblBookingId = new JLabel("Booking ID :");
		lblBookingId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBookingId.setBounds(583, 153, 133, 22);
		getContentPane().add(lblBookingId);
		setSize(982, 589);
		Dbid = new JTextField(""+bid);
		Dbid.setBackground(Color.WHITE);
		Dbid.setEditable(false);
		Dbid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Dbid.setColumns(10);
		Dbid.setBounds(740, 155, 144, 27);
		getContentPane().add(Dbid);

		JLabel lblDeliveredTo = new JLabel("Delivered To :");
		lblDeliveredTo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDeliveredTo.setBounds(169, 295, 133, 22);
		getContentPane().add(lblDeliveredTo);

		JLabel lblRemark = new JLabel("Remark :");
		lblRemark.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRemark.setBounds(185, 359, 106, 22);
		getContentPane().add(lblRemark);

		Dto = new JTextField();
		Dto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Dto.setColumns(10);
		Dto.setBounds(336, 297, 144, 29);
		getContentPane().add(Dto);

		RemTF = new JTextField();
		RemTF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		RemTF.setColumns(10);
		RemTF.setBounds(336, 359, 279, 40);
		getContentPane().add(RemTF);

		btnsub = new JButton("SUBMIT");
		btnsub.setFont(new Font("Arial", Font.BOLD, 22));
		btnsub.setBounds(323, 454, 161, 40);
		getContentPane().add(btnsub);

		btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Arial", Font.BOLD, 22));
		btnCancel.setBounds(612, 454, 161, 40);
		getContentPane().add(btnCancel);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.setFont(new Font("Arial", Font.BOLD, 22));
		btnProceed.setBounds(361, 225, 161, 40);
//		getContentPane().add(btnProceed);
		btnsub.addActionListener(this);
		btnCancel.addActionListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Deliver();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object ob=arg0.getSource();
		if(ob==btnCancel) {
			dispose();
		}
		else if(ob==btnsub) {
			String name=Dto.getText();
			String remark=RemTF.getText();
			String r="update courierdetails set DDate=?, dto=?, remark=? where bno=?";
			Connection c=DBConnection.connect();
			try {
				PreparedStatement ps=c.prepareStatement(r);
				ps.setInt(4, bid);
				ps.setDate(1, date);
				ps.setString(3, remark);
				ps.setString(2, name);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(this, "Delivery Status updated");
				dispose();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	void showData() {
		String r="select bno from courierdetails where bno=?";
		Connection c=DBConnection.connect();
		try {
			PreparedStatement ps=c.prepareStatement(r);
			ps.setInt(1, bid);
			ResultSet rs=ps.executeQuery();
			if(!(rs.next())) {
				JOptionPane.showMessageDialog(this, "Booking ID Not Found");
				return;
			}
			r="select dto from courierdetails where dto is not null and bno=?";
			ps=c.prepareStatement(r);
			ps.setInt(1, bid);
			rs=ps.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(this, "Courier Already Delivered");
				dispose();
				return;
			}
			else {
				pr=true;
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
}

