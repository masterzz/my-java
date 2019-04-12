package network.downloader1;

/**
 * 下载信息类
 */
public class DownloadInfo {
	private int index ;
	private String url ;
	private String location ;
	private int startPos ;
	private int endPos ;
	private int amount ;
	
	public DownloadInfo(int index,String url, String location, int startPos, int endPos,int amount) {
		this.index = index ;
		this.url = url;
		this.location = location;
		this.startPos = startPos;
		this.endPos = endPos;
		this.amount = amount ;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getStartPos() {
		return startPos;
	}
	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}
	public int getEndPos() {
		return endPos;
	}
	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
