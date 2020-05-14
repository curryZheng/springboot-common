package com.ygxc.aqjy.shiro.realm;

import java.util.Set;

import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomizedModularRealmAuthorizer extends ModularRealmAuthorizer {
	 
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        assertRealmsConfigured();
        Set<String> realmNames = principals.getRealmNames();
        //获取realm的名字
        String realmName = realmNames.iterator().next();
        for (Realm realm : getRealms()) {
            if (!(realm instanceof Authorizer)) continue;
            //匹配名字
            if(realmName.equals("admin")) {
                if (realm instanceof AdminRealm) {
                    return ((AdminRealm) realm).isPermitted(principals, permission);
                }
            }
            if(realmName.equals("user")) {
                if (realm instanceof UserRealm) {
                    return ((UserRealm) realm).isPermitted(principals, permission);
                }
            }
        }
        return false;
    }
 
    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        assertRealmsConfigured();
        Set<String> realmNames = principals.getRealmNames();
        //获取realm的名字
        String realmName = realmNames.iterator().next();
        for (Realm realm : getRealms()) {
            if (!(realm instanceof Authorizer)) continue;
            //匹配名字
            if(realmName.equals("admin")) {
                if (realm instanceof AdminRealm) {
                    return ((AdminRealm) realm).isPermitted(principals, permission);
                }
            }
            //匹配名字
            if(realmName.equals("user")) {
                if (realm instanceof UserRealm) {
                    return ((UserRealm) realm).isPermitted(principals, permission);
                }
            }
        }
        return false;    }
 
    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        assertRealmsConfigured();
        Set<String> realmNames = principals.getRealmNames();
        //获取realm的名字
        String realmName = realmNames.iterator().next();
        for (Realm realm : getRealms()) {
            if (!(realm instanceof Authorizer)) continue;
            //匹配名字
            if(realmName.equals("admin")) {
                if (realm instanceof AdminRealm) {
                    return ((AdminRealm) realm).hasRole(principals, roleIdentifier);
                }
            }
            //匹配名字
            if(realmName.equals("admin")) {
                if (realm instanceof UserRealm) {
                    return ((UserRealm) realm).hasRole(principals, roleIdentifier);
                }
            }
        }
        return false;
    }
}
