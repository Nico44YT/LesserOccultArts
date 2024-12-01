package nazario.lesseroccultarts.common.entity.demonentity;

import nazario.lesseroccultarts.registry.LoaEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.Animation;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.builder.RawAnimation;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.UUID;

public class DemonEntity extends TameableEntity implements IAnimatable, Angerable {

    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(DemonEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);

    private final AnimationFactory animationFactory = GeckoLibUtil.createFactory(this);
    private UUID targetUuid;

    public DemonEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createDefaultAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.28)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 84.0);
    }

    public static DemonEntity spawnAsAlly(World world, Vec3d position, PlayerEntity owner) {
        DemonEntity demonEntity = new DemonEntity(LoaEntities.DEMON, world);
        demonEntity.setPosition(position);
        demonEntity.setOwner(owner);
        world.spawnEntity(demonEntity);
        return demonEntity;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 0.28, false));
        this.targetSelector.add(0, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
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
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity mate) {
        return null; // This entity does not create offspring
    }

    @Override
    public boolean tryAttack(Entity target) {
        this.handSwinging = true;
        return super.tryAttack(target);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, this::defaultAnimationPredicate));
    }

    private <E extends IAnimatable> PlayState defaultAnimationPredicate(AnimationEvent<E> event) {
        String animation = "none";
        var controller = event.getController();

        if(this.handSwinging) {
            animation = switch(world.getRandom().nextBetween(0, 1)) {
                case 0 -> "animation.demon.claw";
                case 1 -> "animation.demon.shorter_claws";
                default -> throw new IllegalStateException("How the fuck does this happen?! Contact mod developer of lesser occult arts and say that the demon animation is broken!");
            };
            this.handSwinging = false;
        } else if(controller.getAnimationState().equals(AnimationState.Stopped) || controller.getCurrentAnimation() == null || controller.getCurrentAnimation().animationName.equals("animation.demon.walk") || controller.getCurrentAnimation().animationName.equals("animation.demon.idle")){
            if(event.isMoving()) {
                animation = "animation.demon.walk";
            } else {
                animation = "animation.demon.idle";
            }
        }

        if(!animation.equals("none")) controller.setAnimation(new AnimationBuilder().addAnimation(animation));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationFactory;
    }
}
