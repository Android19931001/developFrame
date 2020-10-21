package com.develop.frame.bridge;

/**
 * Created by wangning on 2020/5/15.
 */

public interface OnWindowItemClickListener {
    //资产清查
    void assetsClearCheck();

    //库存管理
    void inventoryManagement();

    //报废申请
    void scrapApply();

    //流程中心
    void flowApprovalCenter();

    //退出登录账号
    void exitApp();

    //设备维修
    void deviceMaintenance();

    //网点发起维修申请
    void deviceRepairApply();

    //网点发起设备申请
    void deviceApply();

    //网点发起设备申请部门
    void deviceApplyDepartment();

    //耗材预约
    void consumablesAppointment();

    //资产实物管理
    void assetsManager();
}
