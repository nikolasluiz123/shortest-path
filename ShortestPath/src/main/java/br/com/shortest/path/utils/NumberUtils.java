package br.com.shortest.path.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Classe utilit�ria com m�todos que trabalham com n�meros.
 * 
 * @author Nikolas Luiz Schmitt
 */
public class NumberUtils {

	/**
	 * M�todo que gera uma lista de n�meros aleat�rios diferentes.
	 * 
	 * @param count Quantidade de n�meros aleat�rios �nicos que deseja gerar
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
