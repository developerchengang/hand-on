package android.app;

import android.view.TextView;

public class Activity {

    public void onCreate() {}

    public void onStart() {}

    public void onResume() {}

    public TextView findViewById(int id) {
        return new TextView();
    }

}
