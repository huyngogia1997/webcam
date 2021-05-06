package bhx;

import java.awt.*;

public class GBLayout extends GridBagLayout {

	private static final long serialVersionUID = 1L;

	public static GridBagConstraints createFixSingleLine(final boolean leftBoundary, final int gridx, final int gridy) {
		return createFixSingleLine(leftBoundary, gridx, gridy, 1, 1);
	}

	public static GridBagConstraints createFixSingleLine(final boolean leftBoundary, final int gridx, final int gridy, final int gridwidth) {
		return createFixSingleLine(leftBoundary, gridx, gridy, gridwidth, 1);
	}

	public static GridBagConstraints createFixSingleLine(final boolean leftBoundary, final int gridx, final int gridy, final int gridwidth, final int gridheight) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 0D, 0D, 10, 2, new Insets(1, 12, 1, 2), 0, 0);
	}

	public static GridBagConstraints createFixSingleLine(final int gridx, final int gridy) {
		return createFixSingleLine(gridx, gridy, 1, 1);
	}

	public static GridBagConstraints createFixSingleLine(final int gridx, final int gridy, final int gridwidth) {
		return createFixSingleLine(gridx, gridy, gridwidth, 1);
	}

	public static GridBagConstraints createFixSingleLine(final int gridx, final int gridy, final int gridwidth, final int gridheight) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 0D, 0D, 10, 2, new Insets(1, 12, 1, 2), 0, 0);
	}

	public static GridBagConstraints createSingleLine(final int gridx, final int gridy) {
		return createSingleLine(gridx, gridy, 1, 1, 1D);
	}

	public static GridBagConstraints createSingleLine(final int gridx, final int gridy, final int gridwidth) {
		return createSingleLine(gridx, gridy, gridwidth, 1, 1D);
	}

	public static GridBagConstraints createSingleLine(final int gridx, final int gridy, final int gridwidth, final int gridheight) {
		return createSingleLine(gridx, gridy, gridwidth, gridheight, 1D);
	}

	public static GridBagConstraints createSingleLine(final int gridx, final int gridy, final int gridwidth, final int gridheight, final double weightx) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, 0D, 10, 2, new Insets(1, 0, 1, 0), 0, 0);
	}

	public static GridBagConstraints createMultiLine(final int gridx, final int gridy) {
		return createMultiLine(gridx, gridy, 1, 1, 1D);
	}

	public static GridBagConstraints createMultiLine(final int gridx, final int gridy, final int gridwidth) {
		return createMultiLine(gridx, gridy, gridwidth, 1, 1D);
	}

	public static GridBagConstraints createMultiLine(final int gridx, final int gridy, final int gridwidth, final int gridheight) {
		return createMultiLine(gridx, gridy, gridwidth, gridheight, 1D);
	}

	public static GridBagConstraints createMultiLine(final int gridx, final int gridy, final int gridwidth, final int gridheight, final double heightx) {
		return createMultiLine(gridx, gridy, gridwidth, gridheight, 1D, heightx);
	}

	public static GridBagConstraints createMultiLine(final int gridx, final int gridy, final int gridwidth, final int gridheight, final double weightx, final double heightx) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, heightx, 10, 1, new Insets(1, 0, 1, 0), 0, 0);
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static GridBagConstraints cellFillB(final int col, final int row, final Double wx, final Double wy, final int width, final int height, final int ipadx, final int ipady,
			final Insets insets, final int anchor) {
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = col;
		c.gridy = row;
		c.weightx = wx;
		c.weighty = wy;
		c.gridheight = height;
		c.gridwidth = width;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.insets = insets;
		c.anchor = anchor;
		return c;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static GridBagConstraints cellFillH(final int col, final int row, final Double wx, final Double wy, final int width, final int height, final int ipadx, final int ipady,
			final Insets insets, final int anchor) {
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = col;
		c.gridy = row;
		c.weightx = wx;
		c.weighty = wy;
		c.gridheight = height;
		c.gridwidth = width;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.insets = insets;
		c.anchor = anchor;
		return c;
	}

	public static GridBagConstraints cellFillV(final int col, final int row, final Double wx, final Double wy, final int width, final int height, final int ipadx, final int ipady,
			final Insets insets, final int anchor) {
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = col;
		c.gridy = row;
		c.weightx = wx;
		c.weighty = wy;
		c.gridheight = height;
		c.gridwidth = width;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.insets = insets;
		c.anchor = anchor;
		return c;
	}

	public static GridBagConstraints CellB(final int col, final int row) {
		return cellFillB(col, row, 1.0, 1.0, 1, 1, 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellB(final int col, final int row, final Double wx, final Double wy) {
		return cellFillB(col, row, wx, wy, 1, 1, 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellB(final int col, final int row, final Double wx, final Double wy, final int ipadx, final int ipady) {
		return cellFillB(col, row, wx, wy, 1, 1, ipadx, ipady, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellB(final int col, final int row, final Double wx, final Double wy, final int ipadx, final int ipady, final int width, final int height) {
		return cellFillB(col, row, wx, wy, width, height, ipadx, ipady, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellB(final int col, final int row, final Double wx, final Double wy, final int ipadx, final int ipady, final int width, final int height,
			final Insets insets) {
		return cellFillB(col, row, wx, wy, width, height, ipadx, ipady, insets, GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellH(final int col, final int row, final Double wx, final Double wy, final int ipadx, final int ipady) {
		return cellFillH(col, row, wx, wy, 1, 1, ipadx, ipady, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellH(final int col, final int row, final Double wx, final Double wy, final int ipadx, final int ipady, final int width, final int height) {
		return cellFillH(col, row, wx, wy, width, height, ipadx, ipady, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellH(final int col, final int row) {
		return cellFillH(col, row, 1.0, 1.0, 1, 1, 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellH(final int col, final int row, final Double wx, final Double wy) {
		return cellFillH(col, row, wx, wy, 1, 1, 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellH(final int col, final int row, final Double wx, final Double wy, final Insets insets) {
		return cellFillH(col, row, wx, wy, 1, 1, 0, 0, insets, GridBagConstraints.CENTER);
	}

	public static GridBagConstraints CellB(final int col, final int row, final Double wx, final Double wy, final Insets insets) {
		return cellFillB(col, row, wx, wy, 1, 1, 0, 0, insets, GridBagConstraints.CENTER);
	}

}
