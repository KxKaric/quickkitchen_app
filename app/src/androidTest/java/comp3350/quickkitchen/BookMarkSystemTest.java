package comp3350.quickkitchen;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import comp3350.quickkitchen.presentation.BookMarkActivity;
import comp3350.quickkitchen.presentation.HomeActivity;
import static org.hamcrest.CoreMatchers.anything;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BookMarkSystemTest {
    /**
     * System testing
     */
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule = new ActivityScenarioRule<>(HomeActivity.class);


    @Test
    public void shoppingListSystemTest() {
        // -- Home page --
        onView(withId(R.id.oilBtn)).perform(click());
        onView(withId(R.id.searchBtn)).perform(click());

        // -- Recipe page --
        onData(anything()).inAdapterView(withId(R.id.recipeList)).atPosition(0).perform(click());
        pressBack(); //closing the popup
        onView(withId(R.id.nextBtn)).perform(click());

        // -- Instructions page --
        onView(withId(R.id.ShoppingList)).perform(click());

        // -- Shopping List page --
        onView(withId(R.id.shopping_list)).check(matches(isDisplayed()));
    }

    @Test
    public void bookMarkingSystemTest() {
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
