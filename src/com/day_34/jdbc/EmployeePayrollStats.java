package com.day_34.jdbc;
;

public class EmployeePayrollStats {
    private String gender;
    private double sum;
    private double avg;
    private double min;
    private double max;
    private int count;

    public EmployeePayrollStats(String gender, double sum, double avg, double min, double max, int count) {
        this.gender = gender;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
        this.count = count;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "EmployeePayrollStats{" +
                "gender='" + gender + '\'' +
                ", sum=" + sum +
                ", avg=" + avg +
                ", min=" + min +
                ", max=" + max +
                ", count=" + count +
                '}';
    }
}
