package com.workspace.shop.controlles;

import com.workspace.shop.entities.ShopEntity;
import com.workspace.shop.service.IShopService;
import com.workspace.shop.service.ShopService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

    @Autowired
    private IShopService shopService;
    public final Logger logger = LoggerFactory.getLogger(ShopController.class);

    @RequestMapping(value = "/shops", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ShopEntity>> findAll() {
        List<ShopEntity> list = shopService.getAllShops();
        if (list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/shops/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ShopEntity> findOne(@PathVariable("id") int id) {
        ShopEntity queryResult = shopService.getShopById(id);
        if (queryResult != null) {
            return new ResponseEntity<>(queryResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/shops/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ShopEntity> update(@PathVariable("id") int id,
        @RequestParam ShopEntity shopEntity) {
        ShopEntity entity = shopService.findById(id);
        if (entity != null) {
            shopService.update(id, shopEntity);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
