package nazario.lesseroccultarts.common.item;

import nazario.lesseroccultarts.registry.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SilverDagger extends SwordItem {
    public SilverDagger(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity attackerPlayer, LivingEntity victimEntity, Hand hand) {
        if(attackerPlayer.getWorld().isClient) return ActionResult.SUCCESS;
        double distance = attackerPlayer.getPos().distanceTo(victimEntity.getPos());
        if(distance <= 2f && victimEntity instanceof PlayerEntity) {
            NbtCompound nbt = new NbtCompound();
            nbt.putUuid("victim_uuid", victimEntity.getUuid());
            nbt.putBoolean("bloody", true);
            stack.setNbt(nbt);

            attackerPlayer.setStackInHand(hand, stack);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack mainHandStack = user.getStackInHand(Hand.MAIN_HAND);
        ItemStack offHandStack = user.getStackInHand(Hand.OFF_HAND);

        if (offHandStack.isOf(ItemRegistry.SOUL_TAG)) {
            NbtCompound offHandNbt = offHandStack.getNbt();

            if (offHandNbt != null && offHandNbt.getBoolean("linked")) {
                // Handle already linked logic here (currently empty).
            } else {
                NbtCompound mainHandNbt = mainHandStack.getNbt();

                if (mainHandNbt != null && mainHandNbt.getBoolean("bloody")) {
                    if (offHandNbt == null) {
                        offHandNbt = new NbtCompound();
                    }

                    offHandNbt.putUuid("victim_uuid", mainHandNbt.getUuid("victim_uuid"));
                    offHandNbt.putString("victim_name", world.getServer().getPlayerManager().getPlayer(mainHandNbt.getUuid("victim_uuid")).getDisplayName().getString());
                    offHandNbt.putBoolean("linked", true);
                    offHandStack.setNbt(offHandNbt);
                }
            }
        }

        return super.use(world, user, hand);
    }
}
