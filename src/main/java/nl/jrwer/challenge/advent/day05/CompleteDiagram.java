package nl.jrwer.challenge.advent.day05;

class CompleteDiagram extends Diagram {
	@Override
	public void createDiagram() {
		for (LineSegment lineSegment : lineSegments)
			addToMap(lineSegment);
	}

	protected void addToMap(LineSegment lineSegment) {
		switch (lineSegment.direction()) {
		case DIAGONAL:
			addDiagonalToMap(lineSegment);
			break;
		case HORIZONTAL:
			addHorizintalToMap(lineSegment);
			break;
		case VERTICAL:
			addVerticalToMap(lineSegment);
			break;
		default:
			break;
		}
	}

	private void addDiagonalToMap(LineSegment lineSegment) {
//			System.out.println(lineSegment.begin.x +"," + lineSegment.begin.y 
//					+ " -> " + lineSegment.end.x + "," + lineSegment.end.y);

		int x1 = lineSegment.begin.x;
		int y1 = lineSegment.begin.y;
		int x2 = lineSegment.end.x;
		int y2 = lineSegment.end.y;

		if (x1 < x2 && y1 < y2)
			addLeftUpToRightDown(x1, x2, y1);

		if (x1 > x2 && y1 > y2)
			addLeftUpToRightDown(x2, x1, y2);

		if (x1 < x2 && y1 > y2)
			addLeftDownToRightUp(x1, x2, y2);

		if (x1 > x2 && y1 < y2)
			addLeftDownToRightUp(x2, x1, y1);
	}

	/**
	 * Or RightDownToLeftUp
	 * 
	 * @param leftTopX
	 * @param bottomRightX
	 * @param leftTopY
	 */
	private void addLeftUpToRightDown(int leftTopX, int bottomRightX, int leftTopY) {
		for (int i = 0; i <= (bottomRightX - leftTopX); i++)
			addToMap(new VentCoords(leftTopX + i, leftTopY + i));
	}

	/**
	 * Or RightUpToLeftDown
	 * 
	 * @param leftBottomX
	 * @param rightTopX
	 * @param leftBottomY
	 */
	private void addLeftDownToRightUp(int leftBottomX, int rightTopX, int leftBottomY) {
		for (int i = rightTopX - leftBottomX; i >= 0; i--)
			addToMap(new VentCoords(rightTopX - i, leftBottomY + i));
	}
}