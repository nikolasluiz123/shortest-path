package br.com.shortest.path.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NumberUtils {

	public static List<Integer> generateRandomNumbers(Integer count) {
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();

        while (numbers.size() < count) {
            int numeroAleatorio = random.nextInt(750);
            numbers.add(numeroAleatorio);
        }

        return new ArrayList<>(numbers);
	}
}
