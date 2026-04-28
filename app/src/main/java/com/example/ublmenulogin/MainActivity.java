package com.example.ublmenulogin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View; // TAMBAHAN
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnRegister = findViewById(R.id.btn_register);
        EditText txtFullName = findViewById(R.id.txt_fullname);
        EditText txtEmail = findViewById(R.id.txt_email);
        EditText txtAddress = findViewById(R.id.txt_address);
        EditText txtPassword = findViewById(R.id.txt_password);
        TextView tvResult = findViewById(R.id.tv_result);
        RadioGroup rgSemester = findViewById(R.id.rg_semester);
        CheckBox cbAgreement = findViewById(R.id.cb_agreement);

        // TAMBAHAN (TextView error)
        @SuppressLint("MissingInflatedId") TextView tvJurusanError = findViewById(R.id.tvJurusanError);
        TextView tvSemesterError = findViewById(R.id.tvSemesterError);
        TextView tvAgreementError = findViewById(R.id.tvAgreementError);

        Spinner spJurusan = findViewById(R.id.sp_jurusan);
        String[] Jurusan = {"-- Pilih Jurusan --", "Teknik Informatika", "Sistem Informasi", "Ilmu Komputer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Jurusan
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(adapter);

        btnRegister.setOnClickListener(v -> {

            String name = txtFullName.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String address = txtAddress.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            String jurusan = spJurusan.getSelectedItem().toString();
            int rbSemesterId = rgSemester.getCheckedRadioButtonId();

            // TAMBAHAN (reset error)
            tvJurusanError.setVisibility(View.GONE);
            tvSemesterError.setVisibility(View.GONE);
            tvAgreementError.setVisibility(View.GONE);

            if (name.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            }
            else if (spJurusan.getSelectedItemPosition() == 0) {
                tvJurusanError.setVisibility(View.VISIBLE); // TAMBAHAN
            }
            else if (rbSemesterId == -1) {
                tvSemesterError.setVisibility(View.VISIBLE); // TAMBAHAN
            }
            else if (!cbAgreement.isChecked()) {
                tvAgreementError.setVisibility(View.VISIBLE); // TAMBAHAN
            }
            else {
                RadioButton rbSelected = findViewById(rbSemesterId);
                String semester = rbSelected.getText().toString();

                String result = "Registration Success!\n\n" +
                        "Name\t\t: " + name + "\n" +
                        "Email\t\t: " + email + "\n" +
                        "Address\t: " + address + "\n" +
                        "Jurusan\t: " + jurusan + "\n" +
                        "Semester\t: " + semester;

                tvResult.setText(result);
                Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}