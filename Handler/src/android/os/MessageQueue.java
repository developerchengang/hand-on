package android.os;

public class MessageQueue {

    Message mMessages;

    void enqueueMessage(Message msg) {
        if (msg.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }

        synchronized (this) {
            Message p = mMessages;
            if (p == null) {
                mMessages = msg;
            } else {
                Message prev;
                do {
                    prev = p;
                    p = p.next;
                } while (p != null);
                msg.next = null;
                prev.next = msg;
            }
        }
    }

    Message next() {
        for (;;) {
            synchronized (this) {
                Message msg = mMessages;
                if (msg != null) {
                    mMessages = msg.next;
                    return msg;
                }
            }
        }
    }

}
