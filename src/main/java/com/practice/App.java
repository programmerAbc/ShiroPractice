package com.practice;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Shrio Practice Start!");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:ShiroConf.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("SOME_KEY", "SomeValue");
        if (currentUser.isAuthenticated() == false) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("UnknowAccountException:" + uae.getMessage());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("IncorrectCredentialsException:" + ice.getMessage());
            } catch (LockedAccountException lae) {
                System.out.println("LockedAccountException:" + lae.getMessage());
            } catch (AuthenticationException ae) {
                System.out.println("AuthenticationException:" + ae.getMessage());
            } finally {
                if (currentUser.isAuthenticated()) {
                    System.out.println("User:" + currentUser.getPrincipal() + " Login success!!!");
                } else {
                    System.out.println("Login failed!!!");
                    System.exit(0);
                }
            }
        }

        //Test
        //UsernamePasswordToken token = new UsernamePasswordToken("presidentskroob", "1234");
        //currentUser.login(token);
        //System.out.println("User:" + currentUser.getPrincipal());
        //System.out.println("currentUser.hasRole(\"president\"):" + currentUser.hasRole("president"));
        //System.out.println("currentUser.isPermitted(\"sendcommand:*\"):" + currentUser.isPermitted("sendcommand:*"));
        //

        System.out.println("currentUser.hasRole(\"schwartz\"):" + currentUser.hasRole("schwartz"));
        System.out.println("currentUser.hasRole(\"admin\"):" + currentUser.hasRole("admin"));
        System.out.println("currentUser.hasRole(\"goodguy\"):" + currentUser.hasRole("goodguy"));
        System.out.println("currentUser.isPermitted(\"lightsaber:abc\"):" + currentUser.isPermitted("lightsaber:abc"));
        System.out.println("currentUser.isPermitted(\"lightsaber:abcd\"):" + currentUser.isPermitted("lightsaber:abcd"));
        System.out.println("currentUser.isPermitted(\"lightsaber\"):" + currentUser.isPermitted("lightsaber"));
        System.out.println("currentUser.isPermitted(\"lightsaber1:abc\"):" + currentUser.isPermitted("lightsaber1:abc"));
        System.out.println("currentUser.isPermitted(\"winnebago:drive:eagle5\"):" + currentUser.isPermitted("winnebago:drive:eagle5"));
        System.out.println("currentUser.isPermitted(\"winnebago:drive:eagle6\"):" + currentUser.isPermitted("winnebago:drive:eagle6"));
        System.out.println("currentUser.isPermitted(\"lightsaber:*\"):" + currentUser.isPermitted("lightsaber:*"));
        System.out.println("currentUser.isPermitted(\"*\"):" + currentUser.isPermitted("*"));
        System.out.println("User:" + currentUser.getPrincipal() + " Log out");
        currentUser.logout();
    }
}
