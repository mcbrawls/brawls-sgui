package net.mcbrawls.sgui.mixin;

import eu.pb4.sgui.api.gui.BaseSlotGui;
import eu.pb4.sgui.api.gui.GuiInterface;
import eu.pb4.sgui.api.gui.SlotGuiInterface;
import net.mcbrawls.sgui.ParentedGui;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BaseSlotGui.class)
public abstract class BaseSlotMixin implements SlotGuiInterface, ParentedGui {
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

    @Override
    public void onClose() {
        GuiInterface firstParent = ParentedGui.Companion.getFirstParent(this);
        if (firstParent != null && !firstParent.canPlayerClose()) {
            firstParent.open();
        }
    }
}
