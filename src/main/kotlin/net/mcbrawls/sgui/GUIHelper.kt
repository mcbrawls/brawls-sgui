package net.mcbrawls.sgui

import dev.andante.audience.Audience
import eu.pb4.sgui.api.gui.GuiInterface
import eu.pb4.sgui.api.gui.SlotGuiInterface
import net.minecraft.server.network.ServerPlayerEntity

/**
 * Opens a GUI from the given [factory].
 */
fun Audience.openGui(factory: (ServerPlayerEntity) -> GuiInterface) {
    forEachAudience { player ->
        try {
            factory(player).open()
        } catch (_: Exception) {
        }
    }
}

/**
 * Opens a GUI from the given [factory] with [parent].
 */
fun Audience.openParentedGui(parent: SlotGuiInterface, factory: (ServerPlayerEntity, SlotGuiInterface?) -> GuiInterface) {
    openGui { player -> factory(player, parent) }
}
