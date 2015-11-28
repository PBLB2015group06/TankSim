package group06zero;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public abstract class Statistic<T> {
	public static final double INITIAL_SCORE = 50;

	private Map<String, T> patternMap;
	private Map<String, Double> scoreMap;

	private double totalScore;

	private Random rnd = new Random();

	public StatisticForEvade() {
		this.evadePatternMap = new HashMap<>();
		this.scoreMap = new HashMap<>();
		totalScore = 0;
		
	}

	public void addPattern(String patternName, T pattern) {
		map.put(patternName, evadePattern);
		totalScore += INITIAL_SCORE;
	}

	public evadePattern getMostScoredPattern() {
		maxScore = -1000;
		String scoredPatternKey;
		foreach(key, score : scoreMap) {
			if (score > maxScore) {
				maxScore = score;
				scoredPatternKey = key;
			}
		}
		return patternMap.get(scoredPatternKey);
	}

	public evadePattern getPatternBasedOnProbability() {
		double randomNum = rnd.nextDouble();
		int accumulation = 0;
		Collection<T> patternCollection = patternMap.values();
		foreach (pattern : patternCollection {
			normalizedScore = scoreMap.get(pattern) / totalScore;
			if (accumulation <= randomNum && randomNum <= normalizedScore) {
				return patternMap.get(pattern);
			}
			accumulation += normalizedScore;
		}
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