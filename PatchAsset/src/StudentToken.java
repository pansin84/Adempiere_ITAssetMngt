import java.io.BufferedReader;

import java.nio.charset.Charset;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

public class StudentToken
{
    public static void main(String[] args) throws Exception
    {       
        List<String> studenttoken = new ArrayList<String>();
        Path sourcePath = Paths.get("C:\\Users\\pankaj\\new1.txt");
        Charset charset = Charset.forName("US-ASCII");
        BufferedReader reader = Files.newBufferedReader(sourcePath, charset);
        int width = 0;
        int height = 0;

        String line = null;     
        line = reader.readLine();
        
        

            
        while (line!=null)
        {       
            line = line.trim();
            if (width == 0)
            {
                String[] str = line.split("\\s+");
                width = str.length;
            }           
            if (line.length() > 0)  
            height++;
        }
        reader.close();

//        BufferedReader reader1 = Files.newBufferedReader(sourcePath, charset);
//        reader1.readLine();
        for (int i = 0; i < height; i++)
        {
            if(line!=null)
            {
                line = line.trim();
                String[] str = line.split("\\s+");
                for (int j = 0; j < width; j++)
                {
                              // this condition will add only those elements which fall under token column.
                    if (j == (height) && i != 0) 
                        studenttoken.add(str[j]);                   
                }
            }
        }
//        reader1.close();
        System.out.println(studenttoken);
    }
}