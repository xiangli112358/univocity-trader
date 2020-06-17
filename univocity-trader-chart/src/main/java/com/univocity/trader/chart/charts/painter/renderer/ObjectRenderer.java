package com.univocity.trader.chart.charts.painter.renderer;

import com.univocity.trader.chart.charts.*;
import com.univocity.trader.chart.charts.painter.*;
import com.univocity.trader.chart.dynamic.*;

import java.awt.*;
import java.util.function.*;

public abstract class ObjectRenderer<O, T extends Theme> extends AbstractRenderer<T> {

	private int i;
	private O[] objects;
	private final Supplier<O> valueSupplier;
	private final IntFunction<O[]> arrayGenerator;

	public ObjectRenderer(String description, T theme, IntFunction<O[]> arrayGenerator, Supplier<O> valueSupplier) {
		super(description, theme);
		this.arrayGenerator = arrayGenerator;
		this.valueSupplier = valueSupplier;
	}

	@Override
	public final void reset(int length) {
		objects = arrayGenerator.apply(length);
		i = 0;
	}

	@Override
	public final void updateValue() {
		if (i < objects.length) {
			objects[i] = valueSupplier.get();
		}
	}

	@Override
	public final void nextValue() {
		if (i < objects.length) {
			objects[i++] = valueSupplier.get();
		}
	}

	@Override
	public final void paintNext(int i, BasicChart<?> chart, Graphics2D g, AreaPainter areaPainter) {
		if (i < objects.length) {
			paintNext(i, objects[i], chart, g, areaPainter);
		}
	}

	protected abstract void paintNext(int i, O value, BasicChart<?> chart, Graphics2D g, AreaPainter areaPainter);
}
