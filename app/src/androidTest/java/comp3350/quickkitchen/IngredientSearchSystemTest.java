package comp3350.quickkitchen;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import comp3350.quickkitchen.presentation.HomeActivity;

@RunWith(AndroidJUnit4.class)
public class IngredientSearchSystemTest {
    /**
     * IngredientSearchSystemTest
     */
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void singleIngredientSearchSystemTest() {
        // -- Home page --
        onView(withId(R.id.oilBtn)).perform(click());
        onView(withId(R.id.searchBtn)).perform(click());

        // -- Recipe page --
        onView(withId(R.id.recipeList)).check(matches(isDisplayed()));
    }

    @Test
    public void multipleIngredientSearchSystemTest() {
        // -- Home page --
        onView(withId(R.id.oilBtn)).perform(click());
        onView(withId(R.id.tomatoBtn)).perform(click());
        onView(withId(R.id.searchBtn)).perform(click());

        // -- Recipe page --
        onView(withId(R.id.recipeList)).check(matches(isDisplayed()));
    }
}

