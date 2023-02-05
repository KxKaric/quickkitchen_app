public class Main {

    public static void main(String[] args) {

        System.out.println("List of recipes that has preparation time in range [0.3, 1.5]");
        DurationBasedRecipeSearch x = new DurationBasedRecipeSearch(0.3,1.5);
        x.showList();

        System.out.println();

        System.out.println("List of recipes that has preparation time less than 0.6");
        DurationBasedRecipeSearch y = new DurationBasedRecipeSearch(0.6);
        y.showList();

        System.out.println();

        System.out.println("List of recipes that has preparation time less than 0.4");
        y = new DurationBasedRecipeSearch(0.4);
        y.showList();

        System.out.println();

        System.out.println("List of recipes that has preparation time less than 0.6");
        y = new DurationBasedRecipeSearch(1.1, 1.3);
        y.showList();
    }
}

