package it.polito.atlas.alea2;

public interface Player {

	/**
	 * Open all the video window
	 */
	public void open();

	/**
	 * Open the specified video window
	 */
	public void open(TrackVideo tv);

	/**
	 * Add a track
	 * @param tv
	 */
	public void addVideo(TrackVideo tv);
	
	/**
	 * Dispose the video object and free the memory
	 */
	public void dispose();

	/**
	 * Play the video(s)
	 */
	public void play();

	/**
	 * Set video(s) to pause
	 */
	public void pause();
	
	/**
	 * Stop the video(s)
	 */
	void stop();


	/**
	 * Seek the video(s) to the specified position
	 * @param time
	 * position (in milliseconds)
	 */
	public void seek(long time);

	/**
	 * Get the current video(s) position 
	 * @return
	 * time in milliseconds
	 */
	long getPosition();

	/**
	 * Return the video(s) max duration (including the offset)
	 * @return
	 * time in milliseconds
	 */
	public long getEndTime();

}
