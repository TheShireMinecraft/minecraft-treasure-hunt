package us.shirecraft.treasurehunt;

import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;

public class HuntTask extends BukkitRunnable {
    private final Hunt hunt;

    public HuntTask(Hunt hunt) {
        this.hunt = hunt;
    }

    public void run() {
        try {
            hunt.doTick();
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }
}