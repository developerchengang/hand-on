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

            Message message = new Message();
            message.obj = "1";
            mHandler.sendMessageDelayed(Message.obtain(message), 500);

            message = new Message();
            message.obj = "2";
            mHandler.sendMessageDelayed(Message.obtain(message), 100);

            message = new Message();
            message.obj = "3";
            mHandler.sendMessageDelayed(Message.obtain(message), 10);

            message = new Message();
            message.obj = "4";
            mHandler.sendMessageDelayed(Message.obtain(message), 5);

        }).start();
    }
}
