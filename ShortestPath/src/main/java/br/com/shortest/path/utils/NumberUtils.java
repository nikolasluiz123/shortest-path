package br.com.shortest.path.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Classe utilitária com métodos que trabalham com números.
 * 
 * @author Nikolas Luiz Schmitt
 */
public class NumberUtils {

	/**
	 * Método que gera uma lista de números aleatórios diferentes.
	 * 
	 * @param count Quantidade de números aleatórios únicos que deseja gerar
	 * 
	 * @author Nikolas Luiz Schmitt
	 *
	 */
	public static List<Integer> generateRandomNumbers(Integer count, Integer max) {
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();

        while (numbers.size() < count) {
            int numeroAleatorio = random.nextInt(max);
            numbers.add(numeroAleatorio);
        }

        return new ArrayList<>(numbers);
	}
}
