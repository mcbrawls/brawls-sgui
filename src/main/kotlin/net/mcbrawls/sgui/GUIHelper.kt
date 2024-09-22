package net.mcbrawls.sgui

import dev.andante.audience.Audience
import eu.pb4.sgui.api.gui.GuiInterface
import net.mcbrawls.sgui.ParentedGui.Companion.setGuiParent
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
fun Audience.openParentedGui(parent: GuiInterface, factory: (ServerPlayerEntity) -> GuiInterface) {
    openGui { player -> factory(player).setGuiParent(parent) }
}
