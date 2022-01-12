package android.os;

public class Message {

    public Object obj;
    Handler target;
    Message next;

    public static Message obtain() {
        return new Message();
    }

    public static Message obtain(Message orig) {
        Message m = obtain();
        m.obj = orig.obj;
        m.target = orig.target;
        return m;
    }

    void recycleUnchecked() {
        obj = null;
        target = null;
    }

}
