package mchorse.blockbuster.network.client;

import mchorse.blockbuster.common.tileentity.TileEntityModel;
import mchorse.blockbuster.network.common.PacketModifyModelBlock;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientHandlerModifyModelBlock extends ClientMessageHandler<PacketModifyModelBlock>
{
    @Override
    @SideOnly(Side.CLIENT)
    public void run(EntityPlayerSP player, PacketModifyModelBlock message)
    {
        TileEntity tile = player.world.getTileEntity(message.pos);

        if (tile instanceof TileEntityModel)
        {
            TileEntityModel model = (TileEntityModel) tile;

            model.copyData(message.model, message.merge);
            model.entity.ticksExisted = 0;
        }
    }
}