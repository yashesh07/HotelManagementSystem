package main.content;

import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import main.CurrentUser;
import main.database.SqlStatementManager;
import main.resources.ColorPallete;

public class SpaPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 5L;
	JRadioButton spaType1 = new JRadioButton();
	JRadioButton spaType2 = new JRadioButton();
	JRadioButton spaType3 = new JRadioButton();
	JComboBox<Object> sessionTime;
	JButton backButton = new JButton();
	JButton bookButton = new JButton();

	public SpaPanel() {
		this.setSize(1080,635);
		this.setLayout(null);
		this.setBackground(new ColorPallete().getC3orange());

		JLabel titleLabel = new JLabel("SPA");
		titleLabel.setBounds(new Rectangle(440, 25, 200, 75));
		titleLabel.setFont(new Font("Lucida Handwriting", Font.PLAIN, 50));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		this.add(titleLabel);

		JLabel imageLabel1 = new JLabel();
		imageLabel1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/images/spa/spaType1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		imageLabel1.setBounds(45, 125, 300, 100);
		imageLabel1.setHorizontalAlignment(JLabel.CENTER);
		this.add(imageLabel1);

		spaType1.setText("Hot Stone Massage");
		spaType1.setFont(new Font(null, Font.PLAIN, 20));
		spaType1.setBounds(45, 230, 300, 50);
		spaType1.setVerticalTextPosition(JRadioButton.CENTER);
		spaType1.setHorizontalAlignment(JRadioButton.CENTER);
		spaType1.setForeground(Color.BLACK);
		spaType1.setBackground(new ColorPallete().getC3orange());
		spaType1.setFocusPainted(false);
		spaType1.setSelected(true);
		this.add(spaType1);

		JLabel imageLabel2 = new JLabel();
		imageLabel2.setIcon(new ImageIcon(new ImageIcon("src/main/resources/images/spa/spaType2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		imageLabel2.setBounds(390, 125, 300, 100);
		imageLabel2.setHorizontalAlignment(JLabel.CENTER);
		this.add(imageLabel2);

		spaType2.setText("Swedish Massage");
		spaType2.setFont(new Font(null, Font.PLAIN, 20));
		spaType2.setBounds(390, 230, 300, 50);
		spaType2.setVerticalTextPosition(JRadioButton.CENTER);
		spaType2.setHorizontalAlignment(JRadioButton.CENTER);
		spaType2.setForeground(Color.BLACK);
		spaType2.setBackground(new ColorPallete().getC3orange());
		spaType2.setFocusPainted(false);
		this.add(spaType2);

		JLabel imageLabel3 = new JLabel();
		imageLabel3.setIcon(new ImageIcon(new ImageIcon("src/main/resources/images/spa/spaType3.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		imageLabel3.setBounds(735, 125, 300, 100);
		imageLabel3.setHorizontalAlignment(JLabel.CENTER);
		this.add(imageLabel3);

		spaType3.setText("Aromatherapy");
		spaType3.setFont(new Font(null, Font.PLAIN, 20));
		spaType3.setBounds(735, 230, 300, 50);
		spaType3.setVerticalTextPosition(JRadioButton.CENTER);
		spaType3.setHorizontalAlignment(JRadioButton.CENTER);
		spaType3.setForeground(Color.BLACK);
		spaType3.setBackground(new ColorPallete().getC3orange());
		spaType3.setFocusPainted(false);
		this.add(spaType3);

		ButtonGroup groupRole = new ButtonGroup();
		groupRole.add(spaType1);
		groupRole.add(spaType2);
		groupRole.add(spaType3);

		JLabel selectSlotLabel = new JLabel("--select slot--");
		selectSlotLabel.setBounds(390,300,300,20);
		selectSlotLabel.setFont(new Font(null, Font.PLAIN, 20));
		selectSlotLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(selectSlotLabel);

		String[] sessionDuration={"10 AM - 11 AM","11 AM - 12 PM","12 PM - 01 PM","01 PM - 02 PM","02 PM - 03 PM","03 PM - 04 PM","04 PM - 05 PM","05 PM - 06 PM"};
		sessionTime = new JComboBox<>(sessionDuration);
		sessionTime.setSelectedIndex(0);
		sessionTime.setEditable(false);
		sessionTime.setBounds(390,330,300,50);
		sessionTime.setRequestFocusEnabled(false);
		this.add(sessionTime);

		backButton.setText("Back");
		backButton.setBounds(10, 10, 100, 50);
		backButton.setFont(new Font(null, Font.PLAIN, 25));
		backButton.setHorizontalAlignment(JButton.CENTER);
		backButton.setVerticalAlignment(JButton.CENTER);
		backButton.setFocusable(false);
		backButton.setForeground(Color.white);
		backButton.setBorderPainted(false);
		backButton.setBackground(Color.BLACK);
		backButton.addActionListener(this);
		this.add(backButton);
		
		bookButton.setText("Book");
		bookButton.setBounds(460, 475, 160, 50);
		bookButton.setFont(new Font(null, Font.PLAIN, 25));
		bookButton.setHorizontalAlignment(JButton.CENTER);
		bookButton.setVerticalAlignment(JButton.CENTER);
		bookButton.setFocusable(false);
		bookButton.setForeground(Color.white);
		bookButton.setBorderPainted(false);
		bookButton.setBackground(Color.BLACK);
		bookButton.addActionListener(this);
		this.add(bookButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
    		this.removeAll();
        	this.revalidate();
        	this.add(new ActivitiesPanel());
        	this.repaint();
            this.validate();
    	}
		else if(e.getSource() == bookButton){
			String spaType = "";
			if(spaType1.isSelected())	spaType = "Hot Stone Massage";
			else if(spaType2.isSelected())	spaType = "Swedish Massage";
			else if(spaType3.isSelected())	spaType = "Aromatherapy";
			SqlStatementManager sqlHelp = new SqlStatementManager("boyodb", "root", CurrentUser.sqlPassword);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			}
			boolean isAvailable = true;
			String query = "select * from availtable";

			Connection connection = sqlHelp.getConnection();
			Statement st = null;
			try {
				st = connection.createStatement();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			ResultSet rs = null;
			try {
				rs = st.executeQuery(query);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			while(true){
				try {
					if (!rs.next()) break;
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				try {
					if(rs.getString(6)!=null && rs.getString(2).equals(sessionTime.getSelectedItem()) && rs.getString(6).equals("1")){
						isAvailable = false;
						break;
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			try {
				st.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			if(isAvailable){
				String q = "INSERT INTO availtable(timeslot, spa) VALUES(?, ?)";
				PreparedStatement ps = null;
				try {
					ps = connection.prepareStatement(q);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				;
				try {
					ps.setString(1, (String) sessionTime.getSelectedItem());
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				try {
					ps.setString(2, "1");
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				try {
					ps.executeUpdate();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				try {
					ps.close();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				JOptionPane.showMessageDialog(new JFrame(), "slot booked", "Alert",JOptionPane.PLAIN_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(new JFrame(), "slot not available", "Alert",JOptionPane.ERROR_MESSAGE);
			}
			try {
				connection.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}
}
