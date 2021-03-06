package Login;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Form extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel l1, l2, l3;
	final JTextField text1;
	final JPasswordField text2;
	JButton b, s;

	Form() {

		l3 = new JLabel("Login System");
		l3.setForeground(Color.darkGray);
		Font font = new Font("SansSerif", Font.ITALIC, 30);
		l3.setFont(font);
		l3.setBounds(100, 20, 200, 50);
		l1 = new JLabel("Username:");
		l1.setForeground(Color.blue);
		l1.setBounds(100, 100, 70, 20);
		text1 = new JTextField(25);
		text1.setBounds(180, 100, 150, 20);
		l2 = new JLabel("Password:");
		l2.setForeground(Color.blue);
		l2.setBounds(100, 200, 70, 20);
		text2 = new JPasswordField(15);
		text2.setBounds(180, 200, 150, 20);
		text2.setToolTipText("enter password here");
		b = new JButton("Login");
		b.setBackground(Color.cyan);
		b.setBounds(140, 300, 80, 20);
		s = new JButton("SignUp");
		s.setBackground(Color.cyan);
		s.setBounds(260, 300, 80, 20);
		add(l3);
		add(l1);
		add(text1);
		add(l2);
		add(text2);
		add(b);
		add(s);
		b.addActionListener(this);
		s.addActionListener(this);
		setTitle("Form");
		setLayout(null);
		setVisible(true);
		setSize(500, 500);
	}

	public static void main(String[] args) {
		new Form();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String value1 = text1.getText();
		String value2 = new String(text2.getPassword());
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";
		String user1 = "";
		String pass1 = "";
		try {
			conn = DriverManager.getConnection(dbUrl, user, pass);
			st = conn.createStatement();
			if (e.getSource() == b) {
				if (value1.equals("") || value2.equals("")) {
					JOptionPane.showMessageDialog(this, "username or password cannot be empty", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					rs = st.executeQuery(
							"SELECT *FROM login WHERE username='" + value1 + "'&& password='" + value2 + "'");
					while (rs.next()) {
						user1 = rs.getString("username");
						pass1 = rs.getString("password");
					}
					if (value1.equals(user1) && value2.equals(pass1)) {
						JOptionPane.showMessageDialog(this, "Successful! ");
						this.dispose();
						new Welcome(user1);
					} else
						JOptionPane.showMessageDialog(this, "Incorrect username or password", "Error",
								JOptionPane.ERROR_MESSAGE);
				}
			} else if (e.getSource() == s) {
				if (value1.equals("") || value2.equals("")) {
					JOptionPane.showMessageDialog(this, "username or password cannot be empty", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					this.dispose();
					new Entry(value1, value2, dbUrl, user, pass);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

class Welcome {
	Welcome(String user) {
		JFrame f1 = new JFrame("welcome example");
		Font font = new Font("verdana", Font.HANGING_BASELINE, 40);
		JLabel l1 = new JLabel("welcome " + user + "!");
		l1.setFont(font);
		l1.setForeground(Color.BLUE);
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setSize(450, 300);
		JButton b = new JButton("continue");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f1.dispose();

			}
		});
		b.setBounds(200, 400, 90, 20);
		f1.add(l1);
		f1.add(b);
		f1.setLayout(null);
		f1.setVisible(true);
		f1.setSize(500, 500);
	}
}

class Entry extends Form {
	private static final long serialVersionUID = 1L;

	Entry(String value1, String value2, String dbUrl, String user, String pass) {
		JFrame f2 = new JFrame("Registration Form!");
		JLabel l = new JLabel("Registration Form!");
		Font font = new Font("SansSerif", Font.ITALIC, 30);
		l.setFont(font);
		JLabel l1 = new JLabel("First name:");
		l1.setForeground(Color.blue);
		JLabel l2 = new JLabel("Last name:");
		l2.setForeground(Color.blue);
		JLabel l3 = new JLabel("Phone no.:");
		l3.setForeground(Color.blue);
		JLabel l6 = new JLabel("DOB:");
		l6.setForeground(Color.blue);
		JLabel l7 = new JLabel("Country:");
		l7.setForeground(Color.blue);

		JTextField t4 = new JTextField();
		t4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isAlphabetic(e.getKeyChar()) && (e.getKeyChar() != '\b') && (e.getKeyChar() != ' ')) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(f2, "Characters only");
				}
			}
		});

		JComboBox<String> cb = new JComboBox<>();
		cb.addItem("Year");
		for (int i = 2016; i > 1900; i--) {
			cb.addItem(Integer.toString(i));
		}

		JComboBox<String> cb1 = new JComboBox<>();
		cb1.addItem("Month");
		for (int i = 01; i <= 12; i++) {
			cb1.addItem(Integer.toString(i));
		}

		JComboBox<String> cb2 = new JComboBox<>();
		cb2.addItem("Date");
		for (int i = 01; i <= 31; i++) {
			cb2.addItem(Integer.toString(i));
		}

		JLabel l5 = new JLabel("Password:");
		l5.setForeground(Color.blue);
		JLabel l4 = new JLabel("Confirm Password:");
		l4.setForeground(Color.blue);
		JTextField t1 = new JTextField();
		t1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isAlphabetic(e.getKeyChar()) && (e.getKeyChar() != '\b') && (e.getKeyChar() != ' ')) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(f2, "Characters only");
				}
			}
		});
		JTextField t2 = new JTextField();
		t2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isAlphabetic(e.getKeyChar()) && (e.getKeyChar() != '\b') && (e.getKeyChar() != ' ')) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(f2, "Characters only");
				}
			}
		});
		JTextField t3 = new JTextField();
		t3.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != '\b')) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(f2, "number input only!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JPasswordField p = new JPasswordField();
		JPasswordField p1 = new JPasswordField();
		l.setBounds(75, 20, 250, 40);
		l1.setBounds(75, 70, 100, 20);
		t1.setBounds(210, 70, 130, 20);
		l2.setBounds(75, 140, 100, 20);
		t2.setBounds(210, 140, 130, 20);
		l3.setBounds(75, 210, 100, 20);
		t3.setBounds(210, 210, 130, 20);
		l6.setBounds(75, 280, 100, 20);
		cb.setBounds(210, 280, 70, 20);
		cb1.setBounds(280, 280, 70, 20);
		cb2.setBounds(350, 280, 70, 20);
		l7.setBounds(75, 350, 130, 20);
		t4.setBounds(210, 350, 130, 20);
		l5.setBounds(75, 420, 125, 20);
		p1.setBounds(210, 420, 130, 20);
		l4.setBounds(75, 490, 125, 20);
		p.setBounds(210, 490, 130, 20);
		JButton b = new JButton("Submit");
		b.setBounds(140, 600, 80, 20);
		b.setBackground(Color.cyan);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s1 = t1.getText();
				String s2 = t2.getText();
				String s3 = t3.getText();
				int len = s3.length();
				String s6 = t4.getText();
				String s4 = new String(p.getPassword());
				String s5 = new String(p1.getPassword());
				String year = cb.getSelectedItem().toString();
				String month = cb1.getSelectedItem().toString();
				String date = cb2.getSelectedItem().toString();
				if (year.equals("Year") || month.equals("Month") || date.equals("Date")) {
					JOptionPane.showMessageDialog(f2, "Invalid DOB!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String d = year + "-" + month + "-" + date;
					if (len != 10) {
						JOptionPane.showMessageDialog(f2, "Invalid Phone Number!", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						if (s6.equals("")) {
							JOptionPane.showMessageDialog(f2, "Invalid Country!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							if (s5.equals(s4)) {
								try {
									Connection con = DriverManager.getConnection(dbUrl, user, pass);
									PreparedStatement ps = con
											.prepareStatement("insert into login values(?,?,?,?,?,?,?)");
									ps.setString(1, value1);
									ps.setString(2, s4);
									ps.setString(3, s1);
									ps.setString(4, s2);
									ps.setString(5, s3);
									ps.setString(6, d);
									ps.setString(7, s6);
									ps.executeUpdate();
									String msg = "congrats! you are a registered user";
									JOptionPane.showMessageDialog(f2, msg, "Successful", JOptionPane.PLAIN_MESSAGE);
									f2.dispose();
									new Welcome(value1);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(f2, "Password doesn't match", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
		f2.add(l7);
		f2.add(t4);
		f2.add(cb2);
		f2.add(cb1);
		f2.add(cb);
		f2.add(l6);
		f2.add(l);
		f2.add(p);
		f2.add(p1);
		f2.add(l5);
		f2.add(l4);
		f2.add(t3);
		f2.add(t2);
		f2.add(t1);
		f2.add(l1);
		f2.add(l2);
		f2.add(l3);
		f2.add(b);
		f2.setLayout(null);
		f2.setVisible(true);
		f2.setSize(500, 700);
	}
}
