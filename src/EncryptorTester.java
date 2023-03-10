import java.util.Arrays;

public class EncryptorTester
{
    public static void main(String[] args)
    {
        // --------------------------------
        // ---- TEST PART A: fillBlock ----
        // --------------------------------
        System.out.println("---- TESTING PART A ----");
        SuperEncryptor encryptor1 = new SuperEncryptor(10, 10);
        encryptor1.fillBlock("I noeft gg eohao spremitl   erenAAAneedAAAt k.AAAiweAAAA");

        System.out.println(encryptor1.encryptMessage("What is Cognitive Behavioral Therapy?\n" +
                "Cognitive behavioral therapy (CBT) is a form of psychological treatment that has been demonstrated to be effective for a range of problems including depression, anxiety disorders, alcohol and drug use problems, marital problems, eating disorders, and severe mental illness.\n" +
                "Numerous research studies suggest that CBT leads to significant improvement in functioning and quality of life.\n" +
                "In many studies, CBT has been demonstrated to be as effective as, or more effective than, other forms of psychological therapy or psychiatric medications.\n" +
                "It is important to emphasize that advances in CBT have been made on the basis of both research and clinical practice.\n" +
                "Indeed, CBT is an approach for which there is ample scientific evidence that the methods that have been developed actually produce change.\n" +
                "In this manner, CBT differs from many other forms of psychological treatment."));
        System.out.println(encryptor1.decryptMessage(""));
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
