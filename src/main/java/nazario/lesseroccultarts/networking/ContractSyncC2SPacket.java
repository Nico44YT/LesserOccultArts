package nazario.lesseroccultarts.networking;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import nazario.lesseroccultarts.registry.ItemRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Optional;

public class ContractSyncC2SPacket {

    public static final int field_34038 = 4;
    private static final int MAX_TITLE_LENGTH = 128;
    private static final int MAX_PAGE_LENGTH = 8192;
    private static final int MAX_PAGES = 200;
    private final int slot;
    private final List<String> pages;
    private final Optional<String> title;

    public ContractSyncC2SPacket(int slot, List<String> pages, Optional<String> title) {
        this.slot = slot;
        this.pages = ImmutableList.copyOf(pages);
        this.title = title;
    }

    public ContractSyncC2SPacket(PacketByteBuf buf) {
        this.slot = buf.readVarInt();
        this.pages = buf.readCollection(PacketByteBuf.getMaxValidator(Lists::newArrayListWithCapacity, 200), buf2 -> buf2.readString(8192));
        this.title = buf.readOptional(buf2 -> buf2.readString(128));
    }

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        ContractSyncC2SPacket packet = new ContractSyncC2SPacket(buf);

        writeNbtData(packet, player.getInventory().getStack(packet.getSlot()), player);
    }

    public PacketByteBuf write(PacketByteBuf buf) {
        buf.writeVarInt(this.slot);
        buf.writeCollection(this.pages, (buf2, page) -> buf2.writeString((String)page, 8192));
        buf.writeOptional(this.title, (buf2, title) -> buf2.writeString((String)title, 128));
        return buf;
    }

    private static void writeNbtData(ContractSyncC2SPacket packet, ItemStack itemStack, PlayerEntity player) {
        NbtList nbtList = new NbtList();
        packet.getPages().stream().map(NbtString::of).forEach(nbtList::add);
        if (!packet.getPages().isEmpty()) {
            itemStack.setSubNbt("pages", nbtList);
        }
        if (packet.getTitle().isPresent()) {
            itemStack.setSubNbt("author", NbtString.of(player.getGameProfile().getName()));
            itemStack.setSubNbt("title", NbtString.of(packet.getTitle().get()));

            NbtCompound nbtCompound = itemStack.getNbt();
            ItemStack newStack = new ItemStack(ItemRegistry.FINISHED_CONTRACT);
            newStack.setNbt(nbtCompound);

            player.getInventory().setStack(packet.getSlot(), newStack);
        }
    }

    public List<String> getPages() {
        return this.pages;
    }

    public Optional<String> getTitle() {
        return this.title;
    }

    public int getSlot() {
        return this.slot;
    }
}
