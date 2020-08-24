package com.univocity.trader.indicators;

import com.univocity.trader.candles.CandleHelper;
import com.univocity.trader.indicators.base.TimeInterval;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LowerShadowIndicatorTest {

    @Test
    public void lowerShadow(){

        int i = 0;

        LowerShadowIndicator indicator = new LowerShadowIndicator(TimeInterval.MINUTE);

        indicator.accumulate(CandleHelper.newCandle(i++, 10, 18, 20, 10));
        assertEquals(0, indicator.getValue(), 0.0001);

        indicator.accumulate(CandleHelper.newCandle(i++, 17, 20, 21, 17));
        assertEquals(0, indicator.getValue(), 0.0001);

        indicator.accumulate(CandleHelper.newCandle(i++, 15, 15, 16, 14));
        assertEquals(1, indicator.getValue(), 0.0001);

        indicator.accumulate(CandleHelper.newCandle(i++, 15, 11, 15, 8));
        assertEquals(3, indicator.getValue(), 0.0001);

        indicator.accumulate(CandleHelper.newCandle(i++, 11, 12, 12, 10));
        assertEquals(1, indicator.getValue(), 0.0001);

    }

}
