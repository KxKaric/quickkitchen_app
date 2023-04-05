package comp3350.quickkitchen;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;

import static androidx.test.espresso.matcher.ViewMatchers.withId;


import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import comp3350.quickkitchen.presentation.HomeActivity;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class BookMarkSystemTest {
    /**
     * BookMarkSystemTest
     */
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void addToBookMarkSystemTest() {
        // -- Home page --
        onView(withId(R.id.oilBtn)).perform(click());
        onView(withId(R.id.searchBtn)).perform(click());

        // -- Recipe page --
        onData(anything()).inAdapterView(withId(R.id.recipeList)).atPosition(0).perform(longClick());
//        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
//        onView(withId(R.id.bookMarkBtn)).check(matches(isDisplayed()));
//        onView(withId(R.id.add_to_bookmark)).perform(click());
//        pressBack(); //closing the popup

        // -- Home page --
//        onView(withId(R.id.bookMarkBtn)).perform(click());
    }

    @Test
    public void deleteFromBookMarkSystemTest() {
        // -- Home page --
        onView(withId(R.id.oilBtn)).perform(click());
        onView(withId(R.id.searchBtn)).perform(click());

        // -- Recipe page --
        onData(anything()).inAdapterView(withId(R.id.recipeList)).atPosition(0).perform(longClick());
//        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
//        onView(withId(R.id.bookMarkBtn)).check(matches(isDisplayed()));
//        onView(withId(R.id.add_to_bookmark)).perform(click());
//        pressBack(); //closing the popup

        // -- Home page --
//        onView(withId(R.id.bookMarkBtn)).perform(click());
    }


}
