package com.example.samsapp.ui.about;

import android.content.Context;

import com.example.samsapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReadJSONExample {

    // Read the company.json file and convert it to a java object.
    public static Company readCompanyJSONFile(About context) throws IOException,JSONException {
        // Read content of company.json
        String jsonText = readText(context, R.raw.about);
        JSONObject jsonRoot = new JSONObject(jsonText);
        String name = jsonRoot.getString("Наименование");
        String date = jsonRoot.getString("Дата создания");
        String place = jsonRoot.getString("Место нахождения");
        String schedule = jsonRoot.getString("Режим и график работа");
        String contact = jsonRoot.getString("Контактные телефоны");
        String address = jsonRoot.getString("Адреса электронной почты");

        Company company = new Company();
        company.setName(name);
        company.setDate(date);
        company.setPlace(place);
        company.setSchedule(schedule);
        company.setContact(contact);
        company.setAddress(address);
        return company;
    }



    private static String readText(About context, int resId) throws IOException {
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