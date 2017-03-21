package cn.ucai.fulicenter.model.dao;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefrenceUtils {
    private static final String SHARE_NAME = "saveUserInfo";
    private static SharePrefrenceUtils instance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public static final String SHARE_KEY_USER_NAME = "share_key_user_name";

    public SharePrefrenceUtils(Context context) { // 狗造
        mSharedPreferences = context.getSharedPreferences(SHARE_NAME,Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static SharePrefrenceUtils getInstence(Context context){ // 懒汉式
        if(instance==null){
            instance = new SharePrefrenceUtils(context);
        }
        return instance;
    }

    public void saveUser(String username){
        mEditor.putString(SHARE_KEY_USER_NAME,username);
        mEditor.commit(); // 提交
    }

    public String getUser(){
        return mSharedPreferences.getString(SHARE_KEY_USER_NAME,null);
    }

    public void removeUser(){
        mEditor.remove(SHARE_KEY_USER_NAME);
        mEditor.commit();
    }
}