package com.tata.android.adapter.base.inject;

/**
 * empty interface for not to do code mix .
 * 
 * <p>
 * 空接口 仅仅表明 任何实现该接口的类 其成员变量不参与 代码混肴
 * </p>
 * 
 * 
 * 实现该接口的任何类， 其成员名称（变量名称 TextView tv_name） 必须和android 界面元素标识(R.id.tv_name)一致
 * 方可使用
 * 
 * @see ViewInjectorByReflect 配合使用
 * 
 * 
 */
public interface UnMixable {

}
