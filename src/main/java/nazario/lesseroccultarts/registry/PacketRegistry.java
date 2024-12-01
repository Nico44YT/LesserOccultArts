package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.networking.ContractSignC2SPacket;
import nazario.lesseroccultarts.networking.ContractSyncC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.core.jmx.Server;

public class PacketRegistry {
    public static final Identifier CONTRACT_SYNC_ID = LesserOccultArtsMain.id("contract_sync");
    public static final Identifier CONTRACT_SIGN_ID = LesserOccultArtsMain.id("contract_sign");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(CONTRACT_SYNC_ID, ContractSyncC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(CONTRACT_SIGN_ID, ContractSignC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}