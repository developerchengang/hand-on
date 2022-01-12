package android.os;

public class Message {

    public int what;
    public int arg1;
    public int arg2;
    public Object obj;
    public long when;
    Handler target;
    Message next;

    public static final Object sPoolSync = new Object();
    private static Message sPool;
    private static int sPoolSize = 0;

    private static final int MAX_POOL_SIZE = 50;

    public static Message obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                Message m = sPool;
                sPool = m.next;
                m.next = null;
                sPoolSize--;
                return m;
            }
        }
        return new Message();
    }

    public static Message obtain(Message orig) {
        Message m = obtain();
        m.what = orig.what;
        m.arg1 = orig.arg1;
        m.arg2 = orig.arg2;
        m.obj = orig.obj;
        m.target = orig.target;
        return m;
    }

    void recycleUnchecked() {
        what = 0;
        arg1 = 0;
        arg2 = 0;
        obj = null;
        target = null;
        when = 0;

        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "what=" + what +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", obj=" + obj +
                ", when=" + when +
                ", target=" + target +
                ", next=" + next +
                '}';
    }
}
