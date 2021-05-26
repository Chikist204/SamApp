package com.example.samsapp.ui.schedule;

import com.example.samsapp.R;
import com.example.samsapp.ui.about.About;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReadJSONExample {

    // Read the company.json file and convert it to a java object.
    public static Company readCompanyJSONFile(ScheduleFragment context, String gr, String d) throws IOException,JSONException {
        // Read content of company.json
        String jsonText = readText(context, R.raw.schedule);
        JSONObject jsonRoot = new JSONObject(jsonText);
        String group = jsonRoot.getString(gr);
        JSONObject day = new JSONObject(group);
        JSONArray two = new JSONArray(day.getString(d));
        System.out.println(two.get(0));
        Company company = new Company();
        company.setGroup(gr);
        company.setDay(d);
        company.setSchedule(two);
        return company;
    }



    private static String readText(ScheduleFragment context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}