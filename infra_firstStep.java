package appium;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

/*
expects sys props from the type - 
	-DdeviceName="Pixel_2_API_30"
	-DpathToApp="C:\Users\evgen\eclipse-workspace\appium\src\app-debug.apk"
	-Dplatform="ANDROID"
*/

public class infra_firstStep {
	public static void setAppiumDriverByGivenSysProps(){
       	DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty("deviceName"));
		capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("pathToApp"));
		var platformProp = System.getProperty("platform");
		String plarformName = platformProp != null && platformProp.equals("ANDROID") ? "Android" : "IOS";
		capabilities.setCapability("platformName", plarformName);
		var runTypeProp = System.getProperty("runType");
		final String urlStringVal = runTypeProp != null && runTypeProp.equals("nightly") ? "devops_something"
				: "http://127.0.0.1:4723/wd/hub";
		try {
			URL url = new URL(urlStringVal);
			WebDriver driver;
			driver = plarformName.equals("Android") ? new AndroidDriver<AndroidElement>(url, capabilities)
					: new IOSDriver<IOSElement>(url, capabilities);
			System.out.println(driver.getClass());
		} catch (MalformedURLException e) {
			System.out.println(e);
		}
	}

}
