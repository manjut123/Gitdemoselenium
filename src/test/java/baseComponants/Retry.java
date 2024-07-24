package baseComponants;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int maxcnt=2;int cnt=0;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(cnt<maxcnt)
		{
			cnt++;
			return true;
		}
		return false;
	}

}
