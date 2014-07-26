package com.gajdulewicz.problems.externalsort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by rafal on 24/07/14.
 */
public class ExternalSorter {
    private final int maxIntCount;

    private final String[] toSort;

    public ExternalSorter(int maxElemCount) {
        this.maxIntCount = maxElemCount;
        toSort = new String[maxIntCount];
    }

    public static void main(String[] args) throws IOException {
        new ExternalSorter(327680).sort("/Users/rafal/dev/spawncamping-hipster/problems/src/com/gajdulewicz/problems/externalsort/input.txt", "output.txt");
        //new ExternalSorter(21).sort("/Users/rafal/dev/spawncamping-hipster/input-small.txt", "output.txt");
    }

    public void sort(String input, String output) throws IOException {
        List<String> tempFileNames = sortChunks(input);
        System.out.println("wrote " + tempFileNames.size() + " sorted chunks");
        merge(tempFileNames, output);
        deleteTempFiles(tempFileNames);
    }

    private void merge(List<String> tempFileNames, String output) throws IOException {
        Arrays.fill(toSort, null);
        int maxChunkSize = maxIntCount / tempFileNames.size();
        try (BufferedWriter resultWriter = Files.newBufferedWriter(Paths.get(output), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            List<Chunk> chunks = getChunks(tempFileNames, maxChunkSize);
            while (chunks.stream().anyMatch(c -> c.hasMore())) {
                int minIndex = indexOfMin(chunks.stream().filter(c -> c.hasMore()).map(c -> c.peek()).iterator());
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
        List<String> values = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false).collect(Collectors.toList());
        int minInd = 0;
        String minVal = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            String candidate = values.get(i);
            if (candidate.compareTo(minVal) < 0) {
                minVal = candidate;
                minInd = i;
            }
        }
        return minInd;
    }

    private void deleteTempFiles(List<String> tempFileNames) {
        try {
            for (String fileName : tempFileNames) {
                Files.delete(Paths.get(fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                for (; i < maxIntCount && null != (line = reader.readLine()); i++) {
                    toSort[i] = line;
                }
                allRead = line == null;
                if (i > 0) {
                    Arrays.sort(toSort, 0, i, (o1, o2) -> o1 == null ? o2 == null ? 0 : 1 : o1.compareTo(o2));
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
                w.write(toSort[i] + "\n");
            }
        }
    }

    private class Chunk {
        private final BufferedReader reader;
        private final int from;
        private int to;

        private int current;

        private boolean readerEmpty = false;

        private Chunk(BufferedReader reader, int from, int to) {
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
            return toSort[current];
        }

        public String take() {
            if (!hasMore()) {
                throw new RuntimeException("Chunk is empty!");
            }
            String res = toSort[current++];
            if (current == to && !readerEmpty) {
                load();
            }
            return res;
        }

        private void load() {
            this.current = from;
            for (int i = 0; i < (to - from); i++) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        reader.close();
                        readerEmpty = true;
                        to = from + i;

                        return;
                    }
                    toSort[from + i] = line;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
