package com.sticknology.jani.uiMethodsCommon;

public class SpinnerMethods {

    public int getSpinnerPos(String key, String[] menuArray){

        if(key != null) {
            for (int i = 0; i < menuArray.length; i++) {
                if (key.equals(menuArray[i])) {
                    return i;
                }
            }
        }
        return 0;
    }
}
