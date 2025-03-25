package core;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import input.ControllerManager;
import input.ExampleController;
import view.Window;

public class Application implements Runnable{
	
	// ----- Settings -----
	private Dimension dimension;
	private String title;
	
	private final int FPS = 120;
    private final int UPS = 120;
    private boolean running;
	
	// ----- Instances -----
	
	private Window window;
	private ControllerManager controllerManager;
	
	
	Application(String title, Dimension dimension) {
		this.dimension = dimension;
		this.title = title;
		this.running = true;
		controllerManager = new ControllerManager();
		
		ExampleController exampleController = new ExampleController();
        controllerManager.bindKey(exampleController, KeyEvent.VK_SPACE);
        controllerManager.bindMouseButton(exampleController, MouseEvent.BUTTON1);
		this.window = new Window(title, dimension);
	}
	
	public void start() {
		running = true;
		run();
	}
	
	public void stop() {
		running = false;
	}

	@Override
    public void run() {
        double updateInterval = 1_000_000_000.0 / UPS;
        double renderInterval = 1_000_000_000.0 / FPS;
        long lastUpdateTime = System.nanoTime();
        long lastRenderTime = System.nanoTime();

        while (running) {
            long currentTime = System.nanoTime();
            long updateElapsed = currentTime - lastUpdateTime;
            long renderElapsed = currentTime - lastRenderTime;

            if (updateElapsed >= updateInterval) {
                update();
                lastUpdateTime = currentTime;
            }

            if (renderElapsed >= renderInterval) {
                render();
                lastRenderTime = currentTime;
            }
        }
    }

	private void render() {
		// TODO Auto-generated method stub
		window.render();
	}

	private void update() {
		// TODO Auto-generated method stub
		controllerManager.update();
		window.update();
	}
}
