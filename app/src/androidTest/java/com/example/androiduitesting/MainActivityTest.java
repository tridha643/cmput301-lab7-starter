package com.example.androiduitesting;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import androidx.test.espresso.intent.Intents; 
import static
        androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<MainActivity>(MainActivity.class);
    @Test
    public void testAddCity(){
// Click on Add City button
        onView(withId(R.id.button_add)).perform(click());
// Type "Edmonton" in the editText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
// Click on Confirm
                onView(withId(R.id.button_confirm)).perform(click());
// Check if text "Edmonton" is matched with any of the text displayed on the screen
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }
    @Test
    public void testClearCity(){
// Add first city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());
//Add another city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouver"));
                onView(withId(R.id.button_confirm)).perform(click());
//Clear the list
        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());
    }
    @Test
    public void testListView(){
// Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
                onView(withId(R.id.button_confirm)).perform(click());
// Check if in the Adapter view (given id of that adapter view),there is a data
// (which is an instance of String) located at position zero.
// If this data matches the text we provided then Voila! Our test should pass
// You can also use anything() in place of is(instanceOf(String.class))
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list
        )).atPosition(0).check(matches((withText("Edmonton"))));
    }

    //this is implementing the first test case 
    //checking whether the activity has correctly switched or not. 
    @Test 
    public void testSwitchActivity() {
        //Intialize all the intents 
        Intents.init();

        

        //Add a city first to the listview 
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        //then click on the city in this list 
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());


        //checking if the show activity is displayed 
        intended(hasComponent(ShowActivity.class.getName()));

        Intents.release();

    }


    // now moving on to the second test case 
    @Test
    public void testif_city_name_consistent() {
        //intialize our intents 
        Intents.init();

        String test_city_name = "Edmonton";

        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(test_city_name));
        onView(withId(R.id.button_confirm)).perform(click());

        //clicking on the city in the list view 
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());


        //verifying if the city name passed is consistent and correct 
        intended(hasExtra("city_name", test_city_name));

        Intents.release();
    }


    // now finally hte third test case 
    @Test 
    public void testif_back_button_works() {
        Intents.init();

        String test_city_name = "Edmonton";

        //add city and then navigating to show activity 
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(test_city_name));
        onView(withId(R.id.button_confirm)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        intended(hasComponent(ShowActivity.class.getName()));

        Intents.release();
    }
}