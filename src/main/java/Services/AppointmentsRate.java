package Services;

public class AppointmentsRate {
    private int Pending ;
    private int Fail ;
    private int Success ;

    public int getPending() {
        return Pending;
    }

    public void setPending(int pending) {
        Pending = pending;
    }

    public int getFail() {
        return Fail;
    }

    public void setFail(int fail) {
        Fail = fail;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public AppointmentsRate() {

    }

    public AppointmentsRate(int pending, int fail, int success) {

        Pending = pending;
        Fail = fail;
        Success = success;
    }
}
