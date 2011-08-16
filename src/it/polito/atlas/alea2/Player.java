package it.polito.atlas.alea2;

public interface Player {

	/**
	 * Open the video window
	 */
	public void open();

	/**
	 * Dispose the video object and free the memory
	 */
	public void dispose();

	/**
	 * Play the video
	 */
	public void play();

	/**
	 * Set video to pause
	 */
	public void pause();
	
	/**
	 * Stop the video
	 */
	void stop();


	/**
	 * Seek to video position
	 * @param time
	 * position (in milliseconds)
	 */
	public void seek(long time);

	/**
	 * Get video position 
	 * @return
	 * time in milliseconds
	 */
	long getPosition();

	/**
	 * Return the video duration 
	 * @return
	 * time in milliseconds
	 */
	public long getEndTime();

}
