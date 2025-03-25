package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    public static final int MAX_KEY_NUM = 105;
    private int[] keycodes = new int[MAX_KEY_NUM];

    public static final int STATE_RELEASED = 0;
    public static final int STATE_PRESSED = 1 << 0;
    public static final int STATE_TYPED = 1 << 1;

    public KeyboardInput() {
        for (int i = 0; i < keycodes.length; i++) {
            keycodes[i] = STATE_RELEASED;
        }
    }

    public boolean isState(KeyState code, int state) {
        if (keycodes.length <= code.value || code.value < 0) {
            throw new KeyCodesOutOfBoundsException("Key code out of bounds for state checking");
        }
        return (keycodes[code.value] & state) != 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < MAX_KEY_NUM) {
            keycodes[keyCode] |= STATE_TYPED;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < MAX_KEY_NUM) {
            keycodes[keyCode] |= STATE_PRESSED;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < MAX_KEY_NUM) {
            keycodes[keyCode] = STATE_RELEASED;
        }
    }

    public class KeyCodesOutOfBoundsException extends ArrayIndexOutOfBoundsException {
        public KeyCodesOutOfBoundsException(String message) {
            System.err.println(message);
        }
    }

    public enum KeyState {
        RELEASED(0),
        PRESSED(1),
        TYPED(2);

        public int value;

        private KeyState(int value) {
            this.value = value;
        }
    }
}
