package net.mcbrawls.sgui.mixin;

import eu.pb4.sgui.api.gui.GuiInterface;
import eu.pb4.sgui.api.gui.SlotGuiInterface;
import eu.pb4.sgui.api.gui.layered.LayeredGui;
import net.mcbrawls.sgui.ParentedGui;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(targets = { "eu.pb4.sgui.api.gui.layered.BackendSimpleGui" }, remap = false)
public abstract class BackendSimpleGuiMixin implements SlotGuiInterface, ParentedGui {
    @Shadow @Final public LayeredGui gui;

    @Unique
    @Override
    public @Nullable GuiInterface getParent() {
        return ParentedGui.Companion.getGuiParent(this.gui);
    }

    @Unique
    @Override
    public void setParent(@Nullable GuiInterface parent) {
        ParentedGui.Companion.setGuiParent(this.gui, parent);
    }

    @Override
    public void onClose() {
        GuiInterface firstParent = ParentedGui.Companion.getFirstParent(this.gui);
        if (firstParent != null && !firstParent.canPlayerClose()) {
            firstParent.open();
        }
    }
}
