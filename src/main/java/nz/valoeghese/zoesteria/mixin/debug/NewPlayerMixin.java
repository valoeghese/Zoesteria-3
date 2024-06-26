package nz.valoeghese.zoesteria.mixin.debug;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import nz.valoeghese.zoesteria.client.StoreSomeData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractClientPlayer.class)
public class NewPlayerMixin {
	@Inject(at = @At("RETURN"), method="<init>")
	private void onInit(ClientLevel clientLevel, GameProfile gameProfile, CallbackInfo ci) {
		((StoreSomeData) Minecraft.getInstance()).store((AbstractClientPlayer) (Object) this);
	}
}
