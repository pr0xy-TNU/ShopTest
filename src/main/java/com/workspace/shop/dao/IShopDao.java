package com.workspace.shop.dao;


import com.workspace.shop.entities.ShopEntity;
import java.util.List;

public interface IShopDao {

    List<ShopEntity> getAllShops();

    ShopEntity getShopById(int id);

    int save(ShopEntity shopEntity);

    int update(int id, ShopEntity shopEntity);

    int create(ShopEntity shopEntity);

    boolean isExist(int id);
}
