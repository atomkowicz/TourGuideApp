package com.example.android.tourguideapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * Creates and populates list of attractions
     */
    private static ArrayList<Place> attractions;
    private static ArrayList<Place> restaurants;
    private static ArrayList<Place> parks;
    private static ArrayList<Place> events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        generateSampleData();
        //populateListsOfAlbumsAndSongs();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.list_view1);

            final int activeFragmentNumber = getArguments().getInt(ARG_SECTION_NUMBER);

            // Choose right adapter for ListView.
            // ListView will display list of attractions, restaurants, nature reserves or events
            switch (activeFragmentNumber) {
                case 1:
                    listView.setAdapter(new PlaceListAdapter(getActivity(), attractions));
                    break;
                case 2:
                    listView.setAdapter(new PlaceListAdapter(getActivity(), restaurants));
                    break;
                case 3:
                    listView.setAdapter(new PlaceListAdapter(getActivity(), parks));
                    break;
                case 4:
                    listView.setAdapter(new PlaceListAdapter(getActivity(), events));
                    break;
            }

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent();
                    intent.setClass(getContext(), PlaceActivity.class);

                    // Create new intent to open {@link TabbedActivity}
                    switch (activeFragmentNumber) {
                        case 1:
                            intent.putExtra("place", attractions.get(position));
                            break;
                        case 2:
                            intent.putExtra("place", restaurants.get(position));
                            break;
                        case 3:
                            intent.putExtra("place", parks.get(position));
                            break;
                        case 4:
                            intent.putExtra("place", events.get(position));
                            break;
                    }

                    // Start the new activity
                    startActivity(intent);
                }
            });

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Attractions";
                case 1:
                    return "Restaurants";
                case 2:
                    return "Nature";
                case 3:
                    return "Events";
            }
            return null;
        }
    }

    /**
     * Returns sample list of objects
     */
    public void generateSampleData() {
        attractions = new ArrayList<Place>();
        restaurants = new ArrayList<Place>();
        parks = new ArrayList<Place>();
        events = new ArrayList<Place>();

        Resources res = this.getResources();

        String[] attractionsDataArray = res.getStringArray(R.array.attractions);
        String[] restaurantsDataArray = res.getStringArray(R.array.restaurants);
        String[] natureDataArray = res.getStringArray(R.array.nature);
        String[] eventsDataArray = res.getStringArray(R.array.events);

        for (int i = 0; i < attractionsDataArray.length; i++) {
            int j = i + 1;

            String imgName = "atr_" + j + "_thumb";
            int resID = res.getIdentifier(imgName, "drawable", this.getPackageName());
            attractions.add(new Place(j, attractionsDataArray[i], resID));

            String imgNameRest = "rest_" + j + "_thumb";
            int resIDRest = res.getIdentifier(imgNameRest, "drawable", this.getPackageName());
            restaurants.add(new Place(j, restaurantsDataArray[i], resIDRest));

            String imgNameNat = "nat_" + j + "_thumb";
            int resIDNat = res.getIdentifier(imgNameNat, "drawable", this.getPackageName());
            parks.add(new Place(j, natureDataArray[i], resIDNat));

            String imgNameEvent = "ev_" + j + "_thumb";
            int resIDEvent = res.getIdentifier(imgNameEvent, "drawable", this.getPackageName());
            events.add(new Place(j, eventsDataArray[i], resIDEvent));
        }
    }
}
