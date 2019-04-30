package amirjaber.practice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;

import amirjaber.practice.activities.adapters.RecyclerViewAdapter;
import amirjaber.practice.activities.dtos.ParcelableDto;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static amirjaber.practice.activities.utils.ToastUtils.longToast;
import static amirjaber.practice.activities.utils.ToastUtils.shortToast;

public class RecyclerActivity extends AppCompatActivity {

    private ArrayList<ParcelableDto> parcelableDtoList;

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SlidrInterface slidrInterface;

    private Button bInsert, bRemove;
    private EditText etInsert, etRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setTitle("Parcelable Activity 1");
        slidrInterface = Slidr.attach(this);
        slidrInterface.unlock();
        shortToast(getApplicationContext(), "Slide left to close this activity");
        longToast(getApplicationContext(), "Recycler view with parcelable object");

        createSampleList();
        buildRecyclerView();
        init();
    }


    private void createSampleList() {
        parcelableDtoList = new ArrayList<>();
        parcelableDtoList.add(new ParcelableDto(R.drawable.ic_android, "First", "Text 1"));
        parcelableDtoList.add(new ParcelableDto(R.drawable.ic_android, "Second", "Text 2"));
        parcelableDtoList.add(new ParcelableDto(R.drawable.ic_android, "Third", "Text 3"));
    }

    private void buildRecyclerView() {
        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAdapter = new RecyclerViewAdapter(parcelableDtoList);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeText(position);
                Intent intent = new Intent(getApplicationContext(), ParcelableDtoActivity.class);
                intent.putExtra("Parcelable Item", parcelableDtoList.get(position));
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });

    }

    private void init() {
        bInsert = findViewById(R.id.b_insert);
        bRemove = findViewById(R.id.b_remove);
        etInsert = findViewById(R.id.et_insert);
        etRemove = findViewById(R.id.et_remove);

        listenToButtons();
    }

    private void listenToButtons() {
        bInsert.setOnClickListener(v -> {
            int position = Integer.parseInt(etInsert.getText().toString());
            insertItem(position);
        });

        bRemove.setOnClickListener(v -> {
            int position = Integer.parseInt(etRemove.getText().toString());
            removeItem(position);

        });
    }

    private void insertItem(int position) {
        if (position > (parcelableDtoList.size())) {
            longToast(getApplicationContext(), "Position chosen is out of bounds!");
        } else {
            parcelableDtoList.add(position, new ParcelableDto(R.drawable.ic_android, "New item at position " + position, "text"));
            recyclerViewAdapter.notifyItemInserted(position);
        }
    }

    private void removeItem(int position) {
        if (position > (parcelableDtoList.size() - 1)) {
            longToast(getApplicationContext(), "Such index doesn't exist");
        } else {
            parcelableDtoList.remove(position);
            recyclerViewAdapter.notifyItemRemoved(position);
        }
    }

    private void changeText(int position) {
        parcelableDtoList.get(position).setText2("Clicked");
        recyclerViewAdapter.notifyDataSetChanged();
    }


}
