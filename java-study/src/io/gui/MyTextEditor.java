package io.gui;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyTextEditor extends JFrame implements ActionListener{

	private static final long serialVersionUID = 6351611109227931816L;
	
	public static void main(String[] args) {
		new MyTextEditor();
	}
	//文本域
	private JTextArea taContent;
	
	private JButton btnSave ;
	private JButton btnOpen ;
	
	public MyTextEditor(){
		init();
		this.setVisible(true);
	}

	/**
	 * 初始化窗口
	 */
	private void init() {
		this.setBounds(100, 50, 800, 600);
		this.setLayout(null);
		
		//text
		JScrollPane panel = new JScrollPane();
		panel.setLayout(null);
		panel.setBounds(10, 10, 800, 350);
		panel.setAutoscrolls(true);
		panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(panel);
		
		taContent = new JTextArea();
		taContent.setBounds(0, 0, 750, 350);
		panel.add(taContent);
		
		btnSave = new JButton("保存");
		btnSave.setBounds(100, 450, 100, 50);
		btnSave.addActionListener(this);
		this.add(btnSave);
		
		btnOpen = new JButton("打开");
		btnOpen.setBounds(500, 450, 100, 50);
		btnOpen.addActionListener(this);
		this.add(btnOpen);
		
		//
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		Object s= e.getSource();
		if(s == btnSave){
			try {
				FileWriter w = new FileWriter("d:/111.txt");
				w.write(taContent.getText());
				w.close();
				taContent.setText("");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		else if(s == btnOpen){
			//System.out.println("open");
			try {
				FileDialog  fd = new FileDialog(this, "打开文件");
				fd.setVisible(true);
				
				//
				File f = new File(fd.getDirectory(), fd.getFile());
				FileReader reader = new FileReader(f);
				
				char[] buf = new char[1024];
				int len = 0 ;
				while((len = reader.read(buf)) != -1){
					String ss = new String(buf,0,len);
					taContent.append(ss);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
