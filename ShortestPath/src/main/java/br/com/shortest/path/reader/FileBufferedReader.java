package br.com.shortest.path.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileBufferedReader {

	private String fileName;

	public FileBufferedReader(String fileName) {
		this.fileName = fileName;
	}

	public List<String> readFile() throws Exception {
		List<String> numbers = new ArrayList<>();

		FileReader fileReader = new FileReader(this.fileName);

		try (BufferedReader reader = new BufferedReader(fileReader)) {
			String line;

			while ((line = reader.readLine()) != null) {
				numbers.add(line);
			}
		} finally {
			fileReader.close();
		}

		return numbers;
	}
}
