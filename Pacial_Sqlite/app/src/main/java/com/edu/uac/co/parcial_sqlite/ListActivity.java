package com.edu.uac.co.parcial_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements UserListener {

    private UserController uC;
    private RecyclerView uList;
    private RecyclerViewAdapter RVA;

    List<Usuario> userList = new ArrayList<Usuario>();

    private Usuario selectedUser = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        uList = findViewById(R.id.userListLV);
        uList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fillList() {
        userList.clear();

        Cursor cursor = uC.allUsers();

        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
            String stratum = cursor.getString(cursor.getColumnIndexOrThrow("ESTRATO"));
            String wage = cursor.getString(cursor.getColumnIndexOrThrow("SALARIO"));
            String eduLevel = cursor.getString(cursor.getColumnIndexOrThrow("NIVEL_EDUCACION"));

            Usuario localUser = new Usuario(id, name, stratum, wage, eduLevel);
            userList.add(localUser);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        uC = new UserController(getApplicationContext());

        fillList();

        RVA = new RecyclerViewAdapter(userList, this);
        uList.setAdapter(RVA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_toolbar, menu);

        MenuItem menuItem = menu.findItem(R.id.buscar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                RVA.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.edit:
                Toast.makeText(this, "Editar Usuario " + selectedUser.nombre, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, ModifyActivity.class);
                intent.putExtra("userkey", selectedUser);

                startActivity(intent);

                return true;

            case R.id.delete:
                Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();

                uC.deleteUser(selectedUser);
                RVA.deleteUser(selectedUser);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemClicked(Usuario usuario) {
        this.selectedUser = usuario;
    }
}