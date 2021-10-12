package com.example.ulanov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llError;

    Button buttonRandom;
    Button buttonEmpty;
    Button buttonAlmost;
    Button buttonReverse;
    Button buttonSorted;
    Button change_to_second_page;

    EditText randomSize;
    EditText emptyArray;
    EditText almostSortedSize;
    EditText reverseSortedSize;
    EditText SortedSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        buttonRandom = findViewById(R.id.buttonRandom);
        buttonEmpty = findViewById(R.id.buttonEmpty);
        buttonAlmost = findViewById(R.id.buttonAlmost);
        buttonReverse = findViewById(R.id.buttonReverse);
        buttonSorted = findViewById(R.id.buttonSorted);
        change_to_second_page = findViewById(R.id.change_to_second_page);
        Button test = findViewById(R.id.test);

        randomSize = findViewById(R.id.randomSize);
        emptyArray = findViewById(R.id.emptyArray);
        almostSortedSize = findViewById(R.id.almostSortedSize);
        reverseSortedSize = findViewById(R.id.reverseSortedSize);
        SortedSize = findViewById(R.id.SortedSize);

        final Integer[] size_of_array = {0};
        ArrayList<Integer> final_array = new ArrayList<Integer>();

        buttonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(randomSize.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, R.string.empty_size_of_array, Toast.LENGTH_SHORT).show();
                else{
                    final_array.clear();
                    size_of_array[0] = 0;
                    try {
                        size_of_array[0] = Integer.parseInt(randomSize.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                    }
                    ArrayList<Integer> randomArray = new ArrayList<Integer>();
                    Integer rnd = 0;
                    Integer value = 0;
                    for (int i = 0; i < size_of_array[0]; i++)
                    {
                        randomArray.add(i);
                    }
                    for (int i = 0; i < size_of_array[0]; i++)
                    {
                        value = (int)(Math.random()* ((size_of_array[0] - i - 1) + 1));
                        final_array.add(randomArray.get(value));
                        randomArray.remove(value);
                    }
                }
            }
        });

        buttonEmpty.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emptyArray.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, R.string.empty_size_of_array, Toast.LENGTH_SHORT).show();
                else{
                    int sizeOfArrayInt = 0;
                    String number = "";
                    final_array.clear();
                    String stringArray = emptyArray.getText().toString();
                    for (int i = 0; i <= stringArray.length(); i++)
                    {
                        if (i != stringArray.length())
                        {
                            if ((stringArray.charAt(i) == ' ' || i == stringArray.length()) && number != "")
                            {
                                try {
                                    final_array.add(Integer.parseInt(number));
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                                }
                                sizeOfArrayInt++;
                                number = "";
                            }
                        }
                        if(i == stringArray.length())
                        {
                            if (i == stringArray.length() && number != "")
                            {
                                try {
                                    final_array.add(Integer.parseInt(number));
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                                }
                                sizeOfArrayInt++;
                                number = "";
                            }
                        }
                        if (i != stringArray.length() && stringArray.charAt(i) != ' ')
                        {
                            number = number + stringArray.charAt(i);
                        }
                    }
                    size_of_array[0] = sizeOfArrayInt;
                }
            }
        }));

        buttonAlmost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(almostSortedSize.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, R.string.empty_size_of_array, Toast.LENGTH_SHORT).show();
                else{
                    final_array.clear();
                    size_of_array[0] = 0;
                    try {
                        size_of_array[0] = Integer.parseInt(almostSortedSize.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                    }
                    for(int i = 0; i < size_of_array[0]; i++){
                        final_array.add(i);
                    }
                    for(int i = 0; i < 2; i++){
                        int value = (int) (Math.random() * ((size_of_array[0] - i - 1) + 1));
                        int temp = final_array.get(value - 1);
                        final_array.set(value - 1, final_array.get(value));
                        final_array.set(value, temp);
                    }
                }
            }
        });

        buttonReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reverseSortedSize.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, R.string.empty_size_of_array, Toast.LENGTH_SHORT).show();
                else{
                    final_array.clear();
                    size_of_array[0] = 0;
                    try {
                        size_of_array[0] = Integer.parseInt(reverseSortedSize.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                    }
                    for(int i = size_of_array[0] - 1; i >= 0; i--){
                        final_array.add(i);
                    }
                }
            }
        });

        buttonSorted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SortedSize.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, R.string.empty_size_of_array, Toast.LENGTH_SHORT).show();
                else{
                    final_array.clear();
                    size_of_array[0] = 0;
                    try {
                        size_of_array[0] = Integer.parseInt(SortedSize.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                    }
                    for(int i = 0; i < size_of_array[0]; i++){
                        final_array.add(i);
                    }
                }
            }
        });

        change_to_second_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseTypeOfSort.class);
                intent.putExtra("array", final_array);
                intent.putExtra("size_of_array", size_of_array[0]);
                startActivity(intent);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Test.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}