package com.workspace.shop.service;

import com.workspace.shop.entities.ShopEntity;
import java.util.List;

public interface IShopService {

    ShopEntity findById(int id);

    List<ShopEntity> findAll();

    boolean isExist(int id);

    List<ShopEntity> getAllShops();

    ShopEntity getShopById(int id);

    int save(ShopEntity shopEntity);

    void update(int id, ShopEntity shopEntity);

    int create(ShopEntity shopEntity);

}
