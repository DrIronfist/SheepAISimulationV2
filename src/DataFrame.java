import java.io.FileWriter;
import java.io.IOException;

public class DataFrame<T>
{
    public T[][] d;
    public String[] columns;

    public DataFrame(T[][] d, String[] columns)
    {
        this.d = d;
        this.columns = columns;
    }

    private static String capEnd(String s)
    {
        return s.substring(0, s.length()-1) + "\n";
    }

    public String toString()
    {
        String s = "";

        for (int i = 0; i < columns.length; i++)
            s += columns[i]+",";
        s = capEnd(s);

        for (int i = 0; i < d.length; i++)
        {
            for (int j = 0; j < d[i].length; j++)
            {
                s += d[i][j]+",";
            }
            s = capEnd(s);
        }
        s = s.substring(0, s.length()-1);
        return s;
    }

    public void toCSV(String filename)
    {
        try {
            FileWriter myWriter = new FileWriter("./output/"+filename+".csv");
            myWriter.write(toString());
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}