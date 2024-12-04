package nazario.lesseroccultarts.registry;

import nazario.lesseroccultarts.LesserOccultArtsMain;
import nazario.lesseroccultarts.networking.ContractSignC2SPacket;
import nazario.lesseroccultarts.networking.ContractSyncC2SPacket;
import nazario.liby.registry.auto.LibyAutoRegister;
import nazario.liby.registry.auto.LibyEntrypoints;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

@LibyAutoRegister(register = "registerC2SPackets", entrypoint = LibyEntrypoints.MAIN)
@LibyAutoRegister(register = "registerS2CPackets", entrypoint = LibyEntrypoints.CLIENT)
public class LoaPackets {
    public static final Identifier CONTRACT_SYNC_ID = LesserOccultArtsMain.id("contract_sync");
    public static final Identifier CONTRACT_SIGN_ID = LesserOccultArtsMain.id("contract_sign");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(CONTRACT_SYNC_ID, ContractSyncC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(CONTRACT_SIGN_ID, ContractSignC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}