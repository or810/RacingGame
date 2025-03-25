import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

import core.Application;
import core.ApplicationBuilder;
import core.ResourceManager;


public class Launcher {
	
	public Launcher() {
        
		Application application = new ApplicationBuilder()
								   	 .title("Racing Game")
									 .dimention(new Dimension(600, 400))
									 .build();
		ResourceManager.getInstance().loadTexture(
				"C:\\Users\\דוד\\eclipse-workspace\\racingGame\\src\\main\\java\\lines-traffic-paved-roads-background.jpg", "track");
				
		application.start();
		System.out.println("Hi");
		
	}


	public static void main(String[] args) {
		
		Launcher launcher = new Launcher();
	}

}
