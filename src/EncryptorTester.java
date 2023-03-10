import java.util.Arrays;

public class EncryptorTester
{
    public static void main(String[] args)
    {
        // --------------------------------
        // ---- TEST PART A: fillBlock ----
        // --------------------------------
        System.out.println("---- TESTING PART A ----");
        Encryptor encryptor1 = new Encryptor(3, 5);
        encryptor1.fillBlock("I noeft gg eohao spremitl   erenAAAneedAAAt k.AAAiweAAAA");

        System.out.println(encryptor1.encryptMessage("I noeft gg eohao spremitl   erenAAAneedAAAt k.AAAiweAAAA"));
        System.out.println(encryptor1.decryptMessage("Th hr\"eai stpe'sta  mjaeu \"sg icwshe  asaku  meoitun. d asYreww ihotakht   hnlaeopdsp,ie ngpc,et riifomen  toc.fr  aWafheupn   sgyteootta inangfgrt yeriy nolguo,'s vetc  elt.ow siThsae yrasel 'wsoiomn eglt ehtarnwnda, y asal rfmooporrm o ive,em ren nestvetAAtAAlAAeAAAAA"));
    }

    public static void print2DArray(String[][] arr)
    {
        for (String[] row : arr)
        {
            for (String val : row)
            {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static boolean testArr(String[][] expected, String[][] actual)
    {
        if (expected.length != actual.length)
        {
            return false;
        }

        if (expected[0].length != actual[0].length)
        {
            return false;
        }

        for (int row = 0; row < expected.length; row++)
        {
            for (int col = 0; col < expected[0].length; col++)
            {
                String expElement = expected[row][col];
                String actElement = actual[row][col];
                if (!expElement.equals(actElement))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
