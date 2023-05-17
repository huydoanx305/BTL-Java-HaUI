package com.hit.admission.components.scroll;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 *
 * @author Huy Doan
 */
public class ScrollBar extends JScrollBar {

    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(10, 10));
        setBackground(new Color(242, 242, 242));
        setUnitIncrement(20);
    }
}
