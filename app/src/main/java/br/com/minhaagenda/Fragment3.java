package br.com.minhaagenda;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.AppDatabase;
import model.User;
import model.UserDao;

public class Fragment3 extends Fragment {

    EditText campoNome;
    EditText campoEmail;
    EditText campoTelefone;

    Button btnCadastrar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        btnCadastrar =  view.findViewById(R.id.btnCadastrar);
        campoNome = view.findViewById(R.id.campoNome);
        campoTelefone = view.findViewById(R.id.campoTelefone);
        campoEmail = view.findViewById(R.id.campoEmail);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmail.getText().toString();
                Log.d("================", nome);
                Log.d("MSG", nome);
                Log.d("MSG", telefone);
                Log.d("MSG", email);

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                                    AppDatabase.class, "database-name").build();
                            User user = new User(nome, telefone, email);
                            UserDao userDao = db.userDao();
                            userDao.insertAll(user);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });


                Toast.makeText(getActivity().getApplicationContext(),
                        "Salvo com sucesso", Toast.LENGTH_SHORT).show();


            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}