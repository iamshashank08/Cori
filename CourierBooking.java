package cori;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

public class CourierBooking extends JDialog implements ActionListener {
	private JTextField tcno;
	private JTextField namefield;
	private JTextField FpinTF;
	private JTextField Fnumfield;
	private JTextField tcdate;
	private JTextField TnameTF;
	private JTextField TnumTF;
	private JTextField WtTF;
	private JTextField AmtTF;
	private JTextField TpinTF;
	private JPanel panelFrom;
	private JPanel panelTo;
	private JButton btnBook;
	private JButton btnCancel;
	private JComboBox TcityCB;
	Connection con;PreparedStatement ps;ResultSet rs;
	java.sql.Date date;
	int bid;
	double wt;
	double amount;
	private JComboBox CityCB;
	private JComboBox PacktCB;
	public CourierBooking() {
		setBackground(Color.BLUE);
		con=DBConnection.connect();
		getContentPane().setLayout(null);

		panelFrom = new JPanel();
		panelFrom.setBorder(new LineBorder(Color.RED, 2, true));
		panelFrom.setBackground(Color.GREEN);
		panelFrom.setLayout(null);
		panelFrom.setBounds(33, 135, 1004, 240);
		getContentPane().add(panelFrom);

		JLabel lblFromconsigner = new JLabel("From (Consigner)");
		lblFromconsigner.setBounds(411, 13, 139, 20);
		panelFrom.add(lblFromconsigner);
		lblFromconsigner.setFont(new Font("Arial", Font.BOLD, 17));

		JLabel Fname = new JLabel("Name :");
		Fname.setBounds(36, 85, 114, 20);
		panelFrom.add(Fname);
		Fname.setFont(new Font("Arial", Font.BOLD, 17));

		namefield = new JTextField();
		namefield.setBounds(162, 82, 282, 28);
		panelFrom.add(namefield);
		namefield.setColumns(10);

		JLabel Fcity = new JLabel("City :");
		Fcity.setBounds(36, 139, 114, 20);
		panelFrom.add(Fcity);
		Fcity.setFont(new Font("Arial", Font.BOLD, 17));

		JLabel Fpin = new JLabel("Pin Code :");
		Fpin.setBounds(634, 137, 114, 20);
		panelFrom.add(Fpin);
		Fpin.setFont(new Font("Arial", Font.BOLD, 17));

		FpinTF = new JTextField();
		FpinTF.setBounds(760, 136, 211, 28);
		panelFrom.add(FpinTF);
		FpinTF.setColumns(10);

		JLabel Fmobnum = new JLabel("Mobile No. :");
		Fmobnum.setBounds(36, 195, 114, 20);
		panelFrom.add(Fmobnum);
		Fmobnum.setFont(new Font("Arial", Font.BOLD, 17));

		Fnumfield = new JTextField();
		Fnumfield.setBounds(168, 195, 282, 28);
		panelFrom.add(Fnumfield);
		Fnumfield.setColumns(10);


		CityCB = new JComboBox();
		CityCB.setModel(new DefaultComboBoxModel(new String[] {"Select City", "Lucknow", "Bengaluru", "Hyderabad", "Prayagraj", "Pratapgarh", "New Delhi", "Jaipur", "Amritsar", "Pune", "Mumbai", "Bhopal", "Kashmir", "Vishakhapatnam", "Ahemdabad", "Patna", "Chennai", "Tiruvananthpuram", "Rameshwaram", "Kanyakumari"}));
		CityCB.setBounds(162, 139, 282, 28);
		panelFrom.add(CityCB);

		JLabel bookno = new JLabel("Booking No. :");
		bookno.setFont(new Font("Arial", Font.BOLD, 17));
		bookno.setBounds(70, 87, 114, 20);
		getContentPane().add(bookno);

		JLabel bookdate = new JLabel("Booking Date :");
		bookdate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		bookdate.setBounds(733, 87, 128, 20);
		getContentPane().add(bookdate);

		tcno = new JTextField();
		tcno.setHorizontalAlignment(SwingConstants.CENTER);
		tcno.setBackground(Color.WHITE);
		tcno.setEditable(false);
		tcno.setDisabledTextColor(Color.WHITE);
		tcno.setBounds(202, 85, 282, 28);
		getContentPane().add(tcno);
		tcno.setColumns(10);

		tcdate = new JTextField();
		tcdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tcdate.setHorizontalAlignment(SwingConstants.CENTER);
		tcdate.setBackground(Color.WHITE);
		tcdate.setEditable(false);
		tcdate.setColumns(10);
		tcdate.setBounds(855, 82, 182, 33);
		getContentPane().add(tcdate);


		panelTo = new JPanel();
		panelTo.setBorder(new LineBorder(Color.RED, 3, true));
		panelTo.setLayout(null);
		panelTo.setBackground(Color.YELLOW);
		panelTo.setForeground(Color.RED);
		panelTo.setBounds(33, 377, 1004, 273);
		getContentPane().add(panelTo);

		JLabel Tname = new JLabel("Name : ");
		Tname.setBounds(37, 46, 114, 20);
		panelTo.add(Tname);
		Tname.setFont(new Font("Arial", Font.BOLD, 17));

		TnameTF = new JTextField();
		TnameTF.setBounds(169, 46, 282, 28);
		panelTo.add(TnameTF);
		TnameTF.setColumns(10);

		JLabel Tcity = new JLabel("City :");
		Tcity.setBounds(37, 89, 114, 20);
		panelTo.add(Tcity);
		Tcity.setFont(new Font("Arial", Font.BOLD, 17));

		TnumTF = new JTextField();
		TnumTF.setBounds(169, 135, 282, 28);
		panelTo.add(TnumTF);
		TnumTF.setColumns(10);

		JLabel Tmob = new JLabel("Mobile No. :");
		Tmob.setBounds(37, 138, 114, 20);
		panelTo.add(Tmob);
		Tmob.setFont(new Font("Arial", Font.BOLD, 17));

		PacktCB = new JComboBox();
		PacktCB.setModel(new DefaultComboBoxModel(new String[] {"Type of Parcel", "Document", "Parcel"}));
		PacktCB.setBounds(169, 176, 282, 27);
		panelTo.add(PacktCB);

		JLabel Packt = new JLabel("Packet Type");
		Packt.setBounds(37, 176, 114, 20);
		panelTo.add(Packt);
		Packt.setFont(new Font("Arial", Font.BOLD, 17));

		JLabel Wt = new JLabel("Weight :");
		Wt.setBounds(37, 219, 114, 20);
		panelTo.add(Wt);
		Wt.setFont(new Font("Arial", Font.BOLD, 17));

		WtTF = new JTextField();
		WtTF.setBounds(169, 216, 282, 28);
		panelTo.add(WtTF);
		WtTF.setColumns(10);


		AmtTF = new JTextField();
		AmtTF.setBackground(Color.WHITE);
		AmtTF.setEditable(false);
		AmtTF.setBounds(661, 211, 209, 28);
		panelTo.add(AmtTF);
		AmtTF.setColumns(10);

		JLabel Amt = new JLabel("Amount :");
		Amt.setBounds(507, 219, 114, 20);
		panelTo.add(Amt);
		Amt.setFont(new Font("Arial", Font.BOLD, 17));

		TpinTF = new JTextField();
		TpinTF.setBounds(656, 81, 203, 28);
		panelTo.add(TpinTF);
		TpinTF.setColumns(10);

		JLabel Tpin = new JLabel("Pin Code :");
		Tpin.setBounds(507, 84, 114, 20);
		panelTo.add(Tpin);
		Tpin.setFont(new Font("Arial", Font.BOLD, 17));

		JLabel lblToconsignee = new JLabel("To (Consignee)");
		lblToconsignee.setFont(new Font("Arial", Font.BOLD, 17));
		lblToconsignee.setBounds(379, 13, 132, 20);
		panelTo.add(lblToconsignee);

		TcityCB = new JComboBox();
		TcityCB.setModel(new DefaultComboBoxModel(new String[] {"Select City", "Lucknow", "Bengaluru", "Hyderabad", "Prayagraj", "Pratapgarh", "New Delhi", "Jaipur", "Amritsar", "Pune", "Mumbai", "Bhopal", "Kashmir", "Vishakhapatnam", "Ahemdabad", "Patna", "Chennai", "Tiruvananthpuram", "Rameshwaram", "Kanyakumari"}));
		TcityCB.setBounds(169, 89, 282, 28);
		panelTo.add(TcityCB);

		btnBook = new JButton("Book");
		btnBook.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnBook.setBounds(302, 674, 97, 25);
		getContentPane().add(btnBook);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnCancel.setBounds(596, 674, 97, 25);
		getContentPane().add(btnCancel);
		setSize(1067,774);

		btnBook.addActionListener(this);
		btnCancel.addActionListener(this);
		setModal(true);
		generateValues();
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowOpened(WindowEvent we) {
				namefield.requestFocusInWindow();
			}
		});
		setVisible(true);
	}
	public static void main(String[] args) {
		new CourierBooking();
	} 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object ob=arg0.getSource();
		if(ob==btnCancel) {
			dispose();
		}
		else if(ob==btnBook) {
			calculateAmount();
			//Get all values

			wt=Double.parseDouble(WtTF.getText());

			//Get All values end

			String Name=namefield.getText();
			String cityfrom=CityCB.getSelectedItem().toString();
			String Pin=FpinTF.getText();
			String Mobile=Fnumfield.getText();

			String name=TnameTF.getText();
			String cityto=TcityCB.getSelectedItem().toString();
			String pin=TpinTF.getText();
			String mobile=TnumTF.getText();
			String Parcel=PacktCB.getSelectedItem().toString();
			String st="Insert into courierdetails (Bno,BDate,FName,Fcity,FPin,FMobile,"
					+ "TName,Tcity,TPin,TMobile,cType,Weight,Amount) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Connection cc=DBConnection.connect();
			System.out.println("Line 275");
			try {
				PreparedStatement ps=cc.prepareStatement(st);
				ps.setInt(1, bid);
				ps.setDate(2, date);
				ps.setString(3, Name);
				ps.setString(4, cityfrom);
				ps.setString(5, Pin);
				ps.setString(6, Mobile);
				ps.setString(7, name);
				ps.setString(8, cityto);
				ps.setString(9, pin);
				ps.setString(10, mobile);
				ps.setString(11, Parcel);
				ps.setDouble(12, wt);
				ps.setDouble(13, amount);
				ps.executeUpdate();		
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	void generateValues() {
		String st="select max(bno) from courierdetails";
		try {
			ps=con.prepareStatement(st);
			rs=ps.executeQuery();
			rs.next();
			bid=rs.getInt(1);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		++bid;
		tcno.setText("0");
		date=new java.sql.Date(new java.util.Date().getTime());
		tcdate.setText(date.toString());
	}
	void calculateAmount() {
		double excess=0;
		if(wt<=50){
			amount=200;
		}
		else if(wt<50 && wt <=200){
			excess=(wt-200)*10;
			amount = 200 + excess;
		}
		else{

		}
		System.out.println("Amoun="+amount);
		AmtTF.setText(""+amount);
	}

}
