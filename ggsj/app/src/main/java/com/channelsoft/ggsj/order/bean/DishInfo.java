package com.channelsoft.ggsj.order.bean;

/**
 * Created by chenyg on 2016/4/21.
 */
public class DishInfo
{
    private String dishName;
    private String dishPrice;
    private String dishGroupName;
    private String dishPriceByFen;
    private String num;
    private String orderId;
    private String reduceId;
    private String sort;

    public String getDishName()
    {
        return dishName;
    }

    public void setDishName(String dishName)
    {
        this.dishName = dishName;
    }

    public String getDishPrice()
    {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice)
    {
        this.dishPrice = dishPrice;
    }

    public String getDishGroupName()
    {
        return dishGroupName;
    }

    public void setDishGroupName(String dishGroupName)
    {
        this.dishGroupName = dishGroupName;
    }

    public String getDishPriceByFen()
    {
        return dishPriceByFen;
    }

    public void setDishPriceByFen(String dishPriceByFen)
    {
        this.dishPriceByFen = dishPriceByFen;
    }

    public String getNum()
    {
        return num;
    }

    public void setNum(String num)
    {
        this.num = num;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getReduceId()
    {
        return reduceId;
    }

    public void setReduceId(String reduceId)
    {
        this.reduceId = reduceId;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }
}
