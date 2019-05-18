package com.sdau.housesManage.shiro;

import com.alibaba.druid.util.StringUtils;
import com.sdau.housesManage.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component("passwordHelper")
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 1;

    //对新用户密码加密
    public void encryptPassword(User user){
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        //加密
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()==null?"":user.getSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }

    public boolean testingPassword(String password, User user) {

        //加密
        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();

        return StringUtils.equals(user.getPassword(), newPassword);
    }

}
