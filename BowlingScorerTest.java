package test.org.eitan.bowling;

import org.eitan.bowling.BowlingScorer;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BowlingScorerTest {

    private BowlingScorer scorer;

    @Before
    public void setUp() throws Exception {
        scorer = new BowlingScorer();
    }

    @Test
    public void scoreGutterGame() throws Exception {
        repeatedRoll(0, 20);
        assertThat(scorer.score(), is(0));
    }

    @Test
    public void scorePerfectGame() throws Exception {
        repeatedRoll(10, 12);
        assertThat(scorer.score(), is(300));
    }

    @Test
    public void scoreSpare() throws Exception {
        repeatedRoll(0, 16);
        rollSequence(8, 2, 7, 0);
        assertThat(scorer.score(), is(24));
    }

    @Test
    public void scoreGameOfSpares() {
        rollSequence(9, 1, 8, 2, 7, 3, 6, 4, 5, 5, 4, 6, 3, 7, 2, 8, 1, 9, 0, 10, 3);
        assertThat(scorer.score(), is(100 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 + 3));
    }

    @Test
    public void scoreNoBonuses() {
        int[] rolls = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3};
        rollSequence(rolls);
        assertThat(scorer.score(), is(IntStream.of(rolls).sum()));
    }
    private void rollSequence(int... rolls) {
        IntStream.of(rolls).forEach(pins -> scorer.roll(pins));
    }

    private void repeatedRoll(int pins, int times) {
        IntStream.rangeClosed(1, times).forEach(time -> scorer.roll(pins));
    }
}
