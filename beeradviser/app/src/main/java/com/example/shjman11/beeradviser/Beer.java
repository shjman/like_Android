package com.example.shjman11.beeradviser;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class Beer {
    List<String> getBrands(String color){
        List<String> brands=new ArrayList<String>();
        if(color.equals("value1")){
            brands.add("value111111");
            brands.add("value11111111111111");
        }
        if(color.equals("value2")){
            brands.add("value2222");
            brands.add("value222222222222");
        }
        else{
            brands.add("вы выбрали валуе3");
        }
        return brands;
    }
}
