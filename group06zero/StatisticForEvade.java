package group06zero;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;


public class StatisticForEvade {
	public static final String[] EVADE_PATTERN_ARRAY = {"EvadePattern1", "EvadePattern2", "EvadePattern3"};
	public static final double INITIAL_SCORE = 50;

	private Map<String, EvadePattern> evadePatternMap;
	private Map<String, Double> scoreMap;

	private double totalScore;

	private Random rnd = new Random();

	public StatisticForEvade() {
		this.evadePatternMap = new HashMap<>();
		this.scoreMap = new HashMap<>();
		totalScore = 0;
		for(String patternName : EVADE_PATTERN_ARRAY) {
			scoreMap.put(patternName, INITIAL_SCORE);
			totalScore += INITIAL_SCORE;
		}
	}

	public void add(String patternName, EvadePattern evadePattern) {
		map.put(patternName, evadePattern);
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
		return evadeInstanceMap.get(scoredPatternKey);
	}

	public evadePattern getPatternBasedOnProbability() {
		double randomNum = rnd.nextDouble();
		int accumulation = 0;
		for (String evadePattern : EVADE_PATTERN_ARRAY) {
			normalizedScore = scoreMap.get(evadePattern) / totalScore;
			if (accumulation <= randomNum && randomNum <= normalizedScore) {
				return evadePatternMap.get(evadePattern);
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