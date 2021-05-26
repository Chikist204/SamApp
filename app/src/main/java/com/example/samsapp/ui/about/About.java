package com.example.samsapp.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.samsapp.databinding.FragmentAboutBinding;

import org.json.JSONException;

import java.io.IOException;

public class About extends Fragment {

    private AboutViewModel aboutViewModel;
    private FragmentAboutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutViewModel =
                new ViewModelProvider(this).get(AboutViewModel.class);

        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MyCompanyAdapter adapter = null;
        try {
            adapter = new MyCompanyAdapter(getContext(), makeArr());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView lv = (ListView) binding.listView;
        lv.setAdapter(adapter);
        return root;
    }

    public String[] run() throws IOException, JSONException {
        Company compan = ReadJSONExample.readCompanyJSONFile(this);
        return compan.toArr();
    }

    Comp[] makeArr() throws IOException, JSONException {
        String[] about = run();
        Comp[] arr = new Comp[6];

        // Названия месяцев
        String[] key_about = {"Наименование", "Дата создания", "Место нахождения", "Режим и график работа", "Контактные телефоны", "Адреса электронной почты"};
        // Среднесуточная температура
        double[] tempArr = {-12.7, -11.3, -4.5, 7.7, 19.3, 23.9};
        // Количество дней
        int[] dayArr = {31, 28, 31, 30, 31, 30};

        // Сборка месяцев
        for (int i = 0; i < arr.length; i++) {
            Comp company = new Comp();
            company.key_about = key_about[i];
            company.about = about[i];
            arr[i] = company;
        }
        return arr;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}