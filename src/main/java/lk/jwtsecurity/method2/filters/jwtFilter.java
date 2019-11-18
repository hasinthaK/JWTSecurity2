package lk.jwtsecurity.method2.filters;

import lk.jwtsecurity.method2.jwtConfig.jwtProperties;
import lk.jwtsecurity.method2.models.user;
import lk.jwtsecurity.method2.models.userDetailsImpl;
import lk.jwtsecurity.method2.repositories.userRepository;
import lk.jwtsecurity.method2.jwtConfig.jwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    private userRepository userRepo;

    @Autowired
    private jwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = httpServletRequest.getHeader(jwtProperties.HEADER_STRING);

        String username = null;
        String jwt = null;

        if(authHeader!=null && authHeader.startsWith(jwtProperties.TOKEN_PREFIX)){
            jwt = authHeader.replace(jwtProperties.TOKEN_PREFIX, "");
          //  jwt = authHeader.subString(7);
            username = jwtUtility.extractUsername(jwt);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
          //  user user = this.userRepo.finduserByUsername(username);
            userDetailsImpl userDetails = new userDetailsImpl(this.userRepo.finduserByUsername(username));

            if(jwtUtility.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
