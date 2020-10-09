package indianbingo.dealer;

import indianbingo.Util;
import indianbingo.exception.FullBoardException;
import indianbingo.model.GameData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dealer {

    protected List<Integer> markNumbers;

    protected List<Integer> unMarkNumbers;

    private final int boardSize;

    private void initial() {
        for (int i = 1; i <= boardSize; i++) {
            unMarkNumbers.add(i);
        }
    }

    public Dealer(GameData gameData) {
        Objects.requireNonNull(gameData);
        boardSize = gameData.range();
        markNumbers = new ArrayList<>();
        unMarkNumbers = new ArrayList<>();
        initial();
    }

    /**
     * randomly generate number
     */
    public int getNextNumber() {
        int unMarkCnt = unMarkNumbers.size();
        int nextNumber;
        if (unMarkCnt == 0) {
            throw new FullBoardException();
        } else {
            int nextIndex = Util.generateRandomNumber(unMarkCnt);
            nextNumber = unMarkNumbers.remove(nextIndex);
            markNumbers.add(nextNumber);
        }
        return nextNumber;
    }

}
