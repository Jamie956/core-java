package com.jamie.design.pattern.Facory.method2;

/**
 * 当对象的创建逻辑比较复杂，不只是简单的 new 一下就可以，而是要组合其他类对象，
 * 做各种初始化操作的时候，我们推荐使用工厂方法模式，将复杂的创建逻辑拆分到多个工厂类中，
 * 让每个工厂类都不至于过于复杂。
 *
 * 而使用简单工厂模式，将所有的创建逻辑都放到一个工厂类中，会导致这个工厂类变得很复杂
 */
public class Client {
    public static void main(String[] args) {
        //创建分享模版工厂
        ShareFactory shareFactory = new ShareFactory();
        //添加分享模版
        shareFactory.addShareList(new SuccessOrderShare());
        //获取模版
        Share shareFunction = shareFactory.getShareFunction(EnumShareType.SUCCESS_ORDER.getName());
        String successOrder = shareFunction.mainProcess("Success Order");
        System.out.println(successOrder);
    }
}
