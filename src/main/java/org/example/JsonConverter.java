package org.example;

import com.google.gson.Gson;

import java.util.List;

public class JsonConverter
{
    private Gson gson;

    public JsonConverter()
    {
        this.gson = new Gson();
    }

    /**
     * Main author: Aleksandra Kail
     *
     */
    public <Product> String convertProductListToJsonString(List<Product> productList)
    {
        return gson.toJson(productList);
    }

    public <Vendor> String convertVendorListToJsonString(List<Vendor> vendorList)
    {
        return gson.toJson(vendorList);
    }

    /**
     * Main author: Samuel Sukovský
     *
     */
    public <Product> String ConvertProductToJsonString(Product product)
    {
        return gson.toJson(product);
    }

    public <Vendor> String ConvertVendorToJsonString(Vendor vendor)
    {
        return gson.toJson(vendor);
    }

}
