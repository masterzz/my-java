package io.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindow {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		//设置大小
		//f.setSize(800, 600);
		//位置
		//f.setLocation(100, 50);
		
		//
		f.setBounds(100, 50, 800, 600);
		//
		f.setLayout(null);
		//创建按钮
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(100, 50, 100, 50);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("点击了按钮");
			}
		});
		//在frame中添加按钮
		f.add(btnOK);
		
		//添加创建监听
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
		
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("文件");
		JMenuItem mi = new JMenuItem("打开");
		menu.add(mi);
		bar.add(menu);
		//
		f.setJMenuBar(bar);
		
		//
		f.setVisible(true);
	}
}
