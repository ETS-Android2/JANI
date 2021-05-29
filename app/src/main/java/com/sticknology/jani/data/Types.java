package com.sticknology.jani.data;

public class Types {

    public enum IntervalEffortEnum {
        Z1, Z2, Z3, Z4, Z5, RECOVERY, EASY, ENDURANCE, TEMPO, VO2MAX, THRESHOLD, RP5K, RP10K, RPHM,
        RPM, WARMUP, COOL_DOWN
    }

    public enum RunTypeEnum {
        WORKOUT, RECOVERY, LONG_RUN, TRAINING, EASY, HYBRID, RACE, TIME_TRIAL
    }

    public enum WorkoutTypeEnum {
        STRETCHING, WEIGHTS, YOGA, CARDIO
    }

    public enum TrainingDayTypeEnum {
        EASY, RECOVERY, WORKOUT, LONG_DISTANCE, TRAINING
    }

    public enum TrainingWeekTypeEnum {
        RECOVERY, BUILD, TRAINING, PEAK, TAPER, RACE
    }
}