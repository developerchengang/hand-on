package android.os;

public class MessageQueue {

    Message mMessages;

    boolean enqueueMessage(Message msg, long when) {
        if (msg.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }

        synchronized (this) {
            msg.when = when;
            Message p = mMessages;
            if (p == null || when == 0 || when < p.when) {
                msg.next = p;
                mMessages = msg;
            } else {
                Message prev;
                do {
                    prev = p;
                    p = p.next;
                } while (p != null && when >= p.when);
                msg.next = p; // invariant: p == prev.next
                prev.next = msg;
            }
        }

        return true;
    }

    Message next() {
        for (; ; ) {
            synchronized (this) {
                final long now = System.currentTimeMillis();
                Message msg = mMessages;
                if (msg != null) {
                    if (now >= msg.when) {
                        mMessages = msg.next;
                        msg.next = null;
                        return msg;
                    }
                }
            }
        }
    }

}
