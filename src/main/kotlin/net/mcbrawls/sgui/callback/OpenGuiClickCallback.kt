package net.mcbrawls.sgui.callback

import eu.pb4.sgui.api.ClickType
import eu.pb4.sgui.api.elements.GuiElementInterface.ClickCallback
import eu.pb4.sgui.api.gui.GuiInterface
import eu.pb4.sgui.api.gui.SlotGuiInterface
import net.mcbrawls.sgui.openGui
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.server.network.ServerPlayerEntity

/**
 * A click callback to open a GUI.
 */
data class OpenGuiClickCallback(
    /**
     * The factory to create the new GUI.
     */
    val factory: (ServerPlayerEntity, SlotGuiInterface?) -> GuiInterface
) : ClickCallback {
    override fun click(index: Int, type: ClickType, action: SlotActionType, gui: SlotGuiInterface) {
        val player = gui.player
        player.openGui { factory(player, gui) }
    }

    companion object {
        /**
         * Creates a callback that opens a gui.
         * @return a click callback
         */
        fun create(factory: (ServerPlayerEntity) -> GuiInterface): ClickCallback {
            return OpenGuiClickCallback { player, _ -> factory(player) }
        }

        /**
         * Creates a callback that opens a parented gui.
         * @return a click callback
         */
        fun createParented(factory: (ServerPlayerEntity, SlotGuiInterface?) -> GuiInterface): ClickCallback {
            return OpenGuiClickCallback(factory)
        }
    }
}
