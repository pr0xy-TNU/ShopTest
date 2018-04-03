package com.workspace.shop.repository;

import com.workspace.shop.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.workspace.shop.repository")
public interface IShopRepository extends JpaRepository<ShopEntity, Integer> {


}
