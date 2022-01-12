package android.view;

public class TextView extends View {

    private final Thread mThread;

    public TextView() {
        mThread = Thread.currentThread();
    }

    public void setText(CharSequence text) {
        checkThread();
        System.out.println("updated ui :" + text);
    }

    void checkThread() {
        if (mThread != Thread.currentThread()) {
            throw new RuntimeException(
                    "Only the original thread that created a view hierarchy can touch its views.");
        }
    }

}
