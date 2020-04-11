package us.shirecraft.easteregghunt;

import java.lang.reflect.InvocationTargetException;

public class HuntTask implements Runnable {

    private final EasterEggHunt plugin;
    private final Hunt hunt;

    public HuntTask(EasterEggHunt plugin, Hunt hunt) {
        this.plugin = plugin;
        this.hunt = hunt;
    }

    public void run() {
        try {
            hunt.doTick();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}