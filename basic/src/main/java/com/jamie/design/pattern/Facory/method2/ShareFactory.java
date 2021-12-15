package com.jamie.design.pattern.Facory.method2;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享工厂
 */
public class ShareFactory {
    /**
     * 分享模版缓存
     */
    private List<Share> shareFunctionList;

    /**
     * 获取分享模版
     */
    public Share getShareFunction(String type) {
        for (Share share : shareFunctionList) {
            if (share.getShareFunctionType().equalsIgnoreCase(type)) {
                return share;
            }
        }
        return null;
    }

    /**
     * 添加分享模版
     */
    public void addShareList(Share share){
        if (shareFunctionList == null) {
            shareFunctionList = new ArrayList<>();
        }
        shareFunctionList.add(share);
    }
}
