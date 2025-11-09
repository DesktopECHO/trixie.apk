package com.desktopecho.trixie;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ExecService extends JobIntentService {

    public static final int JOB_ID = 1;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ExecService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        final String cmd = intent.getStringExtra("cmd");
        final String args = intent.getStringExtra("args");
        final int delay = intent.getIntExtra("delay", 0);

        Thread thread = new Thread(() -> {
            try {
                if (delay > 0) {
                    Thread.sleep(delay * 1000L);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            switch (cmd) {
                case "telnetd":
                    EnvUtils.telnetd(getBaseContext(), args);
                    break;
                case "httpd":
                    EnvUtils.httpd(getBaseContext(), args);
                    break;
                case "deploy":
                    PrefStore.showNotification(getBaseContext(), null);
                    if (EnvUtils.cli(getApplicationContext(), cmd, args)) {
                        // After successful deployment, start the debian instance
                        EnvUtils.cli(getApplicationContext(), "start", "-m");
                    }
                    break;
                default:
                    PrefStore.showNotification(getBaseContext(), null);
                    EnvUtils.cli(getApplicationContext(), cmd, args);
            }
        });
        thread.start();
    }
}
