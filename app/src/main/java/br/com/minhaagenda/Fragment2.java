package br.com.minhaagenda;

import static br.com.minhaagenda.MainActivity.db;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.concurrent.Executor;

import br.com.minhaagenda.adapter.UserAdapter;
import model.AppDatabase;
import model.User;
import model.UserDao;


public class Fragment2 extends Fragment {
    ArrayList<String> lista = new ArrayList<>();
    ArrayAdapter<String> adaptador;
    ArrayList<User> listUsers = new ArrayList<>();
    UserAdapter<User> userAdapter;
    private int itemLista = android.R.layout.simple_list_item_1;
    ListView listView;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private final Handler handler = new Handler();
    Intent intent;
    UserDao userDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDao = db.userDao();
        progressBar = getActivity().findViewById(R.id.progressBar);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("=", "==============2xxx123213");
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        listView = view.findViewById(R.id.listView);

        // adaptador = new ArrayAdapter<>(getContext(), itemLista, lista);
       //  listView.setAdapter(adaptador);

        userAdapter = new UserAdapter<User>(getContext(), listUsers);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adaptador.getItem(i);
                intent = new Intent(getContext(), Detalhes.class);
                intent.putExtra("aluno", item);

                startActivity(intent);
            }
        });


        return view;
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        Thread t = new Thread() {
            @Override
            public void run() {
                userDao.getAll().forEach(user -> {
                    lista.add(user.nome);
                });
            }
        };
        t.start();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    progressBar = view.findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE);
                    while (progressStatus < 2000) {
                        progressStatus += 1;
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                            }
                        });
                    }
                    handler.post(new Runnable() {
                        public void run() {
                           adaptador.notifyDataSetChanged();

                        }
                    });

                    progressBar.setVisibility(View.INVISIBLE);

                } catch (Exception e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}