package webService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import model.ProjectManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import dao.Database;
import dto.FeedObjects;

@Path("/")
public class FeedService {
	
	
	@GET
	@Path("/ad/{partner_id}")
	@Produces("application/json")
	public String feedget(@PathParam("partner_id") String partner_id)
	{
		JSONObject obj=new JSONObject();				
		 Connection con = null;
		try 
		{
			Database database= new Database();
		    con = database.Get_Connection();
		    PreparedStatement pst=con.prepareStatement("SELECT * FROM campaign where partner_id="+"'"+partner_id+"'");
			ResultSet rst=pst.executeQuery();
			while(rst.next())
			{				
				obj.put("partner_id", rst.getString(1));
				obj.put("duration", rst.getInt(2));
				obj.put("ad_content", rst.getString(3));
				obj.put("creationTime", rst.getString(4));
			}
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());			
			Timestamp ts = Timestamp.valueOf(obj.getString("creationTime"));
			int sec = Integer.parseInt(obj.get("duration").toString());
			Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(ts.getTime());
	        cal.add(Calendar.SECOND, sec);
	        Timestamp later = new Timestamp(cal.getTime().getTime());
			if(date.after(later))
			{
				return "{ no active ad campaigns exist for the specified partner}";
			}

		} catch (Exception e)
		{
			System.out.println("error: "+e);
		}
		return obj+"";
		
	}

	 @POST
	    @Path("/ad")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public String addCampaign(String s) {
		 System.out.println(s+"uuuuuuuuuuuuuu");
		 JsonParser jsonparser=new JsonParser();
		 
		 JsonObject obj=(JsonObject) jsonparser.parse(s);;
		 Gson gson=new Gson();
		 
		 FeedObjects feed=gson.fromJson(obj, FeedObjects.class);

	        PreparedStatement query = null;
	        Connection conn = null;
	        String partner_id = feed.getpartner_id();
	        int duration = feed.getduration();
	        String ad_content=feed.getad_content();
	        try {
	        	Database database= new Database();
			    conn = database.Get_Connection();
			    java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	            String insertQuery = "Insert into campaign (partner_id,duration,ad_content,creationTime) values ('"
	                    + partner_id + "','" + duration + "','" + ad_content + "','"+date+"')";
	            query = conn.prepareStatement(insertQuery);
	            query.executeUpdate();
	            query.close();
	            conn.close();
	        } 
	        
	        catch(MySQLIntegrityConstraintViolationException ex)
	        {
	        	return "{A campaign already exist for a given partner_id}";
	        }
	        
	        catch (Exception e) {
	        	
	            e.printStackTrace();

	        } finally {
	            if (conn != null){/*conn.close();*/}
	        }
	        return "New record inserted";
	    }
	 

		@GET
		@Path("/GetFeeds")
		@Produces("application/json")
		public String feed()
		{
			String feeds  = null;
			try 
			{		
				ArrayList<FeedObjects> feedData = null;
				ProjectManager projectManager= new ProjectManager();
				feedData = projectManager.GetFeeds();
				Gson gson = new Gson();
				System.out.println(gson.toJson(feedData));
				feeds = gson.toJson(feedData);

			} catch (Exception e)
			{
				System.out.println("error: "+e);
			}
			return feeds;
		}
	 
}
