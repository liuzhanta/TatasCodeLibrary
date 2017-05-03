package com.tata.android.adapter.base.inject;

public class HolderInjectorByReflect {
    
    private static final String TAG = "ViewInjectorByReflect";
    
    /**
     * 执行View 与 控件索引对象 关联
     * 
     * 执行注入的对象 成员名称 必须与 View 的子控件的 id 一致
     * 
     * @param UnMixable 所传递的对象 必须实现UnMixable接口 以避免代码混搅过程中 影响java 反射功能使用。
     * @param v
     */
    public static final void injectView(UnMixable obj) {
        Class<?> clz = obj.getClass();
        clz.getClasses();
    }
    
}