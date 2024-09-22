package net.mcbrawls.sgui.callback

import eu.pb4.sgui.api.ClickType
import eu.pb4.sgui.api.elements.GuiElementInterface.ClickCallback
import eu.pb4.sgui.api.gui.GuiInterface
import eu.pb4.sgui.api.gui.SlotGuiInterface
import net.mcbrawls.sgui.openGui
import net.mcbrawls.sgui.openParentedGui
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.server.network.ServerPlayerEntity

/**
 * A click callback to open a GUI.
 */
data class OpenGuiClickCallback(
    /**
     * The factory to create the new GUI.
     */
    val factory: (ServerPlayerEntity) -> GuiInterface,

    val parent: GuiInterface? = null
) : ClickCallback {
    override fun click(index: Int, type: ClickType, action: SlotActionType, gui: SlotGuiInterface) {
        val player = gui.player
        val parent = parent
        if (parent != null) {
            player.openParentedGui(parent, factory)
        } else {
            player.openGui(factory)
        }
    }
}
