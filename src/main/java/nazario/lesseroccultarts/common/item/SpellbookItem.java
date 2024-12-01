package nazario.lesseroccultarts.common.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SpellbookItem extends Item {

    public SpellbookItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        float speed = 1;
        NbtCompound nbtCompound = playerEntity.getStackInHand(hand).getNbt();

        if(getSpellIndex(nbtCompound) == 0) {
            ArrowItem arrowItem = (ArrowItem) Items.ARROW.asItem();
            ProjectileEntity projectileEntity = arrowItem.createArrow(world, new ItemStack(Items.ARROW), playerEntity);
            projectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, speed * 3.0f, 0.0f);

            world.spawnEntity(projectileEntity);
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand), true);
    }

    public int getSpellIndex(NbtCompound nbtCompound) {
        if(nbtCompound == null) return 0;
        return nbtCompound.getInt("spell_index");
    }


}
