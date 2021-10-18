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

    EditText randomSize;
    EditText emptyArray;
    EditText almostSortedSize;
    EditText reverseSortedSize;
    EditText SortedSize;
    EditText lowerLimit;
    EditText upperLimit;

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
        Button test = findViewById(R.id.test);

        randomSize = findViewById(R.id.arraySize);
        emptyArray = findViewById(R.id.emptyArray);
        almostSortedSize = findViewById(R.id.arraySize);
        reverseSortedSize = findViewById(R.id.arraySize);
        SortedSize = findViewById(R.id.arraySize);
        lowerLimit = findViewById(R.id.lowerLimit);
        upperLimit = findViewById(R.id.upperLimit);

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
                        ArrayList<Integer> randomArray = new ArrayList<Integer>();
                        Integer value = 0;
                        for (int i = 0; i < size_of_array[0]; i++)
                        {
                            int max = (int) (Integer.parseInt(upperLimit.getText().toString()) / 0.99);
                            int min = Integer.parseInt(lowerLimit.getText().toString());
                            value = (int) (Math.random() * max) + min;
                            randomArray.add(value);
                        }
                        for (int i = 0; i < size_of_array[0]; i++)
                        {
                            final_array.add(randomArray.get(i));
                        }
                        Intent intent = new Intent(MainActivity.this, ChooseTypeOfSort.class);
                        intent.putExtra("array", final_array);
                        intent.putExtra("size_of_array", size_of_array[0]);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonEmpty.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emptyArray.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, R.string.empty_array, Toast.LENGTH_SHORT).show();
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
                    if(final_array.toArray().length == 0) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        size_of_array[0] = sizeOfArrayInt;
                        Intent intent = new Intent(MainActivity.this, ChooseTypeOfSort.class);
                        intent.putExtra("array", final_array);
                        intent.putExtra("size_of_array", size_of_array[0]);
                        startActivity(intent);
                    }
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
                        for(int i = 0; i < size_of_array[0]; i++){
                            final_array.add(i);
                        }
                        for(int i = 0; i < 2; i++){
                            int value = (int) (Math.random() * ((size_of_array[0] - i - 1) + 1));
                            int temp = final_array.get(value - 1);
                            final_array.set(value - 1, final_array.get(value));
                            final_array.set(value, temp);
                        }
                        Intent intent = new Intent(MainActivity.this, ChooseTypeOfSort.class);
                        intent.putExtra("array", final_array);
                        intent.putExtra("size_of_array", size_of_array[0]);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
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
                        for(int i = size_of_array[0] - 1; i >= 0; i--){
                            final_array.add(i);
                        }
                        Intent intent = new Intent(MainActivity.this, ChooseTypeOfSort.class);
                        intent.putExtra("array", final_array);
                        intent.putExtra("size_of_array", size_of_array[0]);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
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
                        for(int i = 0; i < size_of_array[0]; i++){
                            final_array.add(i);
                        }
                        Intent intent = new Intent(MainActivity.this, ChooseTypeOfSort.class);
                        intent.putExtra("array", final_array);
                        intent.putExtra("size_of_array", size_of_array[0]);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, R.string.wrong_data_format, Toast.LENGTH_SHORT).show();
                    }
                }
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