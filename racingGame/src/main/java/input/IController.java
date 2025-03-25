package input;

public interface IController {
    void handleKeyEvent(KeyboardInput.KeyState keyState, int state);
    void handleMouseEvent(MouseInput.MouseClick mouseClick, int state);
}
