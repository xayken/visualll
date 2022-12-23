package Pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class StudentForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSurname;
	private JList list;
	private JButton btnReadfile;
	private JButton btnWritefile;
	private JTextField txtFile;
	
	static ArrayList<Student> students = new ArrayList<Student>(); 
	
	public void writeFile(String fileName, ArrayList<Student> students) throws IOException{
		
		File file = new File("C:/Users/ersinyazici/workspace/prj2312/"+fileName);
		FileWriter fwriter = new FileWriter(file);
		BufferedWriter bfwriter = new BufferedWriter(fwriter);
		
		for (Student s : students) {
			bfwriter.write(s.name);
			bfwriter.newLine();
			bfwriter.write(s.surname);
			bfwriter.newLine();
			bfwriter.write(String.valueOf(s.age));
			bfwriter.newLine();
		}
		bfwriter.close();
		JOptionPane.showMessageDialog(contentPane, "Write Process Completed!");
	}
	
	public ArrayList<Student> readFile(String fileName) throws FileNotFoundException{
		
		File file = new File("C:/Users/ersinyazici/workspace/prj2312/"+fileName);
		Scanner scan = new Scanner(file);
		ArrayList<Student> tempStudents = new ArrayList<Student>();
		while(scan.hasNext()){
			Student stu = new Student(scan.nextLine(),
									  scan.nextLine(),
									  Integer.parseInt(scan.nextLine()));
			tempStudents.add(stu);
		}
		return tempStudents;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm frame = new StudentForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(10, 11, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtFile = new JTextField();
		txtFile.setBounds(106, 218, 86, 20);
		contentPane.add(txtFile);
		txtFile.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(10, 47, 86, 20);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		final JComboBox cbAge = new JComboBox();
		cbAge.setBounds(10, 92, 86, 20);
		contentPane.add(cbAge);
		for (int i = 18; i < 66; i++) {
			cbAge.addItem(i);
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtName.getText();
				String surname = txtSurname.getText();
				int age = Integer.parseInt(cbAge.getSelectedItem().toString()); 
				Student stu = new Student(name, surname, age);
				students.add(stu);
				txtName.setText("");
				txtSurname.setText("");
				cbAge.setSelectedIndex(0);
				JOptionPane.showMessageDialog(contentPane, "Student Saved!");
			}
		});
		btnSave.setBounds(7, 149, 89, 23);
		contentPane.add(btnSave);
		
		final DefaultListModel<String> model = new DefaultListModel<String>();
		
		final JList list = new JList();
		list.setModel(model);
		list.setBounds(255, 47, 169, 203);
		contentPane.add(list);
		
		btnReadfile = new JButton("ReadFile");
		btnReadfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				ArrayList<Student> tempList = readFile(txtFile.getText());
				for (Student s : tempList) {
					model.addElement(s.name+" "+s.surname+" "+s.age);
				}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnReadfile.setBounds(7, 183, 89, 23);
		contentPane.add(btnReadfile);
		
		btnWritefile = new JButton("WriteFile");
		btnWritefile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					writeFile(txtFile.getText(), students);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnWritefile.setBounds(7, 217, 89, 23);
		contentPane.add(btnWritefile);
	}
}
