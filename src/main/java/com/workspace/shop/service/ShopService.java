package com.workspace.shop.service;

import com.workspace.shop.dao.IShopDao;
import com.workspace.shop.entities.ShopEntity;
import com.workspace.shop.repository.IShopRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@ComponentScan(value = {"com.workspace.shop.dao", "com.workspace.shop.service"})
public class ShopService implements IShopService {

    @Autowired
    private IShopRepository shopRepository;

    @Autowired
    private IShopDao shopDao;

    @Transactional(readOnly = true)
    @Override
    public ShopEntity findById(int id) {
        return shopDao.getShopById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopEntity> findAll() {
        return shopDao.getAllShops();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isExist(int id) {
        return shopDao.isExist(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShopEntity> getAllShops() {
        return shopDao.getAllShops();
    }

    @Transactional(readOnly = true)
    @Override
    public ShopEntity getShopById(int id) {
        return shopDao.getShopById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public int save(ShopEntity shopEntity) {
        return shopDao.save(shopEntity);
    }

    @Transactional
    @Override
    public void update(int id, ShopEntity shopEntity) {
        shopDao.update(id, shopEntity);
    }

    @Transactional
    @Override
    public int create(ShopEntity shopEntity) {
        return shopDao.create(shopEntity);
    }
}
