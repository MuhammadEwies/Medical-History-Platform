package com.basic.demo;

import HibernateSecurity.HibernateCRUDPatient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Order(1)
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("filter jwt");
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        ////////////////////////////////////////////////

        String token = ((HttpServletRequest) request).getParameter("token");
        request.setAttribute("token",token);
        ((HttpServletResponse) response).setHeader("token",token);
        String urlPath = req.getRequestURI();
        System.out.println("token" + token + "***" + "***" + "urlpath" + urlPath + "***");
        ///////////////////////////
        HttpServletResponse response_ = (HttpServletResponse) response;
        boolean authenticated = false;
        // perform authentication

            if ("/login".equals(urlPath)||"/StartApplication".equals(urlPath)||"/IsAuthenticated".equals(urlPath)||"/ForgetPassword".equals(urlPath))
        {
            System.out.println("can pass filter");
            chain.doFilter(request, response);
        }
        else {
           HibernateCRUDPatient hibra = new HibernateCRUDPatient();
            authenticated =hibra.checktoken(token);
            System.out.println("authenticated"+authenticated);
            if (authenticated==true) {
                chain.doFilter(request, response);
            }
            else {
                // don't continue the chain
                System.out.println("معلش بقا مش هتعرف تعدي عشان مفيش توكين");
                response_.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response_.setContentType("what you need to authorize");
                PrintWriter writer = response.getWriter();
            }
        }
    }

    @Override
    public void destroy() {
    }
}