package net.mcbrawls.sgui

import eu.pb4.sgui.api.gui.GuiInterface
import eu.pb4.sgui.api.gui.layered.LayeredGui

interface ParentedGui {
    /**
     * The parent of this GUI.
     */
    var parent: GuiInterface?

    /**
     * Opens the parent GUI.
     * @return whether the gui was opened
     */
    fun openParent(): Boolean {
        val parent = parent ?: return false
        (parent as? LayeredGui)?.markDirty()
        return parent.open()
    }

    companion object {
        /**
         * Sets this GUI's parent GUI.
         */
        fun GuiInterface.setGuiParent(parent: GuiInterface) : GuiInterface {
            val parented = this as? ParentedGui ?: return this
            parented.parent = parent
            return this
        }

        /**
         * Gets this GUI's parent GUI.
         */
        fun GuiInterface.getGuiParent() : GuiInterface? {
            val parented = this as? ParentedGui ?: return null
            return parented.parent
        }

        /**
         * Gets the top-most parent in the parent heirarchy.
         */
        fun GuiInterface.getFirstParent(): GuiInterface? {
            var parent = getGuiParent() ?: return null

            while (true) {
                parent = parent.getGuiParent() ?: break
            }

            return parent
        }

        /**
         * Whether this gui has a parent.
         */
        fun GuiInterface.hasParent(): Boolean {
            return getGuiParent() != null
        }

        /**
         * Opens a GUI's parent GUI.
         */
        fun GuiInterface.openGuiParent(): GuiInterface {
            val parented = this as? ParentedGui ?: return this
            parented.openParent()
            return this
        }
    }
}
