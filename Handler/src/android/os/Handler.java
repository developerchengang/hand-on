package android.os;

public class Handler {

    final Looper mLooper;
    final MessageQueue mQueue;

    public Handler() {
        mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    public Handler(Looper looper) {
        mLooper = looper;
        mQueue = looper.mQueue;
    }

    public final void sendMessage(Message msg) {
        enqueueMessage(mQueue, msg);
    }

    private void enqueueMessage(MessageQueue queue, Message msg) {
        msg.target = this;

        queue.enqueueMessage(msg);
    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }

    public void handleMessage(Message msg) {
    }

}
