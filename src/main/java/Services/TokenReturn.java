package Services;

public class TokenReturn {
    private String token ;
    private int Stutus ;

    public TokenReturn(String token, int stutus) {
        this.token = token;
        Stutus = stutus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStutus() {
        return Stutus;
    }

    public void setStutus(int stutus) {
        Stutus = stutus;
    }
}
