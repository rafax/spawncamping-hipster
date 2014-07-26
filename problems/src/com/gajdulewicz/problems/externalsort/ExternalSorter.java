package com.gajdulewicz.problems.externalsort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rafal on 24/07/14.
 */
public class ExternalSorter {
    private final int maxIntCount;

    private final String[] buffer;

    public ExternalSorter(int maxElemCount) {
        this.maxIntCount = maxElemCount;
        buffer = new String[maxIntCount];
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        new ExternalSorter(1000000).sort("/Users/rafal/dev/spawncamping-hipster/problems/src/com/gajdulewicz/problems/externalsort/input.txt", "output.txt");
        long end = System.currentTimeMillis();
        System.out.println("Sorted in " + (end - start) + " ms");
    }

    public void sort(String input, String output) throws IOException {
        List<String> tempFileNames = sortChunks(input);
        System.out.println("wrote " + tempFileNames.size() + " sorted chunks");
        merge(tempFileNames, output);
        deleteTempFiles(tempFileNames);
    }

    private void merge(List<String> tempFileNames, String output) throws IOException {
        Arrays.fill(buffer, null);
        int maxChunkSize = maxIntCount / tempFileNames.size();
        try (BufferedWriter resultWriter = Files.newBufferedWriter(Paths.get(output), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            List<Chunk> chunks = getChunks(tempFileNames, maxChunkSize);
            while (!chunks.isEmpty()) {
                int minIndex = indexOfMin(chunks.stream().map(c -> c.peek()).iterator());
                String nextLine = chunks.get(minIndex).take();
                if (!chunks.get(minIndex).hasMore()) {
                    chunks.remove(minIndex);
                }
                resultWriter.write(nextLine + "\n");
            }
        }
    }

    private List<Chunk> getChunks(List<String> tempFileNames, int maxChunkSize) throws IOException {
        int start = 0;
        List<Chunk> result = new ArrayList<>();
        for (String fileName : tempFileNames) {
            result.add(new Chunk(Files.newBufferedReader(Paths.get(fileName)), start, start + maxChunkSize));
            start += maxChunkSize;
        }
        return result;
    }

    private int indexOfMin(Iterator<String> iterator) {
        int minInd = 0;
        String minVal = iterator.next();
        for (int i = 1; iterator.hasNext(); i++) {
            String candidate = iterator.next();
            if (candidate.compareTo(minVal) < 0) {
                minVal = candidate;
                minInd = i;
            }
        }
        return minInd;
    }

    private void deleteTempFiles(List<String> tempFileNames) throws IOException {
            for (String fileName : tempFileNames) {
                Files.delete(Paths.get(fileName));
            }
    }

    private List<String> sortChunks(String input) throws IOException {
        List<String> tempFileNames = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(input))) {
            int fileIndex = 0;
            boolean allRead = false;
            while (!allRead) {
                int i = 0;
                String line = null;
                while (i < maxIntCount && null != (line = reader.readLine())) {
                    buffer[i++] = line;
                }
                allRead = line == null;
                if (i > 0) {
                    Arrays.sort(buffer, 0, i, (o1, o2) -> o1 == null ? o2 == null ? 0 : 1 : o1.compareTo(o2));
                    String fileName = fileIndex++ + ".tmp";
                    tempFileNames.add(fileName);
                    writeToFile(fileName, i);
                }
            }
        }

        return tempFileNames;
    }

    private void writeToFile(String fileName, int upTo) throws IOException {
        try (Writer w = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int i = 0; i < upTo; i++) {
                w.write(buffer[i] + "\n");
            }
        }
    }

    private class Chunk {
        private final BufferedReader reader;
        private final int from;
        private int to;

        private int current;

        private boolean readerEmpty = false;

        private Chunk(BufferedReader reader, int from, int to) throws IOException {
            this.reader = reader;
            this.from = from;
            this.to = to;
            load();
        }

        public boolean hasMore() {
            return !readerEmpty || current < to;
        }

        public String peek() {
            if (!hasMore()) {
                throw new RuntimeException("Chunk is empty!");
            }
            return buffer[current];
        }

        public String take() throws IOException {
            if (!hasMore()) {
                throw new RuntimeException("Chunk is empty!");
            }
            String res = buffer[current++];
            if (current == to && !readerEmpty) {
                load();
            }
            return res;
        }

        private void load() throws IOException {
            current = from;
            for (int i = 0; i < (to - from) && !readerEmpty; i++) {
                String line = reader.readLine();
                if (line == null) {
                    reader.close();
                    readerEmpty = true;
                    to = from + i;
                }
                buffer[from + i] = line;
            }
        }
    }
}
