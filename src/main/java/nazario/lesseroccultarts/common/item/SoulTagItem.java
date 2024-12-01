package nazario.lesseroccultarts.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulTagItem extends Item {
    public SoulTagItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if(!stack.hasNbt()) return false;
        assert stack.getNbt() != null;
        return stack.getNbt().getBoolean("linked");
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if(!stack.hasNbt()) return;
        assert stack.getNbt() != null;

        tooltip.add(1, Text.translatable("item.lesseroccultarts.soul_tag.desc", stack.getNbt().getString("victim_name")).setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)));
    }
}
