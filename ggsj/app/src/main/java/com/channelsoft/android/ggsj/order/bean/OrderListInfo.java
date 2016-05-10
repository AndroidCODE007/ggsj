package com.channelsoft.android.ggsj.order.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.BinderThread;

import com.channelsoft.android.ggsj.BR;
import java.util.List;

/**
 * Created by chenyg on 2016/4/21.
 */
public class OrderListInfo extends BaseObservable
{
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

    @Bindable
    public List<DishInfo> getDishList()
    {
        return dishList;
    }

    public void setDishList(List<DishInfo> dishList)
    {
        this.dishList = dishList;
        notifyPropertyChanged(BR.dishList);
    }

    @Bindable
    public List<DishInfo> getSubOrderList()
    {
        return subOrderList;
    }

    public void setSubOrderList(List<DishInfo> subOrderList)
    {
        this.subOrderList = subOrderList;
        notifyPropertyChanged(BR.subOrderList);
    }

    @Bindable
    public List<DishInfo> getReduceOrderList()
    {
        return reduceOrderList;
    }


    public void setReduceOrderList(List<DishInfo> reduceOrderList)
    {
        this.reduceOrderList = reduceOrderList;
        notifyPropertyChanged(BR.reduceOrderList);
    }

    @Bindable
    public String getReduceDishTotalPrice()
    {
        return reduceDishTotalPrice;
    }

    public void setReduceDishTotalPrice(String reduceDishTotalPrice)
    {
        this.reduceDishTotalPrice = reduceDishTotalPrice;
        notifyPropertyChanged(BR.reduceDishTotalPrice);
    }

    @Bindable
    public String getReduceDishTotalPriceByFen()
    {
        return reduceDishTotalPriceByFen;
    }

    public void setReduceDishTotalPriceByFen(String reduceDishTotalPriceByFen)
    {
        this.reduceDishTotalPriceByFen = reduceDishTotalPriceByFen;
        notifyPropertyChanged(BR.reduceDishTotalPriceByFen);
    }

    @Bindable
    public String getSummaryPrice()
    {
        return summaryPrice;
    }

    public void setSummaryPrice(String summaryPrice)
    {
        this.summaryPrice = summaryPrice;
        notifyPropertyChanged(BR.summaryPrice);
    }

    @Bindable
    public String getSummaryPriceByFen()
    {
        return summaryPriceByFen;
    }

    public void setSummaryPriceByFen(String summaryPriceByFen)
    {
        this.summaryPriceByFen = summaryPriceByFen;
        notifyPropertyChanged(BR.summaryPriceByFen);
    }

    @Bindable
    public String getArrivedTime()
    {
        return arrivedTime;
    }

    public void setArrivedTime(String arrivedTime)
    {
        this.arrivedTime = arrivedTime;
        notifyPropertyChanged(BR.arrivedTime);
    }

    @Bindable
    public String getCancelReason()
    {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason)
    {
        this.cancelReason = cancelReason;
        notifyPropertyChanged(BR.cancelReason);
    }

    @Bindable
    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
        notifyPropertyChanged(BR.note);
    }

    @Bindable
    public String getServingInfo()
    {
        return servingInfo;
    }

    public void setServingInfo(String servingInfo)
    {
        this.servingInfo = servingInfo;
        notifyPropertyChanged(BR.servingInfo);
    }

    @Bindable
    public String getDeskId()
    {
        return deskId;
    }


    public void setDeskId(String deskId)
    {
        this.deskId = deskId;
        notifyPropertyChanged(BR.deskId);
    }

    @Bindable
    public String getDeskNo()
    {
        return deskNo;
    }

    public void setDeskNo(String deskNo)
    {
        this.deskNo = deskNo;
        notifyPropertyChanged(BR.deskNo);
    }

    @Bindable
    public String getDeskType()
    {
        return deskType;
    }

    public void setDeskType(String deskType)
    {
        this.deskType = deskType;
        notifyPropertyChanged(BR.deskType);
    }

    @Bindable
    public String getDiscount()
    {
        return discount;
    }

    public void setDiscount(String discount)
    {
        this.discount = discount;
        notifyPropertyChanged(BR.discount);
    }

    @Bindable
    public String getIsArrived()
    {
        return isArrived;
    }

    public void setIsArrived(String isArrived)
    {
        this.isArrived = isArrived;
        notifyPropertyChanged(BR.isArrived);
    }

    @Bindable
    public String getLeaveShopTime()
    {
        return leaveShopTime;
    }

    public void setLeaveShopTime(String leaveShopTime)
    {
        this.leaveShopTime = leaveShopTime;
        notifyPropertyChanged(BR.leaveShopTime);
    }

    @Bindable
    public String getMealNumber()
    {
        return mealNumber;
    }

    public void setMealNumber(String mealNumber)
    {
        this.mealNumber = mealNumber;
        notifyPropertyChanged(BR.mealNumber);
    }

    @Bindable
    public String getMealTime()
    {
        return mealTime;
    }

    public void setMealTime(String mealTime)
    {
        this.mealTime = mealTime;
        notifyPropertyChanged(BR.mealTime);
    }

    @Bindable
    public String getMemoryStatus()
    {
        return memoryStatus;
    }

    public void setMemoryStatus(String memoryStatus)
    {
        this.memoryStatus = memoryStatus;
        notifyPropertyChanged(BR.memoryStatus);
    }

    @Bindable
    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
        notifyPropertyChanged(BR.orderId);
    }

    @Bindable
    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
        notifyPropertyChanged(BR.orderType);
    }

    @Bindable
    public String getParentOrderId()
    {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId)
    {
        this.parentOrderId = parentOrderId;
        notifyPropertyChanged(BR.parentOrderId);
    }

    @Bindable
    public String getPayPrice()
    {
        return payPrice;
    }

    public void setPayPrice(String payPrice)
    {
        this.payPrice = payPrice;
        notifyPropertyChanged(BR.payPrice);
    }

    @Bindable
    public String getPrint()
    {
        return print;
    }

    public void setPrint(String print)
    {
        this.print = print;
        notifyPropertyChanged(BR.print);
    }

    @Bindable
    public String getReceiveOrderTime()
    {
        return receiveOrderTime;
    }

    public void setReceiveOrderTime(String receiveOrderTime)
    {
        this.receiveOrderTime = receiveOrderTime;
        notifyPropertyChanged(BR.receiveOrderTime);
    }

    @Bindable
    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
        notifyPropertyChanged(BR.remark);
    }

    @Bindable
    public String getReturnPrice()
    {
        return returnPrice;
    }

    public void setReturnPrice(String returnPrice)
    {
        this.returnPrice = returnPrice;
        notifyPropertyChanged(BR.returnPrice);
    }

    @Bindable
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getSubmitWay()
    {
        return submitWay;
    }

    public void setSubmitWay(String submitWay)
    {
        this.submitWay = submitWay;
        notifyPropertyChanged(BR.submitWay);
    }

    @Bindable
    public String getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice)
    {
        this.totalPrice = totalPrice;
        notifyPropertyChanged(BR.totalPrice);
    }

    @Bindable
    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
        notifyPropertyChanged(BR.updateTime);
    }

    @Bindable
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
        notifyPropertyChanged(BR.userPhone);
    }

    public String getDishCountAndPrice(List<DishInfo> list,String price)
    {
        return list.size()+"道菜"+"  ￥"+price;
    }

    public String getDishName(List<DishInfo> list)
    {
        String dishName = "";
        if(list != null && list.size() > 0)
        {
            for(int i = 0;i<list.size();i++)
            {
                dishName += list.get(i).getDishName()+ " ";
            }
        }
        return dishName;
    }

    public String getPayMessage(String pay,String returnPay)
    {
        return "顾客实付"+pay +"  退款"+returnPay;
    }
}
