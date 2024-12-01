package nazario.lesseroccultarts.networking;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import nazario.lesseroccultarts.registry.ItemRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Optional;

public class ContractSignC2SPacket {

    private final int slot;

    public ContractSignC2SPacket(int slot) {
        this.slot = slot;
    }

    public ContractSignC2SPacket(PacketByteBuf buf) {
        this.slot = buf.readVarInt();
    }

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        ContractSignC2SPacket packet = new ContractSignC2SPacket(buf);

        writeNbtData(packet, player.getInventory().getStack(packet.getSlot()), player);
    }

    public PacketByteBuf write(PacketByteBuf buf) {
        buf.writeVarInt(this.slot);
        return buf;
    }

    private static void writeNbtData(ContractSignC2SPacket packet, ItemStack itemStack, PlayerEntity player) {
        itemStack.setSubNbt("signer", NbtString.of(player.getGameProfile().getName()));

        NbtCompound nbtCompound = itemStack.getNbt();
        ItemStack newStack = new ItemStack(ItemRegistry.SIGNED_CONTRACT);
        newStack.setNbt(nbtCompound);

        player.getInventory().setStack(packet.getSlot(), newStack);
    }

    public int getSlot() {
        return this.slot;
    }
}
