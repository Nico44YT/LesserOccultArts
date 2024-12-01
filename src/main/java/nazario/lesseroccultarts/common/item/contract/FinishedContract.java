package nazario.lesseroccultarts.common.item.contract;

import nazario.lesseroccultarts.common.screen.contract.ContractSignScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FinishedContract extends Item {
    public FinishedContract(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if(world.isClient) MinecraftClient.getInstance().setScreen(new ContractSignScreen(user, itemStack, hand));

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal(stack.getNbt().getString("title"));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.hasNbt()) {
            assert stack.getNbt() != null;
            tooltip.add(1, Text.empty().append(Text.translatable("screen.lesseroccultarts.contract.author", stack.getNbt().getString("author")).formatted(Formatting.DARK_GRAY)));
            tooltip.add(2, Text.empty().append(Text.translatable("screen.lesseroccultarts.contract.signer", "...").formatted(Formatting.DARK_GRAY)));
        }
    }
}
