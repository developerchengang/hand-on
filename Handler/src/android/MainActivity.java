package android;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.TextView;

public class MainActivity extends Activity {

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText(msg.obj.toString());
        }
    };

    private TextView mTextView;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("MainActivity------>onCreate()");
        mTextView = findViewById(R.id.text_view);
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("MainActivity------>onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("MainActivity------>onResume()");
        new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                Message message = new Message();
                message.obj = String.valueOf(i);

                mHandler.sendMessage(Message.obtain(message));
            }

        }).start();
    }
}
