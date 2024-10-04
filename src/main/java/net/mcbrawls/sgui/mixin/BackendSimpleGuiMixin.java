package net.mcbrawls.sgui.mixin;

import eu.pb4.sgui.api.gui.GuiInterface;
import eu.pb4.sgui.api.gui.SlotGuiInterface;
import eu.pb4.sgui.api.gui.layered.LayeredGui;
import net.mcbrawls.sgui.BasedGui;
import net.mcbrawls.sgui.ParentedGui;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(targets = { "eu.pb4.sgui.api.gui.layered.BackendSimpleGui" }, remap = false)
public abstract class BackendSimpleGuiMixin implements SlotGuiInterface, ParentedGui, BasedGui {
    @Shadow @Final public LayeredGui gui;

    @Unique
    @Override
    public @Nullable GuiInterface getParent() {
        return ((ParentedGui) this.gui).getParent();
    }

    @Unique
    @Override
    public void setParent(@Nullable GuiInterface parent) {
        ((ParentedGui) this.gui).setParent(parent);
    }

    @Override
    public void onClose() {
        this.gui.onClose();

        GuiInterface firstParent = ((ParentedGui) this.gui).getFirstParent();
        if (firstParent != null && !firstParent.canPlayerClose()) {
            firstParent.open();
        }
    }

    @Override
    public @NotNull LayeredGui getBaseGui() {
        return this.gui;
    }
}
