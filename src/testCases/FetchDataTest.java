package testCases;

import org.junit.*;

import webService.FeedService;


public class FetchDataTest  {
	@Test
	public void sampleTest(){
		String expectedResult = "{ no active ad campaigns exist for the specified partner}";
		FeedService feed = new FeedService();
		Assert.assertEquals(expectedResult, feed.feedget("1066"));
	
	
	}
}
