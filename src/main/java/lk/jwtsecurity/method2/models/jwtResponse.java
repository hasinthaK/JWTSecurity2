package lk.jwtsecurity.method2.models;

public class jwtResponse {
    private final String jwt;
    private final String username;

    public jwtResponse(String jwt, String username) {
        this.jwt = jwt;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }
}
