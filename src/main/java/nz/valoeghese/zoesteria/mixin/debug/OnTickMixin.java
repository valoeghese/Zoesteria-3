package nz.valoeghese.zoesteria.mixin.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import nz.valoeghese.zoesteria.client.StoreSomeData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mixin(Minecraft.class)
public class OnTickMixin implements StoreSomeData {
	@Inject(at = @At("RETURN"), method ="tick")
	private void onTick(CallbackInfo info) {
		synchronized(this.cc$oof) {
			Iterator<WeakReference<AbstractClientPlayer>> foo = this.cc$oof.iterator();
			while(foo.hasNext())
			{
				if (foo.next().get() == null) {
					System.out.println("ZOESTERIA GC: Removing Player");
					foo.remove();
				}
			}
		}
	}

	@Unique
	private final List<WeakReference<AbstractClientPlayer>> cc$oof = new ArrayList<>();

	@Override
	public void store(AbstractClientPlayer player) {
		synchronized(this.cc$oof) {
			System.out.println("ZOESTERIA GC: Adding Player");
			this.cc$oof.add(new WeakReference<>(player));
		}
	}
}
