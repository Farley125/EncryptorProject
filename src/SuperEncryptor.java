public class SuperEncryptor {
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;
    private final String CAPITALKEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWERCASEKEY = "abcdefghijklmnopqrstuvwxyz";

    /** Constructor*/
    public SuperEncryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str) {
        letterBlock = new String[numRows][numCols];
        for (int i = 0; i < str.length(); i++) {
            String character = str.charAt(i) + "";
            if (CAPITALKEY.indexOf(character) != -1) {
                int index = CAPITALKEY.indexOf(character);
                if (index == CAPITALKEY.length()) index = 0;
                str = str.substring(0, str.indexOf(character)) + CAPITALKEY.charAt(index) + str.substring(str.indexOf(character))+1;
            } else {
                int index = LOWERCASEKEY.indexOf(character);
                if (index == LOWERCASEKEY.length()) index = 0;
                str = str.substring(0, str.indexOf(character)) + LOWERCASEKEY.charAt(index) + str.substring(str.indexOf(character))+1;
            }
        }
        int row = 0;
        int column = 0;
        for (int i = 0; i < str.length() && (row+1) * (column) < numRows * numCols; i++) {
            if (column == numCols) {
                column = 0;
                row++;
            }
            letterBlock[row][column] = str.substring(i, i+1);
            column++;
        }
        for (int i = 0; i < letterBlock.length; i++) {
            for (int j = 0; j < letterBlock[0].length; j++) {
                if (letterBlock[i][j] == null) letterBlock[i][j] = "A";
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock() {
        String result = "";
        for (int i = 0; i < letterBlock[0].length; i++) {
            for (int j = 0; j < letterBlock.length; j++) {
                result += letterBlock[j][i];
            }
        }
        return result;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message) {
        String result = "";
        int wholeParts = (message.length() - (message.length() % (numRows * numCols))) / (numRows * numCols);
        int lastPart = message.length() % (numRows * numCols);
        int length = numRows * numCols;
        for (int i = 0; i < wholeParts+1; i++) {
            if (i == wholeParts) {
                if (lastPart != 0) {
                    fillBlock(message.substring(length * i, (length * (i+1)) - (length - lastPart)));
                    result += encryptBlock();
                }
            } else {
                fillBlock(message.substring(length * i, length * (i+1)));
                result += encryptBlock();
            }
        }
        return result;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the ???encryption key??? that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage) {
        String result = "";
        int wholeParts = encryptedMessage.length() / (numRows * numCols);
        for (int i = 0; i < wholeParts; i++) {
            String newPart = encryptedMessage.substring(i * (numRows * numCols), (i+1) * (numRows * numCols));
            result += decryptPart(newPart);
        }
        while ((result.charAt(result.length()-1) + "").equals("A")) {
            result = result.substring(0, result.length()-1);
        }
        return result;
    }

    public String decryptPart(String encryptedPart) {
        String result = "";
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                result += encryptedPart.charAt(row + (col * numRows));
            }
        }
        return result;
    }
}
