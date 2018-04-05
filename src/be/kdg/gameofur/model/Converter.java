package be.kdg.gameofur.model;

/**
 * This class converters between the coordinates of the gridpane and the array of the board
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
class Converter {
    //methods
    private int coordinatesToIndex(int row, int column) {
        int index = 1;
        //aantal rijen
        for (int i = 0; i < 8; i++) { //1
            //aantal kolommen
            for (int j = 0; j < 3; j++) { //1
                if (i == row && j == column) {
                    return index;
                } else {
                    index++;
                }
            }
        }

        //coordinaten komen niet overeen met een index?
        return 0;
    }

    private int fieldToIndex(int field, Player player) {
        if (player.getType() == Piece.Type.SUN || (field > 4 && field < 13)) {
            switch (field) {
                case 4: case 5: return field - 3;
                case 6: return field - 1;
                case 3: case 7: case 15: return field + 1;
                case 8: return field + 3;
                case 2: case 9: case 14: return field + 5;
                case 10: return field + 7;
                case 1: case 11: case 13: return field + 9;
                case 12: return field + 11;
                case 0: return field + 13;
                default: return 0;
            }
        } else {
            switch (field) {
                case 4: return field - 1;
                case 3: case 15: return field + 3;
                case 2: case 14: return field + 7;
                case 1: case 13: return field + 11;
                case 0: return field + 15;
                default: return 0;
            }

        }
    }

    int gridToField(int row, int column) {
        int index = coordinatesToIndex(row, column);
        if (index == 0) {
            return 16;
        } else {
            switch (index) {
                case 1:
                case 2:
                    return index + 3;
                case 5:
                    return index + 1;
                case 4:
                case 8:
                case 16:
                    return index - 1;
                case 11:
                    return index - 3;
                case 7:
                case 14:
                case 19:
                    return index - 5;
                case 17:
                    return index - 7;
                case 10:
                case 20:
                case 22:
                    return index - 9;
                case 23:
                    return index - 11;
                case 13:
                    return index - 13;
                case 3:
                    return index + 1;
                case 6:
                case 18:
                    return index - 3;
                case 9:
                case 21:
                    return index - 7;
                case 12:
                case 24:
                    return index - 11;
                case 15:
                    return index - 15;
                default:
                    return 16;
            }
        }
    }

    int fieldToGridColumn(int field, Player player) {
        int index = fieldToIndex(field, player);
        return (index-1) % 3;
    }

    int fieldToGridRow(int field, Player player) {
        int index = fieldToIndex(field, player);
        return (int) Math.ceil((index / 3.0) - 1.0);
    }
}
