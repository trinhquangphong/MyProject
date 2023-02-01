package crud;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCRUD {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCRUD window = new JavaCRUD();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCRUD() {
		initialize();
		Connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_1;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanly", "root","");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void table_load() {
		try {
			pst = con.prepareStatement("SELECT * FROM book");
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 956, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(333, 10, 178, 66);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(51, 86, 486, 281);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(32, 41, 83, 31);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(32, 82, 83, 31);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(32, 123, 83, 31);
		panel.add(lblNewLabel_1_2);
		
		txtname = new JTextField();
		txtname.setBounds(125, 51, 246, 19);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(125, 92, 246, 19);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(125, 133, 246, 19);
		panel.add(txtprice);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname, edition, price;
				bname = txtname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				try {
					String query = "INSERT INTO book(name, edition, price) VALUES(?,?,?)";
					pst = con.prepareStatement(query);
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!!!");
					table_load();
					
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSave.setBounds(32, 181, 100, 31);
		panel.add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEdit.setBounds(166, 181, 100, 31);
		panel.add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtname.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClear.setBounds(299, 181, 100, 31);
		panel.add(btnClear);
		
		table = new JTable();
		table.setBounds(575, 359, 321, -271);
		frame.getContentPane().add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(51, 392, 486, 92);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Book id");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_2.setBounds(52, 30, 68, 26);
		panel_1.add(lblNewLabel_2);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id = txtid.getText();
					String sql = "select name, edition, price from book where id = ?";
					pst = con.prepareStatement(sql);
					pst.setString(1, id);
					rs = pst.executeQuery();
					
					if(rs.next() == true) {
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
					}
					
					else
					{
						txtname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
		txtid.setBounds(124, 35, 275, 19);
		panel_1.add(txtid);
		txtid.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname, edition, price, bid;
				bname = txtname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtid.getText();
				
				try {
					pst = con.prepareStatement("update book set name = ?, edition = ?, price = ? where id = ?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Update Successfully!!!");
					table_load();
					
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(605, 392, 100, 31);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				bid = txtid.getText();
				try {
					pst = con.prepareStatement("delete from book where id = ?");
					pst.setString(1, bid);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Delete successfully!!!");
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtname.requestFocus();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(758, 388, 100, 31);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(590, 84, 313, 281);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
	}
}
