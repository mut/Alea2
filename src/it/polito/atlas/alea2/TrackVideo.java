package it.polito.atlas.alea2;

public class TrackVideo extends Track {
	public TrackVideo (String name)
	{
		super(name);
		type = Track.Types.Video;
		player = null;
	}
	public TrackVideo (String name, Player playerInstance)
	{		
		super(name);
		type = Track.Types.Video;
		player = playerInstance;
	}
	
	public Player player;

	public void open() {
		if (player != null)
			player.open();
	}

	public void close() {
		if (player != null)
			player.dispose();
	}

	public void play() {
		if (player != null)
			player.play();
	}

	public void pause() {
		if (player != null)
			player.pause();
	}

	/**
	 * Seek to video position
	 * @param time
	 * position (in milliseconds)
	 */
	public void seek(long time) {
		if (player != null)
			player.seek(time);
	}

	@Override
	public long getEndTime() {
		if (player == null)
			return -1;
		return player.getEndTime();
	}

	public long getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	private long offset=0;
	
	/**
	 * @return the offset in milliseconds
	 */
	public long getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set in milliseconds
	 */
	public void setOffset(long offset) {
		this.offset = offset;
	}

}
