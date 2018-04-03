package com.workspace.shop.dao;

import com.workspace.shop.entities.ShopEntity;
import com.workspace.shop.utils.Utils;
import com.workspace.shop.utils.Utils.ShopTable;
import java.text.MessageFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;


@Repository
//@ComponentScan(value = {"com.workspace.shop.dao", "com.workspace.shop.service"})
public class ShopDao implements IShopDao {

    private Logger logger = LoggerFactory.getLogger(ShopDao.class);
    @PersistenceContext(name = "persistance-unit")
    private EntityManager manager;

    @Override
    public List<ShopEntity> getAllShops() {
        CriteriaQuery<ShopEntity> query = manager.getCriteriaBuilder()
            .createQuery(ShopEntity.class);
        Root<ShopEntity> entityRoot = query.from(ShopEntity.class);
        List<ShopEntity> entities = manager.createQuery(query).getResultList();
        if (entities != null && !entities.isEmpty()) {
            logger.info("Display all shops");
            return entities;
        } else {
            return null;
        }
    }

    @Override
    public ShopEntity getShopById(int id) {
        Session session;
        Criteria criteria;
        session = manager.unwrap(Session.class);
        criteria = session.createCriteria(ShopEntity.class);
        Projection projections = Projections.projectionList()
            .add(Projections.property("id"))
            .add(Projections.property("address"))
            .add(Projections.property("shopName"))
            .add(Projections.property("owner"))
            .add(Projections.property("customersDensity"));

        criteria.add(Restrictions.eq("id", id));
        criteria.setProjection(projections);
        @SuppressWarnings("unchecked")
        List<Object[]> objects = (List<Object[]>) criteria.list();
        if (objects != null && !objects.isEmpty()) {
            logger.info("Shop with id " + id + " was founded..");
            Object[] temp = objects.get(0);
            int _id = (int) temp[0];
            String address = (String) temp[1];
            String shopName = (String) temp[2];
            String owner = (String) temp[3];
            Double density = (double) temp[4];
            return new ShopEntity(_id, address, density, shopName, owner);
        } else {
            return null;
        }
    }

    @Override
    public int save(ShopEntity shopEntity) {
        ShopEntity flagEntity = manager.merge(shopEntity);
        if (flagEntity !=null){
            logger.info("Shop was added");
        }
        return flagEntity == null ? 0 : 1;
    }

    @Override
    public int update(int id, ShopEntity shopEntity) {
        ShopEntity tempEntity = getShopById(id);

        if (tempEntity != null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaUpdate<ShopEntity> update = builder.
                createCriteriaUpdate(ShopEntity.class);
            Root<ShopEntity> root = update.from(ShopEntity.class);
            update.set(root.get(ShopTable.ADDRESS), shopEntity.getAddress());
            update.set(root.get(ShopTable.CUSTOMERS_DENSITY), shopEntity.getCustomersDensity());
            update.set(root.get(ShopTable.NAME), shopEntity.getShopName());
            update.set(root.get(ShopTable.OWNER), shopEntity.getOwner());
            update.where(builder.equal(root.get(ShopTable.ID), shopEntity.getId()));
            int result = manager.createQuery(update).executeUpdate();
            logger.info(MessageFormat
                .format("Instance with id{0} was updated{1}", id, result));
        } else {
            create(shopEntity);
        }
        return 0;
    }

    @Override
    public int create(ShopEntity shopEntity) {
        if (shopEntity != null) {
            manager.merge(shopEntity);
        }
        return 0;
    }

    @Override
    public boolean isExist(int id) {
        ShopEntity shopEntity = getShopById(id);
        if (shopEntity != null) {
            return true;
        }
        return false;
    }
}
