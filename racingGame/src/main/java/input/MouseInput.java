package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
    public static final int MAX_BUTTONS_NUM = 9;
    private int[] buttonState = new int[MAX_BUTTONS_NUM];

    public static final int STATE_RELEASED = 0;
    public static final int STATE_PRESSED = 1 << 0;
    public static final int STATE_CLICKED = 1 << 1;
    public static final int STATE_ENTERED = 1 << 2;
    public static final int STATE_EXITED = 1 << 3;
    public static final int STATE_MOVED = 1 << 4;
    public static final int STATE_DRAGGED = 1 << 5;

    public MouseInput() {
        for (int i = 0; i < buttonState.length; i++) {
            buttonState[i] = STATE_RELEASED;
        }
    }

    public boolean isState(MouseClick code, int state) {
        if (buttonState.length <= code.value || code.value < 0) {
            throw new MouseCodesOutOfBoundsException("Mouse code out of bounds for state checking");
        }
        return (buttonState[code.value] & state) != 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int button = e.getButton();
        if (button >= 0 && button < MAX_BUTTONS_NUM) {
            buttonState[button] |= STATE_DRAGGED;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int button = e.getButton();
        if (button >= 0 && button < MAX_BUTTONS_NUM) {
            buttonState[button] |= STATE_MOVED;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
        if (button >= 0 && button < MAX_BUTTONS_NUM) {
            buttonState[button] |= STATE_CLICKED;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        if (button >= 0 && button < MAX_BUTTONS_NUM) {
            buttonState[button] |= STATE_PRESSED;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
        if (button >= 0 && button < MAX_BUTTONS_NUM) {
            buttonState[button] = STATE_RELEASED;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int button = e.getButton();
        if (button >= 0 && button < MAX_BUTTONS_NUM) {
            buttonState[button] |= STATE_ENTERED;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int button = e.getButton();
        if (button >= 0 && button < MAX_BUTTONS_NUM) {
            buttonState[button] |= STATE_EXITED;
        }
    }

    public class MouseCodesOutOfBoundsException extends ArrayIndexOutOfBoundsException {
        public MouseCodesOutOfBoundsException(String message) {
            System.err.println(message);
        }
    }

    public enum MouseClick {
        ENTERED(0),
        PRESSED(1),
        CLICKED(2),
        EXITED(3),
        MOVED(4),
        DRAGGED(5);

        public int value;

        private MouseClick(int value) {
            this.value = value;
        }
    }
}
