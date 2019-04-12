package network.qq.client;

import network.qq.common.MessageFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


/**
 * 客户端聊天界面
 */
public class QQClientUI extends JFrame implements ActionListener{
	
	private MessageSender sender ;

	//历史聊天区
	private JTextArea taHistory ;
	
	//好友列表
	private JList<String> lstFriends  ;
	
	//消息输入区
	private JTextArea taInputMessage ;
	
	//发送按钮
	private JButton btnSend ;
	
	//刷新好友列表按钮
	private JButton btnRefresh ;
	
	public QQClientUI(MessageSender sender){
		this.sender = sender ;
		init();
		this.setVisible(true);
	}

	/**
	 * 初始化布局
	 */
	private void init() {
		this.setTitle("QQClient");
		this.setBounds(100,100, 800, 600);
		this.setLayout(null);
		
		//历史区
		taHistory = new JTextArea();
		taHistory.setBounds(0, 0, 600, 400);
		
		JScrollPane sp1 = new JScrollPane(taHistory);
		sp1.setBounds(0, 0, 600, 400);
		this.add(sp1);
		
		//lstFriends
		lstFriends = new JList<String>();
		lstFriends.setBounds(620, 0, 160, 400);
		this.add(lstFriends);
		
		//taInputMessage
		taInputMessage = new JTextArea();
		taInputMessage.setBounds(0, 420, 540, 160);
		this.add(taInputMessage);
		
		//btnSend
		btnSend = new JButton("发送");
		btnSend.setBounds(560, 420, 100, 160);
		btnSend.addActionListener(this);
		this.add(btnSend);
		
		//btnRefresh
		btnRefresh = new JButton("刷新");
		btnRefresh.setBounds(680, 420, 100, 160);
		btnRefresh.addActionListener(this);
		this.add(btnRefresh);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		//发送按钮
		if(source == btnSend){
			String str = taInputMessage.getText();
			if(str != null && !str.equals("")){
				//发送自己的聊天信息
				sender.sendMessage(MessageFactory.popClientTextMessage(str));
			}
		}
		//刷新按钮
		else if(source == btnRefresh){
			sender.sendMessage(MessageFactory.popClientRefreshFriends());
		}
	}
	
	/**
	 * 刷新好友列表
	 */
	public void refreshFriendList(List<String> list){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String s : list){
			listModel.addElement(s);
		}
		lstFriends.setModel(listModel);
	}

	/**
	 * 更新历史区域内容
	 */
	public void updateHistory(String[] ss) {
		taHistory.append("[" +ss[0] + "]说:\r\n");
		String formatStr = ss[1].replace("\r\n", "\r\n\t");
		formatStr = "\t" + formatStr + "\r\n" ; 
		taHistory.append(formatStr);
	}
}
