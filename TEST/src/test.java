import org.apache.lucene.store.Directory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test
{
    private static final String filePath=System.getProperty("user.dir")+"/out.txt";
    public static void main(String [] argv) throws IOException
    {

        String [] convert={"AND","NOT","OR"};
        Directory index_dir;
        File f=new File("src/test.txt");
        Document doc = Jsoup.parse(f,"utf-8");
        Elements elements = doc.select("html");
        String parse = elements.text();
        String[] parse_list = parse.split(" ");
        List<String> parse_result = new ArrayList<>();

        for(String s:parse_list)
        {
            if (s.matches("[a-zA-Z]+[']?[a-zA-Z]"))
            {
                if(Arrays.asList(convert).contains(s))
                {
                    s.toLowerCase();
                }
                parse_result.add(s);
                System.out.println(s);
            }

        }

        Index in = new Index("YouTube",filePath);
        in.createIndex(parse_result);

        try {
            in.search("YouTube");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}