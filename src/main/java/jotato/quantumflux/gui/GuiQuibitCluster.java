package jotato.quantumflux.gui;

import java.text.NumberFormat;

import org.lwjgl.opengl.GL11;

import jotato.quantumflux.inventory.ContainerQuibitCluster;
import jotato.quantumflux.tileentity.TileEntityQuibitCluster;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiQuibitCluster extends GuiContainer
{
    private static final ResourceLocation guiScreen = new ResourceLocation("quantumflux:textures/gui/quibitCluster.png");
    private TileEntityQuibitCluster cluster;
    private String displayName;
    private String maxStorage;
    private String maxTransfer;

    public GuiQuibitCluster(InventoryPlayer playerInventory, TileEntityQuibitCluster cluster)
    {
        super(new ContainerQuibitCluster(playerInventory, cluster));
        this.cluster = cluster;
        setupDisplay();
    }

    private void setupDisplay()
    {
        this.displayName = "Quibit Cluster (Level " + cluster.level + ")";
        this.maxTransfer = NumberFormat.getIntegerInstance().format(cluster.getEnergyTransferRate()) + " RF/Tick";
        this.maxStorage = NumberFormat.getIntegerInstance().format(cluster.getMaxEnergyStored(null)) + " MAX";
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p1, int p2)
    {

        this.fontRendererObj.drawString(displayName, 6, 5, 4210752);
        String energy = NumberFormat.getIntegerInstance().format(cluster.getEnergyStored(null));
        this.fontRendererObj.drawString(energy, 6, 15, 4210752);
        this.fontRendererObj.drawString(maxStorage, 6, 25, 4210752);
        this.fontRendererObj.drawString(maxTransfer, 6, 35, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p1, int p2, int p3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiScreen);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int bufferScale = this.cluster.getBufferScaled(76);
        this.drawTexturedModalRect(k + 154, l + 80 - bufferScale + 1, 0, 241 - bufferScale, 12, bufferScale);

    }
}