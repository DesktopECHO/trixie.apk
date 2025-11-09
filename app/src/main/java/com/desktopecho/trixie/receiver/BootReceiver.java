package com.desktopecho.trixie.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.desktopecho.trixie.EnvUtils;
import com.desktopecho.trixie.PrefStore;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) return;

        int delay = PrefStore.getAutostartDelay(context);

        switch (action) {
            case Intent.ACTION_BOOT_COMPLETED:
                EnvUtils.execServices(context, new String[]{"telnetd", "httpd"}, "start", delay);
                EnvUtils.execService(context, "start", "-m", delay);
                break;
            case Intent.ACTION_SHUTDOWN:
                EnvUtils.execService(context, "stop", "-u", delay);
                EnvUtils.execServices(context, new String[]{"telnetd", "httpd"}, "stop", delay);
                break;
        }
    }
}
