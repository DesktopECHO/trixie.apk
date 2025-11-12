package com.desktopecho.trixie.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;

import com.desktopecho.trixie.PrefStore;
import com.desktopecho.trixie.R;
import com.desktopecho.trixie.activity.MountsActivity;
import com.desktopecho.trixie.activity.PropertiesActivity;

public class PropertiesFragment extends PreferenceFragmentCompat implements
        Preference.OnPreferenceClickListener, OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setSharedPreferencesName(PrefStore.getPropertiesSharedName());

        Intent i = getActivity().getIntent();
        if (i != null) {
            switch (i.getIntExtra("pref", 0)) {
                case 1:
                    setPreferencesFromResource(R.xml.properties_ssh, rootKey);
                    break;
                case 2:
                    setPreferencesFromResource(R.xml.properties_vnc, rootKey);
                    break;
                case 7:
                    setPreferencesFromResource(R.xml.properties_pulse, rootKey);
                    break;
                default:
                    setPreferencesFromResource(R.xml.properties, rootKey);
            }
        }

        initSummaries(getPreferenceScreen());
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "ssh_properties": {
                Intent intent = new Intent(getContext(), PropertiesActivity.class);
                intent.putExtra("pref", 1);
                startActivity(intent);
                break;
            }
            case "gui_properties": {
                Intent intent = new Intent(getContext(), PropertiesActivity.class);

                ListPreference graphics = findPreference("graphics");
                switch (graphics.getValue()) {
                    case "vnc":
                        intent.putExtra("pref", 2);
                        break;
                }

                startActivity(intent);
                break;
            }
            case "pulse_properties": {
                Intent intent = new Intent(getContext(), PropertiesActivity.class);
                intent.putExtra("pref", 7);
                startActivity(intent);
                break;
            }
            case "mounts_editor": {
                Intent intent = new Intent(getContext(), MountsActivity.class);
                startActivity(intent);
                break;
            }
        }

        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);
        setSummary(pref, true);
    }

    private void initSummaries(PreferenceGroup pg) {
        for (int i = 0; i < pg.getPreferenceCount(); ++i) {
            Preference p = pg.getPreference(i);
            if (p instanceof PreferenceGroup)
                initSummaries((PreferenceGroup) p);
            else
                setSummary(p, false);
            if (p instanceof PreferenceScreen)
                p.setOnPreferenceClickListener(this);
        }
    }

    private void setSummary(Preference pref, boolean init) {
        if (pref instanceof EditTextPreference) {
            EditTextPreference editPref = (EditTextPreference) pref;
            pref.setSummary(editPref.getText());

            if (editPref.getKey().equals("dns")
                    && editPref.getText().isEmpty()) {
                pref.setSummary(getString(R.string.summary_dns_preference));
            }
            if (editPref.getKey().equals("disk_size")
                    && editPref.getText().equals("0")) {
                pref.setSummary(getString(R.string.summary_disk_size_preference));
            }
            if (editPref.getKey().equals("user_password") &&
                    editPref.getText().isEmpty()) {
                editPref.setText(PrefStore.generatePassword());
                pref.setSummary(editPref.getText());
            }
            if (editPref.getKey().equals("user_name")) {
                String userName = editPref.getText();
                String privilegedUsers = getString(R.string.privileged_users).replaceAll("android", userName);
                EditTextPreference editPrivilegedUsers = findPreference("privileged_users");
                editPrivilegedUsers.setText(privilegedUsers);
                editPrivilegedUsers.setSummary(privilegedUsers);
            }
        }

        if (pref instanceof ListPreference) {
            ListPreference listPref = (ListPreference) pref;
            pref.setSummary(listPref.getEntry());

            if (listPref.getKey().equals("target_type")) {
                EditTextPreference targetpath = findPreference("target_path");
                EditTextPreference disksize = findPreference("disk_size");
                ListPreference fstype = findPreference("fs_type");

                switch (listPref.getValue()) {
                    case "file":
                        if (init) {
                            targetpath.setText(getString(R.string.target_path_file));
                        }
                        disksize.setEnabled(true);
                        fstype.setEnabled(true);
                        break;
                    case "directory":
                        if (init) {
                            targetpath.setText(getString(R.string.target_path_directory));
                        }
                        disksize.setEnabled(false);
                        fstype.setEnabled(false);
                        break;
                    case "partition":
                        if (init) {
                            targetpath.setText(getString(R.string.target_path_partition));
                        }
                        disksize.setEnabled(false);
                        fstype.setEnabled(true);
                        break;
                    case "ram":
                        if (init) {
                            targetpath.setText(getString(R.string.target_path_ram));
                        }
                        disksize.setEnabled(true);
                        fstype.setEnabled(false);
                        break;
                    case "custom":
                        if (init) {
                            targetpath.setText(getString(R.string.target_path_custom));
                        }
                        disksize.setEnabled(false);
                        fstype.setEnabled(false);
                        break;
                }
            }
        }
    }
}
