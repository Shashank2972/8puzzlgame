package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button[][] buttons = new Button[3][4];
    Button b1;
    public int value = 1;
    int round = 0;
    int N = 3, M = 4;
    int[][] grid = {{0, -1, -1, 0}, {-1, -1, -1, -1}, {0, -1, -1, 0}};
    int[][] grid1= new int[3][4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.btt);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                String buttonid = "button" + i + j;
                int resid = getResources().getIdentifier(buttonid, "id", getPackageName());
                buttons[i][j] = findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    public boolean UsedInGrid(int[][] grid, int num, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                if (grid[i][j] == num)
                    return true;
        }
        return false;
    }

    public boolean isSafe(int[][] grid, int[] row, int[] col, int num, int N, int M) {
        /* Check if 'num' is not already placed in Whole Grid*/
        if (row[0] == 0 && col[0] == 1) {

            if (UsedInGrid(grid, num, N, M)
                    || (Math.abs(num - grid[row[0]][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0]]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0] + 1]) <= 1))
                return false;
        } else if (row[0] == 0 && col[0] == 2) {
            if (UsedInGrid(grid, num, N, M)
                    || (Math.abs(num - grid[row[0]][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0]]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0] - 1]) <= 1))
                return false;
        } else if (row[0] == 1 && col[0] == 0) {
            if (UsedInGrid(grid, num, N, M)
                    || (Math.abs(num - grid[row[0] - 1][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0]][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0] + 1]) <= 1))
                return false;
        } else if (row[0] == 1 && col[0] == 3) {
            if (UsedInGrid(grid, num, N, M)
                    || (Math.abs(num - grid[row[0] - 1][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0]][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0] - 1]) <= 1))
                return false;
        } else if (row[0] == 2 && col[0] == 1) {
            if (UsedInGrid(grid, num, N, M)
                    || (Math.abs(num - grid[row[0] - 1][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0]]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0]][col[0] + 1]) <= 1))
                return false;
        } else if (row[0] == 2 && col[0] == 2) {
            if (UsedInGrid(grid, num, N, M)
                    || (Math.abs(num - grid[row[0]][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0]]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0] - 1]) <= 1))
                return false;
        } else if (row[0] == 1 && col[0] == 1) {
            if (UsedInGrid(grid, num, N, M)
                    || (Math.abs(num - grid[row[0]][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0]]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0]][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0]]) <= 1))
                return false;
        } else if (row[0] == 1 && col[0] == 2) {
            if (UsedInGrid(grid, num, N, M)
                    || (Math.abs(num - grid[row[0]][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0]]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0]][col[0] + 1]) <= 1)
                    || (Math.abs(num - grid[row[0] - 1][col[0] - 1]) <= 1)
                    || (Math.abs(num - grid[row[0] + 1][col[0]]) <= 1))
                return false;
        }
        return true;
    }

    public boolean FindUnassignedLocation(int[][] grid, int[] row, int[] col, int N, int M) {
        for (row[0] = 0; row[0] < N; row[0]++) {
            for (col[0] = 0; col[0] < M; col[0]++) {
                if (grid[row[0]][col[0]] == -1)
                    return true;
            }
        }
        return false;
    }

    public boolean Solve(int[][] grid, int N, int M) {
        int[] row = {0};
        int[] col = {0};
        int num;

        // If there is no unassigned location, we are done
        if (!FindUnassignedLocation(grid, row, col, N, M))
            return true; // success!

        // consider digits 1 to 8
        for (num = 1; num <= 8; num++) {

            // if looks promising
            if (isSafe(grid, row, col, num, N, M)) {

                // make tentative assignment
                grid[row[0]][col[0]] = num;

                // return, if success, yay!
                if (Solve(grid, N, M))
                    return true;

                // failure, unmake & try again
                grid[row[0]][col[0]] = -1;
            }
        }
        return false; // this triggers backtracking
    }

    public void calculate(View v)
    {
        // startActivity(new Intent(this,Main2Activity.class));
        Solve(grid, N, M);
        int [][] grid2={{0,5,3,0},{2,8,1,7},{0,6,4,0}};
        int [][] grid3={{0,4,6,0},{7,1,8,2},{0,3,5,0}};
        boolean res=check();
        boolean res1=checkother(grid1,grid3);
        boolean res2=checkother(grid1,grid2);
        if(res || res1||res2)
        {
            Toast.makeText(this,"You are Correct",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }
        else
            {
                Toast.makeText(this,"You are Wrong",Toast.LENGTH_SHORT).show();
                Intent in=new Intent(this,Main3Activity.class);
                startActivity(in);
            }
    }

    public boolean check()
    {

        int[][] board=new int[3][4];
        for(int i=0;i<3;i++)
        {
            for (int j = 0; j < 4; j++)
            {
                grid1[i][j] = Integer.parseInt(buttons[i][j].getText().toString());
            }
        }

        for(int i=0;i<3;i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(grid[i][j]==grid1[i][j])
                    continue;
                else
                    return false;
            }
        }
        return true;
    }
    public boolean checkother(int[][] board,int[][] checkinggrid)
    {
        for(int i=0;i<3;i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(board[i][j]==checkinggrid[i][j])
                    continue;
                else
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals(""))
            return;

        else
        {
            String t=Integer.toString(value);
            ((Button) v).setText(t);
            if (round < 8) {
                value++;
                round++;
            }

        }
    }

    public void reset(View view)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<4;j++)
            {
                if((i==0 && j==0)||(i==0 && j==3) ||(i==2 && j==0) || (i==2 && j==3))
                    continue;
                else
                buttons[i][j].setText("");
            }
        }
        round=0;
        value=1;
    }
}
