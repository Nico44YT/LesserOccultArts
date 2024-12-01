package nazario.lesseroccultarts.common.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(value=EnvType.CLIENT)
public class MaliceInkParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    MaliceInkParticle(ClientWorld world, double x, double y, double z, double dx, double dy, double dz, SpriteProvider spriteProvider) {
        super(world, x, y, z, dx, dy, dz);
        float f;
        this.spriteProvider = spriteProvider;
        this.maxAge = 20;
        this.red = f = this.random.nextFloat() * 0.6f + 0.4f;
        this.green = f;
        this.blue = f;
        this.scale = 0.2f;
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public int getBrightness(float tint) {
        return 0xF000F0;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;

        this.x += this.velocityX;
        this.y += this.velocityY;
        this.z += this.velocityZ;

        this.velocityX *= 0.9;
        this.velocityY *= 0.9;
        this.velocityZ *= 0.9;

        this.scale *= 0.9f;

        if (this.age++ >= this.maxAge) {
            this.markDead();
            return;
        }
        this.setSpriteForAge(this.spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    @Environment(value=EnvType.CLIENT)
    public static class Factory
            implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double dx, double dy, double dz) {
            return new MaliceInkParticle(clientWorld, x, y, z, dx, dy, dz, this.spriteProvider);
        }
    }
}