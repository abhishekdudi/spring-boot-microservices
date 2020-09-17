package com.abhishekdudi.moviecatalogservice.model;

import java.util.List;

public class CatalogList {

    private List<CatalogItem> catalogItemList;

    public List<CatalogItem> getCatalogItemList() {
        return catalogItemList;
    }

    public void setCatalogItemList(List<CatalogItem> catalogItemList) {
        this.catalogItemList = catalogItemList;
    }
}
