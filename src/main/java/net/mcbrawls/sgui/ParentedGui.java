package net.mcbrawls.sgui;

import eu.pb4.sgui.api.gui.GuiInterface;
import eu.pb4.sgui.api.gui.layered.LayeredGui;
import org.jetbrains.annotations.Nullable;

public interface ParentedGui {
    /**
     * The parent of this GUI.
     */
    @Nullable
    default GuiInterface getParent() {
        throw new AssertionError();
    }

    /**
     * Sets the parent of this GUI.
     */
    default void setParent(@Nullable GuiInterface parent) {
        throw new AssertionError();
    }

    /**
     * Opens the parent GUI.
     * @return whether the gui was opened
     */
    default boolean openParent() {
        GuiInterface parent = this.getParent();
        if (parent == null) {
            return false;
        }

        if (parent.open()) {
            if (parent instanceof LayeredGui layeredGui) {
                layeredGui.markDirty();
            }

            return true;
        }

        return false;
    }

    /**
     * Gets the top-most parent in the parent heirarchy.
     */
    @Nullable
    default GuiInterface getFirstParent() {
        GuiInterface parent = this.getParent();

        if (parent == null) {
            return null;
        }

        while (true) {
            if (parent instanceof ParentedGui parentedGui) {
                GuiInterface newParent = parentedGui.getParent();
                if (newParent != null) {
                    parent = newParent;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return parent;
    }

    /**
     * Whether this gui has a parent.
     */
    default boolean hasParent() {
        return this.getParent() != null;
    }
}
