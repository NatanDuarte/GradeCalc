package com.example.gradecalc.model;

public class Grade {
    private final Double firstExamGrade;
    private final Double firstExamWeight;
    private final Double secondExamGrade;
    private final Double secondExamWeight;
    private final Double quizGrade;
    private final Double quizWeight;
    private final Double othersGrade;
    private final Double othersWeight;

    public Grade(Double firstExamGrade, Double firstExamWeight,
                 Double secondExamGrade, Double secondExamWeight,
                 Double quizGrade, Double quizWeight,
                 Double othersGrade, Double othersWeight) {

        this.firstExamGrade = firstExamGrade;
        this.firstExamWeight = firstExamWeight;
        this.secondExamGrade = secondExamGrade;
        this.secondExamWeight = secondExamWeight;
        this.quizGrade = quizGrade;
        this.quizWeight = quizWeight;
        this.othersGrade = othersGrade;
        this.othersWeight = othersWeight;
    }

    public double weightedMean() {
        Double sumOfProductOfSetByWeight = firstExamGrade * firstExamWeight +
                secondExamGrade * secondExamWeight +
                quizGrade * quizWeight +
                othersGrade * othersWeight;

        Double sumOfTheWeights = firstExamWeight +
                secondExamWeight + quizWeight + othersWeight;

        return sumOfProductOfSetByWeight / sumOfTheWeights;
    }
}
