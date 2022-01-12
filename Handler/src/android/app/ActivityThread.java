package android.app;

import android.MainActivity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public final class ActivityThread {

    final Handler mH = new H();

    private void attach() {
        Activity mainActivity = new MainActivity();
        mainActivity.onCreate();
        mainActivity.onStart();

        Message message = new Message();
        message.obj = mainActivity;
        mH.sendMessage(Message.obtain(message));
    }

    public static void main(String[] args) {
        Looper.prepareMainLooper();

        ActivityThread activityThread = new ActivityThread();
        activityThread.attach();

        Looper.loop();
    }

    private static class H extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Activity mainActivity = (Activity) msg.obj;
            mainActivity.onResume();
        }
    }
}
