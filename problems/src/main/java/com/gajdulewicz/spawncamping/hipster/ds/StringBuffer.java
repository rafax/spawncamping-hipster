package com.gajdulewicz.spawncamping.hipster.ds;

public class StringBuffer {


    private static final int INITIAL_SIZE = 16;
    private char[] data;
    int end;

    public StringBuffer() {
        this("");
    }

    public StringBuffer(String start) {
        int len = start.length();
        data = new char[Math.min(len * 2, INITIAL_SIZE)];
        start.getChars(0, len, data, 0);
        end = len;
    }

    public void append(String text) {
        ensureCapacity(end + text.length());
    }

    private void ensureCapacity(int required) {
        if (required < data.length) {
            return;
        }
        if(required<2*data.length){
            
        }
    }

    @Override
    public String toString() {
        return String.valueOf(data, 0, end);
    }
}
