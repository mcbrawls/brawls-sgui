package net.mcbrawls.sgui

import eu.pb4.sgui.api.gui.SlotGuiInterface
import eu.pb4.sgui.api.gui.layered.LayeredGui
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.server.network.ServerPlayerEntity

/**
 * A simple gui with a parent.
 */
open class ParentedGui(
    /**
     * The parent of this gui. Opened on close.
     */
    private val parent: SlotGuiInterface?,

    type: ScreenHandlerType<*>,
    player: ServerPlayerEntity,
    manipulatePlayerSlots: Boolean
) : LayeredGui(type, player,manipulatePlayerSlots) {
    override fun onOpen() {
        markDirty()
    }

    override fun onClose() {
        parent?.open()
    }
}
