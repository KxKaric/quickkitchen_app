package comp3350.quickkitchen;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;

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
        onData(anything()).inAdapterView(withId(R.id.recipeList)).atPosition(0).perform(click());
        pressBack(); //closing the popup
        onView(withId(R.id.bookMark)).perform(click());
    }

    @Test
    public void deleteFromBookMarkSystemTest() {
        // -- Home page --
        onView(withId(R.id.oilBtn)).perform(click());
        onView(withId(R.id.searchBtn)).perform(click());

        // -- Recipe page --
        onData(anything()).inAdapterView(withId(R.id.recipeList)).atPosition(0).perform(click());
        pressBack(); //closing the popup
        onView(withId(R.id.bookMark)).perform(click());

        // -- Home page --
        pressBack();
        onView(withId(R.id.bookMark)).perform(click());

        // -- Bookmark page --
        onData(anything()).inAdapterView(withId(R.id.bookMark_List)).atPosition(0).perform(click());
        pressBack(); //closing the popup
        onView(withId(R.id.removeFromBM)).perform(click());
    }
}
