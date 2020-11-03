package com.company;

class Table
{

    private final char letter;
    private final int value;


    public Table(char letter, int value)
    {
        this.letter = letter;
        this.value = value;
    }


    public char getLetter() { return letter; }
    public int getValue() { return value; }
}