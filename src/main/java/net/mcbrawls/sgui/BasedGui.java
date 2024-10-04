package net.mcbrawls.sgui;

import eu.pb4.sgui.api.gui.GuiInterface;

public interface BasedGui {
    default GuiInterface getBaseGui() {
        throw new AssertionError();
    }
}
