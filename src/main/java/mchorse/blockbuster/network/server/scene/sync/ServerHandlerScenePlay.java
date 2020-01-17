package mchorse.blockbuster.network.server.scene.sync;

import mchorse.blockbuster.CommonProxy;
import mchorse.blockbuster.common.tileentity.TileEntityDirector;
import mchorse.blockbuster.recording.director.Director;
import mchorse.blockbuster.network.common.scene.sync.PacketScenePlay;
import mchorse.blockbuster.recording.director.Scene;
import mchorse.mclib.network.ServerMessageHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class ServerHandlerScenePlay extends ServerMessageHandler<PacketScenePlay>
{
    @Override
    public void run(EntityPlayerMP player, PacketScenePlay message)
    {
        Scene scene = message.get(player.worldObj);

        if (message.isPlay())
        {
            if (!scene.playing)
            {
                scene.spawn(message.tick);
            }

            scene.resume(message.tick);
        }
        else if (message.isStop())
        {
            scene.stopPlayback();
        }
        else if (message.isPause())
        {
            scene.pause();
        }
        else if (message.isStart())
        {
            scene.spawn(message.tick);
        }
        else if (message.isRestart())
        {
            scene.stopPlayback();
            scene.spawn(message.tick);
        }
    }
}