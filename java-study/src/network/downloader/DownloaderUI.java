package network.downloader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * 下载ui
 */
public class DownloaderUI extends JFrame implements ActionListener{
	//
	private JLabel lblUrl ;
	//url
	private JTextField tfUrl ;
	
	private JLabel lblLocation ;
	//文件保存位置
	private JTextField tfLocation ;
	
	private JLabel lblCount ;
	//线程数
	private JTextField tfCount ;
	
	private JButton btnStart ;
	
	//进度条
	public JProgressBar bar ;
	
	public DownloaderUI(){
		init();
		this.setVisible(true);
	}

	/**
	 * 初始化布局
	 */
	private void init() {
		this.setBounds(100, 50,800, 600);
		this.setLayout(null);
		//url标签
		lblUrl= new JLabel("url地址");
		lblUrl.setBounds(0, 0, 100, 30);
		this.add(lblUrl);
		
		tfUrl = new JTextField("http://localhost:9090/ziling.mp3");
		tfUrl.setBounds(0, 40, 800, 30);
		this.add(tfUrl);
		
		//location标签
		lblLocation= new JLabel("保存地址");
		lblLocation.setBounds(0, 80, 100, 30);
		this.add(lblLocation);
		
		tfLocation = new JTextField("e:/ziling.mp3");
		tfLocation.setBounds(0, 120, 800, 30);
		this.add(tfLocation);
		
		//线程数
		lblCount= new JLabel("线程数量");
		lblCount.setBounds(0, 160, 100, 30);
		this.add(lblCount);
		
		tfCount = new JTextField("3");
		tfCount.setBounds(0, 200, 800, 30);
		this.add(tfCount);
		
		//按钮
		btnStart = new JButton("开始");
		btnStart.setBounds(0, 240, 100, 30);
		btnStart.addActionListener(this);
		this.add(btnStart);
		
		bar = new JProgressBar();
		bar.setBounds(0, 280, 750, 30);
		this.add(bar);
		//
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
	}
	//
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btnStart){
			String url = tfUrl.getText();
			String location = tfLocation.getText();
			int count = Integer.parseInt(tfCount.getText());
			//创建下载器
			Downloader d = new Downloader(url, location, count, this);
			//设置bar的maxvalue
			bar.setMaximum(d.getLength());
			
			//开始下载
			d.startDownload();
		}
	}
}
