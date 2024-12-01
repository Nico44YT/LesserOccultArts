package nazario.lesseroccultarts.common.entity;

import nazario.lesseroccultarts.registry.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.UUID;

public class DemonEntity extends TameableEntity implements IAnimatable, Angerable {
    private final AnimationFactory cache = GeckoLibUtil.createFactory(this);
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(DemonEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);

    private UUID targetUuid;
    public DemonEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder getDefaultAttributes()
    {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16d)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 3d)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6d)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 84d);
        // tweak these however you see fit
    }

    public static DemonEntity spawnAsFren(World world, Vec3d pos, PlayerEntity player)
    {
        DemonEntity demonEntity = new DemonEntity(EntityRegistry.DEMON, world);
        demonEntity.setPosition(pos);
        demonEntity.setOwner(player);
        world.spawnEntity(demonEntity);
        return demonEntity;
    }

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int ticks) {
        this.dataTracker.set(ANGER_TIME, ticks);
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Override
    public UUID getAngryAt() {
        return this.targetUuid;
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        this.targetUuid = uuid;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return cache;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        return PlayState.STOP;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null; // childless entity
    }
}
