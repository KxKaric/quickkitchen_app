package comp3350.quickkitchen;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import comp3350.quickkitchen.presentation.HomeActivity;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class ShowStepsSystemTest {
    /**
     * ShowStepsSystemTest
     */
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void showStepsSystemTest() {
        // -- Home page --
        onView(withId(R.id.oilBtn)).perform(click());
        onView(withId(R.id.searchBtn)).perform(click());

        // -- Recipe page --
        onData(anything()).inAdapterView(withId(R.id.recipeList)).atPosition(0).perform(click());
        pressBack(); //closing the popup
        onView(withId(R.id.nextBtn)).perform(click());

        // -- Instructions page --
        onView(withId(R.id.instrutions_list)).check(matches(isDisplayed()));
    }
}

