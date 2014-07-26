package com.gajdulewicz.problems.externalsort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //new ExternalSorter(327680).sort("/Users/rafal/dev/spawncamping-hipster/problems/src/com/gajdulewicz/problems/externalsort/input.txt", "output.txt");
        new ExternalSorter(10).sort("/Users/rafal/dev/spawncamping-hipster/input-small.txt", "output.txt");

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
        List<Integer> chunkPositions = tempFileNames.stream().map(x -> 0).collect(Collectors.toList());
        List<BufferedReader> readers = getChunkReaders(tempFileNames);
        for (int i = 0; i < tempFileNames.size(); i++) {
            readNextChunk(maxChunkSize, readers, i);
        }
        BufferedWriter resultWriter = Files.newBufferedWriter(Paths.get(output), StandardOpenOption.CREATE_NEW);
        while (chunkPositions.stream().allMatch(x -> x != -1)) {
            int minChunkIndex = indexOfMin(chunkPositions.stream().map(pos -> toSort[pos]));
            int minValueIndex = chunkPositions.get(minChunkIndex);
            resultWriter.write(toSort[minValueIndex] + "\n");
            chunkPositions.add(minChunkIndex, minValueIndex + 1);
            if (minValueIndex != 0 && minValueIndex % maxChunkSize == 0) {
                readNextChunk(maxChunkSize, readers, minChunkIndex);
            }
        }
    }

    private int indexOfMin(Stream<String> map) {
        Iterator<String> iter = map.iterator();
        int minInd = 0, i = 0;
        String minVal = iter.next();
        while (iter.hasNext()) {
            i++;
            String cand = iter.next();
            if (cand.compareTo(minVal) == -1) {
                minVal = cand;
                minInd = i;
            }
        }
        return minInd;
    }

    private void readNextChunk(int maxChunkSize, List<BufferedReader> readers, int i) throws IOException {
        BufferedReader r = readers.get(i);
        int pos = 0;
        String line = r.readLine();
        for (; pos < maxChunkSize && line != null; pos++) {
            toSort[i * maxChunkSize + pos] = r.readLine();
        }
    }

    private List<BufferedReader> getChunkReaders(List<String> tempFileNames) throws IOException {
        List<BufferedReader> result = new ArrayList<>();
        for (String fileName : tempFileNames) {
            result.add(Files.newBufferedReader(Paths.get(fileName)));
        }
        return result;
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
        try (FileReader fr = new FileReader(input)) {
            try (BufferedReader reader = new BufferedReader(fr)) {
                int fileIndex = 0;
                boolean allRead = false;
                while (!allRead) {
                    int i = 0;
                    String line = null;
                    for (; i < maxIntCount && null != (line = reader.readLine()); i++) {
                        toSort[i] = line;
                    }
                    Arrays.sort(toSort, 0, i, (o1, o2) -> o1 == null ? o2 == null ? 0 : 1 : o1.compareTo(o2));
                    String fileName = fileIndex++ + ".tmp";
                    tempFileNames.add(fileName);
                    writeToFile(fileName, i);
                    allRead = line == null;
                }
            }
        }
        return tempFileNames;
    }

    private void writeToFile(String fileName, int upTo) throws IOException {
        try (Writer w = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.CREATE_NEW)) {
            for (int i = 0; i < upTo; i++) {
                w.write(toSort[i] + "\n");
            }
        }
    }

    private class Chunk {
        private final BufferedReader reader;
        private final int from;
        private final int to;

        private int current;

        private boolean readerEmpty = false;
        private int lastAvailableIndex;

        private Chunk(BufferedReader reader, int from, int to) {
            this.reader = reader;
            this.from = from;
            this.to = to;
            load();
        }

        public boolean hasMore() {
            return !readerEmpty || current < lastAvailableIndex;
        }

        public String peek() {
            return toSort[current];
        }

        public String take() {
            String res = toSort[current++];
            if (current == to) {
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
                        readerEmpty = true;
                        lastAvailableIndex = from + i - 1;
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
