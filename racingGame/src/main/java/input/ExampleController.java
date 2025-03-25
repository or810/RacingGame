package input;

public class ExampleController implements IController {
    @Override
    public void handleKeyEvent(KeyboardInput.KeyState keyState, int state) {
        // Handle key events based on the keyState enum
        System.out.println("Key event: " + keyState + " State: " + state);
    }

    @Override
    public void handleMouseEvent(MouseInput.MouseClick mouseClick, int state) {
        // Handle mouse events based on the mouseClick enum
        System.out.println("Mouse event: " + mouseClick + " State: " + state);
    }
}