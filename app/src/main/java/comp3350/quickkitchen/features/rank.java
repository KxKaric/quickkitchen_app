package comp3350.quickkitchen.features;

import java.util.ArrayList;
import java.util.List;
import comp3350.quickkitchen.objects.Recipe;

public class rank {

    public rank(){};//constructor


    //sorts recipe based on ranking in ascending order
    public List<Recipe> sortByRank(List<Recipe>data){
        if(data == null){
            System.out.println("data is NUL !!!!!!!!!!!!!!");
        }
        Recipe temp = data.get(0);
        for(int i=0; i<data.size(); i++){
            for(int j=i+1; j<data.size();j++){
                if(data.get(i).getRanking()>data.get(j).getRanking()){
                    temp=data.get(i);
                    data.set(i,data.get(j));
                    data.set(j,temp);
                }
            }
        }
        return data;
    }
}
