package net.mcbrawls.sgui.mixin;

import eu.pb4.sgui.api.gui.GuiInterface;
import net.mcbrawls.sgui.BasedGui;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiInterface.class)
public interface GuiInterfaceMixin extends BasedGui {
    @Override
    default @NotNull GuiInterface getBaseGui() {
        return (GuiInterface) this;
    }
}
