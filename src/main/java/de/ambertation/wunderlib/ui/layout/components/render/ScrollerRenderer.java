package de.ambertation.wunderlib.ui.layout.components.render;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import de.ambertation.wunderlib.ui.layout.values.Rectangle;

@Environment(EnvType.CLIENT)
public interface ScrollerRenderer {
    default int scrollerHeight() {
        return 16;
    }
    default int scrollerWidth() {
        return 8;
    }

    default int scrollerPadding() {
        return 2;
    }

    default Rectangle getScrollerBounds(Rectangle renderBounds) {
        return new Rectangle(
                renderBounds.right() - this.scrollerWidth(),
                renderBounds.top,
                this.scrollerWidth(),
                renderBounds.height
        );
    }

    default Rectangle getPickerBounds(Rectangle renderBounds, int pickerOffset, int pickerSize) {
        return new Rectangle(
                renderBounds.left,
                renderBounds.top + pickerOffset,
                renderBounds.width,
                pickerSize
        );
    }

    void renderScrollBar(Rectangle renderBounds, int pickerOffset, int pickerSize);
}
