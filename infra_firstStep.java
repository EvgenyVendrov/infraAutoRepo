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

public class infra_firstStep {
	public static void main(String[] args){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty("deviceName"));
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("pathToApp"));
        var platformProp = System.getProperty("platform");
        String plarformName = platformProp != null && platformProp.equals("ANDROID")?"Android":"IOS";
        capabilities.setCapability("platformName", plarformName);
		var runTypeProp = System.getProperty("runType");
		final String urlStringVal = runTypeProp != null && runTypeProp.equals("nightly")?"devops_something":"http://127.0.0.1:4723/wd/hub";
		URL url = null;//java is kinda stupid lol
		try {
			 url = new URL(urlStringVal);	
		}catch(MalformedURLException e) {
			System.out.println(e);
		}
        WebDriver driver;
		if(plarformName.equals("Android")) {
			driver = new AndroidDriver<AndroidElement>(url,capabilities);
		}else {
			driver = new IOSDriver<IOSElement>(url,capabilities);
		}
        System.out.println(driver.getClass());
	}

}
