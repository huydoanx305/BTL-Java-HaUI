package com.hit.admission.components.menu;

import com.hit.admission.utils.ResourceUtil;
import javax.swing.Icon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Huy Doan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelMenu {

    private String icon;
    private String name;
    private MenuType type;

    public Icon toIcon() {
        return ResourceUtil.getImageIcon("menu-icon/" + icon);
    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
