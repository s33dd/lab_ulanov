package com.example.ulanov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;

public class ChooseTypeOfSort extends AppCompatActivity {

    public static int compares = 0;
    public static int changes = 0;

    static ArrayList<Integer> bubble_sort(ArrayList<Integer> array)
    {
        compares = 0;
        changes = 0;
        int temp;
        for (int i = 0; i < array.size(); i++)
        {
            for (int j = i + 1; j < array.size(); j++)
            {
                if (array.get(i) > array.get(j))
                {
                    compares++;
                    temp = array.get(i);
                    array.set(i, array.get(j));
                    array.set(j, temp);
                    changes++;
                }
                else
                    compares++;
            }
        }
        return array;
    }

    static ArrayList<Integer> insert_sort(ArrayList<Integer> array)
    {
        compares = 0;
        changes = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < array.size(); i++){
            result.add(i, array.get(i));
        }
        for (int i = 0; i < array.size(); i++)
        {
            int j = i;
            while (j > 0 && result.get(j - 1) > array.get(i))
            {
                result.set(j, result.get(j - 1));
                j--;
                changes++;
            }
            //if(j != 0 || i != 0)
            compares++;
            result.set(j, array.get(i));
        }
        return result;
    }

    public static void mergeSort(ArrayList<Integer> array, int low, int high) {
        changes = 0;
        compares = 0;
        if (high <= low) return;

        int mid = (low+high)/2;
        mergeSort(array, low, mid);
        mergeSort(array, mid+1, high);
        merge(array, low, mid, high);
    }

    public static void merge(ArrayList<Integer> array, int low, int mid, int high) {
        // Creating temporary subarrays
        int leftArray[] = new int[mid - low + 1];
        int rightArray[] = new int[high - mid];

        // Copying our subarrays into temporaries
        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array.get(low + i);
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array.get(mid + i + 1);

        // Iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // Copying from leftArray and rightArray back into array
        for (int i = low; i < high + 1; i++) {
            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array.set(i, leftArray[leftIndex]);
                    leftIndex++;
                    compares++;
                    changes++;
                } else {
                    array.set(i, rightArray[rightIndex]);
                    rightIndex++;
                    compares++;
                    changes++;
                }
            } else if (leftIndex < leftArray.length) {
                // If all elements have been copied from rightArray, copy rest of leftArray
                array.set(i, leftArray[leftIndex]);
                leftIndex++;
                changes++;
            } else if (rightIndex < rightArray.length) {
                // If all elements have been copied from leftArray, copy rest of rightArray
                array.set(i, rightArray[rightIndex]);
                rightIndex++;
                changes++;
            }
        }
    }

    static int add2pyramid(ArrayList<Integer> array, int i, int N)
    {
        int imax;
        int buf;
        if ((2 * i + 2) < N)
        {
            if (array.get(2 * i + 1) < array.get(2 * i + 2)) imax = 2 * i + 2;
            else imax = 2 * i + 1;
            compares++;
        }
        else imax = 2 * i + 1;
        if (imax >= N) return i;
        if (array.get(i) < array.get(imax))
        {
            compares++;
            buf = array.get(i);
            array.set(i, array.get(imax));
            array.set(imax, buf);
            changes++;
            if (imax < N / 2) i = imax;
        }
        else
        {
            compares++;
        }
        return i;
    }
    public static void sorting(ArrayList<Integer> array, int len)
    {
        compares = 0;
        changes = 0;
        //step 1: building the pyramid
        for (int i = len / 2 - 1; i >= 0; --i)
        {
            int prev_i = i;
            i = add2pyramid(array, i, len);
            if (prev_i != i) ++i;
        }

        //step 2: sorting
        int buf;
        for (int k = len - 1; k > 0; --k)
        {
            changes++;
            buf = array.get(0);
            array.set(0, array.get(k));
            array.set(k, buf);
            int i = 0, prev_i = -1;
            while (i != prev_i)
            {
                prev_i = i;
                i = add2pyramid(array, i, k);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_choose_type_of_sort);

        Switch bubbleSwitch = findViewById(R.id.bubbleSwitch);
        Switch insertSwitch = findViewById(R.id.insertSwitch);
        Switch mergeSwitch = findViewById(R.id.mergeSwitch);
        Switch pyramidSwitch = findViewById(R.id.pyramidSwitch);
        Switch allSwitch = findViewById(R.id.allSwitch);

        Button sort = findViewById(R.id.sort);
        Button prevPage = findViewById(R.id.prevPage);
        Button save = findViewById(R.id.test);

        TextView bubbleResultText = findViewById(R.id.bubbleResultText);
        TextView insertResultText = findViewById(R.id.insertResultText);
        TextView mergeTextResult = findViewById(R.id.mergeTextResult);
        TextView pyramidTextResult = findViewById(R.id.pyramidTextResult);

        bubbleSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bubbleSwitch.isChecked()){
                    insertSwitch.setChecked(false);
                    mergeSwitch.setChecked(false);
                    pyramidSwitch.setChecked(false);
                    allSwitch.setChecked(false);
                }
            }
        });

        insertSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insertSwitch.isChecked()){
                    bubbleSwitch.setChecked(false);
                    mergeSwitch.setChecked(false);
                    pyramidSwitch.setChecked(false);
                    allSwitch.setChecked(false);
                }
            }
        });

        mergeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mergeSwitch.isChecked()){
                    bubbleSwitch.setChecked(false);
                    insertSwitch.setChecked(false);
                    pyramidSwitch.setChecked(false);
                    allSwitch.setChecked(false);
                }
            }
        });

        pyramidSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pyramidSwitch.isChecked()){
                    bubbleSwitch.setChecked(false);
                    insertSwitch.setChecked(false);
                    mergeSwitch.setChecked(false);
                    allSwitch.setChecked(false);
                }
            }
        });

        allSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allSwitch.isChecked()){
                    bubbleSwitch.setChecked(false);
                    insertSwitch.setChecked(false);
                    mergeSwitch.setChecked(false);
                    pyramidSwitch.setChecked(false);
                }
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(allSwitch.isChecked() == bubbleSwitch.isChecked() == insertSwitch.isChecked()
                        == mergeSwitch.isChecked() == pyramidSwitch.isChecked())){
                    Toast.makeText(ChooseTypeOfSort.this, R.string.not_chosen_type_of_sort, Toast.LENGTH_SHORT).show();
                }
                else{
                    Bundle arguments = getIntent().getExtras();
                    int size_of_array = arguments.getInt("size_of_array");
                    ArrayList<Integer> final_array = arguments.getIntegerArrayList("array");
                    ArrayList<Integer> copy_of_array = new ArrayList<>();
                    if(bubbleSwitch.isChecked()){
                        copy_of_array.clear();
                        for(int i = 0; i < size_of_array; i++){
                            copy_of_array.add(final_array.get(i));
                        }
                        bubble_sort(copy_of_array);
                        bubbleResultText.setText("Сравнения: " + Integer.toString(compares) + "\nПерестановки: " +  Integer.toString(changes));
                    }
                    if(insertSwitch.isChecked()){
                        copy_of_array.clear();
                        for(int i = 0; i < size_of_array; i++){
                            copy_of_array.add(final_array.get(i));
                        }
                        insert_sort(copy_of_array);
                        insertResultText.setText("Сравнения: " + Integer.toString(compares)  + "\nПерестановки: " +  Integer.toString(changes));
                    }
                    if(mergeSwitch.isChecked()){
                        copy_of_array.clear();
                        for(int i = 0; i < size_of_array; i++){
                            copy_of_array.add(final_array.get(i));
                        }
                        mergeSort(copy_of_array, 0, size_of_array - 1);
                        mergeTextResult.setText("Сравнения: " + Integer.toString(compares)  + "\nПерестановки: " +  Integer.toString(changes));
                    }
                    if(pyramidSwitch.isChecked()){
                        copy_of_array.clear();
                        for(int i = 0; i < size_of_array; i++){
                            copy_of_array.add(final_array.get(i));
                        }
                        sorting(copy_of_array, size_of_array);
                        pyramidTextResult.setText("Сравнения: " + Integer.toString(compares)  + "\nПерестановки: " +  Integer.toString(changes));
                    }
                    if(allSwitch.isChecked()){
                        copy_of_array.clear();
                        for(int i = 0; i < size_of_array; i++){
                            copy_of_array.add(final_array.get(i));
                        }
                        bubble_sort(copy_of_array);
                        bubbleResultText.setText("Сравнения: " + Integer.toString(compares)  + "\nПерестановки: " +  Integer.toString(changes));
                        copy_of_array.clear();
                        for(int i = 0; i < size_of_array; i++){
                            copy_of_array.add(final_array.get(i));
                        }
                        insert_sort(copy_of_array);
                        insertResultText.setText("Сравнения: " + Integer.toString(compares)  + "\nПерестановки: " +  Integer.toString(changes));
                        copy_of_array.clear();
                        for(int i = 0; i < size_of_array; i++){
                            copy_of_array.add(final_array.get(i));
                        }
                        mergeSort(copy_of_array, 0, size_of_array - 1);
                        mergeTextResult.setText("Сравнения: " + Integer.toString(compares)  + "\nПерестановки: " +  Integer.toString(changes));
                        copy_of_array.clear();
                        for(int i = 0; i < size_of_array; i++){
                            copy_of_array.add(final_array.get(i));
                        }
                        sorting(copy_of_array, size_of_array);
                        pyramidTextResult.setText("Сравнения: " + Integer.toString(compares)  + "\nПерестановки: " +  Integer.toString(changes));
                    }
                }
            }
        });

        prevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseTypeOfSort.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /*save.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        Bundle arguments = getIntent().getExtras();
                        int size_of_array = arguments.getInt("size_of_array");
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                openFileOutput("Results", MODE_PRIVATE)));
                        bw.write( Integer.toString(size_of_array) + " " + bubbleResultText.getText().toString()
                                + " " + insertResultText.getText().toString()
                        + " " + mergeTextResult.getText().toString() + " " + pyramidTextResult.getText().toString());
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }));*/
    }
}