package jdbc.screenbroadcast.student;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StudentUI extends JFrame{
	
	private JLabel lblIcon ;
	
	public StudentUI(){
		init();
	}

	private void init() {
		this.setTitle("学生端");
		this.setBounds(0, 0, 1366, 768);
		this.setLayout(null);
		
		//标签空间
		lblIcon = new JLabel();
		lblIcon.setBounds(0, 0, 1366, 768);
		
		//图标
		ImageIcon icon = new ImageIcon("D:/Koala.jpg");
		lblIcon.setIcon(icon);
		this.add(lblIcon);
		this.setVisible(true);
	}
	
	/**
	 * 更新图标
	 */
	public void updateIcon(byte[] unzipFrameData){
		ImageIcon icon = new ImageIcon(unzipFrameData);
		lblIcon.setIcon(icon);
	}
}
