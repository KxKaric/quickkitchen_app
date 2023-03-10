package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;

import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.application.Services;

public class SearchCal {
/*
* Do the search by cal*/
    private RecipePersistence recipePersistence;
    public SearchCal(){
        /*
        * the constructor of search cal */
        recipePersistence = Services.getRecipePersistence();
    }
}
