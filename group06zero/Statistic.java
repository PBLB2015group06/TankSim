package group06zero;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class Statistic<T> {
	public static final double INITIAL_SCORE = 50;

	private Map<String, T> patternMap;
	private Map<String, Double> scoreMap;

	private double totalScore;

	private Random rnd = new Random();

	public Statistic() {
		this.patternMap = new HashMap<String, T>();
		this.scoreMap = new HashMap<String, Double>();
		this.totalScore = 0;
		
	}

	public void addPattern(String patternName, T pattern) {
		patternMap.put(patternName, pattern);
		totalScore += INITIAL_SCORE;
	}

	public T getMostScoredPattern() {
		double maxScore = -10000;
		String scoredPatternKey = "Pattern1";
		for (Map.Entry<String, Double> e : scoreMap.entrySet()) {
			if (e.getValue() > maxScore) {
				maxScore = e.getValue();
				scoredPatternKey = e.getKey();
			}
		}
		return patternMap.get(scoredPatternKey);
	}

	public T getPatternBasedOnProbability() {
		double randomNum = rnd.nextDouble();
		int accumulation = 0;
		Collection<T> patternCollection = patternMap.values();
		for (T pattern : patternCollection) {
			double normalizedScore = scoreMap.get(pattern) / totalScore;
			if (accumulation <= randomNum && randomNum <= normalizedScore) {
				return patternMap.get(pattern);
			}
			accumulation += normalizedScore;
		}
		return null;
	}

	public boolean isEnoughInfo() {
		return totalScore >= 200;
	}

	public void addScore(String patternName, double score) {
		double currentScore = scoreMap.get(patternName);
		double newScore = currentScore + score;

		if (newScore < 0) newScore = 0;

		scoreMap.put(patternName, newScore);
		totalScore += score;
	}
}