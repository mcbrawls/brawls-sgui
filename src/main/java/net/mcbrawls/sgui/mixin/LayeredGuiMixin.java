package net.mcbrawls.sgui.mixin;

import eu.pb4.sgui.api.gui.GuiInterface;
import eu.pb4.sgui.api.gui.layered.LayeredGui;
import net.mcbrawls.sgui.ParentedGui;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LayeredGui.class)
public abstract class LayeredGuiMixin implements ParentedGui {
    @Unique
    @Nullable
    private GuiInterface parent = null;

    @Unique
    @Override
    public @Nullable GuiInterface getParent() {
        return this.parent;
    }

    @Unique
    @Override
    public void setParent(@Nullable GuiInterface parent) {
        this.parent = parent;
    }
}
