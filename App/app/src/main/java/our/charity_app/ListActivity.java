package our.charity_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  our.charity_app.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class ListActivity extends Activity {

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        createGroupList();

        createCollection();

        expListView = (ExpandableListView) findViewById(R.id.laptop_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, laptopCollection);
         expListView.setAdapter(expListAdapter);

        //setGroupIndicatorToRight();

        expListView.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Doctors Without Border");
        groupList.add("Hope for Haiti");
    //    groupList.add("Lenovo");
     //   groupList.add("Sony");
      //  groupList.add("HCL");
      //  groupList.add("Samsung");
    }

    private void createCollection() {
        // preparing laptops collection(child)
        String[] hpModels = { " is an international humanitarian-aid non-governmental organization (NGO) and Nobel Peace Prize laureate, best known for its projects in war-torn regions and developing countries facing endemic diseases. It was founded in France in response to the Biafran War.[1] The organization is known in most of the world by its localized name or simply as MSF; in Canada and the United States the name Doctors Without Borders is commonly used. In 2015 over 30,000, mostly local, doctors, nurses and other medical professionals, logistical experts, water and sanitation engineers and administrators provided medical aid in over 70 countries.[2] These doctors and nurses decided to volunteer their time to solve issues of world health. Private donors provide about 90% of the organization's funding, while corporate donations provide the rest, giving MSF an annual budget of approximately US$750 million.[3]\n" +
                "Médecins Sans Frontières was created in 1971, in the aftermath of the Biafra secession, by a small group of French doctors and journalists who believed that all people have the right to medical care regardless of race, religion, creed or political affiliation, and that the needs of these people outweigh respect for national borders.[4]" };
        String[] hclModels = { "HCL S2101", "HCL L2102", "HCL V2002" };
        String[] lenovoModels = { "IdeaPad Z Series", "Essential G Series",
                "ThinkPad X Series", "Ideapad Z Series" };
        String[] sonyModels = { "VAIO E Series", "VAIO Z Series",
                "VAIO S Series", "VAIO YB Series" };
        String[] dellModels = { "Hope for Haiti is a nonprofit organization based in Naples, Florida and founded in 1990 by JoAnne Kuehner. The mission of the organization is to improve the quality of life for the Haitian people, particularly children, through education, nutrition, and healthcare.[1] In addition, Hope for Haiti has an emergency relief component and has responded to several natural disasters since its founding.\n" };
        String[] samsungModels = { "NP Series", "Series 5", "SF Series" };

        laptopCollection = new LinkedHashMap<String, List<String>>();

        for (String laptop : groupList) {
            if (laptop.equals("Doctors Without Border")) {
                loadChild(hpModels);
                //add a button/url link here which they can donate too
            } else if (laptop.equals("Hope for Haiti"))
                loadChild(dellModels);
         //   else if (laptop.equals("Sony"))
       //         loadChild(sonyModels);
       //    else if (laptop.equals("HCL"))
        //        loadChild(hclModels);
        //    else if (laptop.equals("Samsung"))
        //        loadChild(samsungModels);
        //    else
         //       loadChild(lenovoModels);

            laptopCollection.put(laptop, childList);
        }
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
