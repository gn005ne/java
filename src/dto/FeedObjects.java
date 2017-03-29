package dto;

public class FeedObjects {
	
	private String partner_id;
	private int duration;
	private String ad_content;
	private String creationTime;
	/**
	 * @return the partner_id
	 */
	public String getpartner_id() {
		return partner_id;
	}
	/**
	 * @param partner_id the partner_id to set
	 */
	public void setpartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	
	/**
	 * @return the duration
	 */
	public int getduration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setduration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the ad_content
	 */
	public String getad_content() {
		return ad_content;
	}
	/**
	 * @param ad_content the ad_content to set
	 */
	public void setad_content(String ad_content) {
		this.ad_content = ad_content;
	}
	
	public String getcreationTime() {
		return creationTime;
	}
	/**
	 * @param ad_content the creationTime to set
	 */
	public void setcreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
	public FeedObjects() {
		super();
	}
	
}
