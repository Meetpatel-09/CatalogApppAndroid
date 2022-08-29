package com.example.catalogapp.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.catalogapp.R;
import com.example.catalogapp.StartActivity;
import com.example.catalogapp.admin.books.ManageBookActivity;
import com.example.catalogapp.admin.category.AddCategoryActivity;
import com.example.catalogapp.admin.category.RemoveCategoryActivity;
import com.example.catalogapp.admin.users.ManageUserActivity;
import com.example.catalogapp.authentication.AdminLogInActivity;
import com.google.android.material.card.MaterialCardView;

public class AdminHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        SharedPreferences sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("isLogin", "false").equals("false")) {
            startActivity(new Intent(AdminHomeActivity.this, AdminLogInActivity.class));
            finish();
        }

        MaterialCardView addCategory = findViewById(R.id.add_category);
        MaterialCardView removeCategory = findViewById(R.id.remove_category);
        MaterialCardView manageUser = findViewById(R.id.manage_user);
        MaterialCardView manageBooks = findViewById(R.id.manage_books);
        Button logOut = findViewById(R.id.btn_logout);

        addCategory.setOnClickListener(this);
        removeCategory.setOnClickListener(this);
        manageUser.setOnClickListener(this);
        manageBooks.setOnClickListener(this);
        logOut.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.add_category:
                intent = new Intent(AdminHomeActivity.this, AddCategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.remove_category:
                intent = new Intent(AdminHomeActivity.this, RemoveCategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.manage_user:
                intent = new Intent(AdminHomeActivity.this, ManageUserActivity.class);
                startActivity(intent);
                break;
            case R.id.manage_books:
                intent = new Intent(AdminHomeActivity.this, ManageBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_logout:
                editor.putString("isLogin", "false");
                editor.commit();
                intent = new Intent(AdminHomeActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}