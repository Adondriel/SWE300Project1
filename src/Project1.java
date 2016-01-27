import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author merlin
 * 
 * Problem 11.6.2 from Programming Challenges. Using memoization
 */
public class Project1
{

    static String x, z;
    static int rem[][];
    static String in;
    static int n;
    static BufferedReader ir;
    
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        ir = new BufferedReader(new InputStreamReader(System.in));
        in = new String(ir.readLine());
        n = Integer.parseInt(in);
        for (int i = 0; i < n; i++)
        {z = new String(ir.readLine());
        x = new String(ir.readLine());
        System.out.println(answer(x,z));
        }
    }


    public static int answer(String x1, String z1)
    {
    	int xE = x.length() - 1;
        int zE = z.length() - 1;
        rem = new int[xE+1][zE+1];
        for (int k=0;k<xE+1;k++)
        for (int j=0;j<zE+1;j++)
        rem[k][j] = -1;
        return cShell(xE, zE);
    }

    private static int cShell(int xE, int zE)
    {
        if (xE == -1)
        return 1;
        if (rem[xE][zE] == -1)
        rem[xE][zE] = cMeat(xE, zE);
        return rem[xE][zE];
    }

    /**
     * @param xE
     * @param zE
     * @return
     */
    private static int cMeat(int xE, int zE)
    {
        if (xE == -1)
        return 1;
        if (xE > zE)
        return 0;
        if (xE == zE)
        {
        if (sameish(xE))
        return 1;
        return 0;
        }
        if (x.charAt(xE) == z.charAt(zE))
        {
        return (cShell(xE - 1, zE - 1))
          + cShell(xE, zE - 1);
        }
        return (cShell(xE, zE - 1));
    }

    private static boolean sameish(int end)
    {
        for (int i = 0; i <= end; i++)
        if (x.charAt(i) != z.charAt(i))
        return false;
        return true;
    }

}
