package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.FeedObjects;


public class Project {
	 
	public ArrayList<FeedObjects> GetFeeds(Connection connection) throws Exception
	{
		ArrayList<FeedObjects> feedData = new ArrayList<FeedObjects>();
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM campaign ORDER BY partner_id DESC");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				FeedObjects feedObject = new FeedObjects();
				feedObject.setpartner_id(rs.getString("partner_id"));
				feedObject.setduration(rs.getInt("duration"));
				feedObject.setad_content(rs.getString("ad_content"));
				feedObject.setcreationTime(rs.getString("creationTime"));
				feedData.add(feedObject);
			}
			return feedData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	
	
}
