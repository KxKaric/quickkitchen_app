package comp3350.quickkitchen;
import android.content.Context;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.test.espresso.matcher.ViewMatchers;
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
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
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

@RunWith(AndroidJUnit4.class)
public class ShoppingListSystemTest {
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

}

