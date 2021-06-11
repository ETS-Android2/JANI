package com.sticknology.jani.data;

//---------------------------------------------------------
//---------------------------------------------------------
//                 Currently Unused
//---------------------------------------------------------
//---------------------------------------------------------

public class Types {

    public enum DictionaryEnum {
        INTERVAL_EFFORT, RUN_TYPE, WORKOUT_TYPE, TRAINING_DAY_TYPE, TRAINING_WEEK_TYPE
    }

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

    public String[] toStringArray(DictionaryEnum wantedEnum){
        switch (wantedEnum) {
            case INTERVAL_EFFORT:{
                return new String[]{"Z1", "Z2", "Z3", "Z4", "Z5", "RECOVERY", "EASY", "ENDURANCE",
                        "TEMPO", "VO2MAX", "THRESHOLD", "RP5K", "RP10K", "RPHM", "RPM", "WARMUP", "COOL_DOWN"};
            }
            case RUN_TYPE: {
                return new String[]{"WORKOUT", "RECOVERY", "LONG_RUN", "TRAINING", "EASY", "HYBRID",
                        "RACE", "TIME_TRIAL"};
            }
            case WORKOUT_TYPE: {
                return new String[]{"STRETCHING", "WEIGHTS", "YOGA", "CARDIO"};
            }
            case TRAINING_DAY_TYPE: {
                return new String[]{"EASY", "RECOVERY", "WORKOUT", "LONG_DISTANCE", "TRAINING"};
            }
            case TRAINING_WEEK_TYPE: {
                return new String[]{"RECOVERY", "BUILD", "TRAINING", "PEAK", "TAPER", "RACE"};
            }
            default: {
                return new String[]{""};
            }
        }
    }
}