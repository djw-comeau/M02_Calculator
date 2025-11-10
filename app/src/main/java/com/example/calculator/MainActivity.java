/*

This was the original code of my submission for this assignment.

package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText textN1;
    private EditText textN2;
    private TextView textANS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hook up fields
        textN1 = findViewById(R.id.editTextN1);
        textN2 = findViewById(R.id.editTextN2);
        textANS = findViewById(R.id.editTextNumAns);

        // Add button
        Button bAdd = findViewById(R.id.b_Add);
        bAdd.setOnClickListener(v -> performOperation("add"));

        // Subtract button
        Button bSubtract = findViewById(R.id.b_Subtract);
        bSubtract.setOnClickListener(v -> performOperation("subtract"));

        // Multiply button
        Button bMultiply = findViewById(R.id.b_Multiply);
        bMultiply.setOnClickListener(v -> performOperation("multiply"));

        // Divide button
        Button bDivide = findViewById(R.id.b_Divide);
        bDivide.setOnClickListener(v -> performOperation("divide"));
    }

    private void performOperation(String op) {
        double d1 = 0.0;
        double d2 = 0.0;
        double answer = 0.0;

        try {
            d1 = Double.parseDouble(textN1.getText().toString());
            d2 = Double.parseDouble(textN2.getText().toString());

            switch (op) {
                case "add":
                    answer = d1 + d2;
                    Log.d("GLaDOS", "Oh look. You combined " + d1 + " and " + d2 + ". Astonishing. Truly.");
                    break;
                case "subtract":
                    answer = d1 - d2;
                    Log.d("GLaDOS", "Removing " + d2 + " from " + d1 + ". Subtraction is just addition, but sad.");
                    break;
                case "multiply":
                    answer = d1 * d2;
                    Log.d("GLaDOS", "Exponential disappointment achieved: " + d1 + " times " + d2 + ".");
                    break;
                case "divide":
                    if (d2 != 0) {
                        answer = d1 / d2;
                        Log.d("GLaDOS", "Dividing " + d1 + " by " + d2 + ". Like splitting test subjects in half.");
                    } else {
                        textANS.setText("Error");
                        Log.w("GLaDOS", "Division by zero. Bold. Pointless. Fatal.");
                        return;
                    }
                    break;
            }
        } catch (Exception e) {
            textANS.setText("Error");
            Log.w("GLaDOS", "Congratulations. You broke math. Input failure: " + e.getMessage());
            return;
        }

        textANS.setText(String.valueOf(answer));
        Log.d("GLaDOS", "Final result displayed: " + answer + ". It won’t help you survive.");
    }
}


 */

// Below is updated code as per AOSP.

/**
 * Copyright (C) 2025 The Android Open Source Project

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *      http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.

 */

/*
Revision 2.0
Assignment: A9 - ASOP
Course: Mobile Application Development
Instructor: Russell Shanahan
Date: November 9, 2025
Student: David Comeau
Student ID: W0158668
 */

package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Portal Calculator
 * Updated to comply with AOSP Java Code Style for Contributors
 * and Google Java Style Guide.
 *
 * All fixes are annotated inline for clarity.
 */
public class MainActivity extends AppCompatActivity {

    // Fix #1 (AOSP §4.8.2 – Field Naming):
    // Renamed fields to follow lowerCamelCase and improve clarity.
    private EditText textNum1;
    private EditText textNum2;
    private TextView textAnswer;

    // Fix #2 (Google Java Style §5.2.4 – Constants & Magic Values):
    // Replaced magic strings with named constants for maintainability.
    private static final String OP_ADD = "add";
    private static final String OP_SUBTRACT = "subtract";
    private static final String OP_MULTIPLY = "multiply";
    private static final String OP_DIVIDE = "divide";

    // Fix #3 (AOSP §4.6.3 – Logging Practices):
    // Defined a static TAG constant instead of repeating string literals.
    private static final String TAG = "GLaDOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hook up UI fields
        textNum1 = findViewById(R.id.editTextN1);
        textNum2 = findViewById(R.id.editTextN2);
        textAnswer = findViewById(R.id.editTextNumAns);

        // Add button
        Button bAdd = findViewById(R.id.b_Add);
        bAdd.setOnClickListener(v -> performOperation(OP_ADD));

        // Subtract button
        Button bSubtract = findViewById(R.id.b_Subtract);
        bSubtract.setOnClickListener(v -> performOperation(OP_SUBTRACT));

        // Multiply button
        Button bMultiply = findViewById(R.id.b_Multiply);
        bMultiply.setOnClickListener(v -> performOperation(OP_MULTIPLY));

        // Divide button
        Button bDivide = findViewById(R.id.b_Divide);
        bDivide.setOnClickListener(v -> performOperation(OP_DIVIDE));
    }

    /**
     * Performs the selected operation on both inputs.
     *
     * Fix #4 (AOSP §4.1 / SOLID – Single Responsibility):
     * Broke large method into smaller helper methods for readability.
     */
    private void performOperation(String op) {
        double d1 = parseInput(textNum1);
        double d2 = parseInput(textNum2);
        double answer = calculate(op, d1, d2);
        displayAnswer(answer);
    }

    /**
     * Attempts to parse user input to a double.
     *
     * Fix #6 (AOSP §4.6.2 – Exception Handling):
     * Catch specific exception type for clarity and safety.
     */
    private double parseInput(EditText field) {
        try {
            return Double.parseDouble(field.getText().toString());
        } catch (NumberFormatException e) {
            textAnswer.setText("Error");
            Log.w(TAG, "Input failure: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Performs arithmetic based on operation string.
     *
     * Fix #5 (AOSP §4.1.1 – Braces):
     * Ensured consistent brace placement (K&R style) and indentation.
     */
    private double calculate(String op, double d1, double d2) {
        double answer = 0.0;

        switch (op) {
            case OP_ADD:
                answer = d1 + d2;
                Log.d(TAG, "Oh look. You combined " + d1 + " and " + d2 + ". Astonishing. Truly.");
                break;

            case OP_SUBTRACT:
                answer = d1 - d2;
                Log.d(TAG, "Removing " + d2 + " from " + d1 + ". Subtraction is just addition, but sad.");
                break;

            case OP_MULTIPLY:
                answer = d1 * d2;
                Log.d(TAG, "Exponential disappointment achieved: " + d1 + " times " + d2 + ".");
                break;

            case OP_DIVIDE:
                if (d2 != 0) {  // K&R brace style applied here
                    answer = d1 / d2;
                    Log.d(TAG, "Dividing " + d1 + " by " + d2 + ". Like splitting test subjects in half.");
                } else {
                    textAnswer.setText("Error");
                    Log.w(TAG, "Division by zero. Bold. Pointless. Fatal.");
                    return 0.0;
                }
                break;

            default:
                Log.w(TAG, "Unknown operation: " + op);
                break;
        }

        return answer;
    }

    /**
     * Updates the answer display field.
     *
     * Fix #4 (continued – Separation of Concerns):
     * Extracted from performOperation() for clarity.
     */
    private void displayAnswer(double answer) {
        textAnswer.setText(String.valueOf(answer));
        Log.d(TAG, "Final result displayed: " + answer + ". It won’t help you survive.");
    }
}

/*
------------------------------------------------------------
 Release Readiness Comments – Portal Calculator
------------------------------------------------------------

https://play.google.com/console/about/guides/releasewithconfidence/

1. SIGN AND OPTIMIZE YOUR RELEASE BUILD
Before uploading to Google Play, I would create a signed release build.
That means generating a private keystore, configuring signingConfigs in Gradle,
and removing debug logs (Log.d) from this class. I would also run Lint
and code inspections to make sure the app is optimized and secure.

2. TEST ON MULTIPLE DEVICES AND CONFIGURATIONS
I would test this app on various Android versions (10–14) and screen sizes
to confirm that the UI scales correctly and that edge cases (e.g., divide by zero)
are handled gracefully. I would test both emulator and real devices to verify
consistent behavior and performance.

3. PREPARE THE STORE LISTING AND POLICY CONTENT
Before publishing, I would complete the Play Store listing with a proper
app description, screenshots, and a 512x512 icon. I would also write a simple
privacy policy explaining that no data is collected and declare the app’s
target audience as “general use / all ages.”

4. VERIFY COMPLIANCE WITH PLAY STORE POLICIES
I would review the Google Play Developer Policy Center to ensure this app
meets all guidelines, including content and data safety. Since this calculator
uses no network or personal information, compliance is straightforward.
I would still complete the Data Safety and Permissions forms before release.

------------------------------------------------------------
*/