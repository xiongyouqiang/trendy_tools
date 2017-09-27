package com.trendy.fw.tools.order.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class OrderStatusConfig {
	public static final String ORDS_APPROVED = "APPROVED";// 已审核
	public static final String ORDS_PRINTED = "PRINTED";// 已打单
	public static final String ORDS_PICKED = "PICKED";// 已拣货
	public static final String ORDS_PACKAGED = "PACKAGED";// 已打包
	public static final String ORDS_SENT = "SENT";// 已发货
	public static final String ORDS_SENT_PART = "SENT_PART";// 部分发货
	public static final String ORDS_SENT_FAIL = "SENT_FAIL";// 发货失败
	public static final String ORDS_FINISHED = "FINISHED";// 已送达
	public static final String ORDS_APPLY_CANCEL = "APPLY_CANCEL";// 申请取消
	public static final String ORDS_CANCELLED = "CANCELLED";// 已取消
	public static final String ORDS_APPLY_REFUND = "APPLY_REFUND";// 申请退款
	public static final String ORDS_REFUNDING = "REFUNDING";// 退款中
	public static final String ORDS_REFUNDED = "REFUNDED";// 已退款
	public static final String ORDS_LACK = "LACK";// 缺货
	public static final String ORDS_WAIT_PAY = "WAIT_PAY";// 未支付
	public static final String ORDS_PAYED = "PAYED";// 已支付
	public static final String ORDS_APPROVED_SUSPEND = "APPROVED_SUSPEND";// 审核挂起
	public static final String ORDS_EDIT = "EDIT";// 正在处理
	public static final String ORDS_SUSPEND = "SUSPEND";// 挂起
	public static final String ORDS_OVERSOLD = "OVERSOLD";// 超卖
	public static final String ORDS_FRONT_WAIT_PAY = "FRONT_WAIT_PAY";// 未付定金
	public static final String ORDS_FRONT_PAYED = "FRONT_PAYED";// 已付定金
	public static final String ORDS_FINAL_WAIT_PAY = "FINAL_WAIT_PAY";// 未付尾款

	public static List<StatusBean> ORDER_STATUS_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> ORDER_STATUS_MAP = new HashMap<String, String>();

	static {
		new OrderStatusConfig().init();
	}

	private void init() {
		initOrderStatusList();
		initOrderStatusMap();
	}

	private void initOrderStatusList() {
		ORDER_STATUS_LIST = new ArrayList<StatusBean>();
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_APPROVED, "已审核"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_PRINTED, "已打单"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_PICKED, "已拣货"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_PACKAGED, "已打包"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_SENT, "已发货"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_SENT_PART, "部分发货"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_SENT_FAIL, "发货失败"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_FINISHED, "已送达"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_APPLY_CANCEL, "申请取消"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_CANCELLED, "已取消"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_APPLY_REFUND, "申请退款"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_REFUNDING, "退款中"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_REFUNDED, "已退款"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_LACK, "缺货"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_WAIT_PAY, "未支付"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_PAYED, "已支付"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_APPROVED_SUSPEND, "审核挂起"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_EDIT, "正在处理"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_SUSPEND, "挂起"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_OVERSOLD, "超卖"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_FRONT_WAIT_PAY, "未付定金"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_FRONT_PAYED, "已付定金"));
		ORDER_STATUS_LIST.add(new StatusBean(ORDS_FINAL_WAIT_PAY, "未付尾款"));
	}

	private void initOrderStatusMap() {
		ORDER_STATUS_MAP = StatusKit.toMap(ORDER_STATUS_LIST);
	}
}
