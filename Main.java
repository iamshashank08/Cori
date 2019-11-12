package cori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Font;
import java.sql.Date;

import javax.swing.JMenuItem;

public class Main extends JFrame implements ActionListener{
	private JMenuItem close,tdel, tbook,cupdate,cbook;

	public Main() {
		setSize(1200, 800);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCourier = new JMenu("Courier");
		mnCourier.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnCourier);

		cbook = new JMenuItem("Book New");
		cbook.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnCourier.add(cbook);

		cupdate = new JMenuItem("Update Delivery");
		cupdate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnCourier.add(cupdate);

		JMenu mnReports = new JMenu("Reports");
		mnReports.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnReports);

		tbook = new JMenuItem("Today's Bookings");
		tbook.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnReports.add(tbook);

		tdel = new JMenuItem("Today's Deliveries");
		tdel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnReports.add(tdel);

		JMenu mnExit = new JMenu("Exit");
		mnExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnExit);

		close = new JMenuItem("Close");
		close.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnExit.add(close);

		close.addActionListener(this);
		cbook.addActionListener(this);
		tdel.addActionListener(this); 
		tbook.addActionListener(this);
		cupdate.addActionListener(this);
		tbook.addActionListener(this);

		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object ob=arg0.getSource();
		if(ob==close){
			System.exit(0);
		}
		else if(ob==cbook){
			new CourierBooking();
		}
		else if(ob==tdel){
			java.sql.Date today=new java.sql.Date(new java.util.Date().getTime());
			new ViewDeliveryByDate(today);
		}
		else if(ob==tbook){
			java.sql.Date today=new java.sql.Date(new java.util.Date().getTime());
			new ViewBookingByDate(today);
		}
		else if(ob==cupdate){
			new Deliver();
		}
	}
}
