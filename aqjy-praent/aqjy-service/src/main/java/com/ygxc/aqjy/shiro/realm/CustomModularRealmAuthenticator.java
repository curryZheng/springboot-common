package com.ygxc.aqjy.shiro.realm;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import com.ygxc.aqjy.shiro.CustomToken;

public class CustomModularRealmAuthenticator extends ModularRealmAuthenticator {

    /**
     * 通过传入数据类型来选择使用哪个realm
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 做realm的校验
        assertRealmsConfigured();
        // 获取从前端传过来的token
        CustomToken customToken = (CustomToken) authenticationToken;
        // 获取登录类型
        String loginType = customToken.getLoginType();
        // 获取所有token
        Collection<Realm> realms = getRealms();
        // 登录类型对应多有realm全部获取到
        Collection<Realm> typeRealms = new ArrayList<Realm>();
        for (Realm realm: realms) {
            // 类型对比
            if (realm.getName().contains(loginType)) {
                typeRealms.add(realm);
            }
        }

        if(typeRealms.size() == 1) {
            return doSingleRealmAuthentication(typeRealms.iterator().next(), authenticationToken);
        } else {
            return doMultiRealmAuthentication(typeRealms,authenticationToken);
        }

    }

}
