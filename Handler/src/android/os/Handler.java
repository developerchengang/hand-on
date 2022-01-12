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
        sendMessageDelayed(msg, 0);
    }

    public final boolean sendMessageDelayed(Message msg, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return sendMessageAtTime(msg, System.currentTimeMillis() + delayMillis);
    }

    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        MessageQueue queue = mQueue;
        if (queue == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            System.out.println("Looper---->" + e.getMessage());
            return false;
        }
        return enqueueMessage(queue, msg, uptimeMillis);
    }

    private boolean enqueueMessage(MessageQueue queue, Message msg, long uptimeMillis) {
        msg.target = this;

        return queue.enqueueMessage(msg, uptimeMillis);
    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }

    public void handleMessage(Message msg) {
    }

}
