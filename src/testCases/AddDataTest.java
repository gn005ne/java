package testCases;

import org.junit.*;

import webService.FeedService;

public class AddDataTest {
	@Before
	public void enterNewEntryToDatabase(){
		FeedService feed = new FeedService();
		feed.addCampaign("{\n" +
		        "   \"partner_id\":\"106\",\n" +
		        "   \"duration\":120,\n" +
		        "   \"ad_content\":\"ad. for pojo\"\n" +
		        "}");
	}
	
	@Test
	public void sampleTest(){
		String expectedResult = "{A campaign already exist for a given partner_id}";
		FeedService feed = new FeedService();
		Assert.assertEquals(expectedResult, feed.addCampaign("{\n" +
        "   \"partner_id\":\"106\",\n" +
        "   \"duration\":120,\n" +
        "   \"ad_content\":\"ad. for pojo\"\n" +
        "}"));
		
		
	}
	

}
