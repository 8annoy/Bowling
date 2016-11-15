package org.eitan.bowling;


import java.util.ArrayList;
import java.util.List;

public class BowlingScorer {

    private List<Integer> rolls = new ArrayList<>();

    public void roll(int pins) {
        rolls.add(pins);
    }

    public int score() {
        int result = 0;
        for(int i = 0; i < rolls.size(); i++) {
            result += calculateScore(i);
        }
        return result;
    }

    private int calculateScore(int i) {
        if(frameForRollIndex(i) > 10)
            return 0;
        int result = rolls.get(i);
        if(isStrike(i)) {
            result +=  rolls.get(i + 1) + rolls.get(i + 2);
        }
        if(isSpare(i)) {
            result += rolls.get(i + 1);
        }
        return result;
    }

    private boolean isSpare(int rollIndex) {
        return !isFirstRollInFrame(rollIndex) && rolls.get(rollIndex) + rolls.get(rollIndex - 1) == 10;
    }

    private boolean isStrike(int i) {
        return rolls.get(i) == 10 && isFirstRollInFrame(i);
    }

    private boolean isFirstRollInFrame(int rollIndex) {
        boolean isFirstRollInFrame = true;
        for(int i = 0; i < rollIndex; i++) {
            isFirstRollInFrame = !isFirstRollInFrame || rolls.get(i) == 10;
        }
        return isFirstRollInFrame;
    }

    private int frameForRollIndex(int rollIndex) {
        int frameCount = 1;
        boolean isFirstRollInFrame = true;
        for(int i = 0; i < rollIndex; i++) {
            if(!isFirstRollInFrame || rolls.get(i) == 10) {
                frameCount++;
                isFirstRollInFrame = true;
            }
            else
                isFirstRollInFrame = false;
        }
        return frameCount;
    }

}
