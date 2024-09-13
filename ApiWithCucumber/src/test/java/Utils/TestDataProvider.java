package Utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	
	@DataProvider(name="AddBooksData")
	public Object[][] addBook()
	{
		Object[][] obj = new Object[][] {{"ankit","123"},{"pandey","456"},{"kumar","678"}};
		
		return obj;
		
	}

}
