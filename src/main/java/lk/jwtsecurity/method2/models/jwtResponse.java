package lk.jwtsecurity.method2.models;

public class jwtResponse {
    private final String jwt;

    public jwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
