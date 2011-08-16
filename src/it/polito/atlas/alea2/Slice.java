package it.polito.atlas.alea2;

public class Slice {

	private long startTime;
	private long endTime;

	public Slice (long startTime, long endTime)
	{
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	/**
	 * @return the Slice Start Time in milliseconds
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the Slice Start Time to set in milliseconds
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the Slice End Time in milliseconds
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the Slice End Time to set in milliseconds
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * The Slice content
	 */
	private String content;
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	private Object info;
	/**
	 * @return the info
	 */
	public Object getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(Object info) {
		this.info = info;
	}

}
