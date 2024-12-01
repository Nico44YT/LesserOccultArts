package nazario.lesseroccultarts.common.screen.contract;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import nazario.lesseroccultarts.networking.ContractSignC2SPacket;
import nazario.lesseroccultarts.registry.PacketRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import nazario.lesseroccultarts.common.screen.contract.ContractPageWidget;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import java.util.*;

public class ContractSignScreen extends Screen {
    private final PlayerEntity player;
    private final ItemStack itemStack;
    private final Hand hand;
    private int currentPage;
    private final List<String> pages = Lists.newArrayList();
    private ContractPageWidget nextPageButton;
    private ContractPageWidget previousPageButton;
    private ButtonWidget doneButton;
    private ButtonWidget signButton;
    private Text pageIndicatorText = ScreenTexts.EMPTY;

    public ContractSignScreen(PlayerEntity player, ItemStack itemStack, Hand hand) {
        super(Text.empty());
        this.player = player;
        this.itemStack = itemStack;
        this.hand = hand;
        NbtCompound nbtCompound = itemStack.getNbt();
        if (nbtCompound != null) {
            BookScreen.filterPages(nbtCompound, this.pages::add);
        }
        if (this.pages.isEmpty()) {
            this.pages.add("");
        }
    }

    private int countPages() {
        return this.pages.size();
    }

    @Override
    protected void init() {
        int centerX = (this.width - 192) / 2;
        this.nextPageButton = this.addDrawableChild(new ContractPageWidget(centerX + 116, 159, true, button -> this.openNextPage(), true, ContractViewScreen.CONTRACT_BACKGROUND_TEXTURE));
        this.previousPageButton = this.addDrawableChild(new ContractPageWidget(centerX + 43, 159, false, button -> this.openPreviousPage(), true, ContractViewScreen.CONTRACT_BACKGROUND_TEXTURE));
        this.signButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, 196, 98, 20, Text.translatable("screen.lesseroccultarts.contract.button_sign"), button -> {
            int i = this.hand == Hand.MAIN_HAND ? this.player.getInventory().selectedSlot : 40;
            ContractSignC2SPacket packet = new ContractSignC2SPacket(i);
            ClientPlayNetworking.send(PacketRegistry.CONTRACT_SIGN_ID, packet.write(PacketByteBufs.create()));
            this.client.setScreen(null);
        }));
        this.doneButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 2, 196, 98, 20, ScreenTexts.DONE, button -> {
            this.client.setScreen(null);
        }));
        this.updateButtons();
    }

    private void openPreviousPage() {
        if (this.currentPage > 0) {
            --this.currentPage;
        }
        this.updateButtons();
    }

    private void openNextPage() {
        if (this.currentPage < this.countPages() - 1) {
            ++this.currentPage;
        }
        this.updateButtons();
    }

    private void updateButtons() {
        this.previousPageButton.visible = this.currentPage > 0;
        this.nextPageButton.visible = this.currentPage < this.countPages() - 1;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // Render background
        this.renderBackground(matrices);

        // Draw the book texture
        int centerX = (this.width - 192) / 2;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, ContractViewScreen.CONTRACT_BACKGROUND_TEXTURE);
        this.drawTexture(matrices, centerX, 2, 0, 0, 192, 192);

        // Render page content
        this.renderPageContent(matrices, centerX);

        // Call to super for additional rendering
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void renderPageContent(MatrixStack matrices, int centerX) {
        // Render page indicator
        this.pageIndicatorText = Text.translatable("book.pageIndicator", this.currentPage + 1, this.countPages());
        int pageIndicatorWidth = this.textRenderer.getWidth(this.pageIndicatorText);
        this.textRenderer.draw(matrices, this.pageIndicatorText, (float) (centerX - pageIndicatorWidth + 192 - 44), 18.0f, 0);

        // Render page content
        String content = this.getCurrentPageContent();
        List<OrderedText> lines = this.textRenderer.wrapLines(Text.literal(content), 114);
        int y = 32;
        for (OrderedText line : lines) {
            this.textRenderer.draw(matrices, line, (float) (centerX + 36), (float) y, 0);
            y += this.textRenderer.fontHeight;
        }
    }

    private String getCurrentPageContent() {
        if (this.currentPage >= 0 && this.currentPage < this.pages.size()) {
            return this.pages.get(this.currentPage);
        }
        return "";
    }
}
