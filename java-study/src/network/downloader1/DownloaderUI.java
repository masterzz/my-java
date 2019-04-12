package network.downloader1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * 下载ui
 */
public class DownloaderUI extends JFrame implements ActionListener {
	//
	private JLabel lblUrl;
	// url
	private JTextField tfUrl;

	private JLabel lblLocation;
	// 文件保存位置
	private JTextField tfLocation;

	private JLabel lblCount;
	// 线程数
	private JTextField tfCount;

	private JButton btnStart;
	private JButton btnPause;
	
	private JPanel barContainer ;

	// 进度条
	public JProgressBar[] bars;

	// 已完成线程数
	private int completedCount = 0;
	
	private Downloader downloader;

	public DownloaderUI() {
		init();
		this.setVisible(true);
	}

	/**
	 * 初始化布局
	 */
	private void init() {
		this.setBounds(100, 50, 800, 600);
		this.setLayout(null);
		// url标签
		lblUrl = new JLabel("url地址");
		lblUrl.setBounds(0, 0, 100, 30);
		this.add(lblUrl);

		tfUrl = new JTextField("http://localhost:9090/ziling.mp3");
		tfUrl.setBounds(0, 40, 800, 30);
		this.add(tfUrl);

		// location标签
		lblLocation = new JLabel("保存地址");
		lblLocation.setBounds(0, 80, 100, 30);
		this.add(lblLocation);

		tfLocation = new JTextField("e:/ziling.mp3");
		tfLocation.setBounds(0, 120, 800, 30);
		this.add(tfLocation);

		// 线程数
		lblCount = new JLabel("线程数量");
		lblCount.setBounds(0, 160, 100, 30);
		this.add(lblCount);

		tfCount = new JTextField("3");
		tfCount.setBounds(0, 200, 800, 30);
		this.add(tfCount);

		//开始按钮
		btnStart = new JButton("开始");
		btnStart.setBounds(0, 240, 100, 30);
		btnStart.addActionListener(this);
		this.add(btnStart);
		
		
		// 暂停按钮
		btnPause = new JButton("暂停");
		btnPause.setBounds(150, 240, 100, 30);
		btnPause.addActionListener(this);
		this.add(btnPause);
		
		barContainer = new JPanel();
		barContainer.setBounds(0, 280, 800, 300);
		barContainer.setLayout(null);
		this.add(barContainer);
		//
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
	}

	// 按钮点击事件
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		//开始按钮
		if (source == btnStart) {
			String url = tfUrl.getText();
			String location = tfLocation.getText();
			int count = Integer.parseInt(tfCount.getText());
			downloader = new Downloader(url, location, count, this);
			// 2.动态进度条
			this.addBars(downloader.getDownloadInfos());
			// 3.开始下载
			downloader.startDownload();
		}
		//暂停
		else if(source == btnPause){
			DownloadThread.pause = !DownloadThread.pause;
			if(DownloadThread.pause){
				btnPause.setText("继续");
			}
			else{
				btnPause.setText("暂停");
			}
		}
	}

	/**
	 * 动态进度条
	 */
	private void addBars(List<DownloadInfo> downloadInfos) {
		bars = new JProgressBar[downloadInfos.size()];
		for (DownloadInfo di : downloadInfos) {
			bars[di.getIndex()] = new JProgressBar();
			bars[di.getIndex()].setForeground(Color.RED);
			bars[di.getIndex()].setBackground(Color.BLACK);
			
			bars[di.getIndex()].setBounds(10, 0 + (di.getIndex() * 10), 750, 6);
			bars[di.getIndex()].setMaximum(di.getEndPos() - di.getStartPos() + 1);
			bars[di.getIndex()].setValue(di.getAmount());			//已经下载的量
			barContainer.add(bars[di.getIndex()]);
		}
		// 重新绘画窗口
		this.repaint();
	}

	/**
	 * 更新进度条
	 */

	public void updateBar(int index, int len) {
		bars[index].setValue(bars[index].getValue() + len);
		bars[index].repaint(0);
		if(bars[index].getValue() >= bars[index].getMaximum()){
			processFinish();
		}
	}

	/**
	 * 处理完成
	 */
	private synchronized void processFinish() {
		completedCount ++ ;
		if(completedCount == bars.length){
			barContainer.removeAll();
//			for(JProgressBar bar : bars){
//				this.remove(bar);
//			}
			completedCount = 0 ;
			bars = null ;
			this.repaint();
			downloader.prop = null ;
			downloader.deleteMetaFile();
		}
	}
}
