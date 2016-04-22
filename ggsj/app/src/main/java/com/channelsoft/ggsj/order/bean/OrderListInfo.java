package com.channelsoft.ggsj.order.bean;

import java.util.List;

/**
 * Created by chenyg on 2016/4/21.
 */
public class OrderListInfo {
    private String arrivedTime;
    private String cancelReason;
    private String note;
    private String servingInfo;
    private String deskId;
    private String deskNo;
    private String deskType;
    private String discount;
    private String isArrived;
    private String leaveShopTime;
    private String mealNumber;
    private String mealTime;
    private String memoryStatus;
    private String orderId;
    private String orderType;
    private String parentOrderId;
    private String payPrice;
    private String print;
    private String receiveOrderTime;
    private String remark;
    private String returnPrice;
    private String status;
    private String submitWay;
    private String totalPrice;
    private String updateTime;
    private String userName;
    private String userPhone;
    private List<DishInfo> dishList;
    private List<DishInfo> subOrderList;
    private List<DishInfo> reduceOrderList;
    private String reduceDishTotalPrice;
    private String reduceDishTotalPriceByFen;
    private String summaryPrice;
    private String summaryPriceByFen;

    public List<DishInfo> getDishList()
    {
        return dishList;
    }

    public void setDishList(List<DishInfo> dishList)
    {
        this.dishList = dishList;
    }

    public List<DishInfo> getSubOrderList()
    {
        return subOrderList;
    }

    public void setSubOrderList(List<DishInfo> subOrderList)
    {
        this.subOrderList = subOrderList;
    }

    public List<DishInfo> getReduceOrderList()
    {
        return reduceOrderList;
    }

    public void setReduceOrderList(List<DishInfo> reduceOrderList)
    {
        this.reduceOrderList = reduceOrderList;
    }

    public String getReduceDishTotalPrice()
    {
        return reduceDishTotalPrice;
    }

    public void setReduceDishTotalPrice(String reduceDishTotalPrice)
    {
        this.reduceDishTotalPrice = reduceDishTotalPrice;
    }

    public String getReduceDishTotalPriceByFen()
    {
        return reduceDishTotalPriceByFen;
    }

    public void setReduceDishTotalPriceByFen(String reduceDishTotalPriceByFen)
    {
        this.reduceDishTotalPriceByFen = reduceDishTotalPriceByFen;
    }

    public String getSummaryPrice()
    {
        return summaryPrice;
    }

    public void setSummaryPrice(String summaryPrice)
    {
        this.summaryPrice = summaryPrice;
    }

    public String getSummaryPriceByFen()
    {
        return summaryPriceByFen;
    }

    public void setSummaryPriceByFen(String summaryPriceByFen)
    {
        this.summaryPriceByFen = summaryPriceByFen;
    }

    public String getArrivedTime()
    {
        return arrivedTime;
    }

    public void setArrivedTime(String arrivedTime)
    {
        this.arrivedTime = arrivedTime;
    }

    public String getCancelReason()
    {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason)
    {
        this.cancelReason = cancelReason;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getServingInfo()
    {
        return servingInfo;
    }

    public void setServingInfo(String servingInfo)
    {
        this.servingInfo = servingInfo;
    }

    public String getDeskId()
    {
        return deskId;
    }

    public void setDeskId(String deskId)
    {
        this.deskId = deskId;
    }

    public String getDeskNo()
    {
        return deskNo;
    }

    public void setDeskNo(String deskNo)
    {
        this.deskNo = deskNo;
    }

    public String getDeskType()
    {
        return deskType;
    }

    public void setDeskType(String deskType)
    {
        this.deskType = deskType;
    }

    public String getDiscount()
    {
        return discount;
    }

    public void setDiscount(String discount)
    {
        this.discount = discount;
    }

    public String getIsArrived()
    {
        return isArrived;
    }

    public void setIsArrived(String isArrived)
    {
        this.isArrived = isArrived;
    }

    public String getLeaveShopTime()
    {
        return leaveShopTime;
    }

    public void setLeaveShopTime(String leaveShopTime)
    {
        this.leaveShopTime = leaveShopTime;
    }

    public String getMealNumber()
    {
        return mealNumber;
    }

    public void setMealNumber(String mealNumber)
    {
        this.mealNumber = mealNumber;
    }

    public String getMealTime()
    {
        return mealTime;
    }

    public void setMealTime(String mealTime)
    {
        this.mealTime = mealTime;
    }

    public String getMemoryStatus()
    {
        return memoryStatus;
    }

    public void setMemoryStatus(String memoryStatus)
    {
        this.memoryStatus = memoryStatus;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getParentOrderId()
    {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId)
    {
        this.parentOrderId = parentOrderId;
    }

    public String getPayPrice()
    {
        return payPrice;
    }

    public void setPayPrice(String payPrice)
    {
        this.payPrice = payPrice;
    }

    public String getPrint()
    {
        return print;
    }

    public void setPrint(String print)
    {
        this.print = print;
    }

    public String getReceiveOrderTime()
    {
        return receiveOrderTime;
    }

    public void setReceiveOrderTime(String receiveOrderTime)
    {
        this.receiveOrderTime = receiveOrderTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getReturnPrice()
    {
        return returnPrice;
    }

    public void setReturnPrice(String returnPrice)
    {
        this.returnPrice = returnPrice;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSubmitWay()
    {
        return submitWay;
    }

    public void setSubmitWay(String submitWay)
    {
        this.submitWay = submitWay;
    }

    public String getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }
}
