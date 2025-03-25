package input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerManager {
    private Map<IController, List<Integer>> keyBindings = new HashMap<>();
    private Map<IController, List<Integer>> mouseBindings = new HashMap<>();
    private KeyboardInput keyboardInput = new KeyboardInput();
    private MouseInput mouseInput = new MouseInput();

    public void bindKey(IController controller, int keyCode) {
        keyBindings.computeIfAbsent(controller, k -> new ArrayList<>()).add(keyCode);
    }

    public void bindMouseButton(IController controller, int button) {
        mouseBindings.computeIfAbsent(controller, k -> new ArrayList<>()).add(button);
    }

    public void update() {
        for (Map.Entry<IController, List<Integer>> entry : keyBindings.entrySet()) {
            IController controller = entry.getKey();
            for (int keyCode : entry.getValue()) {
                if (keyboardInput.isState(KeyboardInput.KeyState.PRESSED, KeyboardInput.STATE_PRESSED)) {
                    controller.handleKeyEvent(KeyboardInput.KeyState.PRESSED, keyCode);
                }
                if (keyboardInput.isState(KeyboardInput.KeyState.TYPED, KeyboardInput.STATE_TYPED)) {
                    controller.handleKeyEvent(KeyboardInput.KeyState.TYPED, keyCode);
                }
                if (keyboardInput.isState(KeyboardInput.KeyState.RELEASED, KeyboardInput.STATE_RELEASED)) {
                    controller.handleKeyEvent(KeyboardInput.KeyState.RELEASED, keyCode);
                }
            }
        }

        for (Map.Entry<IController, List<Integer>> entry : mouseBindings.entrySet()) {
            IController controller = entry.getKey();
            for (int button : entry.getValue()) {
                if (mouseInput.isState(MouseInput.MouseClick.PRESSED, MouseInput.STATE_PRESSED)) {
                    controller.handleMouseEvent(MouseInput.MouseClick.PRESSED, button);
                }
                if (mouseInput.isState(MouseInput.MouseClick.CLICKED, MouseInput.STATE_CLICKED)) {
                    controller.handleMouseEvent(MouseInput.MouseClick.CLICKED, button);
                }
                if (mouseInput.isState(MouseInput.MouseClick.RELEASED, MouseInput.STATE_RELEASED)) {
                    controller.handleMouseEvent(MouseInput.MouseClick.RELEASED, button);
                }
                if (mouseInput.isState(MouseInput.MouseClick.MOVED, MouseInput.STATE_MOVED)) {
                    controller.handleMouseEvent(MouseInput.MouseClick.MOVED, button);
                }
                if (mouseInput.isState(MouseInput.MouseClick.DRAGGED, MouseInput.STATE_DRAGGED)) {
                    controller.handleMouseEvent(MouseInput.MouseClick.DRAGGED, button);
                }
            }
        }
    }
}
