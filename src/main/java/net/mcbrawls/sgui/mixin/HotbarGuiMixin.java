package net.mcbrawls.sgui.mixin;

import eu.pb4.sgui.api.gui.BaseSlotGui;
import eu.pb4.sgui.api.gui.HotbarGui;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HotbarGui.class)
public abstract class HotbarGuiMixin extends BaseSlotGui {
    @Shadow public abstract void setSelectedSlot(int value);

    private HotbarGuiMixin(ServerPlayerEntity player, int size) {
        super(player, size);
    }

    @Override
    public void afterOpen() {
        setSelectedSlot(player.getInventory().selectedSlot);
    }

    @Inject(method = "onSelectedSlotChange", at = @At("HEAD"), cancellable = true, remap = false)
    private void onSelectedSlotChange(int slot, CallbackInfoReturnable<Boolean> cir) {
        setSelectedSlot(slot);
        player.getInventory().selectedSlot = slot;
        cir.setReturnValue(true);
    }
}
