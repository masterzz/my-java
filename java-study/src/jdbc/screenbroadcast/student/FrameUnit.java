package jdbc.screenbroadcast.student;

/**
 * 帧单元,封装的是每个block的信息
 */
public class FrameUnit {
	//时间戳,标识属于哪一个屏
	private long timestamp ;
	
	//帧单元编号
	private int index ;
	
	
	//帧单元个数
	private int count ;
	
	private byte[] unitData ;
	
	public FrameUnit(long timestamp, int index, int count ,byte[] unitData) {
		this.timestamp = timestamp;
		this.index = index;
		this.count = count ;
		this.unitData = unitData;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public byte[] getUnitData() {
		return unitData;
	}

	public void setUnitData(byte[] unitData) {
		this.unitData = unitData;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
